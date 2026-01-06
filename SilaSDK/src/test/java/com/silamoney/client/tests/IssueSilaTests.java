package com.silamoney.client.tests;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.lang.reflect.Field;
import java.net.http.HttpClient;
import java.net.http.HttpHeaders;
import java.net.http.HttpResponse;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.silamoney.client.api.ApiClient;

import com.silamoney.client.api.ApiResponse;
import com.silamoney.client.api.SilaApi;
import com.silamoney.client.config.Configuration;
import com.silamoney.client.domain.BadRequestResponse;
import com.silamoney.client.domain.BaseResponse;
import com.silamoney.client.domain.AccountTransactionMessage;
import com.silamoney.client.domain.ProcessingTypeEnum;
import com.silamoney.client.domain.TransactionResponse;
import com.silamoney.client.exceptions.BadRequestException;
import com.silamoney.client.exceptions.ForbiddenException;
import com.silamoney.client.exceptions.InvalidSignatureException;
import com.silamoney.client.exceptions.ServerSideException;
import com.silamoney.client.testsutils.DefaultConfigurations;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

/**
 *
 * @author Karlo Lorenzana
 */
public class IssueSilaTests {
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
                        "\"status\":\"SUCCESS\"," +
                        "\"descriptor\":\"test descriptor\"," +
                        "\"message\":\"Transaction submitted to the processing queue.\"," +
                        "\"success\":true," +
                        "\"transaction_id\":" + UUID.randomUUID().toString() +
                        "}"
        );

        Map<String, List<String>> headersMap = new HashMap<>();
        headersMap.put("Content-Type", List.of("application/json"));
        Mockito.when(httpHeadersMock.map()).thenReturn(headersMap);
        Mockito.when(httpResponseMock.headers()).thenReturn(httpHeadersMock);

        // Mocking the callApi method to return the mocked HttpResponse
        Mockito.when(apiClient.callApi(Mockito.anyString(), Mockito.anyMap(), Mockito.anyString())).thenReturn(httpResponseMock);

        AccountTransactionMessage issue = AccountTransactionMessage.builder()
                .userHandle(DefaultConfigurations.getUserHandle())
                .userPrivateKey(DefaultConfigurations.getUserPrivateKey()).amount(1000).accountName("default")
                .descriptor("test descriptor").businessUuid(DefaultConfigurations.correctUuid).transactionIdempotencyId(DefaultConfigurations.transactionIdempotencyUuid).build();
        ApiResponse response = api.issueSila(issue);
        assertEquals(200, response.getStatusCode());
        assertTrue(((TransactionResponse) response.getData()).getSuccess());
        assertEquals("test descriptor", ((TransactionResponse) response.getData()).getDescriptor());
        assertEquals("SUCCESS", ((TransactionResponse) response.getData()).getStatus());
        assertNotNull(((TransactionResponse) response.getData()).getTransactionId());
    }

    @Test
    public void Response200SuccessSameDay() throws Exception {
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
                        "\"message\":\"Transaction submitted to the processing queue.\"," +
                        "\"success\":true," +
                        "\"transaction_id\":" + UUID.randomUUID().toString() +
                        "}"
        );

        Map<String, List<String>> headersMap = new HashMap<>();
        headersMap.put("Content-Type", List.of("application/json"));
        Mockito.when(httpHeadersMock.map()).thenReturn(headersMap);
        Mockito.when(httpResponseMock.headers()).thenReturn(httpHeadersMock);

        // Mocking the callApi method to return the mocked HttpResponse
        Mockito.when(apiClient.callApi(Mockito.anyString(), Mockito.anyMap(), Mockito.anyString())).thenReturn(httpResponseMock);

        AccountTransactionMessage issue = AccountTransactionMessage.builder()
                .userHandle(DefaultConfigurations.getUserHandle())
                .userPrivateKey(DefaultConfigurations.getUserPrivateKey()).amount(500).accountName("default")
                .processingType(ProcessingTypeEnum.SAME_DAY).build();
        ApiResponse response = api.issueSila(issue);

        assertEquals(200, response.getStatusCode());
        TransactionResponse parsedResponse = (TransactionResponse) response.getData();
        assertTrue(parsedResponse.getSuccess());
        assertEquals("SUCCESS", parsedResponse.getStatus());
        assertNotNull(parsedResponse.getTransactionId());
    }

    @Test
    public void Response400() throws BadRequestException, InvalidSignatureException, ServerSideException, IOException,
            InterruptedException, ForbiddenException {
        SilaApi api = new SilaApi(DefaultConfigurations.host, DefaultConfigurations.appHandle,
                DefaultConfigurations.privateKey);
        AccountTransactionMessage issue = AccountTransactionMessage.builder().userHandle("")
                .userPrivateKey(DefaultConfigurations.getUserPrivateKey()).amount(1000).accountName(null).build();
        ApiResponse response = api.issueSila(issue);
        assertEquals(400, response.getStatusCode());
        BadRequestResponse parsedResponse = (BadRequestResponse) response.getData();
        assertFalse(parsedResponse.getSuccess());
        assertEquals("FAILURE", parsedResponse.getStatus());
        assertThat("issue sila - bad request", parsedResponse.getMessage(),
                stringContainsInOrder(Arrays.asList("Bad request")));
    }

    @Test
    public void Response400WrongUuid() throws BadRequestException, InvalidSignatureException, ServerSideException,
            IOException, InterruptedException, ForbiddenException {
        SilaApi api = new SilaApi(DefaultConfigurations.host, DefaultConfigurations.appHandle,
                DefaultConfigurations.privateKey);
        AccountTransactionMessage issue = AccountTransactionMessage.builder()
                .userHandle(DefaultConfigurations.getUserHandle())
                .userPrivateKey(DefaultConfigurations.getUserPrivateKey()).amount(1000).accountName(null)
                .businessUuid(DefaultConfigurations.wrongUuid).build();
        ApiResponse response = api.issueSila(issue);
        assertEquals(400, response.getStatusCode());
        BadRequestResponse parsedResponse = (BadRequestResponse) response.getData();
        assertFalse(parsedResponse.getSuccess());
        assertEquals("FAILURE", parsedResponse.getStatus());
        assertThat("issue sila - bad request", parsedResponse.getMessage(),
                stringContainsInOrder(Arrays.asList("Bad request")));
    }

    @Test
    public void Response401User() throws BadRequestException, InvalidSignatureException, ServerSideException,
            IOException, InterruptedException, ForbiddenException {
        SilaApi api = new SilaApi(DefaultConfigurations.host, DefaultConfigurations.appHandle,
                DefaultConfigurations.privateKey);
        AccountTransactionMessage issue = AccountTransactionMessage.builder()
                .userHandle(DefaultConfigurations.getUserHandle()).userPrivateKey(DefaultConfigurations.privateKey)
                .amount(1000).accountName("default").build();
        ApiResponse response = api.issueSila(issue);
        assertEquals(401, response.getStatusCode());
        BaseResponse parsedResponse = (BaseResponse) response.getData();
        assertFalse(parsedResponse.getSuccess());
        assertEquals("FAILURE", parsedResponse.getStatus());
    }

    @Test
    public void Response401() throws BadRequestException, InvalidSignatureException, ServerSideException, IOException,
            InterruptedException, ForbiddenException {
        SilaApi badApi = new SilaApi(DefaultConfigurations.host, DefaultConfigurations.appHandle,
                "3a1076bf45ab87712ad64ccb3b10217737f7faacbf2872e88fdd9a537d8fe266");
        AccountTransactionMessage issue = AccountTransactionMessage.builder()
                .userHandle(DefaultConfigurations.getUserHandle())
                .userPrivateKey(DefaultConfigurations.getUserPrivateKey()).amount(1000).accountName("default").build();
        ApiResponse response = badApi.issueSila(issue);
        assertEquals(401, response.getStatusCode());
        BaseResponse parsedResponse = (BaseResponse) response.getData();
        assertFalse(parsedResponse.getSuccess());
        assertEquals("FAILURE", parsedResponse.getStatus());
    }


    @Test
    public void Response200SuccessInstantSettlement() throws Exception {
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
                        "\"message\":\"Transaction submitted to the processing queue.\"," +
                        "\"success\":true," +
                        "\"transaction_id\":" + UUID.randomUUID().toString() +
                        "}"
        );

        Map<String, List<String>> headersMap = new HashMap<>();
        headersMap.put("Content-Type", List.of("application/json"));
        Mockito.when(httpHeadersMock.map()).thenReturn(headersMap);
        Mockito.when(httpResponseMock.headers()).thenReturn(httpHeadersMock);

        // Mocking the callApi method to return the mocked HttpResponse
        Mockito.when(apiClient.callApi(Mockito.anyString(), Mockito.anyMap(), Mockito.anyString())).thenReturn(httpResponseMock);

        AccountTransactionMessage issue = AccountTransactionMessage.builder()
                .userHandle(DefaultConfigurations.getUserHandle())
                .userPrivateKey(DefaultConfigurations.getUserPrivateKey()).amount(200).accountName("default")
                .processingType(ProcessingTypeEnum.INSTANT_SETTLEMENT).build();
        ApiResponse response = api.issueSila(issue);

        assertEquals(200, response.getStatusCode());
        TransactionResponse parsedResponse = (TransactionResponse) response.getData();
        assertTrue(parsedResponse.getSuccess());
        assertEquals("SUCCESS", parsedResponse.getStatus());
        assertNotNull(parsedResponse.getTransactionId());
    }
}
