package com.silamoney.client.api;

import java.io.IOException;
import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.Map;

import com.silamoney.client.config.Configuration;
import com.silamoney.client.domain.*;
import com.silamoney.client.exceptions.BadRequestException;
import com.silamoney.client.exceptions.ForbiddenException;
import com.silamoney.client.exceptions.InvalidSignatureException;
import com.silamoney.client.exceptions.ServerSideException;
import com.silamoney.client.security.EcdsaUtil;
import com.silamoney.client.util.ResponseUtil;
import com.silamoney.client.util.Serialization;

import io.reactivex.annotations.Nullable;

/**
 *
 * @author Karlo Lorenzana
 */
public class SilaApi {

	private final Configuration configuration;
	private static final String AUTH_SIGNATURE = "authsignature";
	private static final String USER_SIGNATURE = "usersignature";
	private static final String DEFAULT_ENVIRONMENT = Environments.SilaEnvironment.SANDBOX.getUrl();

	/**
	 * Constructor for SilaApi using custom environment.
	 *
	 * @param environment
	 * @param appHandler
	 * @param privateKey
	 */
	public SilaApi(String environment, String appHandler, String privateKey) {
		this.configuration = new Configuration(environment, privateKey, appHandler);
	}

	/**
	 * Constructor for SilaApi using specified environment.
	 *
	 * @param environment
	 * @param appHandler
	 * @param privateKey
	 */
	public SilaApi(Environments.SilaEnvironment environment, String appHandler, String privateKey) {
		this.configuration = new Configuration(environment.getUrl(), privateKey, appHandler);
	}

	/**
	 * Constructor for SilaApi using sandbox environment.
	 *
	 * @param appHandler
	 * @param privateKey
	 */
	public SilaApi(String appHandler, String privateKey) {
		this.configuration = new Configuration(DEFAULT_ENVIRONMENT, privateKey, appHandler);
	}

	/**
	 * Checks if a specific handle is already taken.
	 *
	 * @param handle
	 * @return API response.
	 * @throws BadRequestException
	 * @throws InvalidSignatureException
	 * @throws ServerSideException
	 * @throws IOException
	 * @throws InterruptedException
	 * @throws ForbiddenException
	 */
	public ApiResponse checkHandle(String handle) throws BadRequestException, InvalidSignatureException,
			ServerSideException, IOException, InterruptedException, ForbiddenException {
		HeaderMsg body = new HeaderMsg(handle, this.configuration.getAuthHandle());
		String path = Endpoints.CHECK_HANDLE.getUri();
		String sBody = Serialization.serialize(body);
		Map<String, String> headers = new HashMap<>();

		headers.put(AUTH_SIGNATURE, EcdsaUtil.sign(sBody, this.configuration.getPrivateKey()));

		HttpResponse<?> response = this.configuration.getApiClient().callApi(path, headers, sBody);

		return ResponseUtil.prepareResponse(response, Message.ValueEnum.HEADER_MSG.getValue());
	}

	/**
	 * Attaches KYC data and specified blockchain address to an assigned handle.
	 *
	 * @param user
	 * @return API response.
	 * @throws IOException
	 * @throws InterruptedException
	 * @throws ServerSideException
	 * @throws BadRequestException
	 * @throws InvalidSignatureException
	 * @throws ForbiddenException
	 */
	public ApiResponse register(User user) throws IOException, InterruptedException, BadRequestException,
			InvalidSignatureException, ServerSideException, ForbiddenException {
		EntityMsg body = new EntityMsg(user, this.configuration.getAuthHandle());
		String path = Endpoints.REGISTER.getUri();
		String sBody = Serialization.serialize(body);
		Map<String, String> headers = new HashMap<>();

		headers.put(AUTH_SIGNATURE, EcdsaUtil.sign(sBody, this.configuration.getPrivateKey()));

		HttpResponse<?> response = this.configuration.getApiClient().callApi(path, headers, sBody);

		return ResponseUtil.prepareResponse(response, Message.ValueEnum.ENTITY_MSG.getValue());
	}

	/**
	 * Starts KYC verification process on a registered user handle.
	 *
	 * @param userHandle
	 * @param userPrivateKey
	 * @return
	 * @throws IOException
	 * @throws InterruptedException
	 * @throws BadRequestException
	 * @throws InvalidSignatureException
	 * @throws ServerSideException
	 * @throws ForbiddenException
	 */
	public ApiResponse requestKYC(String userHandle, String kycLevel, String userPrivateKey) throws IOException, InterruptedException,
			BadRequestException, InvalidSignatureException, ServerSideException, ForbiddenException {
		HeaderMsg body = new HeaderMsg(userHandle, kycLevel, this.configuration.getAuthHandle());
		String path = Endpoints.REQUEST_KYC.getUri();
		String sBody = Serialization.serialize(body);
		Map<String, String> headers = new HashMap<>();

		headers.put(AUTH_SIGNATURE, EcdsaUtil.sign(sBody, this.configuration.getPrivateKey()));
		headers.put(USER_SIGNATURE, EcdsaUtil.sign(sBody, userPrivateKey));

		HttpResponse<?> response = this.configuration.getApiClient().callApi(path, headers, sBody);

		return ResponseUtil.prepareResponse(response, Message.ValueEnum.HEADER_MSG.getValue());
	}

	/**
	 * Returns whether entity attached to user handle is verified, not valid, or
	 * still pending.
	 *
	 * @param userHandle
	 * @param userPrivateKey
	 * @return
	 * @throws IOException
	 * @throws InterruptedException
	 * @throws BadRequestException
	 * @throws InvalidSignatureException
	 * @throws ServerSideException
	 * @throws ForbiddenException
	 */
	public ApiResponse checkKYC(String userHandle, String userPrivateKey) throws IOException, InterruptedException,
			BadRequestException, InvalidSignatureException, ServerSideException, ForbiddenException {
		HeaderMsg body = new HeaderMsg(userHandle, this.configuration.getAuthHandle());
		String path = Endpoints.CHECK_KYC.getUri();
		String sBody = Serialization.serialize(body);
		Map<String, String> headers = new HashMap<>();

		headers.put(AUTH_SIGNATURE, EcdsaUtil.sign(sBody, this.configuration.getPrivateKey()));
		headers.put(USER_SIGNATURE, EcdsaUtil.sign(sBody, userPrivateKey));

		HttpResponse<?> response = this.configuration.getApiClient().callApi(path, headers, sBody);

		return ResponseUtil.prepareResponse(response, Message.ValueEnum.HEADER_MSG.getValue());
	}

	/**
	 * Uses a provided Plaid public token to link a bank account to a verified
	 * entity.
	 *
	 * @param userHandle
	 * @param accountName
	 * @param publicToken
	 * @param userPrivateKey
	 * @return
	 * @throws IOException
	 * @throws InterruptedException
	 * @throws BadRequestException
	 * @throws InvalidSignatureException
	 * @throws ServerSideException
	 * @throws ForbiddenException
	 */
	public ApiResponse linkAccount(String userHandle, 
	String accountName, 
	String publicToken, 
	String accountNumber,
	String routingNumber,
	String accountType,
	String userPrivateKey)
			throws IOException, InterruptedException, BadRequestException, InvalidSignatureException,
			ServerSideException, ForbiddenException {
		LinkAccountMsg body = new LinkAccountMsg(userHandle, accountName, publicToken,
				accountNumber, routingNumber, accountType,
				this.configuration.getAuthHandle());
		String path = Endpoints.LINK_ACCOUNT.getUri();
		String sBody = Serialization.serialize(body);
		Map<String, String> headers = new HashMap<>();

		headers.put(AUTH_SIGNATURE, EcdsaUtil.sign(sBody, this.configuration.getPrivateKey()));
		headers.put(USER_SIGNATURE, EcdsaUtil.sign(sBody, userPrivateKey));

		HttpResponse<?> response = this.configuration.getApiClient().callApi(path, headers, sBody);

		return ResponseUtil.prepareResponse(response, Message.ValueEnum.LINK_ACCOUNT_MSG.getValue());
	}

	/**
	 * Gets basic bank account names linked to user handle.
	 *
	 * @param userHandle
	 * @param userPrivateKey
	 * @return
	 * @throws IOException
	 * @throws InterruptedException
	 * @throws BadRequestException
	 * @throws InvalidSignatureException
	 * @throws ServerSideException
	 * @throws ForbiddenException
	 */
	public ApiResponse getAccounts(String userHandle, String userPrivateKey) throws IOException, InterruptedException,
			BadRequestException, InvalidSignatureException, ServerSideException, ForbiddenException {
		GetAccountsMsg body = new GetAccountsMsg(userHandle, this.configuration.getAuthHandle());
		String path = Endpoints.GET_ACCOUNTS.getUri();
		String sBody = Serialization.serialize(body);
		Map<String, String> headers = new HashMap<>();

		headers.put(AUTH_SIGNATURE, EcdsaUtil.sign(sBody, this.configuration.getPrivateKey()));
		headers.put(USER_SIGNATURE, EcdsaUtil.sign(sBody, userPrivateKey));

		HttpResponse<?> response = this.configuration.getApiClient().callApi(path, headers, sBody);

		return ResponseUtil.prepareResponse(response, Message.ValueEnum.GET_ACCOUNTS_MSG.getValue());
	}

	public ApiResponse getAccountBalance(String userHandle, String userPrivateKey, String accountName) throws IOException, InterruptedException,
			BadRequestException, InvalidSignatureException, ServerSideException, ForbiddenException {
		GetAccountBalanceMsg body = new GetAccountBalanceMsg(userHandle, this.configuration.getAuthHandle(), accountName);
		String path = Endpoints.GET_ACCOUNT_BALANCE.getUri();
		String sBody = Serialization.serialize(body);
		Map<String, String> headers = new HashMap<>();

		headers.put(AUTH_SIGNATURE, EcdsaUtil.sign(sBody, this.configuration.getPrivateKey()));
		headers.put(USER_SIGNATURE, EcdsaUtil.sign(sBody, userPrivateKey));

		HttpResponse<?> response = this.configuration.getApiClient().callApi(path, headers, sBody);

		return ResponseUtil.prepareResponse(response, Message.ValueEnum.GET_ACCOUNT_BALANCE_MSG.getValue());
	}

	/**
	 * Debits a specified account and issues tokens to the address belonging to the
	 * requested handle.
	 *
	 * @param userHandle
	 * @param amount
	 * @param accountName
	 * @param descriptor
	 * @param businessUuid
	 * @param userPrivateKey
	 * @return
	 * @throws IOException
	 * @throws InterruptedException
	 * @throws BadRequestException
	 * @throws InvalidSignatureException
	 * @throws ServerSideException
	 * @throws ForbiddenException
	 */
	public ApiResponse issueSila(String userHandle, int amount, @Nullable String accountName,
			String userPrivateKey)
			throws IOException, InterruptedException, BadRequestException, InvalidSignatureException,
			ServerSideException, ForbiddenException {
		if (accountName == null || accountName.isBlank()) {
			accountName = "default";
		}
		IssueMsg body = new IssueMsg(userHandle, accountName, amount, this.configuration.getAuthHandle());
		String path = Endpoints.ISSUE_SILA.getUri();
		String sBody = Serialization.serialize(body);
		Map<String, String> headers = new HashMap<>();

		headers.put(AUTH_SIGNATURE, EcdsaUtil.sign(sBody, this.configuration.getPrivateKey()));
		headers.put(USER_SIGNATURE, EcdsaUtil.sign(sBody, userPrivateKey));

		HttpResponse<?> response = this.configuration.getApiClient().callApi(path, headers, sBody);

		return ResponseUtil.prepareResponse(response, Message.ValueEnum.ISSUE_MSG.getValue());
	}

	/**
	 * Starts a transfer of the requested amount of SILA to the requested
	 * destination handle.
	 * 
	 * @param userHandle
	 * @param amount
	 * @param destination
	 * @param destinationAddress
	 * @param userPrivateKey
	 * @return
	 * @throws IOException
	 * @throws InterruptedException
	 * @throws BadRequestException
	 * @throws InvalidSignatureException
	 * @throws ServerSideException
	 * @throws ForbiddenException
	 */
	public ApiResponse transferSila(String userHandle, int amount, String destination, String destinationAddress,
			@Nullable String descriptor, @Nullable String businessUuid, String userPrivateKey)
			throws IOException, InterruptedException, BadRequestException, InvalidSignatureException,
			ServerSideException, ForbiddenException {
		TransferMsg body = new TransferMsg(userHandle, destination, amount, destinationAddress, descriptor, businessUuid, this.configuration.getAuthHandle());
		String path = Endpoints.TRANSFER_SILA.getUri();
		String sBody = Serialization.serialize(body);
		Map<String, String> headers = new HashMap<>();

		headers.put(AUTH_SIGNATURE, EcdsaUtil.sign(sBody, this.configuration.getPrivateKey()));
		headers.put(USER_SIGNATURE, EcdsaUtil.sign(sBody, userPrivateKey));

		HttpResponse<?> response = this.configuration.getApiClient().callApi(path, headers, sBody);

		return ResponseUtil.prepareResponse(response, Message.ValueEnum.TRANSFER_MSG.getValue());
	}

	/**
	 * Burns given amount of SILA at the handle's blockchain address and credits
	 * their named bank account in the equivalent monetary amount.
	 *
	 * @param userHandle
	 * @param amount
	 * @param accountName
	 * @param userPrivateKey
	 * @return
	 * @throws IOException
	 * @throws InterruptedException
	 * @throws BadRequestException
	 * @throws InvalidSignatureException
	 * @throws ServerSideException
	 * @throws ForbiddenException
	 */
	public ApiResponse redeemSila(String userHandle, int amount, @Nullable String accountName, 
			String userPrivateKey)
			throws IOException, InterruptedException, BadRequestException, InvalidSignatureException,
			ServerSideException, ForbiddenException {
		if (accountName == null || accountName.isBlank()) {
			accountName = "default";
		}
		RedeemMsg body = new RedeemMsg(userHandle, amount, accountName, this.configuration.getAuthHandle());
		String path = Endpoints.REDEEM_SILA.getUri();
		String sBody = Serialization.serialize(body);
		Map<String, String> headers = new HashMap<>();

		headers.put(AUTH_SIGNATURE, EcdsaUtil.sign(sBody, this.configuration.getPrivateKey()));
		headers.put(USER_SIGNATURE, EcdsaUtil.sign(sBody, userPrivateKey));

		HttpResponse<?> response = this.configuration.getApiClient().callApi(path, headers, sBody);

		return ResponseUtil.prepareResponse(response, Message.ValueEnum.REDEEM_MSG.getValue());
	}

	/**
	 * Gets array of user handle's transactions with detailed status information.
	 *
	 * @param userHandle
	 * @param filters
	 * @param userPrivateKey
	 * @return
	 * @throws IOException
	 * @throws InterruptedException
	 * @throws BadRequestException
	 * @throws InvalidSignatureException
	 * @throws ServerSideException
	 * @throws ForbiddenException
	 */
	public ApiResponse getTransactions(String userHandle, SearchFilters filters, String userPrivateKey)
			throws IOException, InterruptedException, BadRequestException, InvalidSignatureException,
			ServerSideException, ForbiddenException {
		GetTransactionsMsg body = new GetTransactionsMsg(userHandle, this.configuration.getAuthHandle(), filters);
		String path = Endpoints.GET_TRANSACTIONS.getUri();
		String sBody = Serialization.serialize(body);
		Map<String, String> headers = new HashMap<>();

		headers.put(AUTH_SIGNATURE, EcdsaUtil.sign(sBody, this.configuration.getPrivateKey()));
		headers.put(USER_SIGNATURE, EcdsaUtil.sign(sBody, userPrivateKey));

		HttpResponse<?> response = this.configuration.getApiClient().callApi(path, headers, sBody);

		return ResponseUtil.prepareResponse(response, Message.ValueEnum.GET_TRANSACTIONS_MSG.getValue());
	}

	/**
	 * Gets Sila balance for a given blockchain address.
	 *
	 * @param host
	 * @param address
	 * @return
	 * @throws IOException
	 * @throws InterruptedException
	 * @throws BadRequestException
	 * @throws InvalidSignatureException
	 * @throws ServerSideException
	 * @throws ForbiddenException
	 */
	public ApiResponse silaBalance(String host, String address) throws IOException, InterruptedException,
			BadRequestException, InvalidSignatureException, ServerSideException, ForbiddenException {
		SilaBalanceMsg body = new SilaBalanceMsg(address);
		String path = Endpoints.GET_SILA_BALANCE.getUri();
		String sBody = Serialization.serialize(body);
		Map<String, String> headers = new HashMap<>();

		String initialBasePath = this.configuration.getBasePath();

		this.configuration.setBasePath(host);

		HttpResponse<?> response = this.configuration.getApiClient().callApi(path, headers, sBody);

		this.configuration.setBasePath(initialBasePath);

		return ResponseUtil.prepareResponse(response, Message.ValueEnum.GET_SILA_BALANCE.getValue());
	}

	/**
	 * Request a public_token for plaid's same day microdeposit auth.
	 * 
	 * @param userHandle
	 * @param accountName
	 * @param userPrivateKey
	 * @return
	 * @throws IOException
	 * @throws InterruptedException
	 * @throws BadRequestException
	 * @throws InvalidSignatureException
	 * @throws ServerSideException
	 * @throws ForbiddenException
	 */
	public ApiResponse plaidSameDayAuth(String userHandle, @Nullable String accountName, String userPrivateKey)
			throws IOException, InterruptedException, BadRequestException, InvalidSignatureException,
			ServerSideException, ForbiddenException {
		PlaidSameDayAuthMsg body = new PlaidSameDayAuthMsg(userHandle, accountName, this.configuration.getAuthHandle());
		String path = Endpoints.PLAID_SAMEDAY_AUTH.getUri();
		String sBody = Serialization.serialize(body);
		Map<String, String> headers = new HashMap<>();

		headers.put(AUTH_SIGNATURE, EcdsaUtil.sign(sBody, this.configuration.getPrivateKey()));
		headers.put(USER_SIGNATURE, EcdsaUtil.sign(sBody, userPrivateKey));

		HttpResponse<?> response = this.configuration.getApiClient().callApi(path, headers, sBody);

		return ResponseUtil.prepareResponse(response, Message.ValueEnum.PLAID_SAMEDAY_AUTH_MSG.getValue());
	}	

	/**
	 * Gets details about the user wallet used to generate the usersignature header..
	 * 
	 * @param userHandle
	 * @param userPrivateKey
	 * @return
	 * @throws IOException
	 * @throws InterruptedException
	 * @throws BadRequestException
	 * @throws InvalidSignatureException
	 * @throws ServerSideException
	 * @throws ForbiddenException
	 */
	public ApiResponse getWallet(String userHandle, String userPrivateKey)
			throws IOException, InterruptedException, BadRequestException, InvalidSignatureException,
			ServerSideException, ForbiddenException {
		GetWalletMsg body = new GetWalletMsg(userHandle, this.configuration.getAuthHandle());
		String path = Endpoints.GET_WALLET.getUri();
		String sBody = Serialization.serialize(body);
		Map<String, String> headers = new HashMap<>();

		headers.put(AUTH_SIGNATURE, EcdsaUtil.sign(sBody, this.configuration.getPrivateKey()));
		headers.put(USER_SIGNATURE, EcdsaUtil.sign(sBody, userPrivateKey));

		HttpResponse<?> response = this.configuration.getApiClient().callApi(path, headers, sBody);

		return ResponseUtil.prepareResponse(response, Message.ValueEnum.GET_WALLET_MSG.getValue());
	}	

	/**
	 * Adds another "wallet"/blockchain address to a user handle.
	 * 
	 * @param userHandle
	 * @param wallet
	 * @param walletVerificationSignature
	 * @param userPrivateKey
	 * @return
	 * @throws IOException
	 * @throws InterruptedException
	 * @throws BadRequestException
	 * @throws InvalidSignatureException
	 * @throws ServerSideException
	 * @throws ForbiddenException
	 */
	public ApiResponse registerWallet(String userHandle, Wallet wallet, String walletVerificationSignature, String userPrivateKey)
			throws IOException, InterruptedException, BadRequestException, InvalidSignatureException,
			ServerSideException, ForbiddenException {
		RegisterWalletMsg body = new RegisterWalletMsg(userHandle, wallet, walletVerificationSignature, this.configuration.getAuthHandle());
		String path = Endpoints.REGISTER_WALLET.getUri();
		String sBody = Serialization.serialize(body);
		Map<String, String> headers = new HashMap<>();

		headers.put(AUTH_SIGNATURE, EcdsaUtil.sign(sBody, this.configuration.getPrivateKey()));
		headers.put(USER_SIGNATURE, EcdsaUtil.sign(sBody, userPrivateKey));
		
		// System.out.println("HEADERS >");
		// System.out.println(GsonUtils.objectToJsonStringFormato(headers));
		// System.out.println("BODY >");
		// System.out.println(GsonUtils.objectToJsonStringFormato(body));

		HttpResponse<?> response = this.configuration.getApiClient().callApi(path, headers, sBody);

		return ResponseUtil.prepareResponse(response, Message.ValueEnum.REGISTER_WALLET_MSG.getValue());
	}
	
	/**
	 * Updates nickname and/or default status of a wallet.
	 * 
	 * @param userHandle
	 * @param nickname
	 * @param status
	 * @param userPrivateKey
	 * @return
	 * @throws IOException
	 * @throws InterruptedException
	 * @throws BadRequestException
	 * @throws InvalidSignatureException
	 * @throws ServerSideException
	 * @throws ForbiddenException
	 */
	public ApiResponse updateWallet(String userHandle, String nickname, boolean status, String userPrivateKey)
			throws IOException, InterruptedException, BadRequestException, InvalidSignatureException,
			ServerSideException, ForbiddenException {
		UpdateWalletMsg body = new UpdateWalletMsg(userHandle, nickname, status, this.configuration.getAuthHandle());
		String path = Endpoints.UPDATE_WALLET.getUri();
		String sBody = Serialization.serialize(body);
		Map<String, String> headers = new HashMap<>();

		headers.put(AUTH_SIGNATURE, EcdsaUtil.sign(sBody, this.configuration.getPrivateKey()));
		headers.put(USER_SIGNATURE, EcdsaUtil.sign(sBody, userPrivateKey));

		HttpResponse<?> response = this.configuration.getApiClient().callApi(path, headers, sBody);

		return ResponseUtil.prepareResponse(response, Message.ValueEnum.UPDATE_WALLET_MSG.getValue());
	}	

	/**
	 * 
	 * @param userHandle
	 * @param userPrivateKey
	 * @return
	 * @throws IOException
	 * @throws InterruptedException
	 * @throws BadRequestException
	 * @throws InvalidSignatureException
	 * @throws ServerSideException
	 * @throws ForbiddenException
	 */
	public ApiResponse deleteWallet(String userHandle, String userPrivateKey)
			throws IOException, InterruptedException, BadRequestException, InvalidSignatureException,
			ServerSideException, ForbiddenException {
		DeleteWalletMsg body = new DeleteWalletMsg(userHandle, this.configuration.getAuthHandle());
		String path = Endpoints.DELETE_WALLET.getUri();
		String sBody = Serialization.serialize(body);
		Map<String, String> headers = new HashMap<>();

		headers.put(AUTH_SIGNATURE, EcdsaUtil.sign(sBody, this.configuration.getPrivateKey()));
		headers.put(USER_SIGNATURE, EcdsaUtil.sign(sBody, userPrivateKey));

		HttpResponse<?> response = this.configuration.getApiClient().callApi(path, headers, sBody);

		return ResponseUtil.prepareResponse(response, Message.ValueEnum.DELETE_WALLET_MSG.getValue());
	}	

	/**
	 * Gets a paginated list of "wallets"/blockchain addresses attached to a user handle.
	 *
	 * @param userHandle
	 * @param filters
	 * @param userPrivateKey
	 * @return
	 * @throws IOException
	 * @throws InterruptedException
	 * @throws BadRequestException
	 * @throws InvalidSignatureException
	 * @throws ServerSideException
	 * @throws ForbiddenException
	 */
	public ApiResponse getWallets(String userHandle, SearchFilters filters, String userPrivateKey)
			throws IOException, InterruptedException, BadRequestException, InvalidSignatureException,
			ServerSideException, ForbiddenException {
		GetWalletsMsg body = new GetWalletsMsg(userHandle, filters,  this.configuration.getAuthHandle());
		String path = Endpoints.GET_WALLETS.getUri();
		String sBody = Serialization.serialize(body);
		Map<String, String> headers = new HashMap<>();

		headers.put(AUTH_SIGNATURE, EcdsaUtil.sign(sBody, this.configuration.getPrivateKey()));
		headers.put(USER_SIGNATURE, EcdsaUtil.sign(sBody, userPrivateKey));

		HttpResponse<?> response = this.configuration.getApiClient().callApi(path, headers, sBody);

		return ResponseUtil.prepareResponse(response, Message.ValueEnum.GET_WALLETS_MSG.getValue());
	}	
}
