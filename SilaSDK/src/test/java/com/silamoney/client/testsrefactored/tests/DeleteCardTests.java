package com.silamoney.client.testsrefactored.tests;

import com.silamoney.client.api.ApiResponse;
import com.silamoney.client.testsutils.DefaultConfigurations;
import com.silamoney.clientrefactored.configuration.Environment;
import com.silamoney.clientrefactored.configuration.SilaApi;
import com.silamoney.clientrefactored.endpoints.cards.deletecard.DeleteCard;
import com.silamoney.clientrefactored.endpoints.cards.deletecard.DeleteCardRequest;
import com.silamoney.clientrefactored.endpoints.cards.deletecard.DeleteCardResponse;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class DeleteCardTests {

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

        DeleteCardRequest request = DeleteCardRequest.builder()
                .userHandle(DefaultConfigurations.getUserHandle())
                .userPrivateKey(DefaultConfigurations.getUserPrivateKey())
                .cardName("visa")
                .build();

        ApiResponse response = DeleteCard.send(request);
        DeleteCardResponse parsedResponse = (DeleteCardResponse) response.getData();
        assertEquals("SUCCESS", parsedResponse.getStatus());
    }

}
