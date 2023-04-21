package com.bidnow.bidnowbackend.service;

import com.bidnow.bidnowbackend.model.Auction;
import com.bidnow.bidnowbackend.model.Bidder;
import com.bidnow.bidnowbackend.repository.AuctionRepo;
import com.bidnow.bidnowbackend.repository.BidderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.response.EthGetTransactionReceipt;
import org.web3j.protocol.core.methods.response.Log;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.protocol.http.HttpService;

import java.io.IOException;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.util.HexFormat;
import java.util.List;

@Service
public class BidNowService {

    @Autowired
    private AuctionRepo auctionRepo;

    @Autowired
    private BidderRepo bidderRepo;



    // config web3j
    public Web3j web3j() {
        return Web3j.build(new HttpService("HTTP://127.0.0.1:7545"));
    }

    public void createNewAuction(String transactionHash) throws IOException {
        // create Web3j instance
        Web3j web3j = web3j();

        // get transactionReceipt instance
        EthGetTransactionReceipt ethGetTransactionReceipt =web3j.ethGetTransactionReceipt(transactionHash).send();
        TransactionReceipt transactionReceipt = ethGetTransactionReceipt.getTransactionReceipt().get();

        // get list log in that transaction
        List<Log> logs = transactionReceipt.getLogs();
        // get the latestLog in that transaction
        Log latestLog = logs.get(logs.size() - 1);
        System.out.println(latestLog.getData());


        // get ownerAuction
        String ownerAuctionHexString = latestLog.getData().substring(2, 66).toString();
        String ownerAuction = ownerAuctionHexString.replace("000000000000000000000000", "0x");
        System.out.println("ownerAuction: " + ownerAuction);

        // get nftContract
        String nftContractHexString = latestLog.getData().substring(66, 130).toString();
        String nftContract = nftContractHexString.replace("000000000000000000000000", "0x");
        System.out.println("nftContract: " + nftContract);

        // get tokenId
        Long tokenId = Long.parseLong(latestLog.getData().substring(130, 194), 16);
        System.out.println("tokenId: " + tokenId);

        // get initialPrice
        Long initialPrice = Long.parseLong(latestLog.getData().substring(194, 258), 16);
        System.out.println("initialPrice: " + initialPrice);

        // get openBiddingTime
        Long openBiddingTime = Long.parseLong(latestLog.getData().substring(258, 322), 16);
        System.out.println("openBiddingTime: " + openBiddingTime);

        // get closeBiddingTime
        Long closeBiddingTime = Long.parseLong(latestLog.getData().substring(322, 386), 16);
        System.out.println("closeBiddingTime: " + closeBiddingTime);

        // 288 (386 - 450)

        // get transferAssetStatus
        Long transferAssetStatus = Long.parseLong(latestLog.getData().substring(450, 514), 16);
        System.out.println("transferAssetStatus: " + transferAssetStatus);

        // get uuid
        BigInteger uuid = BigInteger.valueOf(Long.parseLong(latestLog.getData().substring(514, 578), 16));
        System.out.println("uuid: " + uuid);

        // 14 (578 - 642)

        // get statusAuction
        byte[] bytes = HexFormat.of().parseHex(latestLog.getData().substring(642, 706));
        String statusAuction = new String(bytes, StandardCharsets.UTF_8).replaceAll("\u0000", "");
        System.out.println("statusAuction: " + statusAuction);

        Auction auction = new Auction(
                ownerAuction,
                nftContract,
                tokenId,
                initialPrice,
                openBiddingTime,
                closeBiddingTime,
                statusAuction,
                transferAssetStatus,
                uuid
        );

        auctionRepo.saveCreatingAuctionEvent(auction);

        /*
        * because event which store data of that function is always put in the end of function code
        * So, we only need to get the latestLog.
        * */
    }


    public void bidAuction(String transactionHash) throws IOException {
        // create Web3j instance
        Web3j web3j = web3j();

        // get transactionReceipt instance
        EthGetTransactionReceipt ethGetTransactionReceipt =web3j.ethGetTransactionReceipt(transactionHash).send();
        TransactionReceipt transactionReceipt = ethGetTransactionReceipt.getTransactionReceipt().get();

        // get list log in that transaction
        List<Log> logs = transactionReceipt.getLogs();
        // get the latestLog in that transaction
        Log latestLog = logs.get(logs.size() - 1);
        System.out.println(latestLog.getData());

        // get uuid
        BigInteger uuid = BigInteger.valueOf(Long.parseLong(latestLog.getData().substring(2, 66), 16));
        System.out.println("uuid: " + uuid);

        // get bidderAddress
        String bidderAddressHexString = latestLog.getData().substring(66, 130).toString();
        String bidderAddress = bidderAddressHexString.replace("000000000000000000000000", "0x");
        System.out.println("bidderAddress: " + bidderAddress);

        // get offeredPrice
        Long offeredPrice = Long.parseLong(latestLog.getData().substring(130, 194), 16);
        System.out.println("offeredPrice: " + offeredPrice);

        Bidder bidder = new Bidder(
                bidderAddress,
                offeredPrice,
                uuid
        );

        bidderRepo.saveBiddingAuctionEvent(bidder);
    }
}
