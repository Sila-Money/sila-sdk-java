package com.silamoney.client.tests;

import com.silamoney.client.api.ApiResponse;
import com.silamoney.client.api.SilaApi;
import com.silamoney.client.domain.VirtualAccount;
import com.silamoney.client.domain.VirtualAccountResponse;
import com.silamoney.client.testsutils.DefaultConfigurations;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class UpdateVirtualAccountTests {
    SilaApi api = new SilaApi(DefaultConfigurations.host, DefaultConfigurations.appHandle,
            DefaultConfigurations.privateKey);


    @Test
    public void Register200UpdateVirtualAccount() throws Exception {
        VirtualAccount virtualAccount=DefaultConfigurations.getVirtualAccounts().get(0);
        ApiResponse response = api.updateVirtualAccount(DefaultConfigurations.getUserHandle(),
                DefaultConfigurations.getUserPrivateKey(), virtualAccount.getVirtualAccountId(), virtualAccount.getVirtualAccountName(), true);
        assertEquals(200, response.getStatusCode());
        VirtualAccountResponse parsedResponse = (VirtualAccountResponse) response.getData();

        assertNotNull(parsedResponse.getStatus());
        assertNotNull(parsedResponse.getMessage());

        //Suspend
        ApiResponse response1 = api.updateVirtualAccount(DefaultConfigurations.getUserHandle(),
                DefaultConfigurations.getUserPrivateKey(), virtualAccount.getVirtualAccountId(), virtualAccount.getVirtualAccountName(), false);
        assertEquals(200, response1.getStatusCode());
        VirtualAccountResponse parsedResponse1 = (VirtualAccountResponse) response1.getData();

        assertNotNull(parsedResponse1.getStatus());
        assertNotNull(parsedResponse1.getMessage());

        //UnSuspend
        ApiResponse response2 = api.updateVirtualAccount(DefaultConfigurations.getUserHandle(),
                DefaultConfigurations.getUserPrivateKey(), virtualAccount.getVirtualAccountId(), virtualAccount.getVirtualAccountName(), true);
        assertEquals(200, response2.getStatusCode());
        VirtualAccountResponse parsedResponse2 = (VirtualAccountResponse) response2.getData();

        assertNotNull(parsedResponse2.getStatus());
        assertNotNull(parsedResponse2.getMessage());
    }
    @Test
    public void Register200WithAchCreditEnabledAchDebitEnabled() throws Exception {
        VirtualAccount virtualAccount=DefaultConfigurations.getVirtualAccounts().get(0);
        ApiResponse response = api.updateVirtualAccount(DefaultConfigurations.getUserHandle(),
                DefaultConfigurations.getUserPrivateKey(), virtualAccount.getVirtualAccountId(), virtualAccount.getVirtualAccountName(), false,true,true);
        assertEquals(200, response.getStatusCode());
        VirtualAccountResponse parsedResponse = (VirtualAccountResponse) response.getData();

        assertNotNull(parsedResponse.getStatus());
        assertNotNull(parsedResponse.getMessage());
        response = api.updateVirtualAccount(DefaultConfigurations.getUserHandle(),
                DefaultConfigurations.getUserPrivateKey(), virtualAccount.getVirtualAccountId(), virtualAccount.getVirtualAccountName(), true);
        assertEquals(200, response.getStatusCode());
        parsedResponse = (VirtualAccountResponse) response.getData();

        assertNotNull(parsedResponse.getStatus());
        assertNotNull(parsedResponse.getMessage());
        System.out.println(parsedResponse.getVirtualAccount().isAchCreditEnabled()); // true/false
        System.out.println(parsedResponse.getVirtualAccount().isAchDebitEnabled());  // true/false
    }
}
