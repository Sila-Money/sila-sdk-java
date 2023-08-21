package com.silamoney.client.tests;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import com.silamoney.client.api.ApiResponse;
import com.silamoney.client.api.SilaApi;
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

        response = api.deleteAccount(DefaultConfigurations.getUserHandle(), "defaultNew",
                DefaultConfigurations.getUserPrivateKey());

        assertEquals(200, response.getStatusCode());
        assertEquals("defaultNew", ((DeleteAccountResponse) response.getData()).getAccountName());

        response = api.deleteAccount(DefaultConfigurations.getUserHandle(), "defaultpt",
                DefaultConfigurations.getUserPrivateKey());

        assertEquals(200, response.getStatusCode());
        assertEquals("defaultpt", ((DeleteAccountResponse) response.getData()).getAccountName());

        response = api.deleteAccount(DefaultConfigurations.getUserHandle(), "default",
                DefaultConfigurations.getUserPrivateKey());

        assertEquals(200, response.getStatusCode());
        assertEquals("default", ((DeleteAccountResponse) response.getData()).getAccountName());

        response = api.deleteAccount(DefaultConfigurations.getUser2Handle(), "default",
                DefaultConfigurations.getUser2PrivateKey());

        assertEquals(200, response.getStatusCode());
        assertEquals("default", ((DeleteAccountResponse) response.getData()).getAccountName());

        response = api.deleteAccount(DefaultConfigurations.getUser4Handle(), "defaultptsardine",
                DefaultConfigurations.getUser4PrivateKey());

        assertEquals(200, response.getStatusCode());
        assertEquals("defaultptsardine", ((DeleteAccountResponse) response.getData()).getAccountName());
    }

}

