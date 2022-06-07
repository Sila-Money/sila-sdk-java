package com.silamoney.client.tests;

import com.silamoney.client.api.ApiResponse;
import com.silamoney.client.api.SilaApi;
import com.silamoney.client.domain.*;
import com.silamoney.client.testsutils.DefaultConfigurations;
import org.junit.Test;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.*;

public class ApproveWireTests {
    SilaApi api = new SilaApi(DefaultConfigurations.host, DefaultConfigurations.appHandle,
            DefaultConfigurations.privateKey);

    @Test
    public void Response200Approved() throws IOException, InterruptedException {


        AccountTransactionMessage redeem = AccountTransactionMessage.builder()
                .userHandle(DefaultConfigurations.getUser2Handle())
                .userPrivateKey(DefaultConfigurations.getUser2PrivateKey()).amount(11000).processingType(ProcessingTypeEnum.WIRE)
                .businessUuid(DefaultConfigurations.correctUuidForWire)
                .mockWireAccountName("mock_account_success")
                .accountName("default").build();
       ApiResponse response = api.redeemSila(redeem);

        assertEquals(200, response.getStatusCode());
        TransactionResponse parsedResponse = (TransactionResponse) response.getData();
        assertTrue(parsedResponse.getSuccess());
        assertEquals("SUCCESS", parsedResponse.getStatus());
        assertNotNull(((TransactionResponse) response.getData()).getTransactionId());
        String transactionId=((TransactionResponse) response.getData()).getTransactionId();
        SearchFilters filters = new SearchFilters();
        filters.setTransactionId(transactionId);
        TimeUnit.SECONDS.sleep(20);
        response = api.getTransactions(DefaultConfigurations.getUser2Handle(), filters,
                DefaultConfigurations.getUser2PrivateKey());
        while (!((GetTransactionsResponse) response.getData()).transactions.get(0).status.equals("pending")) {
            TimeUnit.SECONDS.sleep(20);
            response = api.getTransactions(DefaultConfigurations.getUser2Handle(), filters,
                    DefaultConfigurations.getUser2PrivateKey());
        }
        response = api.approveWire(DefaultConfigurations.getUser2Handle(),
                DefaultConfigurations.getUser2PrivateKey(), transactionId, true,"approve wire success","mock_account_success");
        assertEquals("SUCCESS", ((BaseResponse) response.getData()).getStatus());
    }
}
