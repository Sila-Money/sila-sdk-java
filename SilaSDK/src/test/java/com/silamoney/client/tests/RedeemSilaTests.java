package com.silamoney.client.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import com.silamoney.client.api.ApiResponse;
import com.silamoney.client.api.SilaApi;
import com.silamoney.client.domain.BadRequestResponse;
import com.silamoney.client.domain.GetTransactionsResponse;
import com.silamoney.client.domain.SearchFilters;
import com.silamoney.client.domain.TransactionResponse;
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
public class RedeemSilaTests {

	SilaApi api = new SilaApi(DefaultConfigurations.host, DefaultConfigurations.appHandle,
			DefaultConfigurations.privateKey);

	@Test
	public void Response200Success() throws Exception {

		ApiResponse response = api.redeemSila(DefaultConfigurations.getUserHandle(), 100, "default", "test descriptor",
				DefaultConfigurations.correctUuid, DefaultConfigurations.getUserPrivateKey());

		assertEquals(200, response.getStatusCode());
		assertTrue(((TransactionResponse) response.getData()).getSuccess());
		assertEquals("test descriptor", ((TransactionResponse) response.getData()).getDescriptor());
		assertEquals("SUCCESS", ((TransactionResponse) response.getData()).getStatus());
		assertNotNull(((TransactionResponse) response.getData()).getTransactionId());

		String transactionId = ((TransactionResponse) response.getData()).getTransactionId();
		SearchFilters filters = new SearchFilters();
		filters.setTransactionId(transactionId);
		response = api.getTransactions(DefaultConfigurations.getUserHandle(), filters,
				DefaultConfigurations.getUserPrivateKey());
		while (((GetTransactionsResponse) response.getData()).transactions.get(0).status.equals("queued")
				|| ((GetTransactionsResponse) response.getData()).transactions.get(0).status.equals("pending")) {
			TimeUnit.SECONDS.sleep(20);
			response = api.getTransactions(DefaultConfigurations.getUserHandle(), filters,
					DefaultConfigurations.getUserPrivateKey());
		}

		System.out.println(((GetTransactionsResponse) response.getData()).transactions.get(0).status);
		assertEquals("success", ((GetTransactionsResponse) response.getData()).transactions.get(0).status);
	}

	@Test
	public void Response400() throws BadRequestException, InvalidSignatureException, ServerSideException, IOException,
			InterruptedException, ForbiddenException {

		ApiResponse response = api.redeemSila("", 1000, "Custom Account Name", "test descriptor",
				UUID.randomUUID().toString(), DefaultConfigurations.getUserPrivateKey());
		assertEquals(400, response.getStatusCode());
	}

	@Test
	public void Response400WrongUuiud() throws BadRequestException, InvalidSignatureException, ServerSideException,
			IOException, InterruptedException, ForbiddenException {
		ApiResponse response = api.redeemSila("", 1000, "Custom Account Name", "test descriptor",
				DefaultConfigurations.wrongUuid, DefaultConfigurations.getUserPrivateKey());
		assertEquals(400, response.getStatusCode());
	}

	@Test
	public void Response401() throws BadRequestException, InvalidSignatureException, ServerSideException, IOException,
			InterruptedException, ForbiddenException {
		api = new SilaApi(DefaultConfigurations.host, DefaultConfigurations.appHandle,
				"3a1076bf45ab87712ad64ccb3b10217737f7faacbf2872e88fdd9a537d8fe266");

		ApiResponse response = api.redeemSila(DefaultConfigurations.getUserHandle(), 1000, "default", "test descriptor",
				UUID.randomUUID().toString(), DefaultConfigurations.getUserPrivateKey());
		assertEquals(401, response.getStatusCode());
	}
}
