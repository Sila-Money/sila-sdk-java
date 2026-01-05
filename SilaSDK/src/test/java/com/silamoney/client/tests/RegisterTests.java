package com.silamoney.client.tests;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.Random;
import java.util.UUID;

import com.silamoney.client.api.ApiResponse;
import com.silamoney.client.api.SilaApi;
import com.silamoney.client.domain.BaseResponse;
import com.silamoney.client.domain.User;
import com.silamoney.client.exceptions.BadRequestException;
import com.silamoney.client.exceptions.ForbiddenException;
import com.silamoney.client.exceptions.InvalidSignatureException;
import com.silamoney.client.exceptions.ServerSideException;
import com.silamoney.client.testsutils.DefaultConfigurations;

import com.silamoney.client.testsutils.RandomSSNGenerator;
import org.junit.Test;
import org.web3j.crypto.ECKeyPair;
import org.web3j.crypto.Keys;
import org.web3j.crypto.Wallet;
import org.web3j.crypto.WalletFile;

/**
 *
 * @author Karlo Lorenzana
 */
public class RegisterTests {

	SilaApi api = new SilaApi(DefaultConfigurations.host, DefaultConfigurations.appHandle,
			DefaultConfigurations.privateKey);

	@Test
	public void Response200() throws Exception {
		// HANDLE2
		Date birthdate = Date.from(
				LocalDate.of(1950, 1, 31).atStartOfDay(ZoneId.systemDefault()).toInstant()
		);
		User user = new User(DefaultConfigurations.getUserHandle(), "Example", "User",
				"123 Main Street", null, "New City", "OR", "97204-1234",
				"503-123-4567", "example@silamoney.com", RandomSSNGenerator.generateRandomSSN(),
				DefaultConfigurations.getUserCryptoAddress(), birthdate, "US");
		User user2 = new User(DefaultConfigurations.getUser2Handle(), "Example", "User",
				"123 Main Street", null, "New City", "OR", "97204-1234",
				"503-123-4567", "example@silamoney.com", RandomSSNGenerator.generateRandomSSN(),
				DefaultConfigurations.getUser2CryptoAddress(), birthdate);

		ApiResponse response = api.register(user);
		assertEquals(200, response.getStatusCode());
		assertEquals("SUCCESS", ((BaseResponse) response.getData()).getStatus());
		response = api.register(user2);
		assertEquals(200, response.getStatusCode());
		assertEquals("SUCCESS", ((BaseResponse) response.getData()).getStatus());
	}

	@Test
	public void Response3USER1FAIL() throws Exception {
		Date birthdate = Date.from(
				LocalDate.of(1950, 1, 31).atStartOfDay(ZoneId.systemDefault()).toInstant()
		);
		String userHandleInternal = "javaSDK-UserInternal-" + new Random().nextInt();
		ECKeyPair ecKeyPair = Keys.createEcKeyPair();
		WalletFile aWallet = Wallet.createLight(UUID.randomUUID().toString(), ecKeyPair);
		String userCryptoAddressInternal = "0x" + aWallet.getAddress();
		User user = new User(userHandleInternal, "Fail", "User", "123 Main Street", null,
				"New City", "OR", "97204-1234", "503-123-4567", "example@silamoney.com",
				RandomSSNGenerator.generateRandomSSN(), userCryptoAddressInternal, birthdate);
		ApiResponse response = api.register(user);
		assertEquals(200, response.getStatusCode());
	}

	@Test
	public void Response400() throws BadRequestException, InvalidSignatureException, ServerSideException, IOException,
			InterruptedException, ForbiddenException {
		// HANDLE4
		Date birthdate = Date.from(
				LocalDate.of(1900, 1, 31).atStartOfDay(ZoneId.systemDefault()).toInstant()
		);
		User user = new User(DefaultConfigurations.getUserHandle(), "Fail", "User",
				"123 Main Street", null, "New City", "OR", "97204-1234",
				"503-123-4567", "example@silamoney.com", RandomSSNGenerator.generateRandomSSN(),
				DefaultConfigurations.getUserCryptoAddress(), birthdate);

		ApiResponse response = api.register(user);
		assertEquals(400, response.getStatusCode());
	}

	@Test
	public void Response401() throws BadRequestException, InvalidSignatureException, ServerSideException, IOException,
			InterruptedException, ForbiddenException {
		Date birthdate = Date.from(
				LocalDate.of(1950, 1, 31).atStartOfDay(ZoneId.systemDefault()).toInstant()
		);
		User user = new User("invalidsignature.silamoney.eth", "Example", "User",
				"123 Main Street", null, "New City", "OR", "97204-1234",
				"503-123-4567", "example@silamoney.com", RandomSSNGenerator.generateRandomSSN(),
				"0x1234567890abcdef1234567890abcdef12345678", birthdate);

		api = new SilaApi(DefaultConfigurations.host, DefaultConfigurations.appHandle,
				"3a1076bf45ab87712ad64ccb3b10217737f7faacbf2872e88fdd9a537d8fe266");

		ApiResponse response = api.register(user);
		assertEquals(401, response.getStatusCode());
	}

	@Test
	public void Response200WithCKO() throws Exception {
		// HANDLE2
		Date birthdate = Date.from(
				LocalDate.of(1989, 1, 31).atStartOfDay(ZoneId.systemDefault()).toInstant()
		);
		User user = new User(DefaultConfigurations.getUser5Handle(), "Example", "User",
				"123 Main Street", null, "New City", "OR", "97204-1234", "503-123-4567",
				"example"+new Random().nextInt()+"@silamoney.com", RandomSSNGenerator.generateRandomSSN(),
				DefaultConfigurations.getUser5CryptoAddress(), birthdate, "US");

		ApiResponse response = api.register(user);
		assertEquals(200, response.getStatusCode());
		assertEquals("SUCCESS", ((BaseResponse) response.getData()).getStatus());
	}

	@Test
	public void Response200WithIdDocument() throws Exception {
		Date birthdate = Date.from(
				LocalDate.of(1950, 1, 31).atStartOfDay(ZoneId.systemDefault()).toInstant()
		);
		User user = new User(DefaultConfigurations.getUser6Handle(), "Example", "User",
				"123 Main Street", null,"New City", "OR", "97204-1234",
				"503-123-4567", "example@silamoney.com", RandomSSNGenerator.generateRandomSSN(),
				DefaultConfigurations.getUser6CryptoAddress(), birthdate, "US",
				"id_drivers_license", "123456789", "OR", null);

		ApiResponse response = api.register(user);
		assertEquals(200, response.getStatusCode());
		assertEquals("SUCCESS", ((BaseResponse) response.getData()).getStatus());
	}
}
