package com.bidnow.bidnowbackend.model.wrapSMC;

import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.crypto.Credentials;
import org.web3j.ens.EnsResolver;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tx.Contract;
import org.web3j.tx.TransactionManager;
import org.web3j.tx.gas.ContractGasProvider;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;

public class BidNowSMC extends Contract {

    public BidNowSMC(
            String contractBinary,
            String contractAddress,
            Web3j web3j,
            Credentials credentials,
            ContractGasProvider gasProvider
    ) {
        super(contractBinary, contractAddress, web3j, credentials, gasProvider);
    }

    public RemoteCall<TransactionReceipt> updateStatusAllAuction() {
        final Function function = new Function(
                "updateStatusAllAuction",
                Collections.emptyList(),
                Collections.emptyList()
        );
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> transferAssetAfterAuctionEnd(BigInteger uuid) {
        Function function = new Function(
                "transferAssetAfterAuctionEnd", // tên hàm trong smart contract
                Arrays.asList(new Uint256(uuid)), // danh sách đối số truyền vào hàm
                Collections.emptyList() // danh sách giá trị trả về
        );
        return executeRemoteCallTransaction(function);
    }

}
