package com.silamoney.client.tests;

import com.silamoney.client.api.ApiResponse;
import com.silamoney.client.api.SilaApi;
import com.silamoney.client.domain.BaseResponse;
import com.silamoney.client.testsutils.DefaultConfigurations;
import org.junit.Test;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class ResendStatementsTests {
    SilaApi api = new SilaApi(DefaultConfigurations.host, DefaultConfigurations.appHandle,
            DefaultConfigurations.privateKey);

    @Test
    public void Response200() throws Exception {
        ApiResponse response = api.resendStatements(DefaultConfigurations.getUserHandle(),
                DefaultConfigurations.getUserPrivateKey(), DefaultConfigurations.getStatementId(),
                "foo.bar@silamoney.com");

        assertEquals(200, response.getStatusCode());
        BaseResponse parsedResponse = (BaseResponse) response.getData();

        assertEquals("SUCCESS", parsedResponse.getStatus());
        assertEquals(true, parsedResponse.getSuccess());
        assertNotNull(parsedResponse.getReference());
        assertNotNull(parsedResponse.getResponseTimeMs());
        assertNotNull(parsedResponse.getMessage());
    }
}
