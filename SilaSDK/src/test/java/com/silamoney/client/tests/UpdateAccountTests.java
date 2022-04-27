package com.silamoney.client.tests;

import com.silamoney.client.api.ApiResponse;
import com.silamoney.client.api.SilaApi;
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

        //Freeze
        ApiResponse response1 = api.updateAccount(DefaultConfigurations.getUserHandle(),
                DefaultConfigurations.getUserPrivateKey(), "defaultupdated",false);

        UpdateAccountResponse parsedResponse1 = (UpdateAccountResponse) response1.getData();

        assertNotNull(parsedResponse1.getStatus());
        assertNotNull(parsedResponse1.getMessage());
        assertNotNull(parsedResponse1.getAccount().getAccountListStatus());
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
        ApiResponse response2 = api.updateAccount(DefaultConfigurations.getUserHandle(),
                DefaultConfigurations.getUserPrivateKey(), "defaultupdated", "defaultNew",true);

        UpdateAccountResponse parsedResponse2 = (UpdateAccountResponse) response2.getData();

        assertNotNull(parsedResponse2.getStatus());
        assertNotNull(parsedResponse2.getMessage());
        assertNotNull(parsedResponse2.getAccount().getAccountListStatus());
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
