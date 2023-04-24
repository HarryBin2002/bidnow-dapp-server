package com.bidnow.bidnowbackend.repository.iplm;

import com.bidnow.bidnowbackend.constant.Constant;
import com.bidnow.bidnowbackend.dto.response.AuctionDTO;
import com.bidnow.bidnowbackend.model.Auction;
import com.bidnow.bidnowbackend.model.Bidder;
import com.bidnow.bidnowbackend.model.Nft;
import com.bidnow.bidnowbackend.repository.AuctionCustomRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.support.PageableExecutionUtils;

import java.math.BigInteger;
import java.util.*;

public class AuctionCustomRepoImpl implements AuctionCustomRepo {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public void saveCreatingAuctionEvent(Auction auction) {
        mongoTemplate.save(auction);
    }

    @Override
    public Page<AuctionDTO> getListAuctionFromStatus(Pageable pageable, String statusAuction) throws Exception {
        Query query = new Query();

        if (statusAuction != null) {
            query.addCriteria(Criteria.where("statusAuction").is(statusAuction));
        }

        Long count = mongoTemplate.count(query, Auction.class);

        int startRecord = pageable.getPageNumber() * pageable.getPageSize();
        query.skip(startRecord);
        query.limit(pageable.getPageSize());

        List<Auction> auctionList = mongoTemplate.find(query, Auction.class);

        List<AuctionDTO> auctionDTOList = new ArrayList<>();

        for (Auction auction : auctionList) {
            Query query1 = new Query();
            Criteria criteria1 = Criteria.where("nftContract").regex(auction.getNftContract(), "i");
            Criteria criteria2 = Criteria.where("tokenId").is(auction.getTokenId());
            query1.addCriteria(criteria1.andOperator(criteria2));

            Nft nft = mongoTemplate.findOne(query1, Nft.class);

            AuctionDTO auctionDTO = new AuctionDTO(auction);

            auctionDTO.setName(nft.getName());
            auctionDTO.setDescription(nft.getDescription());
            auctionDTO.setIpfsHashImage(nft.getIpfsHashImage());
            auctionDTO.setIpfsHashMetadata(nft.getIpfsHashMetadata());

            auctionDTOList.add(auctionDTO);
        }

        return PageableExecutionUtils.getPage(auctionDTOList, pageable, () -> count);
    }

    @Override
    public void updateStatusAllAuctionOffChain() {
        Query query = new Query();

        List<String> statusAuctionList = new ArrayList<>();
        statusAuctionList.add(Constant.UPCOMING_AUCTION_STATUS);
        statusAuctionList.add(Constant.ACTIVE_AUCTION_STATUS);
//        statusAuctionList.add(Constant.ENDED_AUCTION_STATUS); Dont query ended auction

        query.addCriteria(Criteria.where("statusAuction").in(statusAuctionList));

        List<Auction> auctionList = mongoTemplate.find(query, Auction.class);

        for (Auction auction : auctionList) {
            auction.setStatusAuction(getRealTimeStatusAuction(
                    auction.getOpenBiddingTime(),
                    auction.getCloseBiddingTime()
            ));

            mongoTemplate.save(auction);
        }
    }

    public String getRealTimeStatusAuction(Long openBiddingTime, Long closeBiddingTime) {
        long realTime = System.currentTimeMillis() / 1000;

        if (openBiddingTime < realTime && realTime < closeBiddingTime) {
            return Constant.ACTIVE_AUCTION_STATUS;
        } else if (realTime > closeBiddingTime) {
            return Constant.ENDED_AUCTION_STATUS;
        } else {
            return Constant.UPCOMING_AUCTION_STATUS;
        }
    }

    @Override
    public List<Auction> getAuctionToTransferAsset() {
        Query query = new Query();

        Criteria criteria1 = new Criteria();
        criteria1.where("statusAuction").is(Constant.ENDED_AUCTION_STATUS);
        Criteria criteria2 = new Criteria();
        criteria2.where("transferAssetStatus").is(Constant.NOT_TRANSFER_ASSET);

        query.addCriteria(criteria1.andOperator(criteria2));

        List<Auction> auctionList = mongoTemplate.find(query, Auction.class);

        return auctionList;
    }

    @Override
    public String getWinnerAuction(BigInteger uuid) {
        Query query = new Query();
        Criteria criteria = Criteria.where("uuid").is(uuid);
        query.addCriteria(criteria);

        List<Bidder> bidderList = mongoTemplate.find(query, Bidder.class);

        List<Long> bidderOfferedPrices = new ArrayList<>();

        for (Bidder bidder : bidderList) {
            bidderOfferedPrices.add(bidder.getOfferedPrice());
        }

        Long maxBidderOfferedPrice = Collections.max(bidderOfferedPrices);

        for (Bidder bidder : bidderList) {
            if (bidder.getOfferedPrice() == maxBidderOfferedPrice) {
                return bidder.getBidderAddress();
            }
        }

        return null;
    }
}
