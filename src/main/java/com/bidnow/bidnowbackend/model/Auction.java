package com.bidnow.bidnowbackend.model;

import org.springframework.data.annotation.Id;

import java.math.BigInteger;

public class Auction {
    @Id
    private String id;
    private String ownerAuction;
    private String nftContract;
    private Long tokenId;
    private Long initialPrice;
    private Long openBiddingTime;
    private Long closeBiddingTime;
    private String statusAuction;
    private Long transferAssetStatus;
    private BigInteger uuid;

    public Auction() {
    }

    public Auction(String ownerAuction, String nftContract, Long tokenId, Long initialPrice, Long openBiddingTime, Long closeBiddingTime, String statusAuction, Long transferAssetStatus, BigInteger uuid) {
        this.ownerAuction = ownerAuction;
        this.nftContract = nftContract;
        this.tokenId = tokenId;
        this.initialPrice = initialPrice;
        this.openBiddingTime = openBiddingTime;
        this.closeBiddingTime = closeBiddingTime;
        this.statusAuction = statusAuction;
        this.transferAssetStatus = transferAssetStatus;
        this.uuid = uuid;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOwnerAuction() {
        return ownerAuction;
    }

    public void setOwnerAuction(String ownerAuction) {
        this.ownerAuction = ownerAuction;
    }

    public String getNftContract() {
        return nftContract;
    }

    public void setNftContract(String nftContract) {
        this.nftContract = nftContract;
    }

    public Long getTokenId() {
        return tokenId;
    }

    public void setTokenId(Long tokenId) {
        this.tokenId = tokenId;
    }

    public Long getInitialPrice() {
        return initialPrice;
    }

    public void setInitialPrice(Long initialPrice) {
        this.initialPrice = initialPrice;
    }

    public Long getOpenBiddingTime() {
        return openBiddingTime;
    }

    public void setOpenBiddingTime(Long openBiddingTime) {
        this.openBiddingTime = openBiddingTime;
    }

    public Long getCloseBiddingTime() {
        return closeBiddingTime;
    }

    public void setCloseBiddingTime(Long closeBiddingTime) {
        this.closeBiddingTime = closeBiddingTime;
    }

    public String getStatusAuction() {
        return statusAuction;
    }

    public void setStatusAuction(String statusAuction) {
        this.statusAuction = statusAuction;
    }

    public Long getTransferAssetStatus() {
        return transferAssetStatus;
    }

    public void setTransferAssetStatus(Long transferAssetStatus) {
        this.transferAssetStatus = transferAssetStatus;
    }

    public BigInteger getUuid() {
        return uuid;
    }

    public void setUuid(BigInteger uuid) {
        this.uuid = uuid;
    }
}
