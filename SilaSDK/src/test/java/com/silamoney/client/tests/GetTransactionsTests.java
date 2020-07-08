package com.silamoney.client.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.IOException;

import com.silamoney.client.api.ApiResponse;
import com.silamoney.client.api.SilaApi;
import com.silamoney.client.domain.GetTransactionsResponse;
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
		// TRANSACTIONS3
		ApiResponse response = api.getTransactions(DefaultConfigurations.getUserHandle(), DefaultConfigurations.filters,
				DefaultConfigurations.getUserPrivateKey());

		assertEquals(200, response.getStatusCode());
		assertTrue(((GetTransactionsResponse) response.getData()).success);
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
}
