package com.silamoney.client.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.IOException;

import com.silamoney.client.api.ApiResponse;
import com.silamoney.client.api.SilaApi;
import com.silamoney.client.domain.GetWalletResponse;
import com.silamoney.client.exceptions.BadRequestException;
import com.silamoney.client.exceptions.ForbiddenException;
import com.silamoney.client.exceptions.InvalidSignatureException;
import com.silamoney.client.exceptions.ServerSideException;
import com.silamoney.client.testsutils.DefaultConfigurations;

import org.junit.Test;

/**
 * GetWalletTests
 * 
 * @author wzelada
 */
public class GetWalletTests {

	SilaApi api = new SilaApi(DefaultConfigurations.host, DefaultConfigurations.appHandle,
			DefaultConfigurations.privateKey);

	 

	@Test
	public void Response200() throws Exception {
		 

		ApiResponse response = api.getWallet(DefaultConfigurations.getUserHandle(),
				DefaultConfigurations.getUserPrivateKey());

		assertEquals(200, response.getStatusCode());
		assertNotNull(((GetWalletResponse)response.getData()).getWallet().getNickname());
		assertNotNull(((GetWalletResponse)response.getData()).getWallet().getBlockChainAddress());
		assertNotNull(((GetWalletResponse)response.getData()).getWallet().getBlockChainNetwork());
		assertNotNull(((GetWalletResponse)response.getData()).getWallet().getWalletId());
		// "reference": "591af81b-fc0d-478a-bc01-4447f8359cce",
		// "message": "Blockchain address 0xd6398d7b1768f29a52c0d5e2f91ff1aa4ce2eaa5 registered."
	}

	@Test
	public void Response400() throws BadRequestException, InvalidSignatureException, ServerSideException, IOException,
			InterruptedException, ForbiddenException {
		 
		ApiResponse response = api.getWallet("", DefaultConfigurations.getUserPrivateKey());
		assertEquals(400, response.getStatusCode());
	}

	@Test
	public void Response403() throws BadRequestException, InvalidSignatureException, ServerSideException, IOException,
			InterruptedException, ForbiddenException {
		api = new SilaApi(DefaultConfigurations.host, DefaultConfigurations.appHandle,
				"3a1076bf45ab87712ad64ccb3b10217737f7faacbf2872e88fdd9a537d8fe266");
		 
		ApiResponse response = api.getWallet(DefaultConfigurations.getUserHandle(),
				DefaultConfigurations.getUserPrivateKey());
		assertEquals(403, response.getStatusCode());
	}
}