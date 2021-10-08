package com.silamoney.client.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.IOException;

import com.silamoney.client.api.ApiResponse;
import com.silamoney.client.api.SilaApi;
import com.silamoney.client.domain.PlaidLinkTokenResponse;
import com.silamoney.client.testsutils.DefaultConfigurations;

import org.junit.Test;

public class PlaidLinkTokenTests {

    SilaApi api = new SilaApi(DefaultConfigurations.host, DefaultConfigurations.appHandle,
            DefaultConfigurations.privateKey);

    @Test
    public void Response200() throws IOException, InterruptedException {

        ApiResponse response = api.plaidLinkToken(DefaultConfigurations.getUserHandle());

        assertEquals(200, response.getStatusCode());
        assertNotNull(((PlaidLinkTokenResponse)response.getData()).getLinkToken());
    }
    @Test
    public void Response200AndroidPackageName() throws IOException, InterruptedException {

        ApiResponse response = api.plaidLinkToken(DefaultConfigurations.getUserHandle(), "com.sila.package");

        assertEquals(200, response.getStatusCode());
        assertNotNull(((PlaidLinkTokenResponse) response.getData()).getLinkToken());
    }

}
