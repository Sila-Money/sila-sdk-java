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
public class CertifyBusinessOwnerTests {

	SilaApi api = new SilaApi(DefaultConfigurations.host, DefaultConfigurations.appHandle,
			DefaultConfigurations.privateKey);

	@Test
	public void Response200() throws Exception {
		ApiResponse response = api.certifyBusinessOwner(DefaultConfigurations.getUserHandle(),
				DefaultConfigurations.getUserPrivateKey(), DefaultConfigurations.getBusinessHandle(),
				DefaultConfigurations.getBusinessPrivateKey(), DefaultConfigurations.getUser2Handle(), DefaultConfigurations.getBusinessOwnerToken());

		assertEquals(200, response.getStatusCode());
		assertEquals("Beneficial owner successfully certified.", ((BaseResponse) response.getData()).getMessage());
	}
}
