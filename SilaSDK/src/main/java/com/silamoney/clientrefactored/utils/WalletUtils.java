package com.silamoney.clientrefactored.utils;

import java.math.BigInteger;
import java.util.UUID;

import com.silamoney.clientrefactored.domain.Wallet;

import org.web3j.crypto.ECKeyPair;
import org.web3j.crypto.Keys;
import org.web3j.crypto.WalletFile;

public class WalletUtils {

    private WalletUtils() {
    }

    public static Wallet generateWallet() {
        try {
            ECKeyPair ecKeyPair = Keys.createEcKeyPair();
            BigInteger privateKeyInDec = ecKeyPair.getPrivateKey();

            WalletFile aWallet = org.web3j.crypto.Wallet.createLight(UUID.randomUUID().toString(), ecKeyPair);

            return Wallet.builder().blockChainAddress("0x" + aWallet.getAddress())
                    .privateKey(privateKeyInDec.toString(16)).blockChainNetwork("ETH").build();
        } catch (Exception e) {
            return Wallet.builder().build();
        }

    }

}
