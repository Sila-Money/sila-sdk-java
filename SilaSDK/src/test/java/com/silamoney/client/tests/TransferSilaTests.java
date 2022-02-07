package com.silamoney.client.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.IOException;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import com.silamoney.client.api.ApiResponse;
import com.silamoney.client.api.SilaApi;
import com.silamoney.client.domain.BaseResponse;
import com.silamoney.client.domain.GetTransactionsResponse;
import com.silamoney.client.domain.SearchFilters;
import com.silamoney.client.domain.TransactionResponse;
import com.silamoney.client.domain.TransferSilaResponse;
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
public class TransferSilaTests {

	SilaApi api = new SilaApi(DefaultConfigurations.host, DefaultConfigurations.appHandle,
			DefaultConfigurations.privateKey);

	 

	@Test
	public void Response200Success() throws Exception {
		// TRANSACTIONS4
		 
		ApiResponse response = api.transferSila(DefaultConfigurations.getUserHandle(), 100, DefaultConfigurations.getUser2Handle(), null,
				"test descriptor", DefaultConfigurations.correctUuid,
				DefaultConfigurations.getUserPrivateKey());
				
		assertEquals(200, response.getStatusCode());
		assertEquals("test descriptor", ((TransferSilaResponse) response.getData()).getDescriptor());
		assertEquals("SUCCESS", ((TransferSilaResponse) response.getData()).getStatus());
		assertNotNull(((TransferSilaResponse) response.getData()).getTransactionId());

		String transactionId = ((TransactionResponse) response.getData()).getTransactionId();
		SearchFilters filters = new SearchFilters();
		filters.setTransactionId(transactionId);
		response = api.getTransactions(DefaultConfigurations.getUserHandle(), filters,
				DefaultConfigurations.getUserPrivateKey());
		while (!((GetTransactionsResponse) response.getData()).transactions.get(0).status.equals("success")) {
			TimeUnit.SECONDS.sleep(20);
			response = api.getTransactions(DefaultConfigurations.getUserHandle(), filters,
					DefaultConfigurations.getUserPrivateKey());
		}

		assertEquals("success",((GetTransactionsResponse) response.getData()).transactions.get(0).status);
		assertNotNull(((GetTransactionsResponse) response.getData()).transactions.get(0).destinationLedgerAccountId);
	}

	@Test
	public void Response400() throws BadRequestException, InvalidSignatureException, ServerSideException, IOException,
			InterruptedException, ForbiddenException {
		// TRANSACTIONS5
		 
		ApiResponse response = api.transferSila(DefaultConfigurations.getUserHandle(), 100,
				DefaultConfigurations.getUserPrivateKey(), DefaultConfigurations.getUserPrivateKey(),
				"test descriptor", UUID.randomUUID().toString(),
				DefaultConfigurations.getUserPrivateKey());
		assertEquals(400, response.getStatusCode());
	}
	
	@Test
	public void Response400WrongUuid() throws BadRequestException, InvalidSignatureException, ServerSideException, IOException,
			InterruptedException, ForbiddenException {
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
	public void Response200SuccessWithVirtualAccount() throws Exception {
		// TRANSACTIONS4
		ApiResponse response = api.transferSila(DefaultConfigurations.getUserHandle(), 100, DefaultConfigurations.getUser2Handle(), null,
				"test descriptor", DefaultConfigurations.correctUuid,
				DefaultConfigurations.getUserPrivateKey(),DefaultConfigurations.getVirtualAccounts().get(0).getVirtualAccountId(),DefaultConfigurations.getVirtualAccounts2().getVirtualAccountId());
		assertEquals(200, response.getStatusCode());
		assertEquals("test descriptor", ((TransferSilaResponse) response.getData()).getDescriptor());
		assertEquals("SUCCESS", ((TransferSilaResponse) response.getData()).getStatus());
		assertNotNull(((TransferSilaResponse) response.getData()).getTransactionId());

		String transactionId = ((TransactionResponse) response.getData()).getTransactionId();
		SearchFilters filters = new SearchFilters();
		filters.setTransactionId(transactionId);
		response = api.getTransactions(DefaultConfigurations.getUserHandle(), filters,
				DefaultConfigurations.getUserPrivateKey());
		while (!((GetTransactionsResponse) response.getData()).transactions.get(0).status.equals("success")) {
			TimeUnit.SECONDS.sleep(20);
			response = api.getTransactions(DefaultConfigurations.getUserHandle(), filters,
					DefaultConfigurations.getUserPrivateKey());
		}

		assertEquals("success",((GetTransactionsResponse) response.getData()).transactions.get(0).status);
		assertNotNull(((GetTransactionsResponse) response.getData()).transactions.get(0).silaLedgerType);
		assertNotNull(((GetTransactionsResponse) response.getData()).transactions.get(0).sourceId);
		assertNotNull(((GetTransactionsResponse) response.getData()).transactions.get(0).destinationId);
		assertNotNull(((GetTransactionsResponse) response.getData()).transactions.get(0).destinationSilaLedgerType);

	}

	@Test
	public void Response200SuccessWithVirtualAccountToBlockchain() throws Exception {
		// TRANSACTIONS4
		ApiResponse response = api.transferSila(DefaultConfigurations.getUserHandle(), 100, DefaultConfigurations.getUserHandle(), null,
				"test descriptor", DefaultConfigurations.correctUuid,
				DefaultConfigurations.getUserPrivateKey(),DefaultConfigurations.getVirtualAccounts().get(0).getVirtualAccountId(),DefaultConfigurations.getPaymentMethods().get(0).getBlockchainAddressId());
		assertEquals(200, response.getStatusCode());
		assertEquals("test descriptor", ((TransferSilaResponse) response.getData()).getDescriptor());
		assertEquals("SUCCESS", ((TransferSilaResponse) response.getData()).getStatus());
		assertNotNull(((TransferSilaResponse) response.getData()).getTransactionId());

		String transactionId = ((TransactionResponse) response.getData()).getTransactionId();
		SearchFilters filters = new SearchFilters();
		filters.setTransactionId(transactionId);
		response = api.getTransactions(DefaultConfigurations.getUserHandle(), filters,
				DefaultConfigurations.getUserPrivateKey());
		while (!((GetTransactionsResponse) response.getData()).transactions.get(0).status.equals("success")) {
			TimeUnit.SECONDS.sleep(20);
			response = api.getTransactions(DefaultConfigurations.getUserHandle(), filters,
					DefaultConfigurations.getUserPrivateKey());
		}

		assertEquals("success",((GetTransactionsResponse) response.getData()).transactions.get(0).status);
		assertNotNull(((GetTransactionsResponse) response.getData()).transactions.get(0).silaLedgerType);
		assertNotNull(((GetTransactionsResponse) response.getData()).transactions.get(0).sourceId);
		assertNotNull(((GetTransactionsResponse) response.getData()).transactions.get(0).destinationId);
		assertNotNull(((GetTransactionsResponse) response.getData()).transactions.get(0).destinationSilaLedgerType);
		assertNotNull(((GetTransactionsResponse) response.getData()).transactions.get(0).destinationLedgerAccountId);

	}

}
