package com.silamoney.client.testsrefactored.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import com.silamoney.client.api.ApiResponse;
import com.silamoney.client.security.EcdsaUtil;
import com.silamoney.client.testsutils.DefaultConfigurations;
import com.silamoney.clientrefactored.configuration.Environment;
import com.silamoney.clientrefactored.configuration.SilaApi;
import com.silamoney.clientrefactored.domain.Wallet;
import com.silamoney.clientrefactored.endpoints.wallets.registerwallet.RegisterWallet;
import com.silamoney.clientrefactored.endpoints.wallets.registerwallet.RegisterWalletRequest;
import com.silamoney.clientrefactored.endpoints.wallets.registerwallet.RegisterWalletResponse;
import com.silamoney.clientrefactored.endpoints.wallets.updatewallet.UpdateWallet;
import com.silamoney.clientrefactored.endpoints.wallets.updatewallet.UpdateWalletRequest;
import com.silamoney.clientrefactored.endpoints.wallets.updatewallet.UpdateWalletResponse;
import com.silamoney.clientrefactored.utils.WalletUtils;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

public class UpdateWalletTests {

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

        UpdateWalletRequest request = UpdateWalletRequest.builder()
            .userHandle(DefaultConfigurations.getUserHandle())
            .userPrivateKey(DefaultConfigurations.getUserPrivateKey())
            .defaultWallet(false)
            .nickname("wallet_test_UPD")
            .build();

        ApiResponse response = UpdateWallet.send(request);
        UpdateWalletResponse parsedResponse = (UpdateWalletResponse) response.getData();

        assertNotNull(parsedResponse.getMessage());
        assertNotNull(parsedResponse.getStatus());
        assertTrue(parsedResponse.isSuccess());
        assertEquals("wallet_test_UPD", parsedResponse.getWallet().getNickname());
        assertTrue(parsedResponse.getChanges().size() > 0);
    }
    @Test
    public void Response200WithStatementsEnabled() throws Exception {

        UpdateWalletRequest request = UpdateWalletRequest.builder()
                .userHandle(DefaultConfigurations.getUserHandle())
                .userPrivateKey(DefaultConfigurations.getUserPrivateKey())
                .defaultWallet(false)
                .nickname("wallet_test_UPD")
                .statementsEnabled(true)
                .build();

        ApiResponse response = UpdateWallet.send(request);
        UpdateWalletResponse parsedResponse = (UpdateWalletResponse) response.getData();

        assertNotNull(parsedResponse.getMessage());
        assertNotNull(parsedResponse.getStatus());
        assertTrue(parsedResponse.isSuccess());
        assertEquals("wallet_test_UPD", parsedResponse.getWallet().getNickname());
        assertTrue(parsedResponse.getWallet().isStatementsEnabled());
        assertTrue(parsedResponse.getChanges().size() > 0);
    }
}
