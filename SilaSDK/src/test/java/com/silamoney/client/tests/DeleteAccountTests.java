package com.silamoney.client.tests;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import com.silamoney.client.api.ApiResponse;
import com.silamoney.client.api.SilaApi;
import com.silamoney.client.domain.BaseResponse;
import com.silamoney.client.domain.DeleteAccountResponse;
import com.silamoney.client.testsutils.DefaultConfigurations;

import org.junit.Test;

public class DeleteAccountTests {

    SilaApi api = new SilaApi(DefaultConfigurations.host, DefaultConfigurations.appHandle,
            DefaultConfigurations.privateKey);

    @Test
    public void Response200() throws IOException, InterruptedException {

        ApiResponse response = api.deleteAccount(DefaultConfigurations.getUserHandle(), "defaultunlink",
                DefaultConfigurations.getUserPrivateKey());

        assertEquals(200, response.getStatusCode());
        assertEquals("defaultunlink", ((DeleteAccountResponse) response.getData()).getAccountName());
    }

}
