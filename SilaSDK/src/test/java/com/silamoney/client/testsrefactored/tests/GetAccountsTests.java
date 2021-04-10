package com.silamoney.client.testsrefactored.tests;

import static org.junit.Assert.assertTrue;

import com.silamoney.client.testsutils.DefaultConfigurations;
import com.silamoney.clientrefactored.configuration.Environment;
import com.silamoney.clientrefactored.configuration.SilaApi;
import com.silamoney.clientrefactored.endpoints.accounts.getaccounts.GetAccounts;
import com.silamoney.clientrefactored.endpoints.accounts.getaccounts.GetAccountsRequest;
import com.silamoney.clientrefactored.endpoints.accounts.getaccounts.GetAccountsResponse;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

public class GetAccountsTests {

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

        GetAccountsRequest request = GetAccountsRequest.builder()
            .userHandle(DefaultConfigurations.getUserHandle())
            .userPrivateKey(DefaultConfigurations.getUserPrivateKey())
            .build();
        
        GetAccountsResponse response = GetAccounts.send(request);

        assertTrue(response.getAccounts().size() > 0);
    }
    
}
