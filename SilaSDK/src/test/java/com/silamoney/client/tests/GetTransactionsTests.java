package com.silamoney.client.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.hamcrest.Matchers.*;

import java.io.IOException;

import com.silamoney.client.api.ApiResponse;
import com.silamoney.client.api.SilaApi;
import com.silamoney.client.domain.GetTransactionsResponse;
import com.silamoney.client.domain.ProcessingTypeEnum;
import com.silamoney.client.domain.SearchFilters;
import com.silamoney.client.domain.Transaction;
import com.silamoney.client.exceptions.BadRequestException;
import com.silamoney.client.exceptions.ForbiddenException;
import com.silamoney.client.exceptions.InvalidSignatureException;
import com.silamoney.client.exceptions.ServerSideException;
import com.silamoney.client.testsutils.DefaultConfigurations;

import org.junit.Test;

/**
 *
 * @author Karlo Lorenzana
 */
public class GetTransactionsTests {

	SilaApi api = new SilaApi(DefaultConfigurations.host, DefaultConfigurations.appHandle,
			DefaultConfigurations.privateKey);

	@Test
	public void Response200() throws Exception {
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
		assertThat("get transactions - usd status", parsedResponse.transactions.get(0).usdStatus,
				not(isEmptyOrNullString()));
		assertThat("get transactions - token status", parsedResponse.transactions.get(0).tokenStatus,
				not(isEmptyOrNullString()));
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
		SearchFilters filters = new SearchFilters();
		filters.setDestinationId(DefaultConfigurations.getVirtualAccounts().get(0).getVirtualAccountId());
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
		SearchFilters filters = new SearchFilters();
		filters.setPaymentMethodId(DefaultConfigurations.getVirtualAccounts().get(0).getVirtualAccountId());
		ApiResponse response = api.getTransactions(DefaultConfigurations.getUserHandle(), filters,
				DefaultConfigurations.getUserPrivateKey());

		assertEquals(200, response.getStatusCode());
		GetTransactionsResponse parsedResponse = (GetTransactionsResponse) response.getData();
		assertTrue(parsedResponse.success);
		assertEquals("SUCCESS", parsedResponse.status);
		assertTrue(parsedResponse.transactions.size() > 0);
	}
}