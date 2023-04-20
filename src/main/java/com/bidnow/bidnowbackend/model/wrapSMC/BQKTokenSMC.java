package com.bidnow.bidnowbackend.model.wrapSMC;

import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tx.Contract;
import org.web3j.tx.gas.ContractGasProvider;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;

public class BQKTokenSMC extends Contract {
    public BQKTokenSMC(
            String contractBinary,
            String contractAddress,
            Web3j web3j,
            Credentials credentials,
            ContractGasProvider gasProvider
    ) {
        super(contractBinary, contractAddress, web3j, credentials, gasProvider);
    }

//    public RemoteCall<TransactionReceipt> mintAndApproveToken(String bidNowContract) {
//        Function function = new Function(
//                "mintAndApproveToken",
//                Arrays.asList(new Address(bidNowContract)), // danh sách đối số truyền vào hàm
//                Collections.emptyList() // danh sách giá trị trả về
//        );
//
//        return executeRemoteCallTransaction(function);
//    }

    // approve for fundingAddress
    public RemoteCall<TransactionReceipt> approveBQKContract(String fundingAddress, String bidNowContract) {
        Function function = new Function(
                "approveBQKContract",
                Arrays.asList(
                    new Address(fundingAddress),
                    new Address(bidNowContract)
                ),
                Collections.emptyList()
        );

        return executeRemoteCallTransaction(function);
    }

}
