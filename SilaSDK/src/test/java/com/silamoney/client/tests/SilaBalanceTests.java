package com.silamoney.client.tests;

import com.silamoney.client.api.ApiResponse;
import com.silamoney.client.api.SilaApi;
import com.silamoney.client.domain.GetSilaBalanceResponse;
import com.silamoney.client.testsutils.DefaultConfigurations;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Karlo Lorenzana
 */
public class SilaBalanceTests {

	SilaApi api = new SilaApi(DefaultConfigurations.appHandle,
			DefaultConfigurations.privateKey);

	@Test
	public void Response200Success() throws Exception {
		ApiResponse response = api.silaBalance("0x65a796a4bD3AaF6370791BefFb1A86EAcfdBc3C1");

		assertEquals("0x65a796a4bD3AaF6370791BefFb1A86EAcfdBc3C1", ((GetSilaBalanceResponse)response.getData()).getAddress());
		assertEquals(200, response.getStatusCode());
	}
}
