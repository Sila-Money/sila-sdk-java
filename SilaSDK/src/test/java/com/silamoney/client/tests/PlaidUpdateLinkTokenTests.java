package com.silamoney.client.tests;

import static org.junit.Assert.assertNotNull;

import com.silamoney.client.api.ApiResponse;
import com.silamoney.client.api.SilaApi;
import com.silamoney.client.domain.PlaidUpdateLinkTokenRequest;
import com.silamoney.client.domain.PlaidUpdateLinkTokenResponse;
import com.silamoney.client.testsutils.DefaultConfigurations;

import org.junit.Test;

public class PlaidUpdateLinkTokenTests {

    SilaApi api = new SilaApi(DefaultConfigurations.host, DefaultConfigurations.appHandle,
            DefaultConfigurations.privateKey);

    @Test
    public void Response200() throws Exception {

        PlaidUpdateLinkTokenRequest request = PlaidUpdateLinkTokenRequest.builder().accountName("defaultpt")
                .userHandle(DefaultConfigurations.getUserHandle()).build();

        ApiResponse response = api.plaidUpdateLinkToken(request);

        PlaidUpdateLinkTokenResponse parsedResponse = (PlaidUpdateLinkTokenResponse) response.getData();

        assertNotNull(parsedResponse.getStatus());
        assertNotNull(parsedResponse.getMessage());

    }

}
