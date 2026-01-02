package com.silamoney.client.tests;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

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
import com.silamoney.client.domain.GetTransactionsResponse;
import com.silamoney.client.domain.ProcessingTypeEnum;
import com.silamoney.client.domain.SearchFilters;
import com.silamoney.client.domain.Transaction;
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
public class GetTransactionsTests {
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
						"\"descriptor\":\"test descriptor\"," +
						"\"message\":\"Transaction submitted to the processing queue.\"," +
						"\"success\":true," +
						"\"pagination\": {" +
						"\"returned_count\": 4," +
						"\"total_count\": 4," +
						"\"current_page\": 1," +
						"\"total_pages\": 1" +
						"}," +
						"\"transactions\": [" +
						"{" +
						"\"user_handle\": \"user\"," +
						"\"reference_id\": \"chosen_id\"," +
						"\"transaction_id\": \"12345678-abcd-1234-abcd-1234567890aa\"," +
						"\"transaction_hash\": \"0x1234567890abcdef1234567890abcdef1234567890abcdef1234567890abcdee\"," +
						"\"transaction_type\": \"transfer\"," +
						"\"provider_tx_id\": null," +
						"\"sila_amount\": 100," +
						"\"status\": \"success\"," +
						"\"extended_status\": null," +
						"\"provider_status\": null," +
						"\"created\": \"2020-03-09T17:45:59.709910Z\"," +
						"\"last_update\": \"2020-03-09T17:45:59.709920Z\"," +
						"\"created_epoch\": 1582754399," +
						"\"last_update_epoch\": 1582754414," +
						"\"descriptor\": \"\"," +
						"\"descriptor_ach\": \"\"," +
						"\"IMAD\": \"\"," +
						"\"OMAD\": \"\"," +
						"\"ach_name\": \"\"," +
						"\"destination_address\": \"0xF08D203754F8960C3655Aa00Bde006DB78fdBf5d\"," +
						"\"destination_handle\": \"user\"," +
						"\"handle_address\": \"0x65a796a4bD3AaF6370791BefFb1A86EAcfdBc3C1\"," +
						"\"source_id\": \"37559d1e-2848-4109-a7d8-a8b250c6e863\"," +
						"\"destination_id\": \"ebd20435-02d4-4fe2-8573-777e712974e8\"," +
						"\"sec_code\": \"PPD\"," +
						"\"timeline\": [" +
						"{" +
						"\"date\": \"2020-03-09T17:45:59.716909Z\"," +
						"\"date_epoch\": 1582754399," +
						"\"status\": \"queued\"" +
						"}," +
						"{" +
						"\"date\": \"2020-03-09T17:45:59.727218Z\"," +
						"\"date_epoch\": 1582754400," +
						"\"status\": \"pending\"" +
						"}," +
						"{" +
						"\"date\": \"2020-03-09T17:45:59.728691Z\"," +
						"\"date_epoch\": 1582754414," +
						"\"status\": \"success\"" +
						"}" +
						"]" +
						"}," +
						"{" +
						"\"user_handle\": \"user\"," +
						"\"reference_id\": \"chosen_id\"," +
						"\"transaction_id\": \"12345678-abcd-1234-abcd-1234567890aa\"," +
						"\"transaction_hash\": \"0x1234567890abcdef1234567890abcdef1234567890abcdef1234567890abcdee\"," +
						"\"transaction_type\": \"transfer\"," +
						"\"provider_tx_id\": null," +
						"\"sila_amount\": 100," +
						"\"status\": \"success\"," +
						"\"extended_status\": null," +
						"\"provider_status\": null," +
						"\"created\": \"2020-03-09T17:45:59.709910Z\"," +
						"\"last_update\": \"2020-03-09T17:45:59.709920Z\"," +
						"\"created_epoch\": 1582754399," +
						"\"last_update_epoch\": 1582754414," +
						"\"descriptor\": \"\"," +
						"\"descriptor_ach\": \"\"," +
						"\"error_code\": \"ACH_RETURN\"," +
						"\"error_msg\": \"An ACH return was received for this transaction.\"," +
						"\"error_description\": \"possible additional error message.\"," +
						"\"return_code\": \"R01\"," +
						"\"return_desc\": \"Insufficient funds\"," +
						"\"ach_name\": \"\"," +
						"\"destination_address\": \"0xF08D203754F8960C3655Aa00Bde006DB78fdBf5d\"," +
						"\"destination_handle\": \"user\"," +
						"\"handle_address\": \"0x65a796a4bD3AaF6370791BefFb1A86EAcfdBc3C1\"," +
						"\"source_id\": \"37559d1e-2848-4109-a7d8-a8b250c6e863\"," +
						"\"destination_id\": \"ebd20435-02d4-4fe2-8573-777e712974e8\"," +
						"\"sec_code\": \"PPD\"," +
						"\"timeline\": [" +
						"{" +
						"\"date\": \"2020-03-09T17:45:59.716909Z\"," +
						"\"date_epoch\": 1582754399," +
						"\"status\": \"queued\"" +
						"}," +
						"{" +
						"\"date\": \"2020-03-09T17:45:59.727218Z\"," +
						"\"date_epoch\": 1582754400," +
						"\"status\": \"pending\"" +
						"}," +
						"{" +
						"\"date\": \"2020-03-09T17:45:59.728691Z\"," +
						"\"date_epoch\": 1582754414," +
						"\"status\": \"success\"" +
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

		SearchFilters filters = new SearchFilters();
		ApiResponse response = api.getTransactions(DefaultConfigurations.getUserHandle(), filters,
				DefaultConfigurations.getUserPrivateKey());

		assertEquals(200, response.getStatusCode());
		GetTransactionsResponse parsedResponse = (GetTransactionsResponse) response.getData();
		assertTrue(parsedResponse.success);
		assertEquals("SUCCESS", parsedResponse.status);
		assertThat("get transactions - size", parsedResponse.transactions.size(), greaterThan(1));
		assertThat("get transactions - user address", parsedResponse.transactions.get(0).userHandle,
				not(isEmptyOrNullString()));
		assertThat("get transactions - reference id", parsedResponse.transactions.get(0).referenceId,
				not(isEmptyOrNullString()));
		assertThat("get transactions - transaction id", parsedResponse.transactions.get(0).transactionId,
				not(isEmptyOrNullString()));
		assertTrue(parsedResponse.transactions.get(0).transactionHash != null);
		assertThat("get transactions - transaction type", parsedResponse.transactions.get(0).transactionType,
				not(isEmptyOrNullString()));
		assertThat("get transactions - sila amount", parsedResponse.transactions.get(0).silaAmount, greaterThan(0));
		assertThat("get transactions - status", parsedResponse.transactions.get(0).status, not(isEmptyOrNullString()));
		assertThat("get transactions - created", parsedResponse.transactions.get(0).created,
				not(isEmptyOrNullString()));
		assertThat("get transactions - last update", parsedResponse.transactions.get(0).lastUpdate,
				not(isEmptyOrNullString()));
		assertThat("get transactions - created epoch", parsedResponse.transactions.get(0).createdEpoch, greaterThan(0));
		assertThat("get transactions - last update epoch", parsedResponse.transactions.get(0).lastUpdateEpoch,
				greaterThan(0));
		assertTrue(parsedResponse.transactions.get(0).descriptor != null);
		assertTrue(parsedResponse.transactions.get(0).descriptorAch != null);
		assertTrue(parsedResponse.transactions.get(0).achName != null);
		if (parsedResponse.transactions.get(0).transactionType.equals("transfer")) {
			assertTrue(parsedResponse.transactions.get(0).destinationAddress != null);
			assertTrue(parsedResponse.transactions.get(0).destinationHandle != null);
			assertTrue(parsedResponse.transactions.get(0).handleAddress != null);
		} else {
			assertTrue(parsedResponse.transactions.get(0).processingType != null);
		}

		for (Transaction transaction : parsedResponse.transactions) {
			if (transaction.errorCode != null && transaction.errorCode.equals("ACH_RETURN")) {
				assertNotNull(transaction.returnCode);
				assertNotNull(transaction.returnDesc);
			}
		}
	}

	@Test
	public void Response400() throws Exception {
		SilaApi api = new SilaApi(DefaultConfigurations.host, DefaultConfigurations.appHandle,
				DefaultConfigurations.privateKey);
		ApiResponse response = api.getTransactions("", DefaultConfigurations.filters,
				DefaultConfigurations.getUserPrivateKey());
		assertEquals(400, response.getStatusCode());
	}

	@Test
	public void Response403() throws BadRequestException, InvalidSignatureException, ServerSideException, IOException,
			InterruptedException, ForbiddenException {

		api = new SilaApi(DefaultConfigurations.host, DefaultConfigurations.appHandle,
				"3a1076bf45ab87712ad64ccb3b10217737f7faacbf2872e88fdd9a537d8fe266");

		ApiResponse response = api.getTransactions(DefaultConfigurations.getUserHandle(), DefaultConfigurations.filters,
				DefaultConfigurations.getUserPrivateKey());
		assertEquals(403, response.getStatusCode());
	}

	@Test
	public void Response200WithSourceIdDestinationId() throws Exception {
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
						"\"pagination\": {" +
						"\"returned_count\": 4," +
						"\"total_count\": 4," +
						"\"current_page\": 1," +
						"\"total_pages\": 1" +
						"}," +
						"\"transactions\": [" +
						"{" +
						"\"user_handle\": \"user\"," +
						"\"reference_id\": \"<your unique id>\"," +
						"\"transaction_id\": \"12345678-abcd-1234-abcd-1234567890aa\"," +
						"\"transaction_hash\": \"0x1234567890abcdef1234567890abcdef1234567890abcdef1234567890abcdee\"," +
						"\"transaction_type\": \"transfer\"," +
						"\"sila_ledger_type\":\"Internal\"," +
						"\"provider_tx_id\": null," +
						"\"sila_amount\": 100," +
						"\"status\": \"success\"," +
						"\"extended_status\": null," +
						"\"provider_status\": null," +
						"\"created\": \"2020-03-09T17:45:59.709910Z\"," +
						"\"last_update\": \"2020-03-09T17:45:59.709920Z\"," +
						"\"created_epoch\": 1582754399," +
						"\"last_update_epoch\": 1582754414," +
						"\"descriptor\": \"\"," +
						"\"descriptor_ach\": \"\"," +
						"\"IMAD\": \"\"," +
						"\"OMAD\": \"\"," +
						"\"ach_name\": \"\"," +
						"\"destination_address\": \"0xF08D203754F8960C3655Aa00Bde006DB78fdBf5d\"," +
						"\"destination_handle\": \"user\"," +
						"\"handle_address\": \"0x65a796a4bD3AaF6370791BefFb1A86EAcfdBc3C1\"," +
						"\"source_id\": \"37559d1e-2848-4109-a7d8-a8b250c6e863\"," +
						"\"destination_id\": \"ebd20435-02d4-4fe2-8573-777e712974e8\"," +
						"\"sec_code\": \"PPD\"," +
						"\"timeline\": [" +
						"{" +
						"\"date\": \"2020-03-09T17:45:59.716909Z\"," +
						"\"date_epoch\": 1582754399," +
						"\"status\": \"queued\"" +
						"}," +
						"{" +
						"\"date\": \"2020-03-09T17:45:59.727218Z\"," +
						"\"date_epoch\": 1582754400," +
						"\"status\": \"pending\"" +
						"}," +
						"{" +
						"\"date\": \"2020-03-09T17:45:59.728691Z\"," +
						"\"date_epoch\": 1582754414," +
						"\"status\": \"success\"" +
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

		SearchFilters filters = new SearchFilters();
		filters.setDestinationId(DefaultConfigurations.getWalletId());
		ApiResponse response = api.getTransactions(DefaultConfigurations.getUserHandle(), filters,
				DefaultConfigurations.getUserPrivateKey());

		assertEquals(200, response.getStatusCode());
		GetTransactionsResponse parsedResponse = (GetTransactionsResponse) response.getData();
		assertTrue(parsedResponse.success);
		assertEquals("SUCCESS", parsedResponse.status);
		assertTrue(parsedResponse.transactions.size()>0);
		assertNotNull(parsedResponse.transactions.get(0).silaLedgerType);
		assertNotNull(parsedResponse.transactions.get(0).sourceId);
		assertNotNull(parsedResponse.transactions.get(0).destinationId);
	}

	@Test
	public void Response200WithInstantSettlement() throws Exception {
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
						"\"pagination\": {" +
						"\"returned_count\": 4," +
						"\"total_count\": 4," +
						"\"current_page\": 1," +
						"\"total_pages\": 1" +
						"}," +
						"\"transactions\": [" +
						"{" +
						"\"user_handle\": \"user\"," +
						"\"reference_id\": \"chosen_id\"," +
						"\"transaction_id\": \"12345678-abcd-1234-abcd-1234567890aa\"," +
						"\"transaction_hash\": \"0x1234567890abcdef1234567890abcdef1234567890abcdef1234567890abcdee\"," +
						"\"transaction_type\": \"issue\"," +
						"\"processing_type\": \"INSTANT_SETTLEMENT\"," +
						"\"provider_tx_id\": null," +
						"\"sila_amount\": 100," +
						"\"status\": \"success\"," +
						"\"extended_status\": null," +
						"\"provider_status\": null," +
						"\"created\": \"2020-03-09T17:45:59.709910Z\"," +
						"\"last_update\": \"2020-03-09T17:45:59.709920Z\"," +
						"\"created_epoch\": 1582754399," +
						"\"last_update_epoch\": 1582754414," +
						"\"descriptor\": \"\"," +
						"\"descriptor_ach\": \"\"," +
						"\"IMAD\": \"\"," +
						"\"OMAD\": \"\"," +
						"\"ach_name\": \"\"," +
						"\"destination_address\": \"0xF08D203754F8960C3655Aa00Bde006DB78fdBf5d\"," +
						"\"destination_handle\": \"user\"," +
						"\"handle_address\": \"0x65a796a4bD3AaF6370791BefFb1A86EAcfdBc3C1\"," +
						"\"source_id\": \"37559d1e-2848-4109-a7d8-a8b250c6e863\"," +
						"\"destination_id\": \"ebd20435-02d4-4fe2-8573-777e712974e8\"," +
						"\"sec_code\": \"PPD\"," +
						"\"timeline\": [" +
						"{" +
						"\"date\": \"2020-03-09T17:45:59.716909Z\"," +
						"\"date_epoch\": 1582754399," +
						"\"status\": \"queued\"" +
						"}," +
						"{" +
						"\"date\": \"2020-03-09T17:45:59.727218Z\"," +
						"\"date_epoch\": 1582754400," +
						"\"status\": \"pending\"" +
						"}," +
						"{" +
						"\"date\": \"2020-03-09T17:45:59.728691Z\"," +
						"\"date_epoch\": 1582754414," +
						"\"status\": \"success\"" +
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

		SearchFilters filters = new SearchFilters();
		filters.setProcessingType(ProcessingTypeEnum.INSTANT_SETTLEMENT);
		ApiResponse response = api.getTransactions(DefaultConfigurations.getUserHandle(), filters,
				DefaultConfigurations.getUserPrivateKey());

		assertEquals(200, response.getStatusCode());
		GetTransactionsResponse parsedResponse = (GetTransactionsResponse) response.getData();
		assertTrue(parsedResponse.success);
		assertEquals("SUCCESS", parsedResponse.status);
		assertTrue(parsedResponse.transactions.size()>0);
		assertEquals(ProcessingTypeEnum.INSTANT_SETTLEMENT.getValue(),parsedResponse.transactions.get(0).processingType);
	}

	@Test
	public void Response200WithPaymentMethodId() throws Exception {
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
						"\"pagination\": {" +
						"\"returned_count\": 4," +
						"\"total_count\": 4," +
						"\"current_page\": 1," +
						"\"total_pages\": 1" +
						"}," +
						"\"transactions\": [" +
						"{" +
						"\"user_handle\": \"user\"," +
						"\"reference_id\": \"<your unique id>\"," +
						"\"transaction_id\": \"12345678-abcd-1234-abcd-1234567890aa\"," +
						"\"transaction_hash\": \"0x1234567890abcdef1234567890abcdef1234567890abcdef1234567890abcdee\"," +
						"\"transaction_type\": \"transfer\"," +
						"\"provider_tx_id\": null," +
						"\"sila_amount\": 100," +
						"\"status\": \"success\"," +
						"\"extended_status\": null," +
						"\"provider_status\": null," +
						"\"created\": \"2020-03-09T17:45:59.709910Z\"," +
						"\"last_update\": \"2020-03-09T17:45:59.709920Z\"," +
						"\"created_epoch\": 1582754399," +
						"\"last_update_epoch\": 1582754414," +
						"\"descriptor\": \"\"," +
						"\"descriptor_ach\": \"\"," +
						"\"IMAD\": \"\"," +
						"\"OMAD\": \"\"," +
						"\"ach_name\": \"\"," +
						"\"destination_address\": \"0xF08D203754F8960C3655Aa00Bde006DB78fdBf5d\"," +
						"\"destination_handle\": \"user\"," +
						"\"handle_address\": \"0x65a796a4bD3AaF6370791BefFb1A86EAcfdBc3C1\"," +
						"\"source_id\": \"37559d1e-2848-4109-a7d8-a8b250c6e863\"," +
						"\"destination_id\": \"ebd20435-02d4-4fe2-8573-777e712974e8\"," +
						"\"sec_code\": \"PPD\"," +
						"\"timeline\": [" +
						"{" +
						"\"date\": \"2020-03-09T17:45:59.716909Z\"," +
						"\"date_epoch\": 1582754399," +
						"\"status\": \"queued\"" +
						"}," +
						"{" +
						"\"date\": \"2020-03-09T17:45:59.727218Z\"," +
						"\"date_epoch\": 1582754400," +
						"\"status\": \"pending\"" +
						"}," +
						"{" +
						"\"date\": \"2020-03-09T17:45:59.728691Z\"," +
						"\"date_epoch\": 1582754414," +
						"\"status\": \"success\"" +
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

		SearchFilters filters = new SearchFilters();
		filters.setPaymentMethodId(DefaultConfigurations.getWalletId());
		ApiResponse response = api.getTransactions(DefaultConfigurations.getUserHandle(), filters,
				DefaultConfigurations.getUserPrivateKey());

		assertEquals(200, response.getStatusCode());
		GetTransactionsResponse parsedResponse = (GetTransactionsResponse) response.getData();
		assertTrue(parsedResponse.success);
		assertEquals("SUCCESS", parsedResponse.status);
		assertTrue(parsedResponse.transactions.size() > 0);
	}

	@Test
	public void Response200WithoutUserHandle() throws Exception {
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
						"\"pagination\": {" +
						"\"returned_count\": 4," +
						"\"total_count\": 4," +
						"\"current_page\": 1," +
						"\"total_pages\": 1" +
						"}," +
						"\"transactions\": [" +
						"{" +
						"\"user_handle\": \"user\"," +
						"\"reference_id\": \"<your unique id>\"," +
						"\"transaction_id\": \"12345678-abcd-1234-abcd-1234567890aa\"," +
						"\"transaction_hash\": \"0x1234567890abcdef1234567890abcdef1234567890abcdef1234567890abcdee\"," +
						"\"transaction_type\": \"transfer\"," +
						"\"provider_tx_id\": null," +
						"\"sila_amount\": 100," +
						"\"status\": \"success\"," +
						"\"extended_status\": null," +
						"\"provider_status\": null," +
						"\"created\": \"2020-03-09T17:45:59.709910Z\"," +
						"\"last_update\": \"2020-03-09T17:45:59.709920Z\"," +
						"\"created_epoch\": 1582754399," +
						"\"last_update_epoch\": 1582754414," +
						"\"descriptor\": \"\"," +
						"\"descriptor_ach\": \"\"," +
						"\"IMAD\": \"\"," +
						"\"OMAD\": \"\"," +
						"\"ach_name\": \"\"," +
						"\"destination_address\": \"0xF08D203754F8960C3655Aa00Bde006DB78fdBf5d\"," +
						"\"destination_handle\": \"user\"," +
						"\"handle_address\": \"0x65a796a4bD3AaF6370791BefFb1A86EAcfdBc3C1\"," +
						"\"source_id\": \"37559d1e-2848-4109-a7d8-a8b250c6e863\"," +
						"\"destination_id\": \"ebd20435-02d4-4fe2-8573-777e712974e8\"," +
						"\"sec_code\": \"PPD\"," +
						"\"timeline\": [" +
						"{" +
						"\"date\": \"2020-03-09T17:45:59.716909Z\"," +
						"\"date_epoch\": 1582754399," +
						"\"status\": \"queued\"" +
						"}," +
						"{" +
						"\"date\": \"2020-03-09T17:45:59.727218Z\"," +
						"\"date_epoch\": 1582754400," +
						"\"status\": \"pending\"" +
						"}," +
						"{" +
						"\"date\": \"2020-03-09T17:45:59.728691Z\"," +
						"\"date_epoch\": 1582754414," +
						"\"status\": \"success\"" +
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

		ApiResponse response = api.getTransactions(DefaultConfigurations.filters);
		assertEquals(200, response.getStatusCode());
		GetTransactionsResponse parsedResponse = (GetTransactionsResponse) response.getData();
		assertTrue(parsedResponse.success);
		assertEquals("SUCCESS", parsedResponse.status);
		assertTrue(parsedResponse.transactions.size() > 0);
	}
}
