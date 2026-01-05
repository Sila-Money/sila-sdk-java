package com.silamoney.client.tests;

import com.silamoney.client.api.ApiClient;
import com.silamoney.client.api.ApiResponse;
import com.silamoney.client.api.SilaApi;
import com.silamoney.client.config.Configuration;
import com.silamoney.client.domain.GetStatementTransactionsResponse;
import com.silamoney.client.domain.Pagination;
import com.silamoney.client.domain.Statement;
import com.silamoney.client.domain.TransactionEntity;
import com.silamoney.client.testsutils.DefaultConfigurations;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.lang.reflect.Field;
import java.net.http.HttpClient;
import java.net.http.HttpHeaders;
import java.net.http.HttpResponse;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

public class GetStatementTransactionsTests {
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
                "{" +
                        "\"status\":\"SUCCESS\"," +
                        "\"sila_reference_id\":\"assigned_id\"," +
                        "\"reference\":\"chosen_id\"," +
                        "\"response_time_ms\":\"299\"," +
                        "\"success\":true," +
                        "\"pagination\": {" +
                        "\"returned_count\": 4," +
                        "\"total_count\": 4," +
                        "\"current_page\": 1," +
                        "\"total_pages\": 1" +
                        "}," +
                        "\"statement_data\": [" +
                        "{" +
                        "\"user_handle\": \"user.silamoney\"," +
                        "\"first_name\": \"First\"," +
                        "\"last_name\": \"Last\"," +
                        "\"date\": \"03-2023\"," +
                        "\"wallet_id\": \"00a01d14-0sd0-avcc-a672-cfd908794a225\"," +
                        "\"beginning_balance\": \"$0.00\"," +
                        "\"ending_balance\": \"$5,489.00\"," +
                        "\"transactions\": [" +
                        "{" +
                        "\"settled_date\": \"2023-03-08T19:58:25.686126Z\"," +
                        "\"description\": \"Credit from default\"," +
                        "\"category\": \"Credit\"," +
                        "\"amount\": \"$100.00\"," +
                        "\"running_balance\": \"$100.00\"," +
                        "\"descriptor\":\"test descriptor\"" +
                        "}," +
                        "{" +
                        "\"settled_date\": \"2023-03-08T19:58:26.087678Z\"," +
                        "\"description\": \"Credit from default\"," +
                        "\"category\": \"Credit\"," +
                        "\"amount\": \"$200.00\"," +
                        "\"running_balance\": \"$300.00\"" +
                        "}" +
                        "]" +
                        "}" +
                        "]" +
                        "}"
        );

        Map<String, List<String>> headersMap = new HashMap<>();
        headersMap.put("Content-Type", List.of("application/json"));
        Mockito.when(httpHeadersMock.map()).thenReturn(headersMap);
        Mockito.when(httpResponseMock.headers()).thenReturn(httpHeadersMock);

        // Mocking the callApi method to return the mocked HttpResponse
        Mockito.when(apiClient.callApi(Mockito.anyString(), Mockito.anyMap(), Mockito.anyString())).thenReturn(httpResponseMock);

        Calendar cal = Calendar.getInstance();
        String defaultMonth = new SimpleDateFormat("MM-yyyy").format(cal.getTime());
        Integer page = 1;
        Integer perPage = 1000;

        ApiResponse response = api.getStatementTransactions(DefaultConfigurations.getUserHandle(), DefaultConfigurations.getUserPrivateKey(), DefaultConfigurations.getWalletId(), defaultMonth, page, perPage);
        assertEquals(200, response.getStatusCode());
        GetStatementTransactionsResponse parsedResponse = (GetStatementTransactionsResponse) response.getData();

        assertEquals("SUCCESS", parsedResponse.getStatus());
        assertEquals(true, parsedResponse.getSuccess());

        // Get the most important objects in the response for testing
        Pagination pages = parsedResponse.getPagination();
        List<Statement> statements = parsedResponse.getStatementData();
        Statement statement = statements.get(0);
        TransactionEntity transaction = statement.getTransactions().get(0);

        // Test pagination and response size
        assertNotNull(pages);
        assertNotEquals(pages.getReturnedCount(), 0);
        assertNotNull(parsedResponse.getReference());
        assertNotNull(parsedResponse.getResponseTimeMs());
        assertNotNull(parsedResponse.getStatementData());

        // Test statement fields
        assertNotNull(statement);
        assertNotNull(statement.getUserHandle());
        assertNotNull(statement.getDate());
        assertNotNull(statement.getFirstName());
        assertNotNull(statement.getLastName());
        assertNotNull(statement.getWalletId());
        assertNotNull(statement.getBeginningBalance());
        assertNotNull(statement.getEndingBalance());

        // Test transaction fields
        assertNotNull(transaction);
        assertNotNull(transaction.getSettledDate());
        assertNotNull(transaction.getDescription());
        assertNotNull(transaction.getCategory());
        assertNotNull(transaction.getAmount());
        assertNotNull(transaction.getCategory());
        assertNotNull(transaction.getRunningBalance());
        assertNotNull(transaction.getDescriptor());
    }

    @Test
    public void Response200WithNoMonth() throws Exception {
        SilaApi api = new SilaApi(DefaultConfigurations.host, DefaultConfigurations.appHandle,
                DefaultConfigurations.privateKey);
        ApiResponse response = api.getStatementTransactions(
                DefaultConfigurations.getUserHandle(),
                DefaultConfigurations.getUserPrivateKey(), DefaultConfigurations.getWalletId());
        assertEquals(200, response.getStatusCode());
        GetStatementTransactionsResponse parsedResponse = (GetStatementTransactionsResponse) response.getData();

        assertEquals("SUCCESS", parsedResponse.getStatus());
        assertEquals(true, parsedResponse.getSuccess());
    }

}
