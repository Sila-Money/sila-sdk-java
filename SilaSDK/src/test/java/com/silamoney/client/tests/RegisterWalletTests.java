package com.silamoney.client.tests;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import com.silamoney.client.api.ApiResponse;
import com.silamoney.client.api.SilaApi;
import com.silamoney.client.domain.Wallet;
import com.silamoney.client.exceptions.BadRequestException;
import com.silamoney.client.exceptions.ForbiddenException;
import com.silamoney.client.exceptions.InvalidSignatureException;
import com.silamoney.client.exceptions.ServerSideException;
import com.silamoney.client.security.EcdsaUtil;
import com.silamoney.client.testsutils.DefaultConfigurations;

import org.junit.Test;

/**
 * RegisterWalletTests
 * 
 * @author wzelada
 */
public class RegisterWalletTests {

    SilaApi api = new SilaApi(DefaultConfigurations.host, DefaultConfigurations.appHandle,
            DefaultConfigurations.privateKey);

    @Test
    public void Response200() throws Exception {

        Wallet wallet = api.generateWallet();
        String wallet_verification_signature = EcdsaUtil.sign(wallet.getBlockChainAddress(), wallet.getPrivateKey());

        ApiResponse response = api.registerWallet(DefaultConfigurations.getUserHandle(), wallet,
                wallet_verification_signature, DefaultConfigurations.getUserPrivateKey());
        assertEquals(200, response.getStatusCode());
        DefaultConfigurations.setNewWallet(wallet);
    }
    @Test
    public void Response200WithDefault() throws Exception {

        Wallet wallet = api.generateWallet();
        String wallet_verification_signature = EcdsaUtil.sign(wallet.getBlockChainAddress(), wallet.getPrivateKey());

        ApiResponse response = api.registerWallet(DefaultConfigurations.getUserHandle(), wallet,
                wallet_verification_signature, DefaultConfigurations.getUserPrivateKey(),true);
        assertEquals(200, response.getStatusCode());

        Wallet wallet1 = api.generateWallet();
        String wallet_verification_signature1 = EcdsaUtil.sign(wallet1.getBlockChainAddress(), wallet1.getPrivateKey());

        ApiResponse response1 = api.registerWallet(DefaultConfigurations.getUserHandle(), wallet1,
                wallet_verification_signature1, DefaultConfigurations.getUserPrivateKey());
        assertEquals(200, response1.getStatusCode());
        DefaultConfigurations.setNewWallet(wallet1);
    }
    @Test
    public void ResponseBadWallet() throws BadRequestException, InvalidSignatureException, ServerSideException,
            IOException, InterruptedException, ForbiddenException {

        Wallet wallet = new Wallet("0xe60a5c57130f4e82782cbdb498943f31fe8f92ab96daac2cc13cbbbf9c0b4d9e", "ETH",
                "wallet_test");
        String wallet_verification_signature = "788e10a73c0548b875b6a2c47b985fc873d32ef14fff85e55791f0fccca6282838a81dd24db9ae72a508bb2bbc02f207ab1a1451ada195554fb9487a253432e61c";

        ApiResponse response = api.registerWallet(DefaultConfigurations.getUserHandle(), wallet,
                wallet_verification_signature, DefaultConfigurations.getUserPrivateKey());

        assertEquals(400, response.getStatusCode());
    }
}