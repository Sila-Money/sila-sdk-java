package com.silamoney.client.tests;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import com.silamoney.client.api.ApiResponse;
import com.silamoney.client.api.SilaApi;
import com.silamoney.client.domain.UpdateAccountResponse;
import com.silamoney.client.testsutils.DefaultConfigurations;

import org.junit.Test;

public class UpdateAccountTests {

    SilaApi api = new SilaApi(DefaultConfigurations.host, DefaultConfigurations.appHandle,
            DefaultConfigurations.privateKey);

    @Test
    public void Register200UpdateAccount() throws Exception {

        ApiResponse response = api.updateAccount(DefaultConfigurations.getUserHandle(),
                DefaultConfigurations.getUserPrivateKey(), "defaultupdate", "defaultupdated");

        UpdateAccountResponse parsedResponse = (UpdateAccountResponse) response.getData();

        assertNotNull(parsedResponse.getStatus());
        assertNotNull(parsedResponse.getMessage());
        assertNotNull(parsedResponse.getAccount().getAccountListStatus());
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
