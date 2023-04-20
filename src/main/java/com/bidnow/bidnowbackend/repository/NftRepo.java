package com.bidnow.bidnowbackend.repository;

import com.bidnow.bidnowbackend.model.Nft;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NftRepo extends MongoRepository<Nft, String>, NftCustomRepo {
}
