package com.bidnow.bidnowbackend.service;

import com.bidnow.bidnowbackend.dto.request.NftReq;
import com.bidnow.bidnowbackend.repository.NftRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NftService {
    @Autowired
    private NftRepo nftRepo;

    public void mintNft(NftReq nftReq) {
        nftRepo.mintNft(nftReq);
    }
}
