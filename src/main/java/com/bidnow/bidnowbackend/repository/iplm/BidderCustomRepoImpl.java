package com.bidnow.bidnowbackend.repository.iplm;

import com.bidnow.bidnowbackend.model.Bidder;
import com.bidnow.bidnowbackend.repository.BidderCustomRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;

public class BidderCustomRepoImpl implements BidderCustomRepo {
    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public void saveBiddingAuctionEvent(Bidder bidder) {
        mongoTemplate.save(bidder);
    }
}
