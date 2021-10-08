package com.silamoney.client.testsrefactored.tests;

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

import java.util.Optional;

import static org.junit.Assert.*;

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

        //Freeze
		UpdateAccountRequest request1 = UpdateAccountRequest.builder()
				.accountName("defaultupdated")
				.userHandle(DefaultConfigurations.getUserHandle())
				.userPrivateKey(DefaultConfigurations.getUserPrivateKey())
				.active(false)
				.build();

		ApiResponse response1 = UpdateAccount.send(request1);
		UpdateAccountResponse parsedResponse1 = (UpdateAccountResponse) response1.getData();

		assertNotNull(parsedResponse1.getStatus());
		assertNotNull(parsedResponse1.getMessage());
		assertNotNull(parsedResponse1.getAccount().getAccountLinkStatus());
		assertNotNull(parsedResponse1.getAccount().getAccountName());
		assertNotNull(parsedResponse1.getAccount().getAccountNumber());
		assertNotNull(parsedResponse1.getAccount().getAccountStatus());
		assertNotNull(parsedResponse1.getAccount().getAccountType());
		assertNotNull(parsedResponse1.getAccount().getRoutingNumber());
		assertFalse(parsedResponse1.getAccount().isActive());
		assertNotNull(parsedResponse1.getChanges().get(0).getAttribute());
		assertNotNull(parsedResponse1.getChanges().get(0).getNewValue());
		assertNotNull(parsedResponse1.getChanges().get(0).getOldValue());

		//UnFreeze
		UpdateAccountRequest request2 = UpdateAccountRequest.builder()
				.accountName("defaultupdated")
				.userHandle(DefaultConfigurations.getUserHandle())
				.userPrivateKey(DefaultConfigurations.getUserPrivateKey())
				.active(true)
				.build();

		ApiResponse response2 = UpdateAccount.send(request2);
		UpdateAccountResponse parsedResponse2 = (UpdateAccountResponse) response2.getData();

		assertNotNull(parsedResponse2.getStatus());
		assertNotNull(parsedResponse2.getMessage());
		assertNotNull(parsedResponse2.getAccount().getAccountLinkStatus());
		assertNotNull(parsedResponse2.getAccount().getAccountName());
		assertNotNull(parsedResponse2.getAccount().getAccountNumber());
		assertNotNull(parsedResponse2.getAccount().getAccountStatus());
		assertNotNull(parsedResponse2.getAccount().getAccountType());
		assertNotNull(parsedResponse2.getAccount().getRoutingNumber());
		assertTrue(parsedResponse2.getAccount().isActive());
		assertNotNull(parsedResponse2.getChanges().get(0).getAttribute());
		assertNotNull(parsedResponse2.getChanges().get(0).getNewValue());
		assertNotNull(parsedResponse2.getChanges().get(0).getOldValue());
	}
}
