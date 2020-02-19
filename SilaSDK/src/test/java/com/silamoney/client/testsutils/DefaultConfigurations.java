package com.silamoney.client.testsutils;

import com.silamoney.client.domain.Environments;
import com.silamoney.client.domain.SearchFilters;

import java.io.IOException;
import java.math.BigInteger;
import java.security.InvalidAlgorithmParameterException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.util.ArrayList;
import java.util.Random;
import java.util.UUID;

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
	public static String appHandle = "geko";

	/**
	 * Default private key for testing.
	 */
	public static String privateKey = "8BD29ACC6656E8D8012EE15C2F0C799051E8AB4E9A5BE2EA1006FB8E6179ECAC";

	/**
	 * Default user handle for testing.
	 */
	private static String userHandle;

	/**
	 * Default search filters for testing.
	 */
	public static SearchFilters filters = new SearchFilters()
			.showTimelines().setMaxSilaAmount(1300).setMinSilaAmount(1000).setStatuses(new ArrayList<>() {
				{
					add(SearchFilters.StatusesEnum.PENDING);
					//add(SearchFilters.StatusesEnum.SUCCESSFUL);
					add(SearchFilters.StatusesEnum.FAILED);
					//add(SearchFilters.StatusesEnum.COMPLETE);
				}
			}).setPage(1).setPerPage(20)
			.setTransactionTypes(new ArrayList<>() {
				{
					add(SearchFilters.TransactionTypesEnum.ISSUE);
					add(SearchFilters.TransactionTypesEnum.REDEEM);
					add(SearchFilters.TransactionTypesEnum.TRANSFER);
				}
			});

	public static String getUserHandle() {
		userHandle = userHandle == null || userHandle.isBlank() ? "geko" + new Random().nextInt() : userHandle;
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return plaidToken;
	}	
}
