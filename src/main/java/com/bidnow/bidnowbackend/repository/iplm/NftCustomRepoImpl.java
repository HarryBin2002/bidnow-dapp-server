package com.bidnow.bidnowbackend.repository.iplm;

import com.bidnow.bidnowbackend.dto.request.NftReq;
import com.bidnow.bidnowbackend.model.Nft;
import com.bidnow.bidnowbackend.repository.NftCustomRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;

public class NftCustomRepoImpl implements NftCustomRepo {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public void mintNft(NftReq nftReq) {
        Nft nft = new Nft(nftReq);

        mongoTemplate.save(nft);
    }

}
