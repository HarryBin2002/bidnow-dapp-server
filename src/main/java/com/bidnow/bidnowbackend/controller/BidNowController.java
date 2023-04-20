package com.bidnow.bidnowbackend.controller;

import com.bidnow.bidnowbackend.constant.Constant;
import com.bidnow.bidnowbackend.dto.response.ResponseData;
import com.bidnow.bidnowbackend.service.BidNowService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1/bid-now")
public class BidNowController {

    private static final Logger LOGGER = LoggerFactory.getLogger(BidNowController.class);

    @Autowired
    private BidNowService bidNowService;

    @PostMapping("/create-new-auction")
    public ResponseData createNewAuction(
            @RequestParam String transactionHash
    ) {
        try {
            bidNowService.createNewAuction(transactionHash);
            return new ResponseData(Constant.SUCCESS, "");
        } catch (Exception exception) {
            LOGGER.error(exception.getMessage());
            return new ResponseData(Constant.ERROR, Constant.SOMETHING_WRONG);
        }
    }

    @PostMapping("/bid-auction")
    public ResponseData bidAuction(
            @RequestParam String transactionHash
    ) {
        try {
            bidNowService.bidAuction(transactionHash);
            return new ResponseData(Constant.SUCCESS, "");
        } catch (Exception exception) {
            LOGGER.error(exception.getMessage());
            return new ResponseData(Constant.ERROR, Constant.SOMETHING_WRONG);
        }
    }

}
