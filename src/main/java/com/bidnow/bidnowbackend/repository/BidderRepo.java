package com.bidnow.bidnowbackend.repository;

import com.bidnow.bidnowbackend.model.Bidder;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BidderRepo extends MongoRepository<Bidder, String>, BidderCustomRepo {
}
