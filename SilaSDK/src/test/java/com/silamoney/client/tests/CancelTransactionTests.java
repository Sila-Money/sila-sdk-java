package com.silamoney.client.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

import com.silamoney.client.api.ApiResponse;
import com.silamoney.client.api.SilaApi;
import com.silamoney.client.domain.*;
import com.silamoney.client.testsutils.DefaultConfigurations;

import org.junit.Test;

public class CancelTransactionTests {
        SilaApi api = new SilaApi(DefaultConfigurations.host, DefaultConfigurations.appHandle,
                        DefaultConfigurations.privateKey);

        @Test
        public void Response200Success() throws Exception {
                AccountTransactionMessage redeem = AccountTransactionMessage.builder()
                                .userHandle(DefaultConfigurations.getUserHandle())
                                .userPrivateKey(DefaultConfigurations.getUserPrivateKey()).amount(200)
                                .accountName("default").build();
                ApiResponse response = api.issueSila(redeem);
                assertEquals(200, response.getStatusCode());
                TimeUnit.SECONDS.sleep(2);
                String transactionId = ((TransactionResponse) response.getData()).getTransactionId();
                CancelTransactionMessage message = CancelTransactionMessage.builder()
                                .userHandle(DefaultConfigurations.getUserHandle())
                                .userPrivateKey(DefaultConfigurations.getUserPrivateKey()).transactionId(transactionId)
                                .build();
                response = api.cancelTransaction(message);
                assertEquals(200, response.getStatusCode());
                BaseResponse parsedResponse = (BaseResponse) response.getData();
                assertTrue(parsedResponse.getSuccess());
                assertEquals("SUCCESS", parsedResponse.getStatus());
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
        }

        public void Response403() throws Exception {
                SilaApi badApi = new SilaApi(DefaultConfigurations.host, DefaultConfigurations.appHandle,
                                "3a1076bf45ab87712ad64ccb3b10217737f7faacbf2872e88fdd9a537d8fe266");
                CancelTransactionMessage message = CancelTransactionMessage.builder()
                                .userHandle(DefaultConfigurations.getUserHandle())
                                .userPrivateKey(DefaultConfigurations.getUserPrivateKey())
                                .transactionId(UUID.randomUUID().toString()).build();
                ApiResponse response = badApi.cancelTransaction(message);
                assertEquals(403, response.getStatusCode());
                BaseResponse parsedResponse = (BaseResponse) response.getData();
                assertFalse(response.getSuccess());
                assertEquals("FAILURE", parsedResponse.getStatus());
        }

        public void Response404() throws Exception {
                CancelTransactionMessage message = CancelTransactionMessage.builder()
                                .userHandle(DefaultConfigurations.getUserHandle())
                                .userPrivateKey(DefaultConfigurations.getUserPrivateKey())
                                .transactionId(UUID.randomUUID().toString()).build();
                ApiResponse response = api.cancelTransaction(message);
                assertEquals(404, response.getStatusCode());
                BaseResponse parsedResponse = (BaseResponse) response.getData();
                assertFalse(response.getSuccess());
                assertEquals("FAILURE", parsedResponse.getStatus());
        }
}
