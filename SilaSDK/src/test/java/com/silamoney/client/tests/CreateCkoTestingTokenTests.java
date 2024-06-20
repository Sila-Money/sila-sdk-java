package com.silamoney.client.tests;

import com.silamoney.client.api.ApiResponse;
import com.silamoney.client.api.SilaApi;
import com.silamoney.client.domain.CreateCkoTestingTokenMessage;
import com.silamoney.client.domain.CreateCkoTestingTokenResponse;
import com.silamoney.client.testsutils.DefaultConfigurations;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class CreateCkoTestingTokenTests {
    SilaApi api = new SilaApi(DefaultConfigurations.host, DefaultConfigurations.appHandle,
            DefaultConfigurations.privateKey);

    @Test
    public void Response200() throws Exception {
        CreateCkoTestingTokenMessage message= CreateCkoTestingTokenMessage.builder()
                .userHandle(DefaultConfigurations.getUser5Handle())
                .userPrivateKey(DefaultConfigurations.getUser5PrivateKey())
                .cardNumber("4659105569051157")
                .expiryMonth(12)
                .expiryYear(2027)
                //.cvv(956)
                .ckoPublicKey(DefaultConfigurations.ckoPublicKey).build();
        ApiResponse response = api.createCkoTestingToken(message);
        assertEquals(200, response.getStatusCode());
        CreateCkoTestingTokenResponse parsedResponse = (CreateCkoTestingTokenResponse) response.getData();
        assertEquals("SUCCESS", parsedResponse.getStatus());
        assertNotNull(parsedResponse.getToken());
        DefaultConfigurations.setCkoToken(parsedResponse.getToken());

    }
}
