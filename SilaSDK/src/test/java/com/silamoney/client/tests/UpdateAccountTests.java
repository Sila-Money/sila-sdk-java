package com.silamoney.client.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import com.silamoney.client.api.ApiResponse;
import com.silamoney.client.api.SilaApi;
import com.silamoney.client.testsutils.DefaultConfigurations;
import com.silamoney.clientrefactored.endpoints.accounts.updateaccount.UpdateAccountRequest;
import com.silamoney.clientrefactored.endpoints.accounts.updateaccount.UpdateAccountResponse;

import org.junit.Test;

public class UpdateAccountTests {

    SilaApi api = new SilaApi(DefaultConfigurations.host, DefaultConfigurations.appHandle,
                        DefaultConfigurations.privateKey);

    @Test
	public void Register200UpdateAccount() throws Exception {
		UpdateAccountRequest request = UpdateAccountRequest.builder()
			.accountName("defaultupdate")
            .newAccountName("defaultupdated")
            .userHandle(DefaultConfigurations.getUserHandle())
            .userPrivateKey(DefaultConfigurations.getUserPrivateKey())
			.build();

		ApiResponse response = api.updateAccount(request);

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
