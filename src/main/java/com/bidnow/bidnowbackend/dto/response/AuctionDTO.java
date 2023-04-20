package com.bidnow.bidnowbackend.dto.response;

import com.bidnow.bidnowbackend.model.Auction;

import java.math.BigInteger;

public class AuctionDTO {

    private String ownerAuction;
    private String nftContract;
    private Long tokenId;
    private Long initialPrice;
    private Long openBiddingTime;
    private Long closeBiddingTime;
    private String statusAuction;
    private Long transferAssetStatus;
    private BigInteger uuid;


    private String name;
    private String description;
    private String ipfsHashImage;
    private String ipfsHashMetadata;

    public AuctionDTO() {
    }

    public AuctionDTO(Auction auction) {
        this.ownerAuction = auction.getOwnerAuction();
        this.nftContract = auction.getNftContract();
        this.tokenId = auction.getTokenId();
        this.initialPrice = auction.getInitialPrice();
        this.openBiddingTime = auction.getOpenBiddingTime();
        this.closeBiddingTime = auction.getCloseBiddingTime();
        this.statusAuction = auction.getStatusAuction();
        this.transferAssetStatus = auction.getTransferAssetStatus();
        this.uuid = auction.getUuid();
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIpfsHashImage() {
        return ipfsHashImage;
    }

    public void setIpfsHashImage(String ipfsHashImage) {
        this.ipfsHashImage = ipfsHashImage;
    }

    public String getIpfsHashMetadata() {
        return ipfsHashMetadata;
    }

    public void setIpfsHashMetadata(String ipfsHashMetadata) {
        this.ipfsHashMetadata = ipfsHashMetadata;
    }
}
