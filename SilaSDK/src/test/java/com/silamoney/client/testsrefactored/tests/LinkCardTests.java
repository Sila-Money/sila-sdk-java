package com.silamoney.client.testsrefactored.tests;

import com.silamoney.client.api.ApiResponse;
import com.silamoney.client.testsutils.CardTokenHelper;
import com.silamoney.client.testsutils.DefaultConfigurations;
import com.silamoney.clientrefactored.configuration.Environment;
import com.silamoney.clientrefactored.configuration.SilaApi;
import com.silamoney.clientrefactored.endpoints.cards.linkcard.LinkCard;
import com.silamoney.clientrefactored.endpoints.cards.linkcard.LinkCardRequest;
import com.silamoney.clientrefactored.endpoints.cards.linkcard.LinkCardResponse;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class LinkCardTests {

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

		LinkCardRequest request = LinkCardRequest.builder()
            .cardName("visa")
            .token(CardTokenHelper.getToken())
            .userHandle(DefaultConfigurations.getUserHandle())
            .userPrivateKey(DefaultConfigurations.getUserPrivateKey())
            .accountPostalCode("12345")
            .build();

        ApiResponse response = LinkCard.send(request);
        LinkCardResponse parsedResponse = (LinkCardResponse) response.getData();

        assertEquals("SUCCESS", parsedResponse.getStatus());
        assertNotNull(parsedResponse.getReference());

	}


    
}
