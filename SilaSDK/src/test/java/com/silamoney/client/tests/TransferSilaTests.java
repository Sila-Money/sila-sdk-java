package com.silamoney.client.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.IOException;
import java.lang.reflect.Field;
import java.net.http.HttpClient;
import java.net.http.HttpHeaders;
import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;


import com.silamoney.client.api.ApiClient;
import com.silamoney.client.api.ApiResponse;
import com.silamoney.client.api.SilaApi;
import com.silamoney.client.config.Configuration;
import com.silamoney.client.domain.TransferSilaResponse;
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
public class TransferSilaTests {
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

		ApiResponse response = api.transferSila(DefaultConfigurations.getUserHandle(), 100, DefaultConfigurations.getUser2Handle(), null,
				"test descriptor", DefaultConfigurations.correctUuid,
				DefaultConfigurations.getUserPrivateKey());
				
		assertEquals(200, response.getStatusCode());
		assertEquals("test descriptor", ((TransferSilaResponse) response.getData()).getDescriptor());
		assertEquals("SUCCESS", ((TransferSilaResponse) response.getData()).getStatus());
		assertNotNull(((TransferSilaResponse) response.getData()).getTransactionId());
	}

	@Test
	public void Response400() throws BadRequestException, InvalidSignatureException, ServerSideException, IOException,
			InterruptedException, ForbiddenException {
		SilaApi api = new SilaApi(DefaultConfigurations.host, DefaultConfigurations.appHandle,
				DefaultConfigurations.privateKey);
		 
		ApiResponse response = api.transferSila(DefaultConfigurations.getUserHandle(), 100,
				DefaultConfigurations.getUserPrivateKey(), DefaultConfigurations.getUserPrivateKey(),
				"test descriptor", UUID.randomUUID().toString(),
				DefaultConfigurations.getUserPrivateKey());
		assertEquals(400, response.getStatusCode());
	}
	
	@Test
	public void Response400WrongUuid() throws BadRequestException, InvalidSignatureException, ServerSideException, IOException,
			InterruptedException, ForbiddenException {
		SilaApi api = new SilaApi(DefaultConfigurations.host, DefaultConfigurations.appHandle,
				DefaultConfigurations.privateKey);
		// TRANSACTIONS5
		ApiResponse response = api.transferSila(DefaultConfigurations.getUserHandle(), 100,
				DefaultConfigurations.getUserPrivateKey(), DefaultConfigurations.getUserPrivateKey(),
				"test descriptor", DefaultConfigurations.wrongUuid,
				DefaultConfigurations.getUserPrivateKey());

		assertEquals(400, response.getStatusCode());
	}

	@Test
	public void Response401() throws BadRequestException, InvalidSignatureException, ServerSideException, IOException,
			InterruptedException, ForbiddenException {
		 
		api = new SilaApi(DefaultConfigurations.host, DefaultConfigurations.appHandle,
				"3a1076bf45ab87712ad64ccb3b10217737f7faacbf2872e88fdd9a537d8fe266");
		ApiResponse response = api.transferSila(DefaultConfigurations.getUserHandle(), 100, DefaultConfigurations.getUser2Handle(), null,
				"test descriptor", UUID.randomUUID().toString(),
				DefaultConfigurations.getUserPrivateKey());
		
		assertEquals(401, response.getStatusCode());
	}

	@Test
	public void Response200Success_2() throws Exception {
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
		Mockito.when(apiClient.callApi(Mockito.anyString(), Mockito.anyMap(),
				Mockito.anyString())).thenReturn(httpResponseMock);

		ApiResponse response = api.transferSila(DefaultConfigurations.getUserHandle(), 100, DefaultConfigurations.getUser2Handle(), null,
				"test descriptor", DefaultConfigurations.correctUuid,
				DefaultConfigurations.getUserPrivateKey(),DefaultConfigurations.transactionIdempotencyUuid);

		assertEquals(200, response.getStatusCode());
		assertEquals("test descriptor", ((TransferSilaResponse) response.getData()).getDescriptor());
		assertEquals("SUCCESS", ((TransferSilaResponse) response.getData()).getStatus());
		assertNotNull(((TransferSilaResponse) response.getData()).getTransactionId());
	}
}
