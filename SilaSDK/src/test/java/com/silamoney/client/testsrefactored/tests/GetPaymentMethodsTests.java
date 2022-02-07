package com.silamoney.client.testsrefactored.tests;

import com.silamoney.client.api.ApiResponse;
import com.silamoney.client.testsutils.DefaultConfigurations;
import com.silamoney.clientrefactored.configuration.Environment;
import com.silamoney.clientrefactored.configuration.SilaApi;
import com.silamoney.clientrefactored.endpoints.paymentMethods.GetPaymentMethods;
import com.silamoney.clientrefactored.endpoints.paymentMethods.GetPaymentMethodsRequest;
import com.silamoney.clientrefactored.endpoints.paymentMethods.GetPaymentMethodsResponse;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class GetPaymentMethodsTests {
    @BeforeClass
    public static void init() {
        SilaApi.init(Environment.SANDBOX, DefaultConfigurations.appHandle,
                DefaultConfigurations.privateKey);
    }

    @AfterClass
    public static void dispose() {
        SilaApi.dispose();
    }

    @Test
    public void Response200() throws Exception {
        GetPaymentMethodsRequest request = GetPaymentMethodsRequest.builder()
                .userHandle(DefaultConfigurations.getUserHandle())
                .userPrivateKey(DefaultConfigurations.getUserPrivateKey())
                .build();

        ApiResponse response = GetPaymentMethods.send(request);
        GetPaymentMethodsResponse parsedResponse = (GetPaymentMethodsResponse) response.getData();
        assertEquals("SUCCESS", parsedResponse.getStatus());

    }
}
