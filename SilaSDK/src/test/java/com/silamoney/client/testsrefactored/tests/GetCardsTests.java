package com.silamoney.client.testsrefactored.tests;

import com.silamoney.client.api.ApiResponse;
import com.silamoney.client.testsutils.DefaultConfigurations;
import com.silamoney.clientrefactored.configuration.Environment;
import com.silamoney.clientrefactored.configuration.SilaApi;
import com.silamoney.clientrefactored.endpoints.cards.getcards.GetCards;
import com.silamoney.clientrefactored.endpoints.cards.getcards.GetCardsRequest;
import com.silamoney.clientrefactored.endpoints.cards.getcards.GetCardsResponse;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class GetCardsTests {

    @BeforeClass
    public static void init() {
        SilaApi.init(Environment.SANDBOX, DefaultConfigurations.appHandle,
                DefaultConfigurations.privateKey);
    }

    @AfterClass
    public static void dispose() {
        SilaApi.dispose();
    }

    @Test
    public void Response200() throws Exception {

        GetCardsRequest request = GetCardsRequest.builder()
                .userHandle(DefaultConfigurations.getUserHandle())
                .userPrivateKey(DefaultConfigurations.getUserPrivateKey())
                .build();

        ApiResponse response = GetCards.send(request);
        assertEquals(200, response.getStatusCode());
        GetCardsResponse parsedResponse = (GetCardsResponse) response.getData();
        assertEquals("SUCCESS", parsedResponse.getStatus());
        assertTrue(parsedResponse.getCards().size() > 0);
    }

}
