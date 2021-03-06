package com.silamoney.client.testsrefactored.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import com.silamoney.client.api.ApiResponse;
import com.silamoney.client.testsutils.DefaultConfigurations;
import com.silamoney.clientrefactored.configuration.Environment;
import com.silamoney.clientrefactored.configuration.SilaApi;
import com.silamoney.clientrefactored.endpoints.accounts.linkaccount.LinkAccount;
import com.silamoney.clientrefactored.endpoints.accounts.linkaccount.LinkAccountRequest;
import com.silamoney.clientrefactored.endpoints.accounts.linkaccount.LinkAccountResponse;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

public class LinkAccountTests {

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
	public void Response200PublicToken() throws Exception {

		LinkAccountRequest request = LinkAccountRequest.builder()
            .accountName("defaultpt")
            .plaidToken(DefaultConfigurations.getPlaidToken())
            .userHandle(DefaultConfigurations.getUserHandle())
            .userPrivateKey(DefaultConfigurations.getUserPrivateKey())
            .plaidTokenType("legacy")
            .build();

        ApiResponse response = LinkAccount.send(request);
        LinkAccountResponse parsedResponse = (LinkAccountResponse) response.getData();

        assertEquals("SUCCESS", parsedResponse.getStatus());
        assertEquals("defaultpt", parsedResponse.getAccountName());
        assertNotNull(parsedResponse.getAccountOwnerName());
        assertNotNull(parsedResponse.getEntityName());
        assertNotNull(parsedResponse.getMessage());
        assertNotNull(parsedResponse.getReference());

	}

    @Test
    public void Response200NoPublicToken() throws Exception {

        LinkAccountRequest request = LinkAccountRequest.builder()
            .accountName("default")
            .accountNumber("123456789012")
            .routingNumber("123456789")
            .accountType("CHECKING")
            .userHandle(DefaultConfigurations.getUserHandle())
            .userPrivateKey(DefaultConfigurations.getUserPrivateKey())
            .build();

            ApiResponse response = LinkAccount.send(request);
            LinkAccountResponse parsedResponse = (LinkAccountResponse) response.getData();

        assertEquals("SUCCESS", parsedResponse.getStatus());
        assertEquals("default", parsedResponse.getAccountName());
        assertNotNull(parsedResponse.getMessage());
        assertNotNull(parsedResponse.getReference());

        request = LinkAccountRequest.builder()
            .accountName("defaultupdate")
            .accountNumber("123456789012")
            .routingNumber("123456789")
            .accountType("CHECKING")
            .userHandle(DefaultConfigurations.getUserHandle())
            .userPrivateKey(DefaultConfigurations.getUserPrivateKey())
            .build();

        response = LinkAccount.send(request);
        parsedResponse = (LinkAccountResponse) response.getData();

        assertEquals("SUCCESS", parsedResponse.getStatus());
        assertEquals("defaultupdate", parsedResponse.getAccountName());
        assertNotNull(parsedResponse.getMessage());
        assertNotNull(parsedResponse.getReference());

        request = LinkAccountRequest.builder()
            .accountName("defaultunlink")
            .accountNumber("123456789012")
            .routingNumber("123456789")
            .accountType("CHECKING")
            .userHandle(DefaultConfigurations.getUserHandle())
            .userPrivateKey(DefaultConfigurations.getUserPrivateKey())
            .build();

        response = LinkAccount.send(request);
        parsedResponse = (LinkAccountResponse) response.getData();

        assertEquals("SUCCESS", parsedResponse.getStatus());
        assertEquals("defaultunlink", parsedResponse.getAccountName());
        assertNotNull(parsedResponse.getMessage());
        assertNotNull(parsedResponse.getReference());

    }
    
}
