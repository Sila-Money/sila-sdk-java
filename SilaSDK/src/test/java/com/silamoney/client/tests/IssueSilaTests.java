package com.silamoney.client.tests;

import com.silamoney.client.api.ApiResponse;
import com.silamoney.client.api.SilaApi;
import com.silamoney.client.domain.BaseResponse;
import com.silamoney.client.exceptions.BadRequestException;
import com.silamoney.client.exceptions.ForbiddenException;
import com.silamoney.client.exceptions.InvalidSignatureException;
import com.silamoney.client.exceptions.ServerSideException;
import com.silamoney.client.testsutils.DefaultConfigurations;
import java.io.IOException;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Karlo Lorenzana
 */
public class IssueSilaTests {

	SilaApi api = new SilaApi(DefaultConfigurations.host, DefaultConfigurations.appHandle,
			DefaultConfigurations.privateKey);

	@Test
	public void Response200Success() throws Exception {
		ApiResponse response = api.issueSila(DefaultConfigurations.getUserHandle(), 100, "Custom Account Name",
				DefaultConfigurations.getUserPrivateKey());

		assertEquals(200, response.getStatusCode());
		assertEquals("SUCCESS", ((BaseResponse) response.getData()).getStatus());
	}

	/*
	 * @Test public void Response200Failure() throws Exception { ApiResponse
	 * response = api.IssueSila(DefaultConfigurations.getUserHandle(), 100,
	 * "Custom Account Name", DefaultConfigurations.getUserPrivateKey());
	 * 
	 * assertEquals(200, response.getStatusCode()); assertEquals("FAILURE",
	 * ((BaseResponse) response.getData()).getStatus()); }
	 */

	@Test(expected = BadRequestException.class)
	public void Response400() throws BadRequestException, InvalidSignatureException, ServerSideException, IOException,
			InterruptedException, ForbiddenException {
		api.issueSila("", 1000, null, DefaultConfigurations.getUserPrivateKey());
	}

	@Test(expected = InvalidSignatureException.class)
	public void Response401User() throws BadRequestException, InvalidSignatureException, ServerSideException,
			IOException, InterruptedException, ForbiddenException {
		api.issueSila(DefaultConfigurations.getUserHandle(), 1000, null, DefaultConfigurations.privateKey);
	}

	@Test(expected = InvalidSignatureException.class)
	public void Response401() throws BadRequestException, InvalidSignatureException, ServerSideException, IOException,
			InterruptedException, ForbiddenException {
		api = new SilaApi(DefaultConfigurations.host, DefaultConfigurations.appHandle,
				"3a1076bf45ab87712ad64ccb3b10217737f7faacbf2872e88fdd9a537d8fe266");

		api.issueSila(DefaultConfigurations.getUserHandle(), 1000, null, DefaultConfigurations.getUserPrivateKey());
	}
}
