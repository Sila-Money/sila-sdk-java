package com.silamoney.client.tests;

import com.silamoney.client.api.ApiResponse;
import com.silamoney.client.api.SilaApi;
import com.silamoney.client.domain.VirtualAccountResponse;
import com.silamoney.client.testsutils.DefaultConfigurations;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class OpenVirtualAccountTests {
    SilaApi api = new SilaApi(DefaultConfigurations.host, DefaultConfigurations.appHandle,
            DefaultConfigurations.privateKey);

    @Test
    public void Response200() throws Exception {
        ApiResponse response = api.openVirtualAccount(DefaultConfigurations.getUserHandle(),
                DefaultConfigurations.getUserPrivateKey(),"virtual_account");
        assertEquals(200, response.getStatusCode());
        VirtualAccountResponse parsedResponse = (VirtualAccountResponse) response.getData();
        assertEquals("SUCCESS", parsedResponse.getStatus());
        assertEquals("virtual_account", parsedResponse.getVirtualAccount().getVirtualAccountName());

         response = api.openVirtualAccount(DefaultConfigurations.getUser2Handle(),
                DefaultConfigurations.getUser2PrivateKey(),"virtual_account");
        assertEquals(200, response.getStatusCode());
         parsedResponse = (VirtualAccountResponse) response.getData();
        assertEquals("SUCCESS", parsedResponse.getStatus());
        assertEquals("virtual_account", parsedResponse.getVirtualAccount().getVirtualAccountName());
        DefaultConfigurations.setVirtualAccounts2(parsedResponse.getVirtualAccount());;
    }
    @Test
    public void Response200WithAchCreditEnabledAchDebitEnabled()throws Exception{
        ApiResponse response = api.openVirtualAccount(DefaultConfigurations.getUserHandle(),
                DefaultConfigurations.getUserPrivateKey(),"virtual_account_default",true,true);
        assertEquals(200, response.getStatusCode());
        VirtualAccountResponse parsedResponse = (VirtualAccountResponse) response.getData();
        assertEquals("SUCCESS", parsedResponse.getStatus());
        assertEquals("virtual_account_default", parsedResponse.getVirtualAccount().getVirtualAccountName());
        assertEquals(true,parsedResponse.getVirtualAccount().isAchCreditEnabled());
        assertEquals(true,parsedResponse.getVirtualAccount().isAchDebitEnabled());
    }

}