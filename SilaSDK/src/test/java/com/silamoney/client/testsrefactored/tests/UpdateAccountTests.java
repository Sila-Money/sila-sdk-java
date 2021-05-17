package com.silamoney.client.testsrefactored.tests;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import com.silamoney.client.api.ApiResponse;
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

		ApiResponse response = UpdateAccount.send(request);
		UpdateAccountResponse parsedResponse = (UpdateAccountResponse) response.getData();

		assertNotNull(parsedResponse.getStatus());
		assertNotNull(parsedResponse.getMessage());
		assertNotNull(parsedResponse.getAccount().getAccountLinkStatus());
        assertNotNull(parsedResponse.getAccount().getAccountName());
        assertNotNull(parsedResponse.getAccount().getAccountNumber());
        assertNotNull(parsedResponse.getAccount().getAccountStatus());
        assertNotNull(parsedResponse.getAccount().getAccountType());
        assertNotNull(parsedResponse.getAccount().getRoutingNumber());
        assertTrue(parsedResponse.getAccount().isActive());
		assertNotNull(parsedResponse.getChanges().get(0).getAttribute());
        assertNotNull(parsedResponse.getChanges().get(0).getNewValue());
        assertNotNull(parsedResponse.getChanges().get(0).getOldValue());
	}
    
}
