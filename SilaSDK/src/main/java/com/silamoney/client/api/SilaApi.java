package com.silamoney.client.api;

import java.io.*;
import java.math.BigInteger;
import java.lang.reflect.Type;
import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import com.silamoney.client.config.Configuration;
import com.silamoney.client.domain.*;
import com.silamoney.client.exceptions.BadRequestException;
import com.silamoney.client.exceptions.ForbiddenException;
import com.silamoney.client.exceptions.InvalidSignatureException;
import com.silamoney.client.exceptions.ServerSideException;
import com.silamoney.client.security.EcdsaUtil;
import com.silamoney.client.util.EpochUtils;
import com.silamoney.client.util.ResponseUtil;
import com.silamoney.client.util.Serialization;

import io.reactivex.annotations.Nullable;

import org.web3j.crypto.CipherException;
import org.web3j.crypto.ECKeyPair;
import org.web3j.crypto.Keys;
import org.web3j.crypto.WalletFile;

import java.security.InvalidAlgorithmParameterException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;

/**
 *
 * @author Karlo Lorenzana
 */
public class SilaApi {

    private final Configuration configuration;
    private static final String AUTH_SIGNATURE = "authsignature";
    private static final String USER_SIGNATURE = "usersignature";
    private static final String BUSINESS_SIGNATURE = "businesssignature";
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
     * Delete an existing email, phone number, street address, or identity.
     * 
     * @param message
     * @return
     * @throws IOException
     * @throws InterruptedException
     */
    public ApiResponse deleteRegistrationData(RegistrationDataEnum dataType, DeleteRegistrationMessage message)
            throws IOException, InterruptedException {
        DeleteRegistrationMsg body = new DeleteRegistrationMsg(this.configuration.getAuthHandle(), message);
        String path = String.format("%s/%s", Endpoints.DELETE_REGISTRATION.getUri(), dataType.getUri());
        String sBody = Serialization.serialize(body);
        Map<String, String> headers = new HashMap<>();
        headers.put(AUTH_SIGNATURE, EcdsaUtil.sign(sBody, this.configuration.getPrivateKey()));
        headers.put(USER_SIGNATURE, EcdsaUtil.sign(sBody, message.getUserPrivateKey()));

        HttpResponse<?> response = this.configuration.getApiClient().callApi(path, headers, sBody);

        return ResponseUtil.prepareResponse(BaseResponse.class, response);
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
    public ApiResponse requestKYC(String userHandle, String kycLevel, String userPrivateKey)
            throws IOException, InterruptedException, BadRequestException, InvalidSignatureException,
            ServerSideException, ForbiddenException {
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
     */
    public ApiResponse checkKYC(String userHandle, String userPrivateKey) throws IOException, InterruptedException {
        HeaderMsg body = new HeaderMsg(userHandle, this.configuration.getAuthHandle());
        String path = Endpoints.CHECK_KYC.getUri();
        String sBody = Serialization.serialize(body);
        Map<String, String> headers = new HashMap<>();

        headers.put(AUTH_SIGNATURE, EcdsaUtil.sign(sBody, this.configuration.getPrivateKey()));
        headers.put(USER_SIGNATURE, EcdsaUtil.sign(sBody, userPrivateKey));

        HttpResponse<?> response = this.configuration.getApiClient().callApi(path, headers, sBody);

        return ResponseUtil.prepareResponse(response, Message.ValueEnum.CHECK_KYC.getValue());
    }

    /**
     * Uses a provided Plaid public token to link a bank account to a verified
     * entity. It selectes the first account return with the plaid token.
     *
     * @param userHandle
     * @param userPrivateKey
     * @param accountName
     * @param publicToken
     * @return {@link ApiResponse}
     * @throws IOException
     * @throws InterruptedException
     */
    public ApiResponse linkAccount(String userHandle, String userPrivateKey, String accountName, String publicToken)
            throws IOException, InterruptedException {
        return linkAccount(userHandle, userPrivateKey, accountName, publicToken, null, null, null, null);
    }

    /**
     * Uses a provided Plaid public token to link a bank account to a verified
     * entity. It uses the provided account id to select the account to link.
     *
     * @param userHandle
     * @param userPrivateKey
     * @param accountName
     * @param publicToken
     * @param accountId
     * @return {@link ApiResponse}
     * @throws IOException
     * @throws InterruptedException
     */
    public ApiResponse linkAccount(String userHandle, String userPrivateKey, String accountName, String publicToken,
            String accountId) throws IOException, InterruptedException {
        return linkAccount(userHandle, userPrivateKey, accountName, publicToken, accountId, null, null, null);
    }

    /**
     * Direct account linking. This is a restricted use case. Please contact Sila
     * for approval
     *
     * @param userHandle
     * @param userPrivateKey
     * @param accountName
     * @param accountNumber
     * @param routingNumber
     * @param accountType
     * @return {@link ApiResponse}
     * @throws IOException
     * @throws InterruptedException
     */
    public ApiResponse linkAccount(String userHandle, String userPrivateKey, String accountName, String accountNumber,
            String routingNumber, String accountType) throws IOException, InterruptedException {
        return linkAccount(userHandle, userPrivateKey, accountName, null, null, accountNumber, routingNumber,
                accountType);
    }

    /**
     * Makes a request to the link_acccount endpoint
     *
     * @param userHandle
     * @param userPrivateKey
     * @param accountName
     * @param publicToken
     * @param accountId
     * @param accountNumber
     * @param routingNumber
     * @param accountType
     * @return {@link ApiResponse}
     * @throws IOException
     * @throws InterruptedException
     */
    private ApiResponse linkAccount(String userHandle, String userPrivateKey, String accountName, String publicToken,
            String accountId, String accountNumber, String routingNumber, String accountType)
            throws IOException, InterruptedException {
        LinkAccountMsg body = new LinkAccountMsg(userHandle, accountName, publicToken, accountId, accountNumber,
                routingNumber, accountType, this.configuration.getAuthHandle());
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

    /**
     * @param userHandle
     * @param userPrivateKey
     * @param accountName
     * @return ApiResponse
     * @throws IOException
     * @throws InterruptedException
     * @throws BadRequestException
     * @throws InvalidSignatureException
     * @throws ServerSideException
     * @throws ForbiddenException
     */
    public ApiResponse getAccountBalance(String userHandle, String userPrivateKey, String accountName)
            throws IOException, InterruptedException, BadRequestException, InvalidSignatureException,
            ServerSideException, ForbiddenException {
        GetAccountBalanceMsg body = new GetAccountBalanceMsg(userHandle, this.configuration.getAuthHandle(),
                accountName);
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
     * @param message
     * @return
     * @throws IOException
     * @throws InterruptedException
     * @throws BadRequestException
     * @throws InvalidSignatureException
     * @throws ServerSideException
     * @throws ForbiddenException
     */
    public ApiResponse issueSila(AccountTransactionMessage message) throws IOException, InterruptedException,
            BadRequestException, InvalidSignatureException, ServerSideException, ForbiddenException {
        String path = Endpoints.ISSUE_SILA.getUri();
        AccountTransactionMsg body = new AccountTransactionMsg(this.configuration.getAuthHandle(), message,
                Message.ValueEnum.ISSUE_MSG);
        String sBody = Serialization.serialize(body);
        Map<String, String> headers = new HashMap<>();

        headers.put(AUTH_SIGNATURE, EcdsaUtil.sign(sBody, this.configuration.getPrivateKey()));
        headers.put(USER_SIGNATURE, EcdsaUtil.sign(sBody, message.getUserPrivateKey()));

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
    public ApiResponse transferSila(String userHandle, int amount, String destination, String destinationAddress,
            @Nullable String descriptor, @Nullable String businessUuid, String userPrivateKey)
            throws IOException, InterruptedException, BadRequestException, InvalidSignatureException,
            ServerSideException, ForbiddenException {
        TransferMsg body = new TransferMsg(userHandle, destination, amount, destinationAddress, descriptor,
                businessUuid, this.configuration.getAuthHandle());
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
     * @param message
     * @return
     * @throws IOException
     * @throws InterruptedException
     * @throws BadRequestException
     * @throws InvalidSignatureException
     * @throws ServerSideException
     * @throws ForbiddenException
     */
    public ApiResponse redeemSila(AccountTransactionMessage message) throws IOException, InterruptedException,
            BadRequestException, InvalidSignatureException, ServerSideException, ForbiddenException {
        AccountTransactionMsg body = new AccountTransactionMsg(this.configuration.getAuthHandle(), message,
                Message.ValueEnum.REDEEM_MSG);
        String path = Endpoints.REDEEM_SILA.getUri();
        String sBody = Serialization.serialize(body);
        Map<String, String> headers = new HashMap<>();

        headers.put(AUTH_SIGNATURE, EcdsaUtil.sign(sBody, this.configuration.getPrivateKey()));
        headers.put(USER_SIGNATURE, EcdsaUtil.sign(sBody, message.getUserPrivateKey()));

        HttpResponse<?> response = this.configuration.getApiClient().callApi(path, headers, sBody);

        return ResponseUtil.prepareResponse(response, Message.ValueEnum.REDEEM_MSG.getValue());
    }

    /**
     * Cancel a pending transaction under certain circumstances
     * 
     * @param message
     * @return
     */
    public ApiResponse cancelTransaction(CancelTransactionMessage message) throws IOException, InterruptedException {
        CancelTransactionMsg body = new CancelTransactionMsg(this.configuration.getAuthHandle(), message);
        String path = Endpoints.CANCEL_TRANSACTION.getUri();
        String sBody = Serialization.serialize(body);
        Map<String, String> headers = new HashMap<>();
        headers.put(AUTH_SIGNATURE, EcdsaUtil.sign(sBody, this.configuration.getPrivateKey()));
        headers.put(USER_SIGNATURE, EcdsaUtil.sign(sBody, message.getUserPrivateKey()));

        HttpResponse<?> response = this.configuration.getApiClient().callApi(path, headers, sBody);
        return ResponseUtil.prepareResponse(BaseResponse.class, response);
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
     * @param address
     * @return
     * @throws IOException
     * @throws InterruptedException
     * @throws BadRequestException
     * @throws InvalidSignatureException
     * @throws ServerSideException
     * @throws ForbiddenException
     */
    public ApiResponse silaBalance(String address) throws IOException, InterruptedException, BadRequestException,
            InvalidSignatureException, ServerSideException, ForbiddenException {
        SilaBalanceMsg body = new SilaBalanceMsg(address);
        String path = Endpoints.GET_SILA_BALANCE.getUri();
        String sBody = Serialization.serialize(body);
        Map<String, String> headers = new HashMap<>();

        HttpResponse<?> response = this.configuration.getApiClient().callApi(path, headers, sBody);

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
     * Gets a random generated wallet
     * 
     * @return Wallet
     */
    public Wallet generateWallet() throws InvalidAlgorithmParameterException, NoSuchAlgorithmException,
            NoSuchProviderException, CipherException {
        ECKeyPair ecKeyPair = Keys.createEcKeyPair();
        BigInteger privateKeyInDec = ecKeyPair.getPrivateKey();

        WalletFile aWallet = org.web3j.crypto.Wallet.createLight(UUID.randomUUID().toString(), ecKeyPair);

        return new Wallet("0x" + aWallet.getAddress(), privateKeyInDec.toString(16), "ETH", "generated wallet");
    }

    /**
     * Gets details about the user wallet used to generate the usersignature
     * header..
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
    public ApiResponse getWallet(String userHandle, String userPrivateKey) throws IOException, InterruptedException,
            BadRequestException, InvalidSignatureException, ServerSideException, ForbiddenException {
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
    public ApiResponse registerWallet(String userHandle, Wallet wallet, String walletVerificationSignature,
            String userPrivateKey) throws IOException, InterruptedException, BadRequestException,
            InvalidSignatureException, ServerSideException, ForbiddenException {
        RegisterWalletMsg body = new RegisterWalletMsg(userHandle, wallet, walletVerificationSignature,
                this.configuration.getAuthHandle());
        String path = Endpoints.REGISTER_WALLET.getUri();
        String sBody = Serialization.serialize(body);
        Map<String, String> headers = new HashMap<>();

        headers.put(AUTH_SIGNATURE, EcdsaUtil.sign(sBody, this.configuration.getPrivateKey()));
        headers.put(USER_SIGNATURE, EcdsaUtil.sign(sBody, userPrivateKey));

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
    public ApiResponse deleteWallet(String userHandle, String userPrivateKey) throws IOException, InterruptedException,
            BadRequestException, InvalidSignatureException, ServerSideException, ForbiddenException {
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
     * Gets a paginated list of "wallets"/blockchain addresses attached to a user
     * handle.
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
        GetWalletsMsg body = new GetWalletsMsg(userHandle, filters, this.configuration.getAuthHandle());
        String path = Endpoints.GET_WALLETS.getUri();
        String sBody = Serialization.serialize(body);
        Map<String, String> headers = new HashMap<>();

        headers.put(AUTH_SIGNATURE, EcdsaUtil.sign(sBody, this.configuration.getPrivateKey()));
        headers.put(USER_SIGNATURE, EcdsaUtil.sign(sBody, userPrivateKey));

        HttpResponse<?> response = this.configuration.getApiClient().callApi(path, headers, sBody);

        return ResponseUtil.prepareResponse(response, Message.ValueEnum.GET_WALLETS_MSG.getValue());
    }

    /**
     * List the document types for KYC supporting documentation
     * 
     * @return
     * @throws IOException
     * @throws InterruptedException
     */
    public ApiResponse getDocumentTypes() throws IOException, InterruptedException {
        return getDocumentTypes("");
    }

    /**
     * List the document types for KYC supporting documentation
     * 
     * @param pagination
     * @return
     * @throws IOException
     * @throws InterruptedException
     */
    public ApiResponse getDocumentTypes(PaginationMessage pagination) throws IOException, InterruptedException {
        return getDocumentTypes(pagination.getUrlParams());
    }

    private ApiResponse getDocumentTypes(String urlParams) throws IOException, InterruptedException {
        String path = Endpoints.GET_DOCUMENT_TYPES.getUri() + urlParams;
        HeaderBase header = new HeaderBuilder(this.configuration.getAuthHandle()).useVersion(VersionEnum.V0_2)
                .withCrypto(CryptoEnum.ETH).withReference().build();
        Map<String, HeaderBase> bodyMap = new HashMap<String, HeaderBase>();
        bodyMap.put("header", header);
        String sBody = Serialization.serialize(bodyMap);
        Map<String, String> headers = new HashMap<String, String>();
        headers.put(AUTH_SIGNATURE, EcdsaUtil.sign(sBody, this.configuration.getPrivateKey()));
        HttpResponse<?> response = this.configuration.getApiClient().callApi(path, headers, sBody);

        return ResponseUtil.prepareResponse(DocumentTypesResponse.class, response);
    }

    /**
     * Gets a list of valid business types that can be registered.
     * 
     * @return ApiResponse
     * @throws IOException
     * @throws InterruptedException
     * @throws BadRequestException
     * @throws InvalidSignatureException
     * @throws ServerSideException
     * @throws ForbiddenException
     */
    public ApiResponse getBusinessTypes() throws IOException, InterruptedException, BadRequestException,
            InvalidSignatureException, ServerSideException, ForbiddenException {
        Map<String, String> header = new HashMap<String, String>();
        header.put("created", EpochUtils.getEpoch() + "");
        header.put("auth_handle", this.configuration.getAuthHandle());

        Map<String, Map<String, String>> bodyMap = new HashMap<String, Map<String, String>>();
        bodyMap.put("header", header);

        String path = Endpoints.GET_BUSINESS_TYPES.getUri();
        String sBody = Serialization.serialize(bodyMap);
        Map<String, String> headers = new HashMap<>();

        headers.put(AUTH_SIGNATURE, EcdsaUtil.sign(sBody, this.configuration.getPrivateKey()));

        HttpResponse<?> response = this.configuration.getApiClient().callApi(path, headers, sBody);

        return ResponseUtil.prepareResponse(response, Message.ValueEnum.GET_BUSINESS_TYPES.getValue());
    }

    /**
     * Retrieves the list of pre-defined business roles.
     * 
     * @return ApiResponse
     * @throws IOException
     * @throws InterruptedException
     * @throws BadRequestException
     * @throws InvalidSignatureException
     * @throws ServerSideException
     * @throws ForbiddenException
     */
    public ApiResponse getBusinessRoles() throws IOException, InterruptedException, BadRequestException,
            InvalidSignatureException, ServerSideException, ForbiddenException {
        Map<String, String> header = new HashMap<String, String>();
        header.put("created", EpochUtils.getEpoch() + "");
        header.put("auth_handle", this.configuration.getAuthHandle());

        Map<String, Map<String, String>> bodyMap = new HashMap<String, Map<String, String>>();
        bodyMap.put("header", header);

        String path = Endpoints.GET_BUSINESS_ROLES.getUri();
        String sBody = Serialization.serialize(bodyMap);
        Map<String, String> headers = new HashMap<>();

        headers.put(AUTH_SIGNATURE, EcdsaUtil.sign(sBody, this.configuration.getPrivateKey()));

        HttpResponse<?> response = this.configuration.getApiClient().callApi(path, headers, sBody);

        return ResponseUtil.prepareResponse(response, Message.ValueEnum.GET_BUSINESS_ROLES.getValue());
    }

    /**
     * @return ApiResponse
     * @throws IOException
     * @throws InterruptedException
     * @throws BadRequestException
     * @throws InvalidSignatureException
     * @throws ServerSideException
     * @throws ForbiddenException
     */
    public ApiResponse getNaicsCategories() throws IOException, InterruptedException, BadRequestException,
            InvalidSignatureException, ServerSideException, ForbiddenException {
        Map<String, String> header = new HashMap<String, String>();
        header.put("created", EpochUtils.getEpoch() + "");
        header.put("auth_handle", this.configuration.getAuthHandle());

        Map<String, Map<String, String>> bodyMap = new HashMap<String, Map<String, String>>();
        bodyMap.put("header", header);

        String path = Endpoints.GET_NAICS_CATEGORIES.getUri();
        String sBody = Serialization.serialize(bodyMap);
        Map<String, String> headers = new HashMap<>();

        headers.put(AUTH_SIGNATURE, EcdsaUtil.sign(sBody, this.configuration.getPrivateKey()));

        HttpResponse<?> response = this.configuration.getApiClient().callApi(path, headers, sBody);

        return ResponseUtil.prepareResponse(response, Message.ValueEnum.GET_NAICS_CATEGORIES_MSG.getValue());
    }

    /**
     * @param user
     * @return ApiResponse
     * @throws IOException
     * @throws InterruptedException
     * @throws BadRequestException
     * @throws InvalidSignatureException
     * @throws ServerSideException
     * @throws ForbiddenException
     */
    public ApiResponse registerBusiness(BusinessUser user) throws IOException, InterruptedException,
            BadRequestException, InvalidSignatureException, ServerSideException, ForbiddenException {
        EntityMsg body = new EntityMsg(user, this.configuration.getAuthHandle());
        String path = Endpoints.REGISTER.getUri();
        String sBody = Serialization.serialize(body);
        Map<String, String> headers = new HashMap<>();

        headers.put(AUTH_SIGNATURE, EcdsaUtil.sign(sBody, this.configuration.getPrivateKey()));

        HttpResponse<?> response = this.configuration.getApiClient().callApi(path, headers, sBody);

        return ResponseUtil.prepareResponse(response, Message.ValueEnum.ENTITY_MSG.getValue());
    }

    /**
     * @param userHandle
     * @param userPrivateKey
     * @param businessHandle
     * @param businessPrivateKey
     * @param businessRole
     * @param memberHandle
     * @param details
     * @param ownershipStake
     * @return ApiResponse
     * @throws IOException
     * @throws InterruptedException
     * @throws BadRequestException
     * @throws InvalidSignatureException
     * @throws ServerSideException
     * @throws ForbiddenException
     */
    public ApiResponse linkBusinessMember(String userHandle, String userPrivateKey, String businessHandle,
            String businessPrivateKey, BusinessRole businessRole, String memberHandle, String details,
            Float ownershipStake) throws IOException, InterruptedException, BadRequestException,
            InvalidSignatureException, ServerSideException, ForbiddenException {
        Map<String, String> header = new HashMap<String, String>();
        header.put("created", EpochUtils.getEpoch() + "");
        header.put("auth_handle", this.configuration.getAuthHandle());
        header.put("user_handle", userHandle);
        header.put("business_handle", businessHandle);

        Map<String, Object> bodyMap = new HashMap<String, Object>();
        bodyMap.put("header", header);
        bodyMap.put("role", businessRole.getName());
        bodyMap.put("role_uuid", businessRole.getUuid());
        bodyMap.put("member_handle", memberHandle);
        bodyMap.put("ownership_stake", ownershipStake);
        bodyMap.put("details", details);

        String path = Endpoints.LINK_BUSINESS_MEMBER.getUri();
        String sBody = Serialization.serialize(bodyMap);
        Map<String, String> headers = new HashMap<>();

        headers.put(AUTH_SIGNATURE, EcdsaUtil.sign(sBody, this.configuration.getPrivateKey()));
        headers.put(USER_SIGNATURE, EcdsaUtil.sign(sBody, userPrivateKey));
        headers.put(BUSINESS_SIGNATURE, EcdsaUtil.sign(sBody, businessPrivateKey));

        HttpResponse<?> response = this.configuration.getApiClient().callApi(path, headers, sBody);

        return ResponseUtil.prepareResponse(response, Message.ValueEnum.LINK_BUSINESS_MEMBER_MSG.getValue());
    }

    /**
     * @param userHandle
     * @param userPrivateKey
     * @param businessHandle
     * @param businessPrivateKey
     * @param businessRole
     * @return ApiResponse
     * @throws IOException
     * @throws InterruptedException
     * @throws BadRequestException
     * @throws InvalidSignatureException
     * @throws ServerSideException
     * @throws ForbiddenException
     */
    public ApiResponse unlinkBusinessMember(String userHandle, String userPrivateKey, String businessHandle,
            String businessPrivateKey, BusinessRole businessRole) throws IOException, InterruptedException,
            BadRequestException, InvalidSignatureException, ServerSideException, ForbiddenException {
        Map<String, String> header = new HashMap<String, String>();
        header.put("created", EpochUtils.getEpoch() + "");
        header.put("auth_handle", this.configuration.getAuthHandle());
        header.put("user_handle", userHandle);
        header.put("business_handle", businessHandle);

        Map<String, Object> bodyMap = new HashMap<String, Object>();
        bodyMap.put("header", header);
        bodyMap.put("role", businessRole.getName());
        bodyMap.put("role_uuid", businessRole.getUuid());

        String path = Endpoints.UNLINK_BUSINESS_MEMBER.getUri();
        String sBody = Serialization.serialize(bodyMap);
        Map<String, String> headers = new HashMap<>();

        headers.put(AUTH_SIGNATURE, EcdsaUtil.sign(sBody, this.configuration.getPrivateKey()));
        headers.put(USER_SIGNATURE, EcdsaUtil.sign(sBody, userPrivateKey));
        headers.put(BUSINESS_SIGNATURE, EcdsaUtil.sign(sBody, businessPrivateKey));

        HttpResponse<?> response = this.configuration.getApiClient().callApi(path, headers, sBody);

        return ResponseUtil.prepareResponse(response, Message.ValueEnum.UNLINK_BUSINESS_MEMBER_MSG.getValue());
    }

    /**
     * @param userHandle
     * @param userPrivateKey
     * @return ApiResponse
     * @throws IOException
     * @throws InterruptedException
     * @throws BadRequestException
     * @throws InvalidSignatureException
     * @throws ServerSideException
     * @throws ForbiddenException
     */
    public ApiResponse getEntity(String userHandle, String userPrivateKey) throws IOException, InterruptedException,
            BadRequestException, InvalidSignatureException, ServerSideException, ForbiddenException {
        Map<String, String> header = new HashMap<String, String>();
        header.put("created", EpochUtils.getEpoch() + "");
        header.put("auth_handle", this.configuration.getAuthHandle());
        header.put("user_handle", userHandle);

        Map<String, Object> bodyMap = new HashMap<String, Object>();
        bodyMap.put("header", header);

        String path = Endpoints.GET_ENTITY.getUri();
        String sBody = Serialization.serialize(bodyMap);
        Map<String, String> headers = new HashMap<>();

        headers.put(AUTH_SIGNATURE, EcdsaUtil.sign(sBody, this.configuration.getPrivateKey()));
        headers.put(USER_SIGNATURE, EcdsaUtil.sign(sBody, userPrivateKey));

        HttpResponse<?> response = this.configuration.getApiClient().callApi(path, headers, sBody);

        return ResponseUtil.prepareResponse(response, Message.ValueEnum.GET_ENTITY_MSG.getValue());
    }

    /**
     * @param userHandle
     * @param userPrivateKey
     * @param businessHandle
     * @param businessPrivateKey
     * @param memberHandle
     * @param certificationToken
     * @return ApiResponse
     * @throws IOException
     * @throws InterruptedException
     * @throws BadRequestException
     * @throws InvalidSignatureException
     * @throws ServerSideException
     * @throws ForbiddenException
     */
    public ApiResponse certifyBeneficialOwner(String userHandle, String userPrivateKey, String businessHandle,
            String businessPrivateKey, String memberHandle, String certificationToken)
            throws IOException, InterruptedException, BadRequestException, InvalidSignatureException,
            ServerSideException, ForbiddenException {
        Map<String, String> header = new HashMap<String, String>();
        header.put("created", EpochUtils.getEpoch() + "");
        header.put("auth_handle", this.configuration.getAuthHandle());
        header.put("user_handle", userHandle);
        header.put("business_handle", businessHandle);

        Map<String, Object> bodyMap = new HashMap<String, Object>();
        bodyMap.put("header", header);
        bodyMap.put("member_handle", memberHandle);
        bodyMap.put("certification_token", certificationToken);

        String path = Endpoints.CERTIFY_BENEFICIAL_OWNER.getUri();
        String sBody = Serialization.serialize(bodyMap);
        Map<String, String> headers = new HashMap<>();

        headers.put(AUTH_SIGNATURE, EcdsaUtil.sign(sBody, this.configuration.getPrivateKey()));
        headers.put(USER_SIGNATURE, EcdsaUtil.sign(sBody, userPrivateKey));
        headers.put(BUSINESS_SIGNATURE, EcdsaUtil.sign(sBody, businessPrivateKey));

        HttpResponse<?> response = this.configuration.getApiClient().callApi(path, headers, sBody);

        return ResponseUtil.prepareResponse(response, Message.ValueEnum.CERTIFY_BENEFICIAL_OWNER.getValue());
    }

    /**
     * @param userHandle
     * @param userPrivateKey
     * @param businessHandle
     * @param businessPrivateKey
     * @return ApiResponse
     * @throws IOException
     * @throws InterruptedException
     * @throws BadRequestException
     * @throws InvalidSignatureException
     * @throws ServerSideException
     * @throws ForbiddenException
     */
    public ApiResponse certifyBusiness(String userHandle, String userPrivateKey, String businessHandle,
            String businessPrivateKey) throws IOException, InterruptedException, BadRequestException,
            InvalidSignatureException, ServerSideException, ForbiddenException {
        Map<String, String> header = new HashMap<String, String>();
        header.put("created", EpochUtils.getEpoch() + "");
        header.put("auth_handle", this.configuration.getAuthHandle());
        header.put("user_handle", userHandle);
        header.put("business_handle", businessHandle);

        Map<String, Object> bodyMap = new HashMap<String, Object>();
        bodyMap.put("header", header);

        String path = Endpoints.CERTIFY_BUSINESS.getUri();
        String sBody = Serialization.serialize(bodyMap);
        Map<String, String> headers = new HashMap<>();

        headers.put(AUTH_SIGNATURE, EcdsaUtil.sign(sBody, this.configuration.getPrivateKey()));
        headers.put(USER_SIGNATURE, EcdsaUtil.sign(sBody, userPrivateKey));
        headers.put(BUSINESS_SIGNATURE, EcdsaUtil.sign(sBody, businessPrivateKey));

        HttpResponse<?> response = this.configuration.getApiClient().callApi(path, headers, sBody);

        return ResponseUtil.prepareResponse(response, Message.ValueEnum.CERTIFY_BUSINESS.getValue());
    }

    public ApiResponse getEntities(String entityType, Integer page, Integer perPage)
            throws IOException, InterruptedException, BadRequestException, InvalidSignatureException,
            ServerSideException, ForbiddenException {
        Header header = new Header(null, this.configuration.getAuthHandle());

        Map<String, Object> bodyMap = new HashMap<String, Object>();
        bodyMap.put("header", header);
        bodyMap.put("message", Message.ValueEnum.HEADER_MSG.getValue());
        bodyMap.put("entity_type", entityType);

        String pageQueryParam = page != null ? "&page=" + page : "";
        String perPageQueryParam = perPage != null ? "&per_page=" + perPage : "";

        String path = Endpoints.GET_ENTITIES.getUri() + '?' + pageQueryParam + perPageQueryParam;
        String sBody = Serialization.serialize(bodyMap);
        Map<String, String> headers = new HashMap<>();

        headers.put(AUTH_SIGNATURE, EcdsaUtil.sign(sBody, this.configuration.getPrivateKey()));

        HttpResponse<?> response = this.configuration.getApiClient().callApi(path, headers, sBody);

        return ResponseUtil.prepareResponse(response, Message.ValueEnum.GET_ENTITIES.getValue());
    }

    /**
     * Upload supporting documentation for KYC
     * 
     * @param message
     * @param inputStream
     * @return
     * @throws IOException
     * @throws NoSuchAlgorithmException
     * @throws FileNotFoundException
     * @throws InterruptedException
     */
    public ApiResponse uploadDocument(UploadDocumentMessage message, InputStream inputStream)
            throws FileNotFoundException, NoSuchAlgorithmException, IOException, InterruptedException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        inputStream.transferTo(baos);
        InputStream firstInputStream = new ByteArrayInputStream(baos.toByteArray());
        InputStream secondInputStream = new ByteArrayInputStream(baos.toByteArray());
        String hash = EcdsaUtil.hashFile(firstInputStream);
        UploadDocumentMsg body = new UploadDocumentMsg(this.configuration.getAuthHandle(), hash, message);
        String path = Endpoints.DOCUMENTS.getUri();
        String sBody = Serialization.serialize(body);
        Map<String, String> headers = new HashMap<>();
        headers.put(AUTH_SIGNATURE, EcdsaUtil.sign(sBody, this.configuration.getPrivateKey()));
        headers.put(USER_SIGNATURE, EcdsaUtil.sign(sBody, message.getUserPrivateKey()));

        HttpResponse<?> response = this.configuration.getApiClient().callApi(path, headers, sBody,
                secondInputStream, message.getFilename(), message.getMimeType());

        return ResponseUtil.prepareResponse(DocumentsResponse.class, response);
    }

    /**
     * List previously uploaded supporting documentation for KYC
     * 
     * @param message
     * @return
     * @throws IOException
     * @throws InterruptedException
     */
    public ApiResponse listDocuments(ListDocumentsMessage message) throws IOException, InterruptedException {
        ListDocumentsMsg body = new ListDocumentsMsg(this.configuration.getAuthHandle(), message);
        String path = Endpoints.LIST_DOCUMENTS.getUri() + addQueryParameter("", "order", message.getSortBy());
        return listDocuments(path, message.getUserPrivateKey(), body);
    }

    /**
     * List previously uploaded supporting documentation for KYC with pagination
     * 
     * @param message
     * @param pagination
     * @return
     * @throws InterruptedException
     * @throws IOException
     */
    public ApiResponse listDocuments(ListDocumentsMessage message, PaginationMessage pagination)
            throws IOException, InterruptedException {
        ListDocumentsMsg body = new ListDocumentsMsg(this.configuration.getAuthHandle(), message);
        String path = Endpoints.LIST_DOCUMENTS.getUri()
                + addQueryParameter(pagination.getUrlParams(), "order", message.getOrder());
        return listDocuments(path, message.getUserPrivateKey(), body);
    }

    /**
     * 
     * @param message
     * @return
     * @throws IOException
     * @throws InterruptedException
     */
    public ApiResponse getDocument(GetDocumentMessage message) throws IOException, InterruptedException {
        GetDocumentMsg body = new GetDocumentMsg(this.configuration.getAuthHandle(), message);
        String path = Endpoints.GET_DOCUMENT.getUri();
        String sBody = Serialization.serialize(body);
        Map<String, String> headers = new HashMap<>();
        headers.put(AUTH_SIGNATURE, EcdsaUtil.sign(sBody, this.configuration.getPrivateKey()));
        headers.put(USER_SIGNATURE, EcdsaUtil.sign(sBody, message.getUserPrivateKey()));
        HttpResponse<?> response = this.configuration.getApiClient().callApi(path, headers, sBody);

        return ResponseUtil.prepareFileResponse(response);
    }

    /**
     * Add a new email to a registered entity.
     * 
     * @param user
     * @param message
     * @return
     * @throws IOException
     * @throws InterruptedException
     */
    public ApiResponse addEmail(UserHandleMessage user, EmailMessage message) throws IOException, InterruptedException {
        EmailMsg body = new EmailMsg(this.configuration.getAuthHandle(), user, message);
        return registrationData(Endpoints.ADD_REGISTRATION_DATA, RegistrationDataEnum.EMAIL, user.getUserPrivateKey(),
                body, EmailResponse.class);
    }

    /**
     * Add a new phone number to a registered entity.
     * 
     * @param user
     * @param message
     * @return
     * @throws IOException
     * @throws InterruptedException
     */
    public ApiResponse addPhone(UserHandleMessage user, PhoneMessage message) throws IOException, InterruptedException {
        PhoneMsg body = new PhoneMsg(this.configuration.getAuthHandle(), user, message);
        return registrationData(Endpoints.ADD_REGISTRATION_DATA, RegistrationDataEnum.PHONE, user.getUserPrivateKey(),
                body, PhoneResponse.class);
    }

    /**
     * Add a new identity to a registered entity.
     * 
     * @param user
     * @param message
     * @return
     * @throws IOException
     * @throws InterruptedException
     */
    public ApiResponse addIdentity(UserHandleMessage user, IdentityMessage message)
            throws IOException, InterruptedException {
        IdentityMsg body = new IdentityMsg(this.configuration.getAuthHandle(), user, message);
        return registrationData(Endpoints.ADD_REGISTRATION_DATA, RegistrationDataEnum.IDENTITY,
                user.getUserPrivateKey(), body, IdentityResponse.class);
    }

    /**
     * Add a new street address to a registered entity.
     * 
     * @param user
     * @param message
     * @return
     * @throws IOException
     * @throws InterruptedException
     */
    public ApiResponse addAddress(UserHandleMessage user, AddressMessage message)
            throws IOException, InterruptedException {
        AddressMsg body = new AddressMsg(this.configuration.getAuthHandle(), user, message);
        return registrationData(Endpoints.ADD_REGISTRATION_DATA, RegistrationDataEnum.ADDRESS, user.getUserPrivateKey(),
                body, AddressResponse.class);
    }

    /**
     * Update an existing email of a registered entity.
     * 
     * @param user
     * @param message
     * @return
     * @throws IOException
     * @throws InterruptedException
     */
    public ApiResponse updateEmail(UserHandleMessage user, EmailMessage message)
            throws IOException, InterruptedException {
        EmailMsg body = new EmailMsg(this.configuration.getAuthHandle(), user, message);
        return registrationData(Endpoints.UPDATE_REGISTRATION_DATA, RegistrationDataEnum.EMAIL,
                user.getUserPrivateKey(), body, EmailResponse.class);
    }

    /**
     * Update an existing phone number of a registered entity
     * 
     * @param user
     * @param message
     * @return
     * @throws InterruptedException
     * @throws IOException
     */
    public ApiResponse updatePhone(UserHandleMessage user, PhoneMessage message)
            throws IOException, InterruptedException {
        PhoneMsg body = new PhoneMsg(this.configuration.getAuthHandle(), user, message);
        return registrationData(Endpoints.UPDATE_REGISTRATION_DATA, RegistrationDataEnum.PHONE,
                user.getUserPrivateKey(), body, PhoneResponse.class);
    }

    /**
     * Update an existing identity of a registered entity.
     * 
     * @param user
     * @param message
     * @return
     * @throws InterruptedException
     * @throws IOException
     */
    public ApiResponse updateIdentity(UserHandleMessage user, IdentityMessage message)
            throws IOException, InterruptedException {
        IdentityMsg body = new IdentityMsg(this.configuration.getAuthHandle(), user, message);
        return registrationData(Endpoints.UPDATE_REGISTRATION_DATA, RegistrationDataEnum.IDENTITY,
                user.getUserPrivateKey(), body, IdentityResponse.class);
    }

    /**
     * Update an existing street address of a registered entity.
     * 
     * @param user
     * @param message
     * @return
     * @throws InterruptedException
     * @throws IOException
     */
    public ApiResponse updateAddress(UserHandleMessage user, AddressMessage message)
            throws IOException, InterruptedException {
        AddressMsg body = new AddressMsg(this.configuration.getAuthHandle(), user, message);
        return registrationData(Endpoints.UPDATE_REGISTRATION_DATA, RegistrationDataEnum.ADDRESS,
                user.getUserPrivateKey(), body, AddressResponse.class);
    }

    /**
     * Update an existing individual entity (name, birthdate, or business data).
     * 
     * @param user
     * @param message
     * @return
     * @throws InterruptedException
     * @throws IOException
     */
    public ApiResponse updateEntity(UserHandleMessage user, IndividualEntityMessage message)
            throws IOException, InterruptedException {
        IndividualEntityMsg body = new IndividualEntityMsg(this.configuration.getAuthHandle(), user, message);
        return registrationData(Endpoints.UPDATE_REGISTRATION_DATA, RegistrationDataEnum.ENTITY,
                user.getUserPrivateKey(), body, IndividualEntityResponse.class);
    }

    /**
     * Update an existing business entity (name, birthdate, or business data).
     * 
     * @param user
     * @param message
     * @return
     * @throws InterruptedException
     * @throws IOException
     */
    public ApiResponse updateEntity(UserHandleMessage user, BusinessEntityMessage message)
            throws IOException, InterruptedException {
        BusinessEntityMsg body = new BusinessEntityMsg(this.configuration.getAuthHandle(), user, message);
        return registrationData(Endpoints.UPDATE_REGISTRATION_DATA, RegistrationDataEnum.ENTITY,
                user.getUserPrivateKey(), body, BusinessEntityResponse.class);
    }

    private ApiResponse listDocuments(String path, String userPrivateKey, ListDocumentsMsg body)
            throws IOException, InterruptedException {
        String sBody = Serialization.serialize(body);
        Map<String, String> headers = new HashMap<>();
        headers.put(AUTH_SIGNATURE, EcdsaUtil.sign(sBody, this.configuration.getPrivateKey()));
        headers.put(USER_SIGNATURE, EcdsaUtil.sign(sBody, userPrivateKey));
        HttpResponse<?> response = this.configuration.getApiClient().callApi(path, headers, sBody);

        return ResponseUtil.prepareResponse(ListDocumentsResponse.class, response);
    }

    /**
     * Manages all add/<registration-data> calls
     * 
     * @param dataType
     * @param userPrivateKey
     * @param body
     * @param responseType
     * @return
     * @throws IOException
     * @throws InterruptedException
     */
    private ApiResponse registrationData(Endpoints endpoint, RegistrationDataEnum dataType, String userPrivateKey,
            Object body, Type responseType) throws IOException, InterruptedException {
        String path = endpoint.getUri() + "/" + dataType.getUri();
        String sBody = Serialization.serialize(body);
        Map<String, String> headers = new HashMap<>();
        headers.put(AUTH_SIGNATURE, EcdsaUtil.sign(sBody, this.configuration.getPrivateKey()));
        headers.put(USER_SIGNATURE, EcdsaUtil.sign(sBody, userPrivateKey));
        HttpResponse<?> response = this.configuration.getApiClient().callApi(path, headers, sBody);

        return ResponseUtil.prepareResponse(responseType, response);
    }

    /**
     * Adds a query parameter to the uri if the value is not null
     * 
     * @param queryParameters
     * @param name
     * @param value
     * @return
     */
    private String addQueryParameter(String queryParameters, String name, String value) {
        if (value != null && !value.isEmpty() && !value.isBlank()) {
            queryParameters += queryParameters.isEmpty() ? "?" : "&";
            queryParameters += name + "=" + value;
        }
        return queryParameters;
    }
}
