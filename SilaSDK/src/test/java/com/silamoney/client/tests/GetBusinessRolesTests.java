package com.silamoney.client.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.IOException;

import com.silamoney.client.api.ApiResponse;
import com.silamoney.client.api.SilaApi;
import com.silamoney.client.domain.GetBusinessRolesResponse;
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
public class GetBusinessRolesTests {

	SilaApi api = new SilaApi(DefaultConfigurations.host, DefaultConfigurations.appHandle,
			DefaultConfigurations.privateKey);

	static String userHandle = "";

	@Test
	public void Response200Success() throws Exception {
		ApiResponse response = api.getBusinessRoles();

		assertEquals(200, response.getStatusCode());
		assertTrue(((GetBusinessRolesResponse) response.getData()).getBusinessRoles().size() > 0);

		DefaultConfigurations.setBusinessRoles(((GetBusinessRolesResponse) response.getData()).getBusinessRoles());
	}

	@Test
	public void Response400() throws BadRequestException, InvalidSignatureException, ServerSideException, IOException,
			InterruptedException, ForbiddenException {

		api = new SilaApi(DefaultConfigurations.host, "", DefaultConfigurations.privateKey);

		ApiResponse response = api.getBusinessRoles();
		assertEquals(400, response.getStatusCode());
	}

	@Test
	public void Response401() throws BadRequestException, InvalidSignatureException, ServerSideException, IOException,
			InterruptedException, ForbiddenException {
		api = new SilaApi(DefaultConfigurations.host, DefaultConfigurations.appHandle,
				"3a1076bf45ab87712ad64ccb3b10217737f7faacbf2872e88fdd9a537d8fe266");
		ApiResponse response = api.getBusinessRoles();
		assertEquals(403, response.getStatusCode());
	}
}
