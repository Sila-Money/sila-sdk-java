package com.silamoney.client.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.IOException;

import com.silamoney.client.api.ApiResponse;
import com.silamoney.client.api.SilaApi;
import com.silamoney.client.domain.GetWalletsResponse;
import com.silamoney.client.domain.SearchFilters;
import com.silamoney.client.exceptions.BadRequestException;
import com.silamoney.client.exceptions.ForbiddenException;
import com.silamoney.client.exceptions.InvalidSignatureException;
import com.silamoney.client.exceptions.ServerSideException;
import com.silamoney.client.testsutils.DefaultConfigurations;

import org.junit.Test;

/**
 * GetWalletsTests
 * 
 * @author wzelada
 */
public class GetWalletsTests {

	SilaApi api = new SilaApi(DefaultConfigurations.host, DefaultConfigurations.appHandle,
			DefaultConfigurations.privateKey);

	String userHandle = DefaultConfigurations.getUserHandle();
	String userPrivateKey = DefaultConfigurations.getUserPrivateKey();

	@Test
	public void Response200() throws Exception {
		// WALLETS3
		 

		ApiResponse response = api.getWallets(DefaultConfigurations.getUserHandle(), DefaultConfigurations.filters,
				DefaultConfigurations.getUserPrivateKey());

		assertEquals(200, response.getStatusCode());
		assertNotNull(((GetWalletsResponse)response.getData()).page);
		assertNotNull(((GetWalletsResponse)response.getData()).returnedCount);
		assertNotNull(((GetWalletsResponse)response.getData()).totalCount);
		assertNotNull(((GetWalletsResponse)response.getData()).totalPageCount);
		assertNotNull(((GetWalletsResponse)response.getData()).getWallets());
		assertNotNull(((GetWalletsResponse)response.getData()).getWallets().get(0));
	}

	@Test
	public void Response400() throws BadRequestException, InvalidSignatureException, ServerSideException, IOException,
			InterruptedException, ForbiddenException {
		// WALLETS3
		ApiResponse response = api.getWallets(DefaultConfigurations.getUserHandle(), DefaultConfigurations.filters,
				DefaultConfigurations.getUserPrivateKey());

		assertEquals(200, response.getStatusCode());
	}

	@Test
	public void Response401() throws BadRequestException, InvalidSignatureException, ServerSideException, IOException,
			InterruptedException, ForbiddenException {
		// WALLETS3
		 

		SilaApi api401 = new SilaApi(DefaultConfigurations.host, DefaultConfigurations.appHandle,
				"badba7368134dcd61c60f9b56979c09196d03f5891a20c1557b1afac0202a97c");

		ApiResponse response = api401.getWallets(DefaultConfigurations.getUserHandle(), DefaultConfigurations.filters,
				"badba7368134dcd61c60f9b56979c09196d03f5891a20c1557b1afac0202a97c"); //DefaultConfigurations.getUserPrivateKey()

		assertEquals(403, response.getStatusCode());
	}
	@Test
	public void Response200WithUuid() throws Exception {
		// WALLETS3
		ApiResponse response = api.getWallets(DefaultConfigurations.getUserHandle(), DefaultConfigurations.filters,
				DefaultConfigurations.getUserPrivateKey());

		assertEquals(200, response.getStatusCode());
		assertNotNull(((GetWalletsResponse)response.getData()).page);
		assertNotNull(((GetWalletsResponse)response.getData()).returnedCount);
		assertNotNull(((GetWalletsResponse)response.getData()).totalCount);
		assertNotNull(((GetWalletsResponse)response.getData()).totalPageCount);
		assertNotNull(((GetWalletsResponse)response.getData()).getWallets());
		assertNotNull(((GetWalletsResponse)response.getData()).getWallets().get(0));
		SearchFilters filters=new SearchFilters();
		filters.setUuid(((GetWalletsResponse)response.getData()).getWallets().get(0).getWalletId());
		response = api.getWallets(DefaultConfigurations.getUserHandle(), filters,
				DefaultConfigurations.getUserPrivateKey());
		assertEquals(200, response.getStatusCode());
	}
}