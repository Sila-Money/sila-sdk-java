package com.silamoney.client.tests;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import com.silamoney.client.api.ApiResponse;
import com.silamoney.client.api.SilaApi;
import com.silamoney.client.testsutils.DefaultConfigurations;
import com.silamoney.clientrefactored.endpoints.entities.checkpartnerkyc.CheckPartnerKycRequest;
import com.silamoney.clientrefactored.endpoints.entities.checkpartnerkyc.CheckPartnerKycResponse;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

public class CheckPartnerKycTests {

	SilaApi api = new SilaApi(DefaultConfigurations.host, DefaultConfigurations.appHandle,
            DefaultConfigurations.privateKey);

	@Test
	public void Register200CheckKycApp() throws Exception {
		CheckPartnerKycRequest request = CheckPartnerKycRequest.builder()
			.queryAppHandle(DefaultConfigurations.appHandle)
			.queryUserHandle(DefaultConfigurations.getUserHandle())
			.build();

		ApiResponse response = api.checkPartnerKyc(request);
		
		CheckPartnerKycResponse parsedResponse = (CheckPartnerKycResponse) response.getData();

		// assertNotNull(response.getStatus());
		assertNotNull(parsedResponse.getMessage());
		// assertNotNull(response.getReference());
		// assertNotNull(response.getEntityType());
		// assertNotNull(response.getVerificationStatus());
	}
}
