package com.silamoney.client.tests;

import com.silamoney.client.api.ApiClient;
import com.silamoney.client.api.ApiResponse;
import com.silamoney.client.api.SilaApi;
import com.silamoney.client.config.Configuration;
import com.silamoney.client.domain.UpdateAccountResponse;
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

import static org.junit.Assert.*;

public class UpdateAccountTests {
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
    public void Register200UpdateAccount() throws Exception {
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
                "\"status\":\"SUCCESS\"," +
                "\"message\":\"Account updated.\"," +
                "\"account\":{" +
                    "\"account_status\":\"active\"," +
                    "\"routing_number\":\"123456789\"," +
                    "\"account_number\":\"*5678\"," +
                    "\"account_type\":\"CHECKING\"," +
                    "\"active\":true," +
                    "\"account_link_status\":\"processor_token\"," +
                    "\"account_name\":\"default\"" +
                "}," +
                "\"changes\":[{" +
                    "\"attribute\":\"account_name\"," +
                    "\"old_value\":\"Gringotts Checking\"," +
                    "\"new_value\":\"default\"" +
                "}]" +
            "}"
        );

        Map<String, List<String>> headersMap = new HashMap<>();
        headersMap.put("Content-Type", List.of("application/json"));
        Mockito.when(httpHeadersMock.map()).thenReturn(headersMap);
        Mockito.when(httpResponseMock.headers()).thenReturn(httpHeadersMock);

        // Mocking the callApi method to return the mocked HttpResponse
        Mockito.when(apiClient.callApi(Mockito.anyString(), Mockito.anyMap(),
                Mockito.anyString())).thenReturn(httpResponseMock);

        ApiResponse response = api.updateAccount(DefaultConfigurations.getUserHandle(),
                DefaultConfigurations.getUserPrivateKey(), "Gringotts Checking", "default");

        UpdateAccountResponse parsedResponse = (UpdateAccountResponse) response.getData();
        assertEquals(200, response.getStatusCode());
        assertNotNull(parsedResponse.getStatus());
        assertNotNull(parsedResponse.getMessage());
        assertNotNull(parsedResponse.getAccount().getAccountListStatus());
        assertNotNull(parsedResponse.getAccount().getAccountName());
        assertNotNull(parsedResponse.getAccount().getAccountNumber());
        assertNotNull(parsedResponse.getAccount().getAccountStatus());
        assertNotNull(parsedResponse.getAccount().getAccountType());
        assertNotNull(parsedResponse.getAccount().getRoutingNumber());
        assertTrue(parsedResponse.getAccount().isActive());
        assertNotNull(parsedResponse.getChanges().get(0).getAttribute());
        assertNotNull(parsedResponse.getChanges().get(0).getNewValue());
        assertNotNull(parsedResponse.getChanges().get(0).getOldValue());
    }

}
