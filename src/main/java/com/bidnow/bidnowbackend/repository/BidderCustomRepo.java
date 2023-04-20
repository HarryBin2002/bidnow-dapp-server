package com.bidnow.bidnowbackend.repository;

import com.bidnow.bidnowbackend.model.Bidder;

public interface BidderCustomRepo {
    public void saveBiddingAuctionEvent(Bidder bidder);
}
