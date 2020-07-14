package com.silamoney.client.tests;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.Random;

import com.silamoney.client.api.ApiResponse;
import com.silamoney.client.api.SilaApi;
import com.silamoney.client.domain.BaseResponse;
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
public class CheckHandleTests {

	SilaApi api = new SilaApi(DefaultConfigurations.host, DefaultConfigurations.appHandle,
			DefaultConfigurations.privateKey);

	@Test
	public void Response200Success() throws Exception {
		// HANDLE REGISTRATION 1
		ApiResponse response = api.checkHandle(DefaultConfigurations.getUserHandle());

		assertEquals(200, response.getStatusCode());
		assertEquals("SUCCESS", ((BaseResponse) response.getData()).getStatus());
	}

	@Test
	public void Response200Failure() throws Exception {
		// HANDLE5
		ApiResponse response = api.checkHandle("javasdk-893748932");
		assertEquals(200, response.getStatusCode());
		assertEquals("FAILURE", ((BaseResponse) response.getData()).getStatus());
	}

	@Test
	public void Response400() throws BadRequestException, InvalidSignatureException, ServerSideException, IOException,
			InterruptedException, ForbiddenException {
		ApiResponse response = api.checkHandle("");
		assertEquals(400, response.getStatusCode());
	}

	@Test
	public void Response401() throws BadRequestException, InvalidSignatureException, ServerSideException, IOException,
			InterruptedException, ForbiddenException {
		api = new SilaApi(DefaultConfigurations.host, DefaultConfigurations.appHandle,
				"3a1076bf45ab87712ad64ccb3b10217737f7faacbf2872e88fdd9a537d8fe266");
		ApiResponse response = api.checkHandle("javaSDK-" + new Random().nextInt());
		assertEquals(401, response.getStatusCode());
	}
}
