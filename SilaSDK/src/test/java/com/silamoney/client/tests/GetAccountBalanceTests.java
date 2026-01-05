package com.silamoney.client.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import com.silamoney.client.api.ApiClient;
import com.silamoney.client.api.ApiResponse;
import com.silamoney.client.api.SilaApi;
import com.silamoney.client.config.Configuration;
import com.silamoney.client.domain.AccountBalanceResponse;
import com.silamoney.client.testsutils.DefaultConfigurations;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.lang.reflect.Field;
import java.net.http.HttpClient;
import java.net.http.HttpHeaders;
import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GetAccountBalanceTests {
    HttpClient httpClient = Mockito.mock(HttpClient.class);
    ApiClient apiClient = Mockito.mock(ApiClient.class);

    SilaApi api;

    @Before
    public void setUp() {
        try {
            api = new SilaApi(DefaultConfigurations.host, DefaultConfigurations.appHandle, DefaultConfigurations.privateKey);
            Field configurationField = api.getClass().getDeclaredField("configuration");
            configurationField.setAccessible(true);

            // Get the configuration object
            Configuration configuration = (Configuration) configurationField.get(api);

            // Set the ApiClient using reflection
            Field apiClientField = Configuration.class.getDeclaredField("apiClient");
            apiClientField.setAccessible(true);
            apiClientField.set(configuration, apiClient);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void Response200Success() throws Exception {
        // Mocking the ApiResponse and HttpResponse
        ApiResponse apiResponseMock = Mockito.mock(ApiResponse.class);
        HttpResponse<String> httpResponseMock = Mockito.mock(HttpResponse.class);
        HttpHeaders httpHeadersMock = Mockito.mock(HttpHeaders.class);

        // Mocking the behavior of apiResponseMock and httpResponseMock
        Mockito.when(apiResponseMock.getStatusCode()).thenReturn(200);
        Mockito.when(apiResponseMock.getData()).thenReturn(httpResponseMock);
        Mockito.when(httpResponseMock.statusCode()).thenReturn(200);
        Mockito.when(httpResponseMock.body()).thenReturn(
                "{" +
                        "\"success\":true," +
                        "\"available_balance\":100," +
                        "\"current_balance\":110," +
                        "\"routing_number\":\"123456789\"," +
                        "\"masked_account_number\":\"*5678\"," +
                        "\"account_name\":\"default\"" +
                        "}"
        );

        Map<String, List<String>> headersMap = new HashMap<>();
        headersMap.put("Content-Type", List.of("application/json"));
        Mockito.when(httpHeadersMock.map()).thenReturn(headersMap);
        Mockito.when(httpResponseMock.headers()).thenReturn(httpHeadersMock);

        // Mocking the callApi method to return the mocked HttpResponse
        Mockito.when(apiClient.callApi(Mockito.anyString(), Mockito.anyMap(), Mockito.anyString())).thenReturn(httpResponseMock);

        // BANKACCOUNT5
        final ApiResponse response = api.getAccountBalance(DefaultConfigurations.getUserHandle(),
                DefaultConfigurations.getUserPrivateKey(), "default");

        assertEquals(200, response.getStatusCode());
        AccountBalanceResponse parsedResponse = (AccountBalanceResponse)response.getData();

        assertNotNull(parsedResponse.getAccountName());
        assertNotNull(parsedResponse.getMaskedAccountNumber());
    }

    @Test
    public void Response400Success() throws Exception {
        SilaApi api = new SilaApi(DefaultConfigurations.host, DefaultConfigurations.appHandle,
                DefaultConfigurations.privateKey);

        // BANKACCOUNT5
        final ApiResponse response = api.getAccountBalance("", DefaultConfigurations.getUserPrivateKey(), "default");

        assertEquals(400, response.getStatusCode());
    }
}
