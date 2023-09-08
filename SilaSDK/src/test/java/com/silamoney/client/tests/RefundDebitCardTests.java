package com.silamoney.client.tests;

import com.silamoney.client.api.ApiResponse;
import com.silamoney.client.api.SilaApi;
import com.silamoney.client.domain.*;
import com.silamoney.client.testsutils.DefaultConfigurations;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.*;

/**
 * @author Anuj Kalal
 */
public class RefundDebitCardTests {
    SilaApi api = new SilaApi(DefaultConfigurations.host, DefaultConfigurations.appHandle,
            DefaultConfigurations.privateKey);

    @Test
    public void Response200Success() throws Exception {
        AccountTransactionMessage issue = AccountTransactionMessage.builder()
                .userHandle(DefaultConfigurations.getUser5Handle())
                .userPrivateKey(DefaultConfigurations.getUser5PrivateKey()).amount(1000).cardName(DefaultConfigurations.getCardName())
                .build();
        ApiResponse response = api.issueSila(issue);
        assertEquals(200, response.getStatusCode());
        assertTrue(((TransactionResponse) response.getData()).getSuccess());
        assertNotNull(((TransactionResponse) response.getData()).getTransactionId());
        String transactionId = ((TransactionResponse) response.getData()).getTransactionId();

        SearchFilters filters = new SearchFilters();
        filters.setTransactionId(transactionId);
        response = api.getTransactions(DefaultConfigurations.getUser5Handle(), filters,
                DefaultConfigurations.getUser5PrivateKey());
        while (!((GetTransactionsResponse) response.getData()).transactions.get(0).status.equals("success")) {
            TimeUnit.SECONDS.sleep(20);
            response = api.getTransactions(DefaultConfigurations.getUser5Handle(), filters,
                    DefaultConfigurations.getUser5PrivateKey());
        }

        assertEquals("success", ((GetTransactionsResponse) response.getData()).transactions.get(0).status);
        RefundDebitCardMessage refundDebitCardMessage = RefundDebitCardMessage.builder()
                .userHandle(DefaultConfigurations.getUser5Handle())
                .userPrivateKey(DefaultConfigurations.getUser5PrivateKey()).transactionId(transactionId).build();
        response = api.refundDebitCard(refundDebitCardMessage);
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
