package com.bidnow.bidnowbackend.model.wrapSMC;

import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Bool;
import org.web3j.abi.datatypes.Function;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tx.Contract;
import org.web3j.tx.gas.ContractGasProvider;

import java.util.Arrays;
import java.util.Collections;

public class NftSMC extends Contract {
    public NftSMC(
            String contractBinary,
            String contractAddress,
            Web3j web3j,
            Credentials credentials,
            ContractGasProvider gasProvider
    ) {
        super(contractBinary, contractAddress, web3j, credentials, gasProvider);
    }

    public RemoteCall<TransactionReceipt> setApprovalForAll(String bidNowContract, boolean approved) {
        Function function = new Function(
                "setApprovalForAll",
                Arrays.asList(
                    new Address(bidNowContract),
                    new Bool(approved)
                ),
                Collections.emptyList()
        );

        return executeRemoteCallTransaction(function);
    }
}
