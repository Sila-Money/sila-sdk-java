package com.silamoney.client.tests;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.UUID;

import com.silamoney.client.api.ApiResponse;
import com.silamoney.client.api.SilaApi;
import com.silamoney.client.domain.IssueSilaResponse;
import com.silamoney.client.exceptions.BadRequestException;
import com.silamoney.client.exceptions.ForbiddenException;
import com.silamoney.client.exceptions.InvalidSignatureException;
import com.silamoney.client.exceptions.ServerSideException;
import com.silamoney.client.testsutils.DefaultConfigurations;
import com.silamoney.client.testsutils.GsonUtils;

import org.junit.Test;

/**
 *
 * @author Karlo Lorenzana
 */
public class IssueSilaTests {

	SilaApi api = new SilaApi(DefaultConfigurations.host, DefaultConfigurations.appHandle,
			DefaultConfigurations.privateKey);

	String userHandle = "javasdk-893748932";
	String userHandle2 = "javasdk-893748933";
	String userPrivateKey = "f6b5751234d4586873714066c538b9ddaa51ee5e3188a58236be1671f0be0ed3";

	@Test
	public void Response200Success() throws Exception {
		// TRANSACTIONS1
		if (DefaultConfigurations.getUserHandle() == null) {
			DefaultConfigurations.setUserHandle(userHandle);
		}
		if (DefaultConfigurations.getUserPrivateKey() == null) {
			DefaultConfigurations.setUserPrivateKey(userPrivateKey);
		}
		ApiResponse response = api.issueSila(DefaultConfigurations.getUserHandle(), 100, "default",
				"test descriptor", DefaultConfigurations.correctUuid,
				DefaultConfigurations.getUserPrivateKey());

		System.out.println(GsonUtils.objectToJsonStringFormato(response));
		assertEquals(200, response.getStatusCode());
		assertEquals("test descriptor",((IssueSilaResponse) response.getData()).getDescriptor());
		assertEquals("SUCCESS", ((IssueSilaResponse) response.getData()).getStatus());
	}

	@Test
	public void ResponseNotPassed() throws Exception {
		// TRANSACTIONS2
		ApiResponse response = api.issueSila(userHandle2, 100, "default", 
				"test descriptor", UUID.randomUUID().toString(), userPrivateKey);
		// System.out.println(GsonUtils.objectToJsonStringFormato(response));
		assertEquals(401, response.getStatusCode());
		assertEquals("FAILURE", ((IssueSilaResponse) response.getData()).getStatus());
	}

	@Test
	public void Response400() throws BadRequestException, InvalidSignatureException, ServerSideException, IOException,
			InterruptedException, ForbiddenException {
		if (DefaultConfigurations.getUserHandle() == null) {
			DefaultConfigurations.setUserHandle(userHandle);
		}
		if (DefaultConfigurations.getUserPrivateKey() == null) {
			DefaultConfigurations.setUserPrivateKey(userPrivateKey);
		}
		ApiResponse response = api.issueSila("", 1000, null, 
				"test descriptor", UUID.randomUUID().toString(), DefaultConfigurations.getUserPrivateKey());
		// System.out.println(GsonUtils.objectToJsonStringFormato(response));
		assertEquals(400, response.getStatusCode());
	}
	
	@Test
	public void Response400WrongUuid() throws BadRequestException, InvalidSignatureException, ServerSideException, IOException,
			InterruptedException, ForbiddenException {
		if (DefaultConfigurations.getUserHandle() == null) {
			DefaultConfigurations.setUserHandle(userHandle);
		}
		if (DefaultConfigurations.getUserPrivateKey() == null) {
			DefaultConfigurations.setUserPrivateKey(userPrivateKey);
		}
		ApiResponse response = api.issueSila("", 1000, null, 
				"test descriptor", DefaultConfigurations.wrongUuid, DefaultConfigurations.getUserPrivateKey());
		// System.out.println(GsonUtils.objectToJsonStringFormato(response));
		assertEquals(400, response.getStatusCode());
	}

	@Test
	public void Response401User() throws BadRequestException, InvalidSignatureException, ServerSideException,
			IOException, InterruptedException, ForbiddenException {
		if (DefaultConfigurations.getUserHandle() == null) {
			DefaultConfigurations.setUserHandle(userHandle2);
		}
		if (DefaultConfigurations.getUserPrivateKey() == null) {
			DefaultConfigurations.setUserPrivateKey(userPrivateKey);
		}
		ApiResponse response = api.issueSila(DefaultConfigurations.getUserHandle(), 1000, "default",
				"test descriptor", UUID.randomUUID().toString(),
				DefaultConfigurations.privateKey);
		// System.out.println(GsonUtils.objectToJsonStringFormato(response));
		assertEquals(401, response.getStatusCode());
	}

	@Test
	public void Response401() throws BadRequestException, InvalidSignatureException, ServerSideException, IOException,
			InterruptedException, ForbiddenException {
		if (DefaultConfigurations.getUserHandle() == null) {
			DefaultConfigurations.setUserHandle(userHandle);
		}
		if (DefaultConfigurations.getUserPrivateKey() == null) {
			DefaultConfigurations.setUserPrivateKey(userPrivateKey);
		}
		api = new SilaApi(DefaultConfigurations.host, DefaultConfigurations.appHandle,
				"3a1076bf45ab87712ad64ccb3b10217737f7faacbf2872e88fdd9a537d8fe266");

		ApiResponse response = api.issueSila(DefaultConfigurations.getUserHandle(), 1000, null,
				"test descriptor", UUID.randomUUID().toString(),
				DefaultConfigurations.privateKey);
		// System.out.println(GsonUtils.objectToJsonStringFormato(response));
		assertEquals(401, response.getStatusCode());
	}
}
