package com.silamoney.client.tests;

import com.silamoney.client.api.ApiResponse;
import com.silamoney.client.api.SilaApi;
import com.silamoney.client.domain.*;
import com.silamoney.client.testsutils.DefaultConfigurations;
import org.junit.Test;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class MockWireOutFileTests {
    SilaApi api = new SilaApi(DefaultConfigurations.host, DefaultConfigurations.appHandle,
            DefaultConfigurations.privateKey);

    @Test
    public void Response200() throws IOException, InterruptedException {
        AccountTransactionMessage redeem = AccountTransactionMessage.builder()
                .userHandle(DefaultConfigurations.getUser2Handle())
                .userPrivateKey(DefaultConfigurations.getUser2PrivateKey()).amount(200).processingType(ProcessingTypeEnum.WIRE)
                .businessUuid(DefaultConfigurations.correctUuid)
                .accountName("default").build();
        ApiResponse response = api.redeemSila(redeem);

        assertEquals(200, response.getStatusCode());
        TransactionResponse parsedResponse = (TransactionResponse) response.getData();
        assertTrue(parsedResponse.getSuccess());
        assertEquals("SUCCESS", parsedResponse.getStatus());
        String transactionId = parsedResponse.getTransactionId();
        SearchFilters filters = new SearchFilters();
        filters.setTransactionId(transactionId);
        TimeUnit.SECONDS.sleep(30);
        response = api.getTransactions(DefaultConfigurations.getUser2Handle(), filters,
                DefaultConfigurations.getUser2PrivateKey());
        while (((GetTransactionsResponse) response.getData()).transactions.get(0).status.equals("success")) {
            TimeUnit.SECONDS.sleep(20);
            response = api.getTransactions(DefaultConfigurations.getUser2Handle(), filters,
                    DefaultConfigurations.getUser2PrivateKey());
        }
        response = api.mockWireOutFile(DefaultConfigurations.getUser2Handle(),
                DefaultConfigurations.getUser2PrivateKey(), transactionId, "PR");
        assertEquals(200, response.getStatusCode());
    }
}
