package com.silamoney.client.tests;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

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
public class RequestKYCTests {

	SilaApi api = new SilaApi(DefaultConfigurations.host, DefaultConfigurations.appHandle,
			DefaultConfigurations.privateKey);

	String userHandle = "javasdk-893748932";
	String userHandle2Failure = "javasdk-349425739";
	String userPrivateKey = "f6b5751234d4586873714066c538b9ddaa51ee5e3188a58236be1671f0be0ed3";
	String userPrivateKeyFailure = "f6406f347993b09ee3760e8ef0fb70abdeaa90265dc02de78f86da5eff9b6272";

	@Test
	public void response200() throws Exception {
		// KYCID1
		if (DefaultConfigurations.getUserHandle() == null) {
			DefaultConfigurations.setUserHandle(userHandle);
		}
		if (DefaultConfigurations.getUserPrivateKey() == null) {
			DefaultConfigurations.setUserPrivateKey(userPrivateKey);
		}
		ApiResponse response = api.requestKYC(DefaultConfigurations.getUserHandle(), null,
				DefaultConfigurations.getUserPrivateKey());

		assertEquals(200, response.getStatusCode());
		assertEquals("SUCCESS", ((BaseResponse) response.getData()).getStatus());
	}

	@Test
	public void response200Failure() throws Exception {
		// KYCID3
		ApiResponse response = api.requestKYC(userHandle2Failure, null, userPrivateKeyFailure);
		assertEquals(200, response.getStatusCode());
	}

	@Test
	public void response403() throws BadRequestException, InvalidSignatureException, ServerSideException, IOException,
			InterruptedException, ForbiddenException {
		// KYCID4
		if (DefaultConfigurations.getUserHandle() == null) {
			DefaultConfigurations.setUserHandle(userHandle);
		}
		if (DefaultConfigurations.getUserPrivateKey() == null) {
			DefaultConfigurations.setUserPrivateKey(userPrivateKey);
		}
		ApiResponse response = api.requestKYC(DefaultConfigurations.getUserHandle(), "FAIL",
				DefaultConfigurations.getUserPrivateKey());

		assertEquals(403, response.getStatusCode());
	}

	@Test
	public void response401() throws BadRequestException, InvalidSignatureException, ServerSideException, IOException,
			InterruptedException, ForbiddenException {
		api = new SilaApi(DefaultConfigurations.host, DefaultConfigurations.appHandle,
				"3a1076bf45ab87712ad64ccb3b10217737f7faacbf2872e88fdd9a537d8fe266");

		if (DefaultConfigurations.getUserHandle() == null) {
			DefaultConfigurations.setUserHandle(userHandle);
		}
		if (DefaultConfigurations.getUserPrivateKey() == null) {
			DefaultConfigurations.setUserPrivateKey(userPrivateKey);
		}
		ApiResponse response = api.requestKYC(DefaultConfigurations.getUserHandle(), null,
				DefaultConfigurations.getUserPrivateKey());

		assertEquals(401, response.getStatusCode());
		api.requestKYC(DefaultConfigurations.getUserHandle(), "", DefaultConfigurations.getUserPrivateKey());
	}

	@Test
	public void response400() throws BadRequestException, InvalidSignatureException, ServerSideException, IOException,
			InterruptedException, ForbiddenException {
		if (DefaultConfigurations.getUserHandle() == null) {
			DefaultConfigurations.setUserHandle(userHandle);
		}
		if (DefaultConfigurations.getUserPrivateKey() == null) {
			DefaultConfigurations.setUserPrivateKey(userPrivateKey);
		}
		ApiResponse response = api.requestKYC("", null, DefaultConfigurations.getUserPrivateKey());

		assertEquals(400, response.getStatusCode());
		// System.out.println(GsonUtils.objectToJsonStringFormato(response));

		api.requestKYC(DefaultConfigurations.getUserHandle(), "", DefaultConfigurations.getUserPrivateKey());
	}
}
