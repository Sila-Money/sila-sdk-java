package com.silamoney.client.tests;

import com.silamoney.client.api.ApiResponse;
import com.silamoney.client.api.SilaApi;
import com.silamoney.client.domain.BaseResponse;
import com.silamoney.client.domain.User;
import com.silamoney.client.exceptions.BadRequestException;
import com.silamoney.client.exceptions.ForbiddenException;
import com.silamoney.client.exceptions.InvalidSignatureException;
import com.silamoney.client.exceptions.ServerSideException;
import com.silamoney.client.testsutils.DefaultConfigurations;
import java.io.IOException;
import org.joda.time.LocalDate;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Karlo Lorenzana
 */
public class RegisterTests {

	SilaApi api = new SilaApi(DefaultConfigurations.host, DefaultConfigurations.appHandle,
			DefaultConfigurations.privateKey);

	@Test
	public void Response200() throws Exception {
		LocalDate birthdate = new LocalDate(1900, 01, 31);
		User user = new User(DefaultConfigurations.getUserHandle(), "Example", "User", "123 Main Street", null,
				"New City", "OR", "97204-1234", "503-123-4567", "example@silamoney.com", "123452222",
				DefaultConfigurations.getUserCryptoAddress(), birthdate.toDate());

		ApiResponse response = api.register(user);

		assertEquals(200, response.getStatusCode());
		assertEquals("SUCCESS", ((BaseResponse) response.getData()).getStatus());
	}

	@Test(expected = BadRequestException.class)
	public void Response400() throws BadRequestException, InvalidSignatureException, ServerSideException, IOException,
			InterruptedException, ForbiddenException {
		LocalDate birthdate = new LocalDate(1900, 01, 31);
		User user = new User("badrequest.silamoney.eth", "Example", "User", "123 Main Street", null, "New City", "OR",
				"97204-1234", "503-123-4567", "example@silamoney.com", "123452222", "", birthdate.toDate());

		api.register(user);
	}

	@Test(expected = InvalidSignatureException.class)
	public void Response401() throws BadRequestException, InvalidSignatureException, ServerSideException, IOException,
			InterruptedException, ForbiddenException {
		LocalDate birthdate = new LocalDate(1900, 01, 31);
		User user = new User("invalidsignature.silamoney.eth", "Example", "User", "123 Main Street", null, "New City",
				"OR", "97204-1234", "503-123-4567", "example@silamoney.com", "123452222",
				"0x1234567890abcdef1234567890abcdef12345678", birthdate.toDate());

		api = new SilaApi(DefaultConfigurations.host, DefaultConfigurations.appHandle,
				"3a1076bf45ab87712ad64ccb3b10217737f7faacbf2872e88fdd9a537d8fe266");

		api.register(user);
	}
}
