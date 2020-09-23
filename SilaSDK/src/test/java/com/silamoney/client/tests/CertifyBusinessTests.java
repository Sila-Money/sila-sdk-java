package com.silamoney.client.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import com.silamoney.client.api.ApiResponse;
import com.silamoney.client.api.SilaApi;
import com.silamoney.client.domain.BaseResponse;
import com.silamoney.client.testsutils.DefaultConfigurations;

import org.junit.Test;

/**
 *
 * @author Karlo Lorenzana
 */
public class CertifyBusinessTests {

	SilaApi api = new SilaApi(DefaultConfigurations.host, DefaultConfigurations.appHandle,
			DefaultConfigurations.privateKey);

	@Test
	public void Response200() throws Exception {
		ApiResponse response = api.certifyBusiness(DefaultConfigurations.getUserHandle(),
				DefaultConfigurations.getUserPrivateKey(), DefaultConfigurations.getBusinessHandle(),
				DefaultConfigurations.getBusinessPrivateKey());

		assertEquals(200, response.getStatusCode());
		assertTrue(response.getSuccess());
		assertEquals("Business successfully certified.", ((BaseResponse) response.getData()).getMessage());
	}
}
