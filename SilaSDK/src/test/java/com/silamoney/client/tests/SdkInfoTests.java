package com.silamoney.client.tests;

import static org.junit.Assert.assertEquals;

import com.silamoney.client.util.SdkInfo;

import org.junit.Test;

public class SdkInfoTests {

	@Test
	public void UserAgentUsesPomVersion() {
		assertEquals("SilaSDK-java/" + System.getProperty("sdk.version"), SdkInfo.USER_AGENT);
	}
}
