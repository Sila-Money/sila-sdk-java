package com.silamoney.client.tests;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.Random;
import java.util.UUID;

import com.silamoney.client.api.ApiResponse;
import com.silamoney.client.api.SilaApi;
import com.silamoney.client.domain.BaseResponse;
import com.silamoney.client.domain.Device;
import com.silamoney.client.domain.User;
import com.silamoney.client.exceptions.BadRequestException;
import com.silamoney.client.exceptions.ForbiddenException;
import com.silamoney.client.exceptions.InvalidSignatureException;
import com.silamoney.client.exceptions.ServerSideException;
import com.silamoney.client.testsutils.DefaultConfigurations;

import org.joda.time.LocalDate;
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
		LocalDate birthdate = new LocalDate(1900, 01, 31);
		User user = new User(DefaultConfigurations.getUserHandle(), "Example", "User", "123 Main Street", null,
				"New City", "OR", "97204-1234", "503-123-4567", "example@silamoney.com", "123452383",
				DefaultConfigurations.getUserCryptoAddress(), birthdate.toDate(), "US", "asdfghjkl");

		User user2 = new User(DefaultConfigurations.getUser2Handle(), "Example", "User", "123 Main Street", null,
				"New City", "OR", "97204-1234", "503-123-4567", "example@silamoney.com", "123452383",
				DefaultConfigurations.getUser2CryptoAddress(), birthdate.toDate());

		ApiResponse response = api.register(user);
		assertEquals(200, response.getStatusCode());
		assertEquals("SUCCESS", ((BaseResponse) response.getData()).getStatus());
		response = api.register(user2);
		assertEquals(200, response.getStatusCode());
		assertEquals("SUCCESS", ((BaseResponse) response.getData()).getStatus());
	}

	@Test
	public void Response3USER1FAIL() throws Exception {
		// HANDLE3
		// USER1
		LocalDate birthdate = new LocalDate(1900, 01, 31);
		String userHandleInternal = "javaSDK-" + new Random().nextInt();
		ECKeyPair ecKeyPair = Keys.createEcKeyPair();
		WalletFile aWallet = Wallet.createLight(UUID.randomUUID().toString(), ecKeyPair);
		String userCryptoAddressInternal = "0x" + aWallet.getAddress();
		User user = new User(userHandleInternal, "Example", "User", "123 Main Street", null, "New City", "OR",
				"97204-1234", "503-123-4567", "example@silamoney.com", "123452383", userCryptoAddressInternal,
				birthdate.toDate(), true);
		ApiResponse response = api.register(user);

		// USER2
		userHandleInternal = "javaSDK-" + new Random().nextInt();
		ecKeyPair = Keys.createEcKeyPair();
		aWallet = Wallet.createLight(UUID.randomUUID().toString(), ecKeyPair);
		userCryptoAddressInternal = "0x" + aWallet.getAddress();
		user = new User(userHandleInternal, "Example", "User", "123 Main Street", null, "New City", "OR", "97204-1234",
				"503-123-4567", "example@silamoney.com", "123452383", userCryptoAddressInternal, birthdate.toDate(),
				true);
		response = api.register(user);

		// USER3 FAIL NAME
		userHandleInternal = "javaSDK-" + new Random().nextInt();
		ecKeyPair = Keys.createEcKeyPair();
		aWallet = Wallet.createLight(UUID.randomUUID().toString(), ecKeyPair);
		userCryptoAddressInternal = "0x" + aWallet.getAddress();
		user = new User(userHandleInternal, "Fail", "User", "123 Main Street", null, "New City", "OR", "97204-1234",
				"503-123-4567", "example@silamoney.com", "123452383", userCryptoAddressInternal, birthdate.toDate(),
				true);
		response = api.register(user);
		assertEquals(200, response.getStatusCode());
	}

	@Test
	public void Response400() throws BadRequestException, InvalidSignatureException, ServerSideException, IOException,
			InterruptedException, ForbiddenException {
		// HANDLE4
		LocalDate birthdate = new LocalDate(1900, 01, 31);
		User user = new User(DefaultConfigurations.getUserHandle(), "Fail", "User", "123 Main Street", null, "New City",
				"OR", "97204-1234", "503-123-4567", "example@silamoney.com", "123452383",
				DefaultConfigurations.getUserCryptoAddress(), birthdate.toDate(), true);

		ApiResponse response = api.register(user);
		assertEquals(400, response.getStatusCode());
	}

	@Test
	public void Response401() throws BadRequestException, InvalidSignatureException, ServerSideException, IOException,
			InterruptedException, ForbiddenException {
		LocalDate birthdate = new LocalDate(1900, 01, 31);
		User user = new User("invalidsignature.silamoney.eth", "Example", "User", "123 Main Street", null, "New City",
				"OR", "97204-1234", "503-123-4567", "example@silamoney.com", "123452383",
				"0x1234567890abcdef1234567890abcdef12345678", birthdate.toDate(), true);

		api = new SilaApi(DefaultConfigurations.host, DefaultConfigurations.appHandle,
				"3a1076bf45ab87712ad64ccb3b10217737f7faacbf2872e88fdd9a537d8fe266");

		ApiResponse response = api.register(user);
		assertEquals(401, response.getStatusCode());
	}
	@Test
	public void Response200WithSardine() throws Exception {
		// HANDLE2
		LocalDate birthdate = new LocalDate(1989, 01, 31);
		User user = new User(DefaultConfigurations.getUser4Handle(), "Example", "User", "123 Main Street", null,
				"New City", "OR", "97204-1234", "503-123-4567", "example@silamoney.com", "123452383",
				DefaultConfigurations.getUser4CryptoAddress(), birthdate.toDate(), "US", "asdfghjkl",DefaultConfigurations.sessionIdentifier);

		ApiResponse response = api.register(user);
		assertEquals(200, response.getStatusCode());
		assertEquals("SUCCESS", ((BaseResponse) response.getData()).getStatus());
	}
}
