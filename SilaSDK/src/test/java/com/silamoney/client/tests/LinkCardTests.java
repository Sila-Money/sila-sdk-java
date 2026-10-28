package com.silamoney.client.tests;

import com.silamoney.client.api.ApiResponse;
import com.silamoney.client.api.SilaApi;
import com.silamoney.client.domain.BadRequestResponse;
import com.silamoney.client.domain.LinkCardResponse;
import com.silamoney.client.testsutils.DefaultConfigurations;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

/**
 * @author Aditi
 */
public class LinkCardTests {

    SilaApi api = new SilaApi(DefaultConfigurations.host, DefaultConfigurations.appHandle,
            DefaultConfigurations.privateKey);

    @Test
    public void Response200SuccessWithCKO() throws Exception {
        ApiResponse response = api.linkCard(DefaultConfigurations.getUser5Handle(),
                DefaultConfigurations.getUser5PrivateKey(), DefaultConfigurations.getCkoToken(), DefaultConfigurations.getCardName(), null,"cko");

        assertEquals(200, response.getStatusCode());
        LinkCardResponse parsedResponse = (LinkCardResponse) response.getData();
        assertEquals("SUCCESS", parsedResponse.getStatus());
    }


    @Test
    public void Response400EmptyHandle() throws Exception {
        ApiResponse response = api.linkCard("", DefaultConfigurations.getUser5PrivateKey(),
                DefaultConfigurations.getCkoToken(), DefaultConfigurations.getCardName(), null, "cko");

        assertEquals(400, response.getStatusCode());
        BadRequestResponse parsedResponse = (BadRequestResponse) response.getData();
        assertFalse(parsedResponse.getSuccess());
        assertEquals("FAILURE", parsedResponse.getStatus());
    }
}