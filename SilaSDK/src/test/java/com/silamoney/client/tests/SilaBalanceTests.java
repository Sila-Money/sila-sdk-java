package com.silamoney.client.tests;

import com.silamoney.client.api.ApiResponse;
import com.silamoney.client.api.SilaApi;
import com.silamoney.client.testsutils.DefaultConfigurations;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Karlo Lorenzana
 */
public class SilaBalanceTests {

	SilaApi api = new SilaApi(DefaultConfigurations.host, DefaultConfigurations.appHandle,
			DefaultConfigurations.privateKey);

//	@Test
//	public void Response200Success() throws Exception {
//		ApiResponse response = api.silaBalance(DefaultConfigurations.host, "0xabc123abc123abc123");
//
//		assertEquals(200, response.getStatusCode());
//		assertEquals("1000", response.getData());
//	}
}
