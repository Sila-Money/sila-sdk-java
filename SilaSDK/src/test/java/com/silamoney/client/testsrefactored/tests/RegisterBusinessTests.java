package com.silamoney.client.testsrefactored.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import com.silamoney.client.testsutils.DefaultConfigurations;
import com.silamoney.clientrefactored.configuration.Environment;
import com.silamoney.clientrefactored.configuration.SilaApi;
import com.silamoney.clientrefactored.domain.Address;
import com.silamoney.clientrefactored.domain.Contact;
import com.silamoney.clientrefactored.domain.CryptoEntry;
import com.silamoney.clientrefactored.domain.Device;
import com.silamoney.clientrefactored.domain.Entity;
import com.silamoney.clientrefactored.domain.Identity;
import com.silamoney.clientrefactored.endpoints.entities.register.Register;
import com.silamoney.clientrefactored.endpoints.entities.register.RegisterRequest;
import com.silamoney.clientrefactored.endpoints.entities.register.RegisterResponse;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

public class RegisterBusinessTests {

	@BeforeClass
	public static void init() {
		SilaApi.init(Environment.SANDBOX, DefaultConfigurations.appHandle,
				DefaultConfigurations.privateKey);
	}

	@Test
	public void Register200Business() throws Exception {
		RegisterRequest request = RegisterRequest.builder()
				.address(Address.builder()
					.addressAlias("Office")
					.city("New City")
					.postalCode("97204-1234")
					.state("OR")
					.streetAddress1("123 Main Street")
					.build()
				)
				.contact(Contact.builder()
					.email("example@silamoney.com")
					.phone("503-123-4567")
					.smsOptIn(true)
					.build()
				)
				.cryptoEntry(CryptoEntry.builder()
					.cryptoAlias("Address 1")
					.cryptoAddress(DefaultConfigurations.getBusinessCryptoAddress())
					.build()
				)
				.entity(Entity.builder()
					.type("business")
					.entityName("Entity name")
					.businessType(DefaultConfigurations.getBusinessTypes().get(0).getName())
					.businessWebsite("https://www.website.com")
					.doingBusinessAs("doing business as")
					.naicsCode(DefaultConfigurations.getDefaultNaicCategoryDescription().getCode())
					.build()
				)
				.identity(Identity.builder()
					.identityAlias("EIN")
					.identityValue("123452222")
					.build()
				)
				.device(Device.builder()
					.deivceFingerprint("asdfghjkl")
					.build()
				)
				.userHandle(DefaultConfigurations.getBusinessHandle())
				.build();
		RegisterResponse response = Register.send(request);

		assertEquals("SUCCESS", response.getStatus());
		assertTrue(response.isSuccess());
	}

	@AfterClass
	public static void dispose() {
		SilaApi.dispose();
	}
}
