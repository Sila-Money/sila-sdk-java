package com.silamoney.client.tests;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;

import java.io.IOException;

import com.silamoney.client.api.ApiResponse;
import com.silamoney.client.api.SilaApi;
import com.silamoney.client.domain.BadRequestResponse;
import com.silamoney.client.domain.LinkAccountResponse;
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
public class LinkAccountTests {

	SilaApi api = new SilaApi(DefaultConfigurations.host, DefaultConfigurations.appHandle,
			DefaultConfigurations.privateKey);

	@Test
	public void Response200Success() throws Exception {
		// BANKACCOUNT1
		ApiResponse response = api.linkAccountPlaidToken(DefaultConfigurations.getUserHandle(),
				DefaultConfigurations.getUserPrivateKey(), "defaultpt", DefaultConfigurations.getPlaidToken(), null, "legacy");

		assertEquals(200, response.getStatusCode());
		LinkAccountResponse parsedResponse = (LinkAccountResponse) response.getData();
		assertEquals("SUCCESS", parsedResponse.getStatus());
		assertThat(parsedResponse.getMessage(),
				containsString("successfully linked with status \"instantly_verified\""));
		assertEquals("defaultpt", parsedResponse.getAccountName());
	}

	@Test
	public void ResponseInvalidPublicToken() throws BadRequestException, InvalidSignatureException, ServerSideException,
			IOException, InterruptedException, ForbiddenException {
		// BANKACCOUNT3
		ApiResponse response = api.linkAccountPlaidToken(DefaultConfigurations.getUserHandle(),
				DefaultConfigurations.getUserPrivateKey(), "default", DefaultConfigurations.getPlaidToken() + "12345", "123456", "processor");

		assertEquals(400, response.getStatusCode());
		assertEquals("FAILURE", ((BadRequestResponse) response.getData()).getStatus());
	}

	@Test
	public void Response400() throws BadRequestException, InvalidSignatureException, ServerSideException, IOException,
			InterruptedException, ForbiddenException {
		ApiResponse response = api.linkAccountPlaidToken("", DefaultConfigurations.getUserPrivateKey(), "default",
				DefaultConfigurations.getPlaidToken(), "123456", "processor");
		assertEquals(400, response.getStatusCode());
	}

	@Test
	public void Response401() throws BadRequestException, InvalidSignatureException, ServerSideException, IOException,
			InterruptedException, ForbiddenException {
		api = new SilaApi(DefaultConfigurations.host, DefaultConfigurations.appHandle,
				"3a1076bf45ab87712ad64ccb3b10217737f7faacbf2872e88fdd9a537d8fe266");
		ApiResponse response = api.linkAccountPlaidToken(DefaultConfigurations.getUserHandle(),
				DefaultConfigurations.getUserPrivateKey(), "default", DefaultConfigurations.getPlaidToken(), "123456", "processor");

		assertEquals(401, response.getStatusCode());
		assertEquals("FAILURE", ((LinkAccountResponse) response.getData()).getStatus());
	}

	@Test
	public void Response200SuccessWithMX() throws Exception {
		ApiResponse response = api.linkAccountMX(DefaultConfigurations.getUserHandle(),
				DefaultConfigurations.getUserPrivateKey(),DefaultConfigurations.getProviderToken(),"mx","processor");

		assertEquals(200, response.getStatusCode());
		LinkAccountResponse parsedResponse = (LinkAccountResponse) response.getData();
		assertEquals("SUCCESS", parsedResponse.getStatus());
		assertThat(parsedResponse.getProvider(),
				containsString("MX"));
	}
}