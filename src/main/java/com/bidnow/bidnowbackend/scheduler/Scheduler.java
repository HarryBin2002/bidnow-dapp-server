package com.bidnow.bidnowbackend.scheduler;


import com.bidnow.bidnowbackend.constant.Constant;
import com.bidnow.bidnowbackend.model.Auction;
import com.bidnow.bidnowbackend.model.wrapSMC.BidNowSMC;
import com.bidnow.bidnowbackend.repository.AuctionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.gas.ContractGasProvider;
import org.web3j.tx.gas.StaticGasProvider;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

@Component
public class Scheduler {

    @Autowired
    private AuctionRepo auctionRepo;

    // config credential
    public Credentials credentials() {
        Credentials credentials = Credentials.create("62ca9bb186f6111a0de4bc68485e08428829bc44039d993fc0e2a913cc9cd512");
        return credentials;
    }

    // config web3j
    public Web3j web3j() {
        return Web3j.build(new HttpService("HTTP://127.0.0.1:7545"));
    }


    Web3j web3j = web3j();
    private Credentials credentials = credentials();

    private ContractGasProvider contractGasProvider = new StaticGasProvider(Constant.GAS_PRICE, Constant.GAS_LIMIT);


    private BidNowSMC bidNowSMCContract = new BidNowSMC(
            Constant.BidNowContractBinary,
            Constant.BidNowContract,
            web3j,
            credentials,
            contractGasProvider
    );

    /***
     * Update data on-chain by calling updateStatusAllAuction function inside SMC
     */
//    @Scheduled(fixedRate = 10000) // 10s
    public void updateStatusAllAuction() throws Exception {
        System.out.println("program run in updateStatusAllAuction");
        RemoteCall<TransactionReceipt> remoteCall = bidNowSMCContract.updateStatusAllAuction();

        TransactionReceipt transactionReceipt = remoteCall.send();

        System.out.println(transactionReceipt.getTransactionHash());
    }

    /***
     * Update data off-chain
     * Query data in DB and setStatusAuction
     */
//    @Scheduled(fixedRate = 16000) // 16s
    public void updateStatusAllAuctionOffChain() {
        System.out.println("program run in updateStatusAllAuctionOffChain");

        auctionRepo.updateStatusAllAuctionOffChain();
    }

    /***
     * TransferAssetScheduler
     * Query data from DB (Auction has ended status and NOT_TRANSFER_ASSET)
     * Give it as a parameter to call transferAssetAfterAuctionEnd inside SMC
     * Update data off-chain (ALREADY_TRANSFER_ASSET)
     */
//    @Scheduled(fixedRate = 18000) // 18s
    public void TransferAssetScheduler() throws Exception {

        System.out.println("program run in TransferAssetScheduler");

        List<Auction> auctionList = getAuctionToTransferAsset();

        if (!auctionList.isEmpty()) {
            for (Auction auction : auctionList) {
                RemoteCall<TransactionReceipt> remoteCall = bidNowSMCContract.transferAssetAfterAuctionEnd(auction.getUuid());

                TransactionReceipt transactionReceipt = remoteCall.send();

                System.out.println("transferAssetTxHash: " + transactionReceipt.getTransactionHash());

                // update TransferAssetStatus of this auction
                auction.setTransferAssetStatus(Constant.ALREADY_TRANSFER_ASSET);

                auctionRepo.save(auction);
            }
        }
    }

    private List<Auction> getAuctionToTransferAsset() {
        return auctionRepo.getAuctionToTransferAsset();
    }


    // only for testing
//    @Scheduled(fixedRate = 10000)
    public void executeCronjob() throws Exception {
        updateStatusAllAuction();

        updateStatusAllAuctionOffChain();

        TransferAssetScheduler();
    }
}
