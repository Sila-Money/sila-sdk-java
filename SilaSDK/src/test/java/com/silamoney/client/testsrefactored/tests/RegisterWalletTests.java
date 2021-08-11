package com.silamoney.client.testsrefactored.tests;

import static org.junit.Assert.assertNotNull;

import com.silamoney.client.api.ApiResponse;
import com.silamoney.client.security.EcdsaUtil;
import com.silamoney.client.testsutils.DefaultConfigurations;
import com.silamoney.clientrefactored.configuration.Environment;
import com.silamoney.clientrefactored.configuration.SilaApi;
import com.silamoney.clientrefactored.domain.Wallet;
import com.silamoney.clientrefactored.endpoints.wallets.registerwallet.RegisterWallet;
import com.silamoney.clientrefactored.endpoints.wallets.registerwallet.RegisterWalletRequest;
import com.silamoney.clientrefactored.endpoints.wallets.registerwallet.RegisterWalletResponse;
import com.silamoney.clientrefactored.utils.WalletUtils;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

public class RegisterWalletTests {

    @BeforeClass
	public static void init() {
		SilaApi.init(Environment.SANDBOX, DefaultConfigurations.appHandle,
				DefaultConfigurations.privateKey);
	}

    @AfterClass
	public static void dispose() {
		SilaApi.dispose();
	}

    @Test
    public void Response200() throws Exception {

        Wallet wallet = WalletUtils.generateWallet();
        String walletVerificationSignature = EcdsaUtil.sign(wallet.getBlockChainAddress(), wallet.getPrivateKey()); 

        RegisterWalletRequest request = RegisterWalletRequest.builder()
            .userHandle(DefaultConfigurations.getUserHandle())
            .userPrivateKey(DefaultConfigurations.getUserPrivateKey())
            .wallet(wallet)
            .walletVerificationSignature(walletVerificationSignature)
            .build();

        ApiResponse response = RegisterWallet.send(request);
        RegisterWalletResponse parsedResponse = (RegisterWalletResponse) response.getData();

        assertNotNull(parsedResponse.getMessage());
        assertNotNull(parsedResponse.getReference());
        assertNotNull(parsedResponse.getStatus());
        assertNotNull(parsedResponse.getWalletNickname());

        DefaultConfigurations.setNewWalletRefactored(wallet);
    }
    
}
