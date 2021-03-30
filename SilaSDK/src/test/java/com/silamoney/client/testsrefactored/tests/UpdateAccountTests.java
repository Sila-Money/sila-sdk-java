package com.silamoney.client.testsrefactored.tests;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import com.silamoney.client.testsutils.DefaultConfigurations;
import com.silamoney.clientrefactored.configuration.Environment;
import com.silamoney.clientrefactored.configuration.SilaApi;
import com.silamoney.clientrefactored.endpoints.accounts.updateaccount.UpdateAccount;
import com.silamoney.clientrefactored.endpoints.accounts.updateaccount.UpdateAccountRequest;
import com.silamoney.clientrefactored.endpoints.accounts.updateaccount.UpdateAccountResponse;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

public class UpdateAccountTests {

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
	public void Register200UpdateAccount() throws Exception {
		UpdateAccountRequest request = UpdateAccountRequest.builder()
			.accountName("defaultupdate")
            .newAccountName("defaultupdated")
            .userHandle(DefaultConfigurations.getUserHandle())
            .userPrivateKey(DefaultConfigurations.getUserPrivateKey())
			.build();

		UpdateAccountResponse response = UpdateAccount.send(request);

		assertNotNull(response.getStatus());
		assertNotNull(response.getMessage());
		assertNotNull(response.getAccount().getAccountLinkStatus());
        assertNotNull(response.getAccount().getAccountName());
        assertNotNull(response.getAccount().getAccountNumber());
        assertNotNull(response.getAccount().getAccountStatus());
        assertNotNull(response.getAccount().getAccountType());
        assertNotNull(response.getAccount().getRoutingNumber());
        assertTrue(response.getAccount().isActive());
		assertNotNull(response.getChanges().get(0).getAttribute());
        assertNotNull(response.getChanges().get(0).getNewValue());
        assertNotNull(response.getChanges().get(0).getOldValue());
	}
    
}
