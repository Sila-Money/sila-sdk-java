package com.silamoney.client.tests;

import com.silamoney.client.api.ApiResponse;
import com.silamoney.client.api.SilaApi;
import com.silamoney.client.domain.GetVirtualAccountsResponse;
import com.silamoney.client.testsutils.DefaultConfigurations;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class GetVirtualAccountsTests {
    SilaApi api = new SilaApi(DefaultConfigurations.host, DefaultConfigurations.appHandle,
            DefaultConfigurations.privateKey);

    @Test
    public void Response200() throws Exception {
        ApiResponse response = api.getVirtualAccounts(DefaultConfigurations.getUserHandle(),
                DefaultConfigurations.getUserPrivateKey());
        assertEquals(200, response.getStatusCode());
        GetVirtualAccountsResponse parsedResponse = (GetVirtualAccountsResponse) response.getData();
        assertEquals("SUCCESS", parsedResponse.getStatus());
        assertTrue(parsedResponse.getVirtualAccounts().size()>0);
        DefaultConfigurations.setVirtualAccounts(parsedResponse.getVirtualAccounts());

    }

    @Test
    public void Response400() throws Exception {

        ApiResponse response = api.getVirtualAccounts("",
                DefaultConfigurations.getUserPrivateKey());
        assertEquals(400, response.getStatusCode());
    }

}