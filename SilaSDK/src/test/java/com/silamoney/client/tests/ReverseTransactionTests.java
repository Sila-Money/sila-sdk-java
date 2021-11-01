package com.silamoney.client.tests;

import com.silamoney.client.api.ApiResponse;
import com.silamoney.client.api.SilaApi;
import com.silamoney.client.domain.*;
import com.silamoney.client.testsutils.DefaultConfigurations;
import org.junit.Test;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.*;

public class ReverseTransactionTests {

    SilaApi api = new SilaApi(DefaultConfigurations.host, DefaultConfigurations.appHandle,
            DefaultConfigurations.privateKey);

    @Test
    public void Response200() throws IOException, InterruptedException {
        AccountTransactionMessage issue = AccountTransactionMessage.builder()
                .userHandle(DefaultConfigurations.getUserHandle())
                .userPrivateKey(DefaultConfigurations.getUserPrivateKey()).amount(1000).cardName("visa")
                .descriptor("test descriptor").businessUuid(DefaultConfigurations.correctUuid).build();
        ApiResponse response = api.issueSila(issue);
        assertEquals(200, response.getStatusCode());
        assertTrue(((TransactionResponse) response.getData()).getSuccess());
        assertEquals("test descriptor", ((TransactionResponse) response.getData()).getDescriptor());
        assertEquals("SUCCESS", ((TransactionResponse) response.getData()).getStatus());
        assertNotNull(((TransactionResponse) response.getData()).getTransactionId());
        String transactionId = ((TransactionResponse) response.getData()).getTransactionId();

        SearchFilters filters = new SearchFilters();
        filters.setTransactionId(transactionId);
        response = api.getTransactions(DefaultConfigurations.getUserHandle(), filters,
                DefaultConfigurations.getUserPrivateKey());
        while (((GetTransactionsResponse) response.getData()).transactions.get(0).status.equals("queued")
                || ((GetTransactionsResponse) response.getData()).transactions.get(0).status
                .equals("pending")) {
            TimeUnit.SECONDS.sleep(20);
            response = api.getTransactions(DefaultConfigurations.getUserHandle(), filters,
                    DefaultConfigurations.getUserPrivateKey());
        }
        assertEquals("success", ((GetTransactionsResponse) response.getData()).transactions.get(0).status);
        response = api.reverseTransaction(DefaultConfigurations.getUserHandle(),
                DefaultConfigurations.getUserPrivateKey(), transactionId);
        assertEquals("SUCCESS", ((BaseResponse)response.getData()).getStatus());
    }

}
