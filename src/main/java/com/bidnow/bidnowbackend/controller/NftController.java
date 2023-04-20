package com.bidnow.bidnowbackend.controller;

import com.bidnow.bidnowbackend.constant.Constant;
import com.bidnow.bidnowbackend.dto.request.NftReq;
import com.bidnow.bidnowbackend.dto.response.ResponseData;
import com.bidnow.bidnowbackend.service.NftService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1/nft")
public class NftController {

    @Autowired
    private NftService nftService;

    @PostMapping("/mint-nft")
    public ResponseData mintNft(
        @RequestBody NftReq nftReq
    ) {
        try {
            nftService.mintNft(nftReq);
            return new ResponseData(Constant.SUCCESS, "");
        } catch (Exception exception) {
            throw new RuntimeException(exception.getMessage());
        }
    }



}
