package com.silamoney.client.tests;

import static org.junit.Assert.assertNotNull;

import com.silamoney.client.api.ApiResponse;
import com.silamoney.client.api.SilaApi;
import com.silamoney.client.domain.CheckPartnerKycResponse;
import com.silamoney.client.testsutils.DefaultConfigurations;

import org.junit.Test;

public class CheckPartnerKycTests {

	SilaApi api = new SilaApi(DefaultConfigurations.host, DefaultConfigurations.appHandle,
			DefaultConfigurations.privateKey);

	@Test
	public void Register200CheckKycApp() throws Exception {

		ApiResponse response = api.checkPartnerKyc(DefaultConfigurations.appHandle,
				DefaultConfigurations.getUserHandle());

		CheckPartnerKycResponse parsedResponse = (CheckPartnerKycResponse) response.getData();

		assertNotNull(parsedResponse.getMessage());
	}
}
