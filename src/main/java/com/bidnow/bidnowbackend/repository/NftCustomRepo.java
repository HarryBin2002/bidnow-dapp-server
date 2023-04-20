package com.bidnow.bidnowbackend.repository;

import com.bidnow.bidnowbackend.dto.request.NftReq;

public interface NftCustomRepo {
    public void mintNft(NftReq nftReq);
}
