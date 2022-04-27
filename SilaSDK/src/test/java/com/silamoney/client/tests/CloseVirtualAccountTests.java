package com.silamoney.client.tests;

import com.silamoney.client.api.ApiResponse;
import com.silamoney.client.api.SilaApi;
import com.silamoney.client.domain.VirtualAccountResponse;
import com.silamoney.client.testsutils.DefaultConfigurations;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;

public class CloseVirtualAccountTests {
    SilaApi api = new SilaApi(DefaultConfigurations.host, DefaultConfigurations.appHandle,
            DefaultConfigurations.privateKey);

    @Test
    public void Response200() throws Exception {
        ApiResponse response = api.openVirtualAccount(DefaultConfigurations.getUserHandle(),
                DefaultConfigurations.getUserPrivateKey(),"virtual_account_2");
        assertEquals(200, response.getStatusCode());
        VirtualAccountResponse parsedResponse = (VirtualAccountResponse) response.getData();
        assertEquals("SUCCESS", parsedResponse.getStatus());
        String virtualAccountId=parsedResponse.getVirtualAccount().getVirtualAccountId();
        String accountNumber=parsedResponse.getVirtualAccount().getAccountNumber();
        TimeUnit.SECONDS.sleep(4);
         response = api.closeVirtualAccount(DefaultConfigurations.getUserHandle(),
                DefaultConfigurations.getUserPrivateKey(), virtualAccountId,accountNumber);
        assertEquals(200, response.getStatusCode());
         parsedResponse = (VirtualAccountResponse) response.getData();
        assertEquals("SUCCESS", parsedResponse.getStatus());
    }

}
