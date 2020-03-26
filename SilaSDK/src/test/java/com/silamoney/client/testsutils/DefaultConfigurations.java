package com.silamoney.client.testsutils;

import java.io.IOException;
import java.math.BigInteger;
import java.security.InvalidAlgorithmParameterException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.util.ArrayList;
import java.util.UUID;

import com.silamoney.client.domain.Environments;
import com.silamoney.client.domain.SearchFilters;

import org.web3j.crypto.CipherException;
import org.web3j.crypto.ECKeyPair;
import org.web3j.crypto.Keys;
import org.web3j.crypto.Wallet;
import org.web3j.crypto.WalletFile;

/**
 * Sets the default configuration values for testing.
 *
 * @author Karlo Lorenzana
 */
public class DefaultConfigurations {

	/**
	 * Default host for testing.
	 */
	public static String host = Environments.SilaEnvironment.SANDBOX.getUrl();

	/**
	 * Default appHandle for testing.
	 */
	public static String appHandle = "digital_geko_e2e.silamoney.eth";

	/**
	 * Default private key for testing.
	 */
	public static String privateKey = "e60a5c57130f4e82782cbdb498943f31fe8f92ab96daac2cc13cbbbf9c0b4d9e";

	/**
	 * Default user handle for testing.
	 */
	private static String userHandle;

	/**
	 * Default search filters for testing.
	 */
	public static SearchFilters filters = new SearchFilters()
			.showTimelines().setMaxSilaAmount(1300).setMinSilaAmount(1000).setStatuses(new ArrayList<>() {
				/**
				 *
				 */
				private static final long serialVersionUID = -5522339729487411576L;

				{
					add(SearchFilters.StatusesEnum.PENDING);
					//add(SearchFilters.StatusesEnum.SUCCESSFUL);
					add(SearchFilters.StatusesEnum.FAILED);
					//add(SearchFilters.StatusesEnum.COMPLETE);
				}
			}).setPage(1).setPerPage(20)
			.setTransactionTypes(new ArrayList<>() {
				/**
				 *
				 */
				private static final long serialVersionUID = -5630390615963025868L;

				{
					add(SearchFilters.TransactionTypesEnum.ISSUE);
					add(SearchFilters.TransactionTypesEnum.REDEEM);
					add(SearchFilters.TransactionTypesEnum.TRANSFER);
				}
			});

	public static String getUserHandle() {
		// userHandle = userHandle == null || userHandle.isBlank() ? "javaSDK-" + new Random().nextInt() : userHandle;
		return userHandle;
	}

	private static String userCryptoAddress;
	private static String userPrivateKey;

	public static String getUserCryptoAddress() {
		if (userCryptoAddress == null || userCryptoAddress.isBlank()) {
			try {

				ECKeyPair ecKeyPair = Keys.createEcKeyPair();
				BigInteger privateKeyInDec = ecKeyPair.getPrivateKey();

				userPrivateKey = privateKeyInDec.toString(16);

				WalletFile aWallet = Wallet.createLight(UUID.randomUUID().toString(), ecKeyPair);
				userCryptoAddress = "0x" + aWallet.getAddress();
				// System.out.println("userPrivateKey >>> " + userPrivateKey);
			} catch (InvalidAlgorithmParameterException | NoSuchAlgorithmException | NoSuchProviderException
					| CipherException e) {
				e.printStackTrace();
			}
		}

		return userCryptoAddress;
	}

	public static String getUserPrivateKey() {
		return userPrivateKey;
	}

	private static String plaidToken;

	public static String getPlaidToken() {
		try {
			plaidToken = plaidToken == null || plaidToken.isBlank() ? PlaidTokenHelper.getPlaidToken() : plaidToken;
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}
		return plaidToken;
	}

	public static void setUserPrivateKey(String userPrivateKey) {
		DefaultConfigurations.userPrivateKey = userPrivateKey;
	}

	public static void setUserHandle(String userHandle) {
		DefaultConfigurations.userHandle = userHandle;
	}

	public static void setUserCryptoAddress(String userCryptoAddress) {
		DefaultConfigurations.userCryptoAddress = userCryptoAddress;
	}
}
