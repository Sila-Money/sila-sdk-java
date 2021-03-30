package com.silamoney.client.testsrefactored.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

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
            .build();

        LinkAccountResponse response = LinkAccount.send(request);

        assertEquals("SUCCESS", response.getStatus());
        assertEquals("defaultpt", response.getAccountName());
        assertNotNull(response.getAccountOwnerName());
        assertNotNull(response.getEntityName());
        assertNotNull(response.getMessage());
        assertNotNull(response.getReference());

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

        LinkAccountResponse response = LinkAccount.send(request);

        assertEquals("SUCCESS", response.getStatus());
        assertEquals("default", response.getAccountName());
        assertNotNull(response.getMessage());
        assertNotNull(response.getReference());

        request = LinkAccountRequest.builder()
            .accountName("defaultupdate")
            .accountNumber("123456789012")
            .routingNumber("123456789")
            .accountType("CHECKING")
            .userHandle(DefaultConfigurations.getUserHandle())
            .userPrivateKey(DefaultConfigurations.getUserPrivateKey())
            .build();

        response = LinkAccount.send(request);

        assertEquals("SUCCESS", response.getStatus());
        assertEquals("defaultupdate", response.getAccountName());
        assertNotNull(response.getMessage());
        assertNotNull(response.getReference());

        request = LinkAccountRequest.builder()
            .accountName("defaultunlink")
            .accountNumber("123456789012")
            .routingNumber("123456789")
            .accountType("CHECKING")
            .userHandle(DefaultConfigurations.getUserHandle())
            .userPrivateKey(DefaultConfigurations.getUserPrivateKey())
            .build();

        response = LinkAccount.send(request);

        assertEquals("SUCCESS", response.getStatus());
        assertEquals("defaultunlink", response.getAccountName());
        assertNotNull(response.getMessage());
        assertNotNull(response.getReference());

    }
    
}
