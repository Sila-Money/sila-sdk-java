package com.silamoney.client.tests;

import com.silamoney.client.api.ApiResponse;
import com.silamoney.client.api.SilaApi;
import com.silamoney.client.domain.GetTransactionsResponse;
import com.silamoney.client.exceptions.BadRequestException;
import com.silamoney.client.exceptions.ForbiddenException;
import com.silamoney.client.exceptions.InvalidSignatureException;
import com.silamoney.client.exceptions.ServerSideException;
import com.silamoney.client.testsutils.DefaultConfigurations;
import java.io.IOException;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Karlo Lorenzana
 */
public class GetTransactionsTests {

	SilaApi api = new SilaApi(DefaultConfigurations.host, DefaultConfigurations.appHandle,
			DefaultConfigurations.privateKey);

	@Test
	public void Response200() throws Exception {		
		ApiResponse response = api.getTransactions(DefaultConfigurations.getUserHandle(), DefaultConfigurations.filters,
				DefaultConfigurations.getUserPrivateKey());

		assertEquals(200, response.getStatusCode());
		assertTrue(((GetTransactionsResponse) response.getData()).success);
	}

	@Test(expected = BadRequestException.class)
	public void Response400() throws Exception {
		api.getTransactions("", DefaultConfigurations.filters, DefaultConfigurations.getUserPrivateKey());
	}

	/*@Test(expected = ForbiddenException.class)
	public void Response403User() throws BadRequestException, InvalidSignatureException, ServerSideException, IOException,
			InterruptedException, ForbiddenException {
		api.GetTransactions(DefaultConfigurations.getUserHandle(), DefaultConfigurations.filters,
				DefaultConfigurations.privateKey);
	}*/

	@Test(expected = ForbiddenException.class)
	public void Response403() throws BadRequestException, InvalidSignatureException, ServerSideException, IOException,
			InterruptedException, ForbiddenException {
		api = new SilaApi(DefaultConfigurations.host, DefaultConfigurations.appHandle,
				"3a1076bf45ab87712ad64ccb3b10217737f7faacbf2872e88fdd9a537d8fe266");
		
		api.getTransactions(DefaultConfigurations.getUserHandle(), DefaultConfigurations.filters,
				DefaultConfigurations.getUserPrivateKey());
	}

	/*
	 * @Test(expected = ServerSideException.class) public void Response401() throws
	 * BadRequestException, InvalidSignatureException, ServerSideException,
	 * IOException, InterruptedException, ForbiddenException { api.GetTransactions(
	 * "serverside.silamoney.eth", DefaultConfigurations.filters,
	 * DefaultConfigurations.getUserPrivateKey() ); }
	 */
}
