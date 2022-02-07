package com.silamoney.client.tests;

import com.silamoney.client.api.ApiResponse;
import com.silamoney.client.api.SilaApi;
import com.silamoney.client.domain.GetPaymentMethodsResponse;
import com.silamoney.client.domain.PaymentMethodsSearchFilters;
import com.silamoney.client.exceptions.BadRequestException;
import com.silamoney.client.exceptions.ForbiddenException;
import com.silamoney.client.exceptions.InvalidSignatureException;
import com.silamoney.client.exceptions.ServerSideException;
import com.silamoney.client.testsutils.DefaultConfigurations;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class GetPaymentMethodsTests {
    SilaApi api = new SilaApi(DefaultConfigurations.host, DefaultConfigurations.appHandle,
            DefaultConfigurations.privateKey);

    @Test
    public void Response200() throws Exception {
        ApiResponse response = api.getPaymentMethods(DefaultConfigurations.getUserHandle(),
                DefaultConfigurations.getUserPrivateKey());
        assertEquals(200, response.getStatusCode());
        GetPaymentMethodsResponse parsedResponse = (GetPaymentMethodsResponse) response.getData();
        assertEquals("SUCCESS", parsedResponse.getStatus());
        assertTrue(parsedResponse.getPaymentMethods().size()>0);
        DefaultConfigurations.setPaymentMethods(parsedResponse.getPaymentMethods());
    }
    @Test
    public void Response200SearchFilters() throws Exception {
        PaymentMethodsSearchFilters searchFilters=new PaymentMethodsSearchFilters();
        List<PaymentMethodsSearchFilters.PaymentMethodsTypesEnum> paymentMethodsTypesEnumList=new ArrayList<>();
        paymentMethodsTypesEnumList.add(PaymentMethodsSearchFilters.PaymentMethodsTypesEnum.BLOCKCHAIN_ADDRESS);
        searchFilters.setPaymentMethodsTypes(paymentMethodsTypesEnumList);
        searchFilters.setPerPage(20);
        searchFilters.setPage(1);
        ApiResponse response = api.getPaymentMethods(DefaultConfigurations.getUserHandle(),
                DefaultConfigurations.getUserPrivateKey(),searchFilters);
        assertEquals(200, response.getStatusCode());
        GetPaymentMethodsResponse parsedResponse = (GetPaymentMethodsResponse) response.getData();
        assertEquals("SUCCESS", parsedResponse.getStatus());
        assertTrue(parsedResponse.getPaymentMethods().size()>0);
        assertEquals(PaymentMethodsSearchFilters.PaymentMethodsTypesEnum.BLOCKCHAIN_ADDRESS.getValue(),parsedResponse.getPaymentMethods().get(0).getPaymentMethodType());
    }

    @Test
    public void Response400() throws Exception {

        ApiResponse response = api.getPaymentMethods("",
                DefaultConfigurations.getUserPrivateKey());
        assertEquals(400, response.getStatusCode());
    }

    @Test
    public void Response403() throws BadRequestException, InvalidSignatureException, ServerSideException, IOException,
            InterruptedException, ForbiddenException {

        api = new SilaApi(DefaultConfigurations.host, DefaultConfigurations.appHandle,
                "3a1076bf45ab87712ad64ccb3b10217737f7faacbf2872e88fdd9a537d8fe266");

        ApiResponse response = api.getPaymentMethods(DefaultConfigurations.getUserHandle(),
                DefaultConfigurations.getUserPrivateKey());
        assertEquals(403, response.getStatusCode());
    }
}