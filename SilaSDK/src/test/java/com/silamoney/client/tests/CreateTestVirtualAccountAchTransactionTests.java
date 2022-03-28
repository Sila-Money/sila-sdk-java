package com.silamoney.client.tests;

import com.silamoney.client.api.ApiResponse;
import com.silamoney.client.api.SilaApi;
import com.silamoney.client.domain.BaseResponse;
import com.silamoney.client.testsutils.DefaultConfigurations;
import org.joda.time.LocalDate;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CreateTestVirtualAccountAchTransactionTests {
    SilaApi api = new SilaApi(DefaultConfigurations.host, DefaultConfigurations.appHandle,
            DefaultConfigurations.privateKey);

    @Test
    public void Response200() throws Exception {
        LocalDate date = new LocalDate(1900, 01, 31);
        ApiResponse response = api.createTestVirtualAccountAchTransaction(DefaultConfigurations.getUserHandle(), DefaultConfigurations.getUserPrivateKey(), 50, DefaultConfigurations.getVirtualAccounts().get(0).getAccountNumber(), date.toDate(), 22, "entityName", "PAYROLL", "SILA INC");
        assertEquals(200, response.getStatusCode());
        BaseResponse parsedResponse = (BaseResponse) response.getData();
        assertEquals("SUCCESS", parsedResponse.getStatus());


    }
    @Test
    public void Response200WithOptional() throws Exception {
        ApiResponse response = api.createTestVirtualAccountAchTransaction(DefaultConfigurations.getUserHandle(), DefaultConfigurations.getUserPrivateKey(), 50, DefaultConfigurations.getVirtualAccounts().get(0).getAccountNumber(), 22, "entityName");
        assertEquals(200, response.getStatusCode());
        BaseResponse parsedResponse = (BaseResponse) response.getData();
        assertEquals("SUCCESS", parsedResponse.getStatus());
    }
}
