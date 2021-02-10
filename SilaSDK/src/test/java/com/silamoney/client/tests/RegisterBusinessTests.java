package com.silamoney.client.tests;

import static org.junit.Assert.assertEquals;

import com.silamoney.client.api.ApiResponse;
import com.silamoney.client.api.SilaApi;
import com.silamoney.client.domain.BaseResponse;
import com.silamoney.client.domain.BusinessType;
import com.silamoney.client.domain.BusinessUser;
import com.silamoney.client.domain.Device;
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
	public void Response_001_400_2_Chars() throws Exception {
		BusinessType businessType = DefaultConfigurations.getBusinessTypes().get(0);
		NaicsCategoryDescription naicsCategory = DefaultConfigurations.getDefaultNaicCategoryDescription();
		BusinessUser user = new BusinessUser(DefaultConfigurations.getBusinessHandle(), "Office", "123 Main Street",
				null, "New City", "OR", "97204-1234", "503-123-4567", "example@silamoney.com", "123452222",
				DefaultConfigurations.getBusinessCryptoAddress(), "en", businessType,
				"https://www.website.com", "doing business as", naicsCategory, true);

		ApiResponse response = api.registerBusiness(user, new Device("1234567890"));

		assertEquals(400, response.getStatusCode());
	}

	@Test
	public void Response_002_400_200_Chars() throws Exception {
		BusinessType businessType = DefaultConfigurations.getBusinessTypes().get(0);
		NaicsCategoryDescription naicsCategory = DefaultConfigurations.getDefaultNaicCategoryDescription();
		BusinessUser user = new BusinessUser(DefaultConfigurations.getBusinessHandle(), "Office", "123 Main Street",
				null, "New City", "OR", "97204-1234", "503-123-4567", "example@silamoney.com", "123452222",
				DefaultConfigurations.getBusinessCryptoAddress(), 
				"L1mcysCdEHXWJdeQnj7xMJIhjYMPWq2qS7sa7U8D2GfZyR0ID5gYrYmEF8qZgInNFa1zSsD2dyKbPlzGgiMeMWpnOc1SHxdyjDFL84R8MMUApQ1WRiUoIGIqyaF3BoPVTOn8v14al51P4g6JckAxZFtLI9Udte5S6JGiYVBuFe2MUad8SyjSHB6QIrNziV7vsxewkE1n2"
				, businessType,
				"https://www.website.com", "doing business as", naicsCategory, true);

		ApiResponse response = api.registerBusiness(user, new Device("1234567890"));

		assertEquals(400, response.getStatusCode());
	}

	@Test
	public void Response_003_200() throws Exception {
		BusinessType businessType = DefaultConfigurations.getBusinessTypes().get(0);
		NaicsCategoryDescription naicsCategory = DefaultConfigurations.getDefaultNaicCategoryDescription();
		BusinessUser user = new BusinessUser(DefaultConfigurations.getBusinessHandle(), "Office", "123 Main Street",
				null, "New City", "OR", "97204-1234", "503-123-4567", "example@silamoney.com", "123452222",
				DefaultConfigurations.getBusinessCryptoAddress(), "entity name", businessType,
				"https://www.website.com", "doing business as", naicsCategory, true);

		ApiResponse response = api.registerBusiness(user, new Device("1234567890"));

		assertEquals(200, response.getStatusCode());
		assertEquals("SUCCESS", ((BaseResponse) response.getData()).getStatus());
	}
}
