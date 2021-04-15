package com.silamoney.client.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import com.silamoney.client.api.ApiResponse;
import com.silamoney.client.api.SilaApi;
import com.silamoney.client.testsutils.DefaultConfigurations;
import com.silamoney.clientrefactored.endpoints.entities.requestkyc.RequestKycRequest;
import com.silamoney.clientrefactored.endpoints.entities.requestkyc.RequestKycResponse;

import org.junit.Test;

public class RequestKYCTests {

	SilaApi api = new SilaApi(DefaultConfigurations.host, DefaultConfigurations.appHandle,
			DefaultConfigurations.privateKey);

	@Test
	public void Response200Individual() throws Exception {

		RequestKycRequest request = RequestKycRequest.builder().userHandle(DefaultConfigurations.getUserHandle())
				.userPrivateKey(DefaultConfigurations.getUserPrivateKey()).kycLevel("INSTANT-ACH").build();

		ApiResponse response = api.requestKYC(request);

		RequestKycResponse parsedResponse = (RequestKycResponse) response.getData();

		assertEquals("SUCCESS", parsedResponse.getStatus());
		assertNotNull(parsedResponse.getMessage());
		assertNotNull(parsedResponse.getReference());
		assertNotNull(parsedResponse.getVerificationUuid());
		assertTrue(parsedResponse.isSuccess());

		request = RequestKycRequest.builder().userHandle(DefaultConfigurations.getUser2Handle())
				.userPrivateKey(DefaultConfigurations.getUser2PrivateKey()).build();

		response = api.requestKYC(request);

		parsedResponse = (RequestKycResponse) response.getData();

		assertEquals("SUCCESS", parsedResponse.getStatus());
		assertNotNull(parsedResponse.getMessage());
		assertNotNull(parsedResponse.getReference());
		assertNotNull(parsedResponse.getVerificationUuid());
		assertTrue(parsedResponse.isSuccess());
	}

	@Test
	public void Response200Business() throws Exception {

		RequestKycRequest request = RequestKycRequest.builder().userHandle(DefaultConfigurations.getBusinessHandle())
				.userPrivateKey(DefaultConfigurations.getBusinessPrivateKey()).build();

		ApiResponse response = api.requestKYC(request);

		RequestKycResponse parsedResponse = (RequestKycResponse) response.getData();

		assertEquals("SUCCESS", parsedResponse.getStatus());
		assertNotNull(parsedResponse.getMessage());
		assertNotNull(parsedResponse.getReference());
		assertNotNull(parsedResponse.getVerificationUuid());
		assertTrue(parsedResponse.isSuccess());

	}

}
