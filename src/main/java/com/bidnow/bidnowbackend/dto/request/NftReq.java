package com.bidnow.bidnowbackend.dto.request;

public class NftReq {
    private String nftOwnerAddress;
    private String nftContract;
    private Long tokenId;
    private String name;
    private String description;
    private String ipfsHashImage;
    private String ipfsHashMetadata;



    public String getNftOwnerAddress() {
        return nftOwnerAddress;
    }

    public void setNftOwnerAddress(String nftOwnerAddress) {
        this.nftOwnerAddress = nftOwnerAddress;
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
