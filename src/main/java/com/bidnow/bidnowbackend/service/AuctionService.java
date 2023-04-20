package com.bidnow.bidnowbackend.service;

import com.bidnow.bidnowbackend.dto.response.AuctionDTO;
import com.bidnow.bidnowbackend.model.Auction;
import com.bidnow.bidnowbackend.repository.AuctionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigInteger;

@Service
public class AuctionService {

    @Autowired
    private AuctionRepo auctionRepo;

    public Page<AuctionDTO> getListAuctionFromStatus(Pageable pageable, String statusAuction) throws Exception {
        return auctionRepo.getListAuctionFromStatus(pageable, statusAuction);
    }

    public Auction getAuctionFromUuid(BigInteger uuid) {
        return auctionRepo.findByUuid(uuid).get();
    }

    public String getWinnerAuction(BigInteger uuid) {
        return auctionRepo.getWinnerAuction(uuid);
    }

}
