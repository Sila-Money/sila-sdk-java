package com.silamoney.client.tests;

import com.silamoney.client.api.ApiResponse;
import com.silamoney.client.api.SilaApi;
import com.silamoney.client.domain.*;
import com.silamoney.client.testsutils.DefaultConfigurations;
import org.junit.Test;

import java.lang.reflect.Field;
import java.util.UUID;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 * @author Anuj Kalal
 */
public class RefundDebitCardTests {
    SilaApi api = new SilaApi(DefaultConfigurations.host, DefaultConfigurations.appHandle,
            DefaultConfigurations.privateKey);

    @Test
    public void Response200Success() throws Exception {
        String transactionId = String.valueOf(UUID.randomUUID());
        ApiResponse apiResponseMock = mock(ApiResponse.class);
        RefundDebitCardResponse mockedApiResponse = new RefundDebitCardResponse();
        Field transactionIdField = RefundDebitCardResponse.class.getDeclaredField("transactionId");
        transactionIdField.setAccessible(true);
        transactionIdField.set(mockedApiResponse, transactionId);
        when(apiResponseMock.getData()).thenReturn(mockedApiResponse);
        when(apiResponseMock.getStatusCode()).thenReturn(202);
        mockedApiResponse.setSuccess(true);

        SilaApi apiMock = mock(SilaApi.class);
        when(apiMock.refundDebitCard(any())).thenReturn(apiResponseMock);

        RefundDebitCardMessage refundDebitCardMessage = RefundDebitCardMessage.builder()
                .userHandle(DefaultConfigurations.getUser5Handle())
                .userPrivateKey(DefaultConfigurations.getUser5PrivateKey()).transactionId(transactionId).build();
        ApiResponse response = apiMock.refundDebitCard(refundDebitCardMessage);
        assertEquals(202, response.getStatusCode());
        RefundDebitCardResponse parsedResponse = (RefundDebitCardResponse) response.getData();
        assertTrue(((RefundDebitCardResponse) response.getData()).getSuccess());
        assertEquals(transactionId, parsedResponse.getTransactionId());
    }

    @Test
    public void Response400() throws Exception {

        RefundDebitCardMessage refundDebitCardMessage = RefundDebitCardMessage.builder()
                .userHandle("")
                .userPrivateKey(DefaultConfigurations.getUser5PrivateKey())
                .transactionId(null).build();
        ApiResponse  response = api.refundDebitCard(refundDebitCardMessage);
        assertEquals(400, response.getStatusCode());
        BadRequestResponse parsedResponse = (BadRequestResponse) response.getData();
        assertFalse(parsedResponse.getSuccess());
        assertEquals("FAILURE", parsedResponse.getStatus());
    }
}
