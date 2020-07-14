package com.silamoney.client.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.IOException;
import java.util.UUID;

import com.silamoney.client.api.ApiResponse;
import com.silamoney.client.api.SilaApi;
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
public class IssueSilaTests {

	SilaApi api = new SilaApi(DefaultConfigurations.host, DefaultConfigurations.appHandle,
			DefaultConfigurations.privateKey);

	@Test
	public void Response200Success() throws Exception {
		// TRANSACTIONS1
		ApiResponse response = api.issueSila(DefaultConfigurations.getUserHandle(), 100, "default",
				"test descriptor", DefaultConfigurations.correctUuid,
				DefaultConfigurations.getUserPrivateKey());

		assertEquals(200, response.getStatusCode());
		assertEquals("test descriptor",((TransactionResponse) response.getData()).getDescriptor());
		assertEquals("SUCCESS", ((TransactionResponse) response.getData()).getStatus());
		assertNotNull(((TransactionResponse) response.getData()).getTransactionId());
	}

	@Test
	public void Response400() throws BadRequestException, InvalidSignatureException, ServerSideException, IOException,
			InterruptedException, ForbiddenException {
		ApiResponse response = api.issueSila("", 1000, null, 
				"test descriptor", UUID.randomUUID().toString(), DefaultConfigurations.getUserPrivateKey());
		// System.out.println(GsonUtils.objectToJsonStringFormato(response));
		assertEquals(400, response.getStatusCode());
	}
	
	@Test
	public void Response400WrongUuid() throws BadRequestException, InvalidSignatureException, ServerSideException, IOException,
			InterruptedException, ForbiddenException {
		ApiResponse response = api.issueSila("", 1000, null, 
				"test descriptor", DefaultConfigurations.wrongUuid, DefaultConfigurations.getUserPrivateKey());
		// System.out.println(GsonUtils.objectToJsonStringFormato(response));
		assertEquals(400, response.getStatusCode());
	}

	@Test
	public void Response401User() throws BadRequestException, InvalidSignatureException, ServerSideException,
			IOException, InterruptedException, ForbiddenException {
		ApiResponse response = api.issueSila(DefaultConfigurations.getUserHandle(), 1000, "default",
				"test descriptor", UUID.randomUUID().toString(),
				DefaultConfigurations.privateKey);
		assertEquals(401, response.getStatusCode());
	}

	@Test
	public void Response401() throws BadRequestException, InvalidSignatureException, ServerSideException, IOException,
			InterruptedException, ForbiddenException {
		api = new SilaApi(DefaultConfigurations.host, DefaultConfigurations.appHandle,
				"3a1076bf45ab87712ad64ccb3b10217737f7faacbf2872e88fdd9a537d8fe266");

		ApiResponse response = api.issueSila(DefaultConfigurations.getUserHandle(), 1000, null,
				"test descriptor", UUID.randomUUID().toString(),
				DefaultConfigurations.privateKey);
		assertEquals(401, response.getStatusCode());
	}
}
