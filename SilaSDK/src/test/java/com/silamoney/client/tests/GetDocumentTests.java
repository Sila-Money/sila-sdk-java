package com.silamoney.client.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;

import java.util.Arrays;
import java.util.UUID;

import static org.hamcrest.Matchers.*;

import com.silamoney.client.api.ApiResponse;
import com.silamoney.client.api.SilaApi;
import com.silamoney.client.domain.BadRequestResponse;
import com.silamoney.client.domain.BaseResponse;
import com.silamoney.client.domain.GetDocumentMessage;
import com.silamoney.client.testsutils.DefaultConfigurations;

import org.junit.Test;

public class GetDocumentTests {
    SilaApi api = new SilaApi(DefaultConfigurations.host, DefaultConfigurations.appHandle,
            DefaultConfigurations.privateKey);

    @Test
    public void Response200() throws Exception {
        GetDocumentMessage message = GetDocumentMessage.builder().userHandle(DefaultConfigurations.getUserHandle())
                .userPrivateKey(DefaultConfigurations.getUserPrivateKey())
                .documentId(DefaultConfigurations.getDocumentId()).build();
        ApiResponse response = api.getDocument(message);
        assertEquals(200, response.getStatusCode());
        assertEquals("image/png", response.getHeaders().get("Content-Type").get(0));
        String parsedResponse = (String) response.getData();
        assertThat(parsedResponse, notNullValue());
    }

    @Test
    public void Response400() throws Exception {
        GetDocumentMessage message = GetDocumentMessage.builder().userHandle("")
                .userPrivateKey(DefaultConfigurations.getUserPrivateKey()).build();
        ApiResponse response = api.getDocument(message);
        assertEquals(400, response.getStatusCode());
        BadRequestResponse parsedResponse = (BadRequestResponse) response.getData();
        assertFalse(parsedResponse.getSuccess());
        assertEquals("FAILURE", parsedResponse.getStatus());
        assertThat(parsedResponse.getMessage(), stringContainsInOrder(Arrays.asList("Bad request")));
    }

    @Test
    public void Response403() throws Exception {
        SilaApi badApi = new SilaApi(DefaultConfigurations.host, DefaultConfigurations.appHandle,
                "3a1076bf45ab87712ad64ccb3b10217737f7faacbf2872e88fdd9a537d8fe266");
        GetDocumentMessage message = GetDocumentMessage.builder().userHandle(DefaultConfigurations.getUserHandle())
                .userPrivateKey(DefaultConfigurations.getUserPrivateKey())
                .documentId(DefaultConfigurations.getDocumentId()).build();
        ApiResponse response = badApi.getDocument(message);
        assertEquals(403, response.getStatusCode());
        BaseResponse parsedResponse = (BaseResponse) response.getData();
        assertFalse(parsedResponse.getSuccess());
        assertEquals("FAILURE", parsedResponse.getStatus());
        assertThat(parsedResponse.getMessage(),
                stringContainsInOrder(Arrays.asList("Failed to authenticate app signature.")));
    }

    @Test
    public void Response404() throws Exception {
        GetDocumentMessage message = GetDocumentMessage.builder().userHandle(DefaultConfigurations.getUserHandle())
                .userPrivateKey(DefaultConfigurations.getUserPrivateKey()).documentId(UUID.randomUUID().toString())
                .build();
        ApiResponse response = api.getDocument(message);
        assertEquals(404, response.getStatusCode());
        BaseResponse parsedResponse = (BaseResponse) response.getData();
        assertFalse(parsedResponse.getSuccess());
        assertEquals("FAILURE", parsedResponse.getStatus());
    }
}
