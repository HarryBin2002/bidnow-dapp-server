package com.bidnow.bidnowbackend.model;

import org.springframework.data.annotation.Id;

import java.math.BigInteger;

public class Bidder {
    @Id
    private String id;
    private String bidderAddress;
    private Long offeredPrice;
    private BigInteger uuid;

    public Bidder() {
    }

    public Bidder(String bidderAddress, Long offeredPrice, BigInteger uuid) {
        this.bidderAddress = bidderAddress;
        this.offeredPrice = offeredPrice;
        this.uuid = uuid;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBidderAddress() {
        return bidderAddress;
    }

    public void setBidderAddress(String bidderAddress) {
        this.bidderAddress = bidderAddress;
    }

    public Long getOfferedPrice() {
        return offeredPrice;
    }

    public void setOfferedPrice(Long offeredPrice) {
        this.offeredPrice = offeredPrice;
    }

    public BigInteger getUuid() {
        return uuid;
    }

    public void setUuid(BigInteger uuid) {
        this.uuid = uuid;
    }
}
