package com.silamoney.client.tests;

import com.silamoney.client.api.ApiResponse;
import com.silamoney.client.api.SilaApi;
import com.silamoney.client.domain.GetCardsResponse;
import com.silamoney.client.testsutils.DefaultConfigurations;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class GetCardsTests {

        SilaApi api = new SilaApi(DefaultConfigurations.host, DefaultConfigurations.appHandle,
                        DefaultConfigurations.privateKey);

        @Test
        public void Response200() throws Exception {
                ApiResponse response = api.getCards(DefaultConfigurations.getUserHandle(), DefaultConfigurations.getUserPrivateKey());
                assertEquals(200, response.getStatusCode());
                GetCardsResponse parsedResponse = (GetCardsResponse) response.getData();
                assertEquals("SUCCESS", parsedResponse.getStatus());
                assertTrue(parsedResponse.getCards().size()>0);
        }

}
