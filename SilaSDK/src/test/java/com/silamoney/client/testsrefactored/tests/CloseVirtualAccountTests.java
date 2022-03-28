package com.silamoney.client.testsrefactored.tests;

import com.silamoney.client.api.ApiResponse;
import com.silamoney.client.testsutils.DefaultConfigurations;
import com.silamoney.clientrefactored.configuration.Environment;
import com.silamoney.clientrefactored.configuration.SilaApi;
import com.silamoney.clientrefactored.endpoints.virtualAccount.VirtualAccountResponse;
import com.silamoney.clientrefactored.endpoints.virtualAccount.closeVirtualAccount.CloseVirtualAccount;
import com.silamoney.clientrefactored.endpoints.virtualAccount.closeVirtualAccount.CloseVirtualAccountRequest;
import com.silamoney.clientrefactored.endpoints.virtualAccount.openVirtualAccount.OpenVirtualAccount;
import com.silamoney.clientrefactored.endpoints.virtualAccount.openVirtualAccount.OpenVirtualAccountRequest;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.*;

public class CloseVirtualAccountTests {

	@BeforeClass
	public static void init() {
		SilaApi.init(Environment.SANDBOX, DefaultConfigurations.appHandle,
				DefaultConfigurations.privateKey);
	}

	@Test
	public void Register200() throws Exception {
		OpenVirtualAccountRequest request= OpenVirtualAccountRequest.builder().userHandle(DefaultConfigurations.getUserHandle()).userPrivateKey(DefaultConfigurations.getUserPrivateKey()).virtualAccountName("virtual_account_close").build();

		ApiResponse response = OpenVirtualAccount.send(request);
		VirtualAccountResponse parsedResponse = (VirtualAccountResponse) response.getData();

		assertEquals("SUCCESS", parsedResponse.getStatus());
		assertNotNull(parsedResponse.getVirtualAccount());
		assertTrue(parsedResponse.isSuccess());
		String virtualAccountId=parsedResponse.getVirtualAccount().getVirtualAccountId();
		String accountNumber=parsedResponse.getVirtualAccount().getAccountNumber();
		TimeUnit.SECONDS.sleep(4);
		CloseVirtualAccountRequest virtualAccountRequest=CloseVirtualAccountRequest.builder().userHandle(DefaultConfigurations.getUserHandle()).userPrivateKey(DefaultConfigurations.getUserPrivateKey()).virtualAccountId(virtualAccountId).accountNumber(accountNumber).build();
		 response = CloseVirtualAccount.send(virtualAccountRequest);
		assertEquals(200, response.getStatusCode());
		 parsedResponse = (VirtualAccountResponse) response.getData();
		assertEquals("SUCCESS", parsedResponse.getStatus());
	}

	@AfterClass
	public static void dispose() {
		SilaApi.dispose();
	}
}
