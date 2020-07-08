package com.silamoney.client.testsutils;

import java.io.IOException;
import java.math.BigInteger;
import java.security.InvalidAlgorithmParameterException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

import com.silamoney.client.domain.BusinessType;
import com.silamoney.client.domain.Environments;
import com.silamoney.client.domain.NaicsCategoryDescription;
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
	 * Default business handle for testing.
	 */
	private static String businessHandle;

	/**
	 * Default wallet_verification_signature for wallet testing.
	 */
	private static String wallet_verification_signature;

	/**
	 * Default blockchain_address for wallet testing.
	 */
	private static String blockchain_address;

	/**
	 * business types for testing.
	 */
	private static List<BusinessType> businessTypes;

	/**
	 * naics categories for testing.
	 */
	private static Map<String, ArrayList<NaicsCategoryDescription>> naicsCategories;

	/**
	 * Default search filters for testing.
	 */
	public static SearchFilters filters = new SearchFilters().showTimelines().setMaxSilaAmount(1300)
			.setMinSilaAmount(1000).setStatuses(new ArrayList<>() {
				/**
				 *
				 */
				private static final long serialVersionUID = -5522339729487411576L;

				{
					add(SearchFilters.StatusesEnum.PENDING);
					// add(SearchFilters.StatusesEnum.SUCCESSFUL);
					add(SearchFilters.StatusesEnum.FAILED);
					// add(SearchFilters.StatusesEnum.COMPLETE);
				}
			}).setPage(1).setPerPage(20).setTransactionTypes(new ArrayList<>() {
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
		userHandle = userHandle == null || userHandle.isBlank() ? "javaSDK-" + new Random().nextInt() : userHandle;
		return userHandle;
	}

	public static String getBusinessHandle() {
		businessHandle = businessHandle == null || businessHandle.isBlank() ? "javaSDK-" + new Random().nextInt()
				: businessHandle;
		return businessHandle;
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

	private static String businessCryptoAddress;
	private static String businessPrivateKey;

	public static String getBusinessCryptoAddress() {
		if (businessCryptoAddress == null || businessCryptoAddress.isBlank()) {
			try {

				ECKeyPair ecKeyPair = Keys.createEcKeyPair();
				BigInteger privateKeyInDec = ecKeyPair.getPrivateKey();

				businessPrivateKey = privateKeyInDec.toString(16);

				WalletFile aWallet = Wallet.createLight(UUID.randomUUID().toString(), ecKeyPair);
				businessCryptoAddress = "0x" + aWallet.getAddress();
			} catch (InvalidAlgorithmParameterException | NoSuchAlgorithmException | NoSuchProviderException
					| CipherException e) {
				e.printStackTrace();
			}
		}

		return businessCryptoAddress;
	}

	public static String registerWallet() {
		if (blockchain_address == null || userCryptoAddress.isBlank()) {
			try {

				ECKeyPair ecKeyPair = Keys.createEcKeyPair();
				BigInteger privateKeyInDec = ecKeyPair.getPrivateKey();

				wallet_verification_signature = privateKeyInDec.toString(16);

				WalletFile aWallet = Wallet.createLight(UUID.randomUUID().toString(), ecKeyPair);
				blockchain_address = "0x" + aWallet.getAddress();
			} catch (InvalidAlgorithmParameterException | NoSuchAlgorithmException | NoSuchProviderException
					| CipherException e) {
				e.printStackTrace();
			}
		}

		return blockchain_address;
	}

	public static String getUserPrivateKey() {
		return userPrivateKey;
	}


	public static String getBusinessPrivateKey() {
		return businessPrivateKey;
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

	public static void setUserPrivateKey(String pUserPrivateKey) {
		userPrivateKey = pUserPrivateKey;
	}

	/*
	 * public static void setUserHandle(String userHandle) {
	 * this.userHandle = userHandle; }
	 */

	public static void setUserCryptoAddress(String pUserCryptoAddress) {
		userCryptoAddress = pUserCryptoAddress;
	}

	public static String getWallet_verification_signature() {
		return wallet_verification_signature;
	}

	/**
	 * @return the businessTypes
	 */
	public static List<BusinessType> getBusinessTypes() {
		return businessTypes;
	}

	/**
	 * @param businessTypes the businessTypes to set
	 */
	public static void setBusinessTypes(List<BusinessType> pBusinessTypes) {
		businessTypes = pBusinessTypes;
	}

	/**
	 * @return the naicsCategories
	 */
	public static Map<String, ArrayList<NaicsCategoryDescription>> getnaicsCategories() {
		return naicsCategories;
	}

	/**
	 * @param naicsCategories the naicsCategories to set
	 */
	public static void  setNaicsCategories(Map<String, ArrayList<NaicsCategoryDescription>> pNaicsCategories) {
		naicsCategories = pNaicsCategories;
	}

	public static NaicsCategoryDescription getDefaultNaicCategoryDescription(){
		for (String key : naicsCategories.keySet()) {
			for (NaicsCategoryDescription categoryDescription : naicsCategories.get(key)) {
				return categoryDescription;
			}
		}

		return null;
	}

}
