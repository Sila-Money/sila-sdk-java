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

    String userHandle = "javasdk-893748932";
    String userPrivateKey = "f6b5751234d4586873714066c538b9ddaa51ee5e3188a58236be1671f0be0ed3";

    @Test
    public void Response200Success() throws Exception {
        // BANKACCOUNT5
        if (DefaultConfigurations.getUserHandle() == null) {
            DefaultConfigurations.setUserHandle(userHandle);
        }
        if (DefaultConfigurations.getUserPrivateKey() == null) {
            DefaultConfigurations.setUserPrivateKey(userPrivateKey);
        }
        final ApiResponse response = api.getAccountBalance(DefaultConfigurations.getUserHandle(),
                DefaultConfigurations.getUserPrivateKey(), "defaultpt");

        assertEquals(200, response.getStatusCode());
        assertNotNull(((AccountBalanceResponse)response.getData()).getAccountName());
        assertNotNull(((AccountBalanceResponse)response.getData()).getAvailableBalance());
        assertNotNull(((AccountBalanceResponse)response.getData()).getCurrentBalance());
        assertNotNull(((AccountBalanceResponse)response.getData()).getMaskedAccountNumber());
        assertNotNull(((AccountBalanceResponse)response.getData()).getRoutingNumber());
    }

    @Test
    public void Response400Success() throws Exception {
        // BANKACCOUNT5
        if (DefaultConfigurations.getUserHandle() == null) {
            DefaultConfigurations.setUserHandle(userHandle);
        }
        if (DefaultConfigurations.getUserPrivateKey() == null) {
            DefaultConfigurations.setUserPrivateKey(userPrivateKey);
        }
        final ApiResponse response = api.getAccountBalance("", DefaultConfigurations.getUserPrivateKey(), "default");

        assertEquals(400, response.getStatusCode());
    }
}
