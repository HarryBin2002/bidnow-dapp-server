package com.bidnow.bidnowbackend.controller;

import com.bidnow.bidnowbackend.constant.Constant;
import com.bidnow.bidnowbackend.dto.response.AuctionDTO;
import com.bidnow.bidnowbackend.dto.response.Pagination;
import com.bidnow.bidnowbackend.dto.response.ResponseData;
import com.bidnow.bidnowbackend.dto.response.ResponseDataPagination;
import com.bidnow.bidnowbackend.service.AuctionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.List;

@RestController
@RequestMapping("/api/v1/auction")
public class AuctionController {

    @Autowired
    private AuctionService auctionService;


    @GetMapping("/get-list-auction-from-status")
    public ResponseDataPagination getListAuctionFromStatus(
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "20") int size,
        @RequestParam String statusAuction
    ) {
        ResponseDataPagination responseDataPagination = new ResponseDataPagination();
        Pagination pagination = new Pagination();

        try {
            Pageable pageable = PageRequest.of(page, size);
            Page<AuctionDTO> auctionDTOPage = auctionService.getListAuctionFromStatus(pageable, statusAuction);

            List<AuctionDTO> auctionDTOList = auctionDTOPage.getContent();

            pagination.setCurrentPage(page);
            pagination.setPageSize(size);
            pagination.setTotalPage(auctionDTOPage.getTotalPages());
            pagination.setTotalRecords(auctionDTOPage.getTotalElements());

            responseDataPagination.setStatus(Constant.SUCCESS);
            responseDataPagination.setMessage("");
            responseDataPagination.setData(auctionDTOList);
            responseDataPagination.setPagination(pagination);
        } catch (Exception exception) {
            responseDataPagination.setStatus(Constant.ERROR);
            responseDataPagination.setMessage(Constant.SOMETHING_WRONG);
        }

        return responseDataPagination;
    }

    @GetMapping("/get-auction-from-uuid")
    public ResponseData getAuctionFromUuid(
        @RequestParam BigInteger uuid
    ) {
        try {
            return new ResponseData(Constant.SUCCESS, auctionService.getAuctionFromUuid(uuid), "");
        } catch (Exception exception) {
            return new ResponseData(Constant.ERROR, Constant.SOMETHING_WRONG);
        }
    }

    @GetMapping("/get-winner-auction")
    public ResponseData getWinnerAuction(
        @RequestParam BigInteger uuid
    ) {
        try {
            return new ResponseData(Constant.SUCCESS, auctionService.getWinnerAuction(uuid), "");
        } catch (Exception exception) {
            return new ResponseData(Constant.ERROR, Constant.SOMETHING_WRONG);
        }
    }




}
