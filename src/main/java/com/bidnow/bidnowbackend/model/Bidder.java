package com.bidnow.bidnowbackend.model;

import org.springframework.data.annotation.Id;

public class Bidder {
    @Id
    private String id;
    private String bidderAddress;
    private Long offeredPrice;
    private Long uuid;

    public Bidder() {
    }

    public Bidder(String bidderAddress, Long offeredPrice, Long uuid) {
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

    public Long getUuid() {
        return uuid;
    }

    public void setUuid(Long uuid) {
        this.uuid = uuid;
    }
}
