package com.silamoney.client.tests;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import com.silamoney.client.api.ApiResponse;
import com.silamoney.client.api.SilaApi;
import com.silamoney.client.domain.BaseResponse;
import com.silamoney.client.domain.RequestKycResponse;
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
public class RequestKYCTests {

	SilaApi api = new SilaApi(DefaultConfigurations.host, DefaultConfigurations.appHandle,
			DefaultConfigurations.privateKey);

	@Test
	public void response200() throws Exception {
		ApiResponse response = api.requestKYC(DefaultConfigurations.getUserHandle(), "INSTANT-ACH",
				DefaultConfigurations.getUserPrivateKey());

		assertEquals(200, response.getStatusCode());
		RequestKycResponse parsedResponse= (RequestKycResponse) response.getData();
		assertEquals("SUCCESS", parsedResponse.getStatus());

		response = api.requestKYC(DefaultConfigurations.getUser2Handle(), null,
				DefaultConfigurations.getUser2PrivateKey());
		assertEquals(200, response.getStatusCode());
		assertEquals("SUCCESS", ((BaseResponse) response.getData()).getStatus());
	}

	@Test
	public void response200Business() throws Exception {
		// KYCID1
		ApiResponse response = api.requestKYC(DefaultConfigurations.getBusinessHandle(), null,
				DefaultConfigurations.getBusinessPrivateKey());

		assertEquals(200, response.getStatusCode());
		assertEquals("SUCCESS", ((BaseResponse) response.getData()).getStatus());
	}

	@Test
	public void response403() throws BadRequestException, InvalidSignatureException, ServerSideException, IOException,
			InterruptedException, ForbiddenException {
		// KYCID4
		ApiResponse response = api.requestKYC(DefaultConfigurations.getUserHandle(), "FAIL",
				DefaultConfigurations.getUserPrivateKey());

		assertEquals(403, response.getStatusCode());
	}

	@Test
	public void response401() throws BadRequestException, InvalidSignatureException, ServerSideException, IOException,
			InterruptedException, ForbiddenException {
		api = new SilaApi(DefaultConfigurations.host, DefaultConfigurations.appHandle,
				"3a1076bf45ab87712ad64ccb3b10217737f7faacbf2872e88fdd9a537d8fe266");

		ApiResponse response = api.requestKYC(DefaultConfigurations.getUserHandle(), null,
				DefaultConfigurations.getUserPrivateKey());

		assertEquals(401, response.getStatusCode());
		api.requestKYC(DefaultConfigurations.getUserHandle(), "", DefaultConfigurations.getUserPrivateKey());
	}

	@Test
	public void response400() throws BadRequestException, InvalidSignatureException, ServerSideException, IOException,
			InterruptedException, ForbiddenException {
		ApiResponse response = api.requestKYC("", null, DefaultConfigurations.getUserPrivateKey());

		assertEquals(400, response.getStatusCode());
	}

	@Test
	public void response200WithSardine() throws Exception {
		ApiResponse response = api.requestKYC(DefaultConfigurations.getUser4Handle(), "INSTANT-ACHV2",
				DefaultConfigurations.getUser4PrivateKey());

		assertEquals(200, response.getStatusCode());
		RequestKycResponse parsedResponse= (RequestKycResponse) response.getData();
		assertEquals("SUCCESS", parsedResponse.getStatus());
	}
}