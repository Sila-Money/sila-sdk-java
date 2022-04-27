package com.silamoney.client.tests;

import com.silamoney.client.api.ApiResponse;
import com.silamoney.client.api.SilaApi;
import com.silamoney.client.domain.LinkCardResponse;
import com.silamoney.client.testsutils.CardTokenHelper;
import com.silamoney.client.testsutils.DefaultConfigurations;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @author Aditi
 */
public class LinkCardTests {

    SilaApi api = new SilaApi(DefaultConfigurations.host, DefaultConfigurations.appHandle,
            DefaultConfigurations.privateKey);

    @Test
    public void Response200Success() throws Exception {
        ApiResponse response = api.linkCard(DefaultConfigurations.getUserHandle(),
                DefaultConfigurations.getUserPrivateKey(), CardTokenHelper.getToken(), "visa", "12345");

        assertEquals(200, response.getStatusCode());
        LinkCardResponse parsedResponse = (LinkCardResponse) response.getData();
        assertEquals("SUCCESS", parsedResponse.getStatus());
    }
}