package com.bidnow.bidnowbackend.repository;

import com.bidnow.bidnowbackend.dto.response.AuctionDTO;
import com.bidnow.bidnowbackend.model.Auction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.math.BigInteger;
import java.util.List;

public interface AuctionCustomRepo {
    public void saveCreatingAuctionEvent(Auction auction);
    public Page<AuctionDTO> getListAuctionFromStatus(Pageable pageable, String statusAuction) throws Exception;
    public void updateStatusAllAuctionOffChain();
    public List<Auction> getAuctionToTransferAsset();
    public String getWinnerAuction(BigInteger uuid);

}
