package com.silamoney.client.tests;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;

import com.silamoney.client.domain.BadRequestResponse;
import com.silamoney.client.exceptions.BadRequestException;
import com.silamoney.client.exceptions.ForbiddenException;
import com.silamoney.client.exceptions.InvalidSignatureException;
import com.silamoney.client.exceptions.ServerSideException;
import org.mockito.Mockito;

import java.io.IOException;
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
import com.silamoney.client.domain.LinkAccountResponse;
import com.silamoney.client.testsutils.DefaultConfigurations;

import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Karlo Lorenzana
 */
public class LinkAccountTests {
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
		// BANKACCOUNT1
		// Mocking the ApiResponse and HttpResponse
		ApiResponse apiResponseMock = Mockito.mock(ApiResponse.class);
		HttpResponse<String> httpResponseMock = Mockito.mock(HttpResponse.class);
		HttpHeaders httpHeadersMock = Mockito.mock(HttpHeaders.class);

		// Mocking the behavior of apiResponseMock and httpResponseMock
		Mockito.when(apiResponseMock.getStatusCode()).thenReturn(200);
		Mockito.when(apiResponseMock.getData()).thenReturn(httpResponseMock);
		Mockito.when(httpResponseMock.statusCode()).thenReturn(200);
		Mockito.when(httpResponseMock.body()).thenReturn("{" +
				"\"success\":true," +
				"\"status\":\"SUCCESS\"," +
				"\"message\":\"successfully linked with status \\\"instantly_verified\\\"\"," +
				"\"account_name\":\"defaultpt\"" +
				"}"
		);

		Map<String, List<String>> headersMap = new HashMap<>();
		headersMap.put("Content-Type", List.of("application/json"));
		Mockito.when(httpHeadersMock.map()).thenReturn(headersMap);
		Mockito.when(httpResponseMock.headers()).thenReturn(httpHeadersMock);

		// Mocking the callApi method to return the mocked HttpResponse
		Mockito.when(apiClient.callApi(Mockito.anyString(), Mockito.anyMap(), Mockito.anyString())).thenReturn(httpResponseMock);

		// Calling the actual method
		ApiResponse response = api.linkAccountPlaidToken(DefaultConfigurations.getUserHandle(),
				DefaultConfigurations.getUserPrivateKey(), "defaultpt", "mock_token", null, "legacy");

		assertEquals(200, response.getStatusCode());
		LinkAccountResponse parsedResponse = (LinkAccountResponse) response.getData();
		assertEquals("SUCCESS", parsedResponse.getStatus());
		assertThat(parsedResponse.getMessage(),
				containsString("successfully linked with status \"instantly_verified\""));
		assertEquals("defaultpt", parsedResponse.getAccountName());
	}

	@Test
	public void ResponseInvalidPublicToken() throws BadRequestException, InvalidSignatureException, ServerSideException,
			IOException, InterruptedException, ForbiddenException {
		// BANKACCOUNT3
		api = new SilaApi(DefaultConfigurations.host, DefaultConfigurations.appHandle, DefaultConfigurations.privateKey);

		ApiResponse response = api.linkAccountPlaidToken(DefaultConfigurations.getUserHandle(),
				DefaultConfigurations.getUserPrivateKey(), "defaultinvalid", "mock_token" + "12345", "123456", "processor");

		assertEquals(400, response.getStatusCode());
		assertEquals("FAILURE", ((BadRequestResponse) response.getData()).getStatus());
	}

	@Test
	public void Response400() throws BadRequestException, InvalidSignatureException, ServerSideException, IOException,
			InterruptedException, ForbiddenException {
		api = new SilaApi(DefaultConfigurations.host, DefaultConfigurations.appHandle, DefaultConfigurations.privateKey);

		ApiResponse response = api.linkAccountPlaidToken("", DefaultConfigurations.getUserPrivateKey(), "default",
				"mock_token", "123456", "processor");
		assertEquals(400, response.getStatusCode());
	}

	@Test
	public void Response401() throws BadRequestException, InvalidSignatureException, ServerSideException, IOException,
			InterruptedException, ForbiddenException {
		api = new SilaApi(DefaultConfigurations.host, DefaultConfigurations.appHandle,
				"3a1076bf45ab87712ad64ccb3b10217737f7faacbf2872e88fdd9a537d8fe266");
		ApiResponse response = api.linkAccountPlaidToken(DefaultConfigurations.getUserHandle(),
				DefaultConfigurations.getUserPrivateKey(), "default", "mock_token", "123456", "processor");

		assertEquals(401, response.getStatusCode());
		assertEquals("FAILURE", ((LinkAccountResponse) response.getData()).getStatus());
	}

	@Test
	public void Response200SuccessWithMX() throws Exception {
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
						"\"message\":\"Bank account successfully linked.\"," +
						"\"account_name\":\"defaultmx\"," +
						"\"provider\":\"MX\"" +
						"}"
		);

		Map<String, List<String>> headersMap = new HashMap<>();
		headersMap.put("Content-Type", List.of("application/json"));
		Mockito.when(httpHeadersMock.map()).thenReturn(headersMap);
		Mockito.when(httpResponseMock.headers()).thenReturn(httpHeadersMock);

		// Mocking the callApi method to return the mocked HttpResponse
		Mockito.when(apiClient.callApi(Mockito.anyString(), Mockito.anyMap(), Mockito.anyString())).thenReturn(httpResponseMock);

		ApiResponse response = api.linkAccountMX(DefaultConfigurations.getUserHandle(),
				DefaultConfigurations.getUserPrivateKey(), "mock_auth_code", "mx","processor");

		assertEquals(200, response.getStatusCode());
		LinkAccountResponse parsedResponse = (LinkAccountResponse) response.getData();
		assertEquals("SUCCESS", parsedResponse.getStatus());
		assertThat(parsedResponse.getProvider(),
				containsString("MX"));
	}
}