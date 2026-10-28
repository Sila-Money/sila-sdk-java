package com.silamoney.client.tests;

import com.silamoney.client.api.ApiResponse;
import com.silamoney.client.api.SilaApi;
import com.silamoney.client.domain.BadRequestResponse;
import com.silamoney.client.domain.UpdateAccountResponse;
import com.silamoney.client.testsutils.DefaultConfigurations;

import org.junit.Test;


import static org.junit.Assert.*;

public class UpdateAccountTests {
    SilaApi api = new SilaApi(DefaultConfigurations.host, DefaultConfigurations.appHandle,
            DefaultConfigurations.privateKey);

    @Test
    public void Register200UpdateAccount() throws Exception {
        ApiResponse response = api.updateAccount(DefaultConfigurations.getUserHandle(),
                DefaultConfigurations.getUserPrivateKey(), "Gringotts Checking", "default");

        UpdateAccountResponse parsedResponse = (UpdateAccountResponse) response.getData();
        assertEquals(200, response.getStatusCode());
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

    @Test
    public void Response400EmptyHandle() throws Exception {
        ApiResponse response = api.updateAccount("", DefaultConfigurations.getUserPrivateKey(),
                "Gringotts Checking", "default");

        assertEquals(400, response.getStatusCode());
        BadRequestResponse parsedResponse = (BadRequestResponse) response.getData();
        assertFalse(parsedResponse.getSuccess());
        assertEquals("FAILURE", parsedResponse.getStatus());
    }
}
