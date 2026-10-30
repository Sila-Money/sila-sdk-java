package com.silamoney.client.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import com.silamoney.client.api.ApiResponse;
import com.silamoney.client.api.SilaApi;
import com.silamoney.client.domain.AccountBalanceResponse;
import com.silamoney.client.testsutils.DefaultConfigurations;
import org.junit.Test;


public class GetAccountBalanceTests {
    SilaApi api = new SilaApi(DefaultConfigurations.host, DefaultConfigurations.appHandle,
            DefaultConfigurations.privateKey);

    @Test
    public void Response200Success() throws Exception {
        // BANKACCOUNT5
        final ApiResponse response = api.getAccountBalance(DefaultConfigurations.getUserHandle(),
                DefaultConfigurations.getUserPrivateKey(), "default");

        assertEquals(200, response.getStatusCode());
        AccountBalanceResponse parsedResponse = (AccountBalanceResponse)response.getData();

        assertNotNull(parsedResponse.getAccountName());
        assertNotNull(parsedResponse.getMaskedAccountNumber());
    }

    @Test
    public void Response400Success() throws Exception {
        SilaApi api = new SilaApi(DefaultConfigurations.host, DefaultConfigurations.appHandle,
                DefaultConfigurations.privateKey);

        // BANKACCOUNT5
        final ApiResponse response = api.getAccountBalance("", DefaultConfigurations.getUserPrivateKey(), "default");

        assertEquals(400, response.getStatusCode());
    }
}
