package com.silamoney.client.tests;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import com.silamoney.client.api.ApiResponse;
import com.silamoney.client.api.SilaApi;
import com.silamoney.client.domain.Wallet;
import com.silamoney.client.exceptions.BadRequestException;
import com.silamoney.client.exceptions.ForbiddenException;
import com.silamoney.client.exceptions.InvalidSignatureException;
import com.silamoney.client.exceptions.ServerSideException;
import com.silamoney.client.testsutils.DefaultConfigurations;

import org.junit.Test;

/**
 * RegisterWalletTests
 * 
 * @author wzelada
 */
public class RegisterWalletTests {

	SilaApi api = new SilaApi(DefaultConfigurations.host, DefaultConfigurations.appHandle,
			DefaultConfigurations.privateKey);

	String userHandle = "javasdk-893748932";
	String userPrivateKey = "f6b5751234d4586873714066c538b9ddaa51ee5e3188a58236be1671f0be0ed3";

	// @Test
	// public void Response200() throws Exception {
	// 	if (DefaultConfigurations.getUserHandle() == null) {
	// 		DefaultConfigurations.setUserHandle(userHandle);
	// 	}
	// 	if (DefaultConfigurations.getUserPrivateKey() == null) {
	// 		DefaultConfigurations.setUserPrivateKey(userPrivateKey);
	// 	}

	// 	Wallet wallet = new Wallet("0xe60a5c57130f4e82782cbdb498943f31fe8f92ab96daac2cc13cbbbf9c0b4d9e", "ETH",
	// 			"wallet_test");
	// 	String wallet_verification_signature = "788e10a73c0548b875b6a2c47b985fc873d32ef14fff85e55791f0fccca6282838a81dd24db9ae72a508bb2bbc02f207ab1a1451ada195554fb9487a253432e61c";

	// 	ApiResponse response = api.registerWallet(DefaultConfigurations.getUserHandle(), wallet,
	// 			wallet_verification_signature, DefaultConfigurations.getUserPrivateKey());

	// 	//System.out.println(GsonUtils.objectToJsonStringFormato(response));
	// 	assertEquals(200, response.getStatusCode());
	// }

	@Test
	public void ResponseBadWallet() throws BadRequestException, InvalidSignatureException, ServerSideException,
			IOException, InterruptedException, ForbiddenException {
		if (DefaultConfigurations.getUserHandle() == null) {
			DefaultConfigurations.setUserHandle(userHandle);
		}
		if (DefaultConfigurations.getUserPrivateKey() == null) {
			DefaultConfigurations.setUserPrivateKey(userPrivateKey);
		}

		Wallet wallet = new Wallet("0xe60a5c57130f4e82782cbdb498943f31fe8f92ab96daac2cc13cbbbf9c0b4d9e", "ETH",
				"wallet_test");
		String wallet_verification_signature = "788e10a73c0548b875b6a2c47b985fc873d32ef14fff85e55791f0fccca6282838a81dd24db9ae72a508bb2bbc02f207ab1a1451ada195554fb9487a253432e61c";

		ApiResponse response = api.registerWallet(DefaultConfigurations.getUserHandle(), wallet,
				wallet_verification_signature, DefaultConfigurations.getUserPrivateKey());

		// System.out.println(GsonUtils.objectToJsonStringFormato(response));
		assertEquals(400, response.getStatusCode());
	}

	// @Test
	// public void Response401() throws BadRequestException, InvalidSignatureException, ServerSideException, IOException,
	// 		InterruptedException, ForbiddenException {
	// 	api = new SilaApi(DefaultConfigurations.host, DefaultConfigurations.appHandle,
	// 			"3a1076bf45ab87712ad64ccb3b10217737f7faacbf2872e88fdd9a537d8fe266");
	// 	if (DefaultConfigurations.getUserHandle() == null) {
	// 		DefaultConfigurations.setUserHandle(userHandle);
	// 	}
	// 	if (DefaultConfigurations.getUserPrivateKey() == null) {
	// 		DefaultConfigurations.setUserPrivateKey(userPrivateKey);
	// 	}

	// 	Wallet wallet = new Wallet("0xe60a5c57130f4e82782cbdb498943f31fe8f92ab96daac2cc13cbbbf9c0b4d9e", "ETH",
	// 			"wallet_test");
	// 	String wallet_verification_signature = "788e10a73c0548b875b6a2c47b985fc873d32ef14fff85e55791f0fccca6282838a81dd24db9ae72a508bb2bbc02f207ab1a1451ada195554fb9487a253432e61c";

	// 	ApiResponse response = api.registerWallet(DefaultConfigurations.getUserHandle(), wallet,
	// 			wallet_verification_signature, DefaultConfigurations.getUserPrivateKey());

	// 	// System.out.println(GsonUtils.objectToJsonStringFormato(response));
	// 	assertEquals(401, response.getStatusCode());
	// }
}