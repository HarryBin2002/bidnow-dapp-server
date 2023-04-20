package com.bidnow.bidnowbackend.model;

import com.bidnow.bidnowbackend.dto.request.NftReq;
import org.springframework.data.annotation.Id;

public class Nft {
    @Id
    private String id;
    private String nftOwnerAddress;
    private String nftContract;
    private Long tokenId;
    private String name;
    private String description;
    private String ipfsHashImage;
    private String ipfsHashMetadata;

    public Nft() {
    }

    public Nft(String id, String nftOwnerAddress, String nftContract, Long tokenId, String name, String description, String ipfsHashImage, String ipfsHashMetadata) {
        this.id = id;
        this.nftOwnerAddress = nftOwnerAddress;
        this.nftContract = nftContract;
        this.tokenId = tokenId;
        this.name = name;
        this.description = description;
        this.ipfsHashImage = ipfsHashImage;
        this.ipfsHashMetadata = ipfsHashMetadata;
    }

    public Nft(NftReq nftReq) {
        this.nftOwnerAddress = nftReq.getNftOwnerAddress();
        this.nftContract = nftReq.getNftContract();
        this.tokenId = nftReq.getTokenId();
        this.name = nftReq.getName();
        this.description = nftReq.getDescription();
        this.ipfsHashImage = nftReq.getIpfsHashImage();
        this.ipfsHashMetadata = nftReq.getIpfsHashMetadata();
    }

    public String getNftOwnerAddress() {
        return nftOwnerAddress;
    }

    public void setNftOwnerAddress(String nftOwnerAddress) {
        this.nftOwnerAddress = nftOwnerAddress;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
