package com.silamoney.client.testsrefactored.tests;

import static org.junit.Assert.assertNotNull;

import com.silamoney.client.testsutils.DefaultConfigurations;
import com.silamoney.clientrefactored.configuration.Environment;
import com.silamoney.clientrefactored.configuration.SilaApi;
import com.silamoney.clientrefactored.endpoints.entities.checkpartnerkyc.CheckPartnerKyc;
import com.silamoney.clientrefactored.endpoints.entities.checkpartnerkyc.CheckPartnerKycRequest;
import com.silamoney.clientrefactored.endpoints.entities.checkpartnerkyc.CheckPartnerKycResponse;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

public class CheckPartnerKycTests {

	@BeforeClass
	public static void init() {
		SilaApi.init(Environment.SANDBOX, DefaultConfigurations.appHandle,
				DefaultConfigurations.privateKey);
	}

	@Test
	public void Register200CheckKycApp() throws Exception {
		CheckPartnerKycRequest request = CheckPartnerKycRequest.builder()
			.queryAppHandle(DefaultConfigurations.appHandle)
			.queryUserHandle(DefaultConfigurations.getUserHandle())
			.build();

		CheckPartnerKycResponse response = CheckPartnerKyc.send(request);

		assertNotNull(response.getStatus());
		assertNotNull(response.getMessage());
		assertNotNull(response.getReference());
		assertNotNull(response.getEntityType());
		assertNotNull(response.getVerificationStatus());
	}

	@AfterClass
	public static void dispose() {
		SilaApi.dispose();
	}
}
