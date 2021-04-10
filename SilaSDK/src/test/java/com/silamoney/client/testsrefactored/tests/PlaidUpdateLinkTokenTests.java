package com.silamoney.client.testsrefactored.tests;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import com.silamoney.client.security.EcdsaUtil;
import com.silamoney.client.testsutils.DefaultConfigurations;
import com.silamoney.clientrefactored.configuration.Environment;
import com.silamoney.clientrefactored.configuration.SilaApi;
import com.silamoney.clientrefactored.domain.Wallet;
import com.silamoney.clientrefactored.endpoints.accounts.plaidupdatelinktoken.PlaidUpdateLinkToken;
import com.silamoney.clientrefactored.endpoints.accounts.plaidupdatelinktoken.PlaidUpdateLinkTokenRequest;
import com.silamoney.clientrefactored.endpoints.accounts.plaidupdatelinktoken.PlaidUpdateLinkTokenResponse;
import com.silamoney.clientrefactored.endpoints.wallets.registerwallet.RegisterWallet;
import com.silamoney.clientrefactored.endpoints.wallets.registerwallet.RegisterWalletRequest;
import com.silamoney.clientrefactored.endpoints.wallets.registerwallet.RegisterWalletResponse;
import com.silamoney.clientrefactored.utils.WalletUtils;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

public class PlaidUpdateLinkTokenTests {

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

        PlaidUpdateLinkTokenRequest request = PlaidUpdateLinkTokenRequest.builder()
            .accountName("defaultpt")
            .userHandle(DefaultConfigurations.getUserHandle())
            .build();

        PlaidUpdateLinkTokenResponse response = PlaidUpdateLinkToken.send(request);

        assertTrue(response.isSuccess());
        assertNotNull(response.getStatus());
        assertNotNull(response.getLinkToken());
        assertNotNull(response.getMessage());

    }
    
}
