package com.bidnow.bidnowbackend.repository;

import com.bidnow.bidnowbackend.model.Auction;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.Optional;

@Repository
public interface AuctionRepo extends MongoRepository<Auction, String>, AuctionCustomRepo {
    Optional<Auction> findByUuid(BigInteger uuid);
}
