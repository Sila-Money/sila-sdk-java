package com.silamoney.client.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.lang.reflect.Field;
import java.net.http.HttpClient;
import java.net.http.HttpHeaders;
import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.silamoney.client.api.ApiClient;
import com.silamoney.client.api.ApiResponse;
import com.silamoney.client.api.SilaApi;
import com.silamoney.client.config.Configuration;
import com.silamoney.client.domain.Account;
import com.silamoney.client.testsutils.DefaultConfigurations;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

public class GetAccountsTests {
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
    public void Response200() throws Exception {
        // Mocking the ApiResponse and HttpResponse
        ApiResponse apiResponseMock = Mockito.mock(ApiResponse.class);
        HttpResponse<String> httpResponseMock = Mockito.mock(HttpResponse.class);
        HttpHeaders httpHeadersMock = Mockito.mock(HttpHeaders.class);

        // Mocking the behavior of apiResponseMock and httpResponseMock
        Mockito.when(apiResponseMock.getStatusCode()).thenReturn(200);
        Mockito.when(apiResponseMock.getData()).thenReturn(httpResponseMock);
        Mockito.when(httpResponseMock.statusCode()).thenReturn(200);
        Mockito.when(httpResponseMock.body()).thenReturn(
                "[{" +
                        "\"account_status\":\"active\"," +
                        "\"routing_number\":\"123456789\"," +
                        "\"account_number\":\"*5678\"," +
                        "\"account_type\":\"CHECKING\"," +
                        "\"active\":true," +
                        "\"account_link_status\":\"processor_token\"," +
                        "\"account_name\":\"default\"" +
                        "}]"
        );
        Map<String, List<String>> headersMap = new HashMap<>();
        headersMap.put("Content-Type", List.of("application/json"));
        Mockito.when(httpHeadersMock.map()).thenReturn(headersMap);
        Mockito.when(httpResponseMock.headers()).thenReturn(httpHeadersMock);
        // Mocking the callApi method to return the mocked HttpResponse
        Mockito.when(apiClient.callApi(Mockito.anyString(), Mockito.anyMap(), Mockito.anyString())).thenReturn(httpResponseMock);

        ApiResponse response = api.getAccounts(DefaultConfigurations.getUserHandle(), DefaultConfigurations.getUserPrivateKey());

        List<Account> parsedResponse = (List<Account>)response.getData();

        assertTrue(parsedResponse.size() > 0);
    }

    @Test
    public void Response200_WithAccountNumberFilter() throws Exception {
        // Mocking the ApiResponse and HttpResponse
        ApiResponse apiResponseMock = Mockito.mock(ApiResponse.class);
        HttpResponse<String> httpResponseMock = Mockito.mock(HttpResponse.class);
        HttpHeaders httpHeadersMock = Mockito.mock(HttpHeaders.class);

        // Mocking the behavior of apiResponseMock and httpResponseMock
        Mockito.when(apiResponseMock.getStatusCode()).thenReturn(200);
        Mockito.when(apiResponseMock.getData()).thenReturn(httpResponseMock);
        Mockito.when(httpResponseMock.statusCode()).thenReturn(200);
        Mockito.when(httpResponseMock.body()).thenReturn(
                "[{" +
                        "\"account_status\":\"active\"," +
                        "\"routing_number\":\"123456789\"," +
                        "\"account_number\":\"*4589\"," +
                        "\"account_type\":\"CHECKING\"," +
                        "\"active\":true," +
                        "\"account_link_status\":\"processor_token\"," +
                        "\"account_name\":\"default\"" +
                        "}]"
        );

        Map<String, List<String>> headersMap = new HashMap<>();
        headersMap.put("Content-Type", List.of("application/json"));
        Mockito.when(httpHeadersMock.map()).thenReturn(headersMap);
        Mockito.when(httpResponseMock.headers()).thenReturn(httpHeadersMock);

        // Mocking the callApi method to return the mocked HttpResponse
        Mockito.when(apiClient.callApi(Mockito.anyString(), Mockito.anyMap(), Mockito.anyString())).thenReturn(httpResponseMock);

        String accountNumber = "3136824589";
        String last4 = accountNumber.substring(accountNumber.length() - 4);
        String maskedLast4 = "*" + last4;

        ApiResponse response = api.getAccounts(
            DefaultConfigurations.getUserHandle(),
            DefaultConfigurations.getUserPrivateKey(),
            null,
            accountNumber
        );

        List<Account> parsed = (List<Account>) response.getData();
        assertEquals(1, parsed.size());
        assertEquals(maskedLast4, parsed.get(0).getAccountNumber());
    }

    @Test
    public void Response200_FindNoAccount() throws Exception {
        SilaApi api = new SilaApi(DefaultConfigurations.host, DefaultConfigurations.appHandle,
                DefaultConfigurations.privateKey);
        ApiResponse response = api.getAccounts(
            DefaultConfigurations.getUserHandle(),
            DefaultConfigurations.getUserPrivateKey(),
            null,
            "123"
        );

        List<Account> parsed = (List<Account>) response.getData();
        assertEquals(0, parsed.size());
    }

}
