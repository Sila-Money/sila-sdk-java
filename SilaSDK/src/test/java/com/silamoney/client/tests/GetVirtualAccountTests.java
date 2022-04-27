package com.silamoney.client.tests;

import com.silamoney.client.api.ApiResponse;
import com.silamoney.client.api.SilaApi;
import com.silamoney.client.domain.GetVirtualAccountResponse;
import com.silamoney.client.domain.VirtualAccount;
import com.silamoney.client.testsutils.DefaultConfigurations;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class GetVirtualAccountTests {
    SilaApi api = new SilaApi(DefaultConfigurations.host, DefaultConfigurations.appHandle,
            DefaultConfigurations.privateKey);
    VirtualAccount virtualAccount = DefaultConfigurations.getVirtualAccounts().get(0);

    @Test
    public void Response200() throws Exception {

        ApiResponse response = api.getVirtualAccount(DefaultConfigurations.getUserHandle(),
                DefaultConfigurations.getUserPrivateKey(), virtualAccount.getVirtualAccountId());
        assertEquals(200, response.getStatusCode());
        GetVirtualAccountResponse parsedResponse = (GetVirtualAccountResponse) response.getData();
        assertEquals("SUCCESS", parsedResponse.getStatus());
        assertNotNull(parsedResponse.getVirtualAccount());
        assertNotNull(parsedResponse.getVirtualAccount().getAccountType());
        assertNotNull(parsedResponse.getVirtualAccount().getAccountNumber());
        assertNotNull(parsedResponse.getVirtualAccount().getVirtualAccountName());
        assertEquals(virtualAccount.getVirtualAccountId(), parsedResponse.getVirtualAccount().getVirtualAccountId());


    }

    @Test
    public void Response400() throws Exception {

        ApiResponse response = api.getVirtualAccount("",
                DefaultConfigurations.getUserPrivateKey(), "");
        assertEquals(400, response.getStatusCode());
    }
}