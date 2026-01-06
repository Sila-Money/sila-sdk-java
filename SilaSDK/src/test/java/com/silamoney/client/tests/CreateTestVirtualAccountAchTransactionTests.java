package com.silamoney.client.tests;

import com.silamoney.client.api.ApiResponse;
import com.silamoney.client.api.SilaApi;
import com.silamoney.client.domain.BaseResponse;
import com.silamoney.client.testsutils.DefaultConfigurations;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CreateTestVirtualAccountAchTransactionTests {
    SilaApi api = new SilaApi(DefaultConfigurations.host, DefaultConfigurations.appHandle,
            DefaultConfigurations.privateKey);

    @Test
    public void Response200() throws Exception {
        Date date = Date.from(
                LocalDate.of(1990, 1, 31).atStartOfDay(ZoneId.systemDefault()).toInstant()
        );
        ApiResponse response = api.createTestVirtualAccountAchTransaction(DefaultConfigurations.getUserHandle(), DefaultConfigurations.getUserPrivateKey(), 50, DefaultConfigurations.getVirtualAccounts().get(0).getAccountNumber(), date, 22, "entityName", "PAYROLL", "SILA INC");
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
