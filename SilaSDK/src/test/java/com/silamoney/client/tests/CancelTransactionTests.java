package com.silamoney.client.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.UUID;

import static org.hamcrest.Matchers.*;

import com.silamoney.client.api.ApiResponse;
import com.silamoney.client.api.SilaApi;
import com.silamoney.client.domain.AccountTransactionMessage;
import com.silamoney.client.domain.BadRequestResponse;
import com.silamoney.client.domain.BaseResponse;
import com.silamoney.client.domain.CancelTransactionMessage;
import com.silamoney.client.domain.TransactionResponse;
import com.silamoney.client.testsutils.DefaultConfigurations;

import org.junit.Test;

public class CancelTransactionTests {
    SilaApi api = new SilaApi(DefaultConfigurations.host, DefaultConfigurations.appHandle,
            DefaultConfigurations.privateKey);

    @Test
    public void Response200Success() throws Exception {
        AccountTransactionMessage redeem = AccountTransactionMessage.builder()
                .userHandle(DefaultConfigurations.getUserHandle())
                .userPrivateKey(DefaultConfigurations.getUserPrivateKey()).amount(200).accountName("default").build();
        ApiResponse response = api.issueSila(redeem);
        assertEquals(200, response.getStatusCode());
        String transactionId = ((TransactionResponse) response.getData()).getTransactionId();
        CancelTransactionMessage message = CancelTransactionMessage.builder()
                .userHandle(DefaultConfigurations.getUserHandle())
                .userPrivateKey(DefaultConfigurations.getUserPrivateKey()).transactionId(transactionId).build();
        response = api.cancelTransaction(message);
        assertEquals(200, response.getStatusCode());
        BaseResponse parsedResponse = (BaseResponse) response.getData();
        assertTrue(parsedResponse.getSuccess());
        assertEquals("SUCCESS", parsedResponse.getStatus());
        assertThat("cancel transaction - message", parsedResponse.getMessage(),
                stringContainsInOrder(Arrays.asList(String.format("Transaction %s has been canceled", transactionId))));
    }

    @Test
    public void Response400() throws Exception {
        CancelTransactionMessage message = CancelTransactionMessage.builder()
                .userHandle(DefaultConfigurations.getUserHandle())
                .userPrivateKey(DefaultConfigurations.getUserPrivateKey()).build();
        ApiResponse response = api.cancelTransaction(message);
        assertEquals(400, response.getStatusCode());
        BadRequestResponse parsedResponse = (BadRequestResponse) response.getData();
        assertFalse(response.getSuccess());
        assertEquals("FAILURE", parsedResponse.getStatus());
        assertThat("cancel transaction - bad request", parsedResponse.getMessage(),
                stringContainsInOrder(Arrays.asList("Bad request")));
    }

    public void Response403() throws Exception {
        SilaApi badApi = new SilaApi(DefaultConfigurations.host, DefaultConfigurations.appHandle,
                "3a1076bf45ab87712ad64ccb3b10217737f7faacbf2872e88fdd9a537d8fe266");
        CancelTransactionMessage message = CancelTransactionMessage.builder()
                .userHandle(DefaultConfigurations.getUserHandle())
                .userPrivateKey(DefaultConfigurations.getUserPrivateKey()).transactionId(UUID.randomUUID().toString())
                .build();
        ApiResponse response = badApi.cancelTransaction(message);
        assertEquals(403, response.getStatusCode());
        BaseResponse parsedResponse = (BaseResponse) response.getData();
        assertFalse(response.getSuccess());
        assertEquals("FAILURE", parsedResponse.getStatus());
        assertThat("document types - bad signature", parsedResponse.getMessage(),
                stringContainsInOrder(Arrays.asList("Failed to authenticate app signature.")));
    }

    public void Response404() throws Exception {
        CancelTransactionMessage message = CancelTransactionMessage.builder()
                .userHandle(DefaultConfigurations.getUserHandle())
                .userPrivateKey(DefaultConfigurations.getUserPrivateKey()).transactionId(UUID.randomUUID().toString())
                .build();
        ApiResponse response = api.cancelTransaction(message);
        assertEquals(404, response.getStatusCode());
        BaseResponse parsedResponse = (BaseResponse) response.getData();
        assertFalse(response.getSuccess());
        assertEquals("FAILURE", parsedResponse.getStatus());
    }
}
