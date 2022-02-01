package com.silamoney.client.testsrefactored.tests;

import com.silamoney.client.api.ApiResponse;
import com.silamoney.client.testsutils.DefaultConfigurations;
import com.silamoney.clientrefactored.configuration.Environment;
import com.silamoney.clientrefactored.configuration.SilaApi;
import com.silamoney.clientrefactored.endpoints.virtualAccount.getVirtualAccounts.GetVirtualAccounts;
import com.silamoney.clientrefactored.endpoints.virtualAccount.getVirtualAccounts.GetVirtualAccountsRequest;
import com.silamoney.clientrefactored.endpoints.virtualAccount.getVirtualAccounts.GetVirtualAccountsResponse;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

public class GetVirtualAccountsTests {

	@BeforeClass
	public static void init() {
		SilaApi.init(Environment.SANDBOX, DefaultConfigurations.appHandle,
				DefaultConfigurations.privateKey);
	}

	@Test
	public void Register200() throws Exception {
		GetVirtualAccountsRequest request= GetVirtualAccountsRequest.builder().userHandle(DefaultConfigurations.getUserHandle()).userPrivateKey(DefaultConfigurations.getUserPrivateKey()).build();
				
		ApiResponse response = GetVirtualAccounts.send(request);
		GetVirtualAccountsResponse parsedResponse = (GetVirtualAccountsResponse) response.getData();

		assertEquals("SUCCESS", parsedResponse.getStatus());
		assertNotNull(parsedResponse.getVirtualAccounts());
		assertTrue(parsedResponse.isSuccess());
	}

	@AfterClass
	public static void dispose() {
		SilaApi.dispose();
	}
}
