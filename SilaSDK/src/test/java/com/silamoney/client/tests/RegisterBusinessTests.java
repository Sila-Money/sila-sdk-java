package com.silamoney.client.tests;

import static org.junit.Assert.assertEquals;

import com.silamoney.client.api.ApiResponse;
import com.silamoney.client.api.SilaApi;
import com.silamoney.client.domain.BaseResponse;
import com.silamoney.client.domain.BusinessType;
import com.silamoney.client.domain.BusinessUser;
import com.silamoney.client.domain.NaicsCategoryDescription;
import com.silamoney.client.testsutils.DefaultConfigurations;

import org.junit.Test;

/**
 *
 * @author Karlo Lorenzana
 */
public class RegisterBusinessTests {

	SilaApi api = new SilaApi(DefaultConfigurations.host, DefaultConfigurations.appHandle,
			DefaultConfigurations.privateKey);

	@Test
	public void Response200() throws Exception {
		BusinessType businessType = DefaultConfigurations.getBusinessTypes().get(0);
		NaicsCategoryDescription naicsCategory = DefaultConfigurations.getDefaultNaicCategoryDescription();
		BusinessUser user = new BusinessUser(DefaultConfigurations.getBusinessHandle(), "Office", "123 Main Street",
				null, "New City", "OR", "97204-1234", "503-123-4567", "example@silamoney.com", "123452222",
				DefaultConfigurations.getBusinessCryptoAddress(), "entity name", businessType,
				"https://www.website.com", "doing business as", naicsCategory);

		ApiResponse response = api.registerBusiness(user);

		assertEquals(200, response.getStatusCode());
		assertEquals("SUCCESS", ((BaseResponse) response.getData()).getStatus());
	}
}
