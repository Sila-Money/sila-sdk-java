package com.silamoney.client.tests;

import com.silamoney.client.api.ApiResponse;
import com.silamoney.client.api.SilaApi;
import com.silamoney.client.domain.BaseResponse;
import com.silamoney.client.testsutils.DefaultConfigurations;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class ResendStatementsTests {

    SilaApi api = new SilaApi(DefaultConfigurations.host, "arc_sandbox_test_app01",
            "9c17e7b767b8f4a63863caf1619ef3e9967a34b287ce58542f3eb19b5a72f076");
    String userHandle="user_handle1_1686776339cudgjmzwckh4ohh";
    String privateKey="c087fb917b921f930355b97edda0ab29a5ea40963cef376be999e6aedf0efe0e";

    @Test
    public void Response200() throws Exception {
        ApiResponse response = api.resendStatements(userHandle, privateKey, DefaultConfigurations.getStatementId(),"anuj.kalal@silamoney.com");
        assertEquals(200, response.getStatusCode());
        BaseResponse parsedResponse = (BaseResponse) response.getData();

        assertEquals("SUCCESS", parsedResponse.getStatus());
        assertEquals(true, parsedResponse.getSuccess());
        assertNotNull(parsedResponse.getReference());
        assertNotNull(parsedResponse.getResponseTimeMs());
        assertNotNull(parsedResponse.getMessage());
    }
}
