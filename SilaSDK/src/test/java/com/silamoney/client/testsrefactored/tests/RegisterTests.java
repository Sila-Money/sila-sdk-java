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
import com.silamoney.clientrefactored.exceptions.BadRequestException;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

public class RegisterTests {

	@BeforeClass
	public static void init() {
		SilaApi.init(Environment.SANDBOX, DefaultConfigurations.appHandle,
				DefaultConfigurations.privateKey);
	}

	@Test
	public void Register200User1() throws Exception {
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
					.build()
				)
				.cryptoEntry(CryptoEntry.builder()
					.cryptoAlias("Address 1")
					.cryptoAddress(DefaultConfigurations.getUserCryptoAddress())
					.build()
				)
				.entity(Entity.builder()
					.birthdate("1900-01-31")
					.entityName("Example User")
					.firstName("Example")
					.lastName("User")
					.build()
				)
				.identity(Identity.builder()
					.identityAlias("SSN")
					.identityValue("123452222")
					.build()
				)
				.device(Device.builder()
					.deivceFingerprint("asdfghjkl")
					.build()
				)
				.userHandle(DefaultConfigurations.getUserHandle())
				.build();
		RegisterResponse response = Register.send(request);

		assertEquals("SUCCESS", response.getStatus());
		assertTrue(response.isSuccess());
	}

	@Test
	public void Register200User2() throws Exception {
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
					.build()
				)
				.cryptoEntry(CryptoEntry.builder()
					.cryptoAlias("Address 1")
					.cryptoAddress(DefaultConfigurations.getUser2CryptoAddress())
					.build()
				)
				.entity(Entity.builder()
					.birthdate("1900-01-31")
					.entityName("Example User")
					.firstName("Example")
					.lastName("User")
					.build()
				)
				.identity(Identity.builder()
					.identityAlias("SSN")
					.identityValue("123452222")
					.build()
				)
				.device(Device.builder()
					.deivceFingerprint("asdfghjkl")
					.build()
				)
				.userHandle(DefaultConfigurations.getUser2Handle())
				.build();
		RegisterResponse response = Register.send(request);

		assertEquals("SUCCESS", response.getStatus());
		assertTrue(response.isSuccess());
	}

	@Test(expected = BadRequestException.class)
	public void Register400() throws Exception {
		RegisterRequest request = RegisterRequest.builder()
				.address(Address.builder().addressAlias("Office").city("Portland").postalCode("97204").state("OR")
						.streetAddress1("123 Candelabra Blvd.").build())
				.contact(
						Contact.builder().contactAlias("").email("example@silamoney.com").phone("503-123-4567").build())
				.cryptoEntry(CryptoEntry.builder().cryptoAlias("Address 1")
						.cryptoAddress("0x1234567890abcdef1234567890abcdef12345678").build())
				.entity(Entity.builder().birthdate("1900-01-31").entityName("Example User").firstName("Example")
						.lastName("User").build())
				.identity(Identity.builder().identityAlias("SSN").identityValue("123452222").build()).userHandle("")
				.build();
		Register.send(request);
	}

	@AfterClass
	public static void dispose() {
		SilaApi.dispose();
	}
}
