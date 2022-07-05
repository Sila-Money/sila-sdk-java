package com.silamoney.client.api;

import java.io.IOException;
import java.lang.reflect.Type;
import java.math.BigInteger;
import java.net.http.HttpResponse;
import java.security.InvalidAlgorithmParameterException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import com.silamoney.client.config.Configuration;
import com.silamoney.client.domain.*;
import com.silamoney.client.security.EcdsaUtil;
import com.silamoney.client.util.ResponseUtil;
import com.silamoney.client.util.Serialization;
import com.silamoney.client.domain.WebhookSearchFilters;
import org.web3j.crypto.CipherException;
import org.web3j.crypto.ECKeyPair;
import org.web3j.crypto.Keys;
import org.web3j.crypto.WalletFile;

import io.reactivex.annotations.Nullable;

/**
 *
 * @author Karlo Lorenzana
 */
public class SilaApi {

    private final Configuration configuration;
    private static final String HEADER_STRING = "header";
    private static final String SLASH = "/";
    private static final String AUTH_SIGNATURE = "authsignature";
    private static final String USER_SIGNATURE = "usersignature";
    private static final String BUSINESS_SIGNATURE = "businesssignature";
    private static final String DEFAULT_ENVIRONMENT = Environments.SilaEnvironment.SANDBOX.getUrl();
    private static final Character QUESTION = '?';
    private static final String EMPTY_STRING = "";
    private static final String PAGE = "&page=";
    private static final String PER_PAGE = "&per_page=";
    private static final String ORDER = "order";
    private static final String AND = "&";
    private static final String EQUAL = "=";
    /**
     * Constructor for SilaApi using custom environment.
     *
     * @param environment
     * @param appHandle
     * @param privateKey
     */
    public SilaApi(String environment, String appHandle, String privateKey) {
        this.configuration = new Configuration(environment, privateKey, appHandle);
    }

    /**
     * Constructor for SilaApi using specified environment.
     *
     * @param environment
     * @param appHandle
     * @param privateKey
     */
    public SilaApi(Environments.SilaEnvironment environment, String appHandle, String privateKey) {
        this.configuration = new Configuration(environment.getUrl(), privateKey, appHandle);
    }

    /**
     * Constructor for SilaApi using sandbox environment.
     *
     * @param appHandle
     * @param privateKey
     */
    public SilaApi(String appHandle, String privateKey) {
        this.configuration = new Configuration(DEFAULT_ENVIRONMENT, privateKey, appHandle);
    }

    /**
     * Checks if a specific handle is already taken.
     *
     * @param handle
     * @return API response.
     * @throws IOException
     * @throws InterruptedException
     *
     */
    public ApiResponse checkHandle(String handle) throws IOException, InterruptedException {
        HeaderMsg body = new HeaderMsg(handle, this.configuration.getAuthHandle());
        String path = Endpoints.CHECK_HANDLE.getUri();
        HttpResponse<?> response = getHttpResponse(path, body,null, this.configuration.getPrivateKey(), null);
        return ResponseUtil.prepareResponse(response, Message.ValueEnum.HEADER_MSG.getValue());
    }

    /**
     * Attaches KYC data and specified blockchain address to an assigned handle.
     *
     * @param user
     * @return API response.
     * @throws IOException
     * @throws InterruptedException
     *
     */
    public ApiResponse register(User user) throws IOException, InterruptedException {
        EntityMsg body = new EntityMsg(user, this.configuration.getAuthHandle());
        String path = Endpoints.REGISTER.getUri();
        HttpResponse<?> response = getHttpResponse(path, body,null, this.configuration.getPrivateKey(), null);
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
        HttpResponse<?> response = getHttpResponse(path, body,message.getUserPrivateKey(), this.configuration.getPrivateKey(), null);
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
     *
     */
    public ApiResponse requestKYC(String userHandle, String kycLevel, String userPrivateKey)
            throws IOException, InterruptedException {
        HeaderMsg body = new HeaderMsg(userHandle, kycLevel, this.configuration.getAuthHandle());
        String path = Endpoints.REQUEST_KYC.getUri();
        HttpResponse<?> response = getHttpResponse(path, body,userPrivateKey, this.configuration.getPrivateKey(), null);
        return ResponseUtil.prepareResponse(response, Message.ValueEnum.REQUEST_KYC.getValue());
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
     *
     */
    public ApiResponse checkKYC(String userHandle, String userPrivateKey) throws IOException, InterruptedException {
        return checkKYC(userHandle, userPrivateKey, null);
    }

    /**
     * Returns whether entity attached to user handle is verified, not valid, or
     * still pending.
     *
     * @param userHandle
     * @param userPrivateKey
     * @param keyLevel
     * @return
     * @throws IOException
     * @throws InterruptedException
     */
    public ApiResponse checkKYC(String userHandle, String userPrivateKey, String keyLevel) throws IOException, InterruptedException {
        HeaderMsg body = keyLevel != null ? new HeaderMsg(userHandle, keyLevel, this.configuration.getAuthHandle()) : new HeaderMsg(userHandle, this.configuration.getAuthHandle());
        String path = Endpoints.CHECK_KYC.getUri();
        HttpResponse<?> response = getHttpResponse(path, body,userPrivateKey, this.configuration.getPrivateKey(), null);
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
     *
     * @deprecated This method is deprecated. Please refer to the documentation for
     *             the new implementation.
     */
    @Deprecated(forRemoval = true)
    public ApiResponse linkAccount(String userHandle, String userPrivateKey, String accountName, String publicToken)
            throws IOException, InterruptedException {
        return linkAccount(userHandle, userPrivateKey, accountName, publicToken, null, null, null, null, null);
    }

    /**
     *
     * @param userHandle
     * @param userPrivateKey
     * @param accountName
     * @param plaidToken
     * @param accountId
     * @param plaidTokenType
     * @return
     * @throws IOException
     * @throws InterruptedException
     */
    public ApiResponse linkAccountPlaidToken(String userHandle, String userPrivateKey, String accountName,
            String plaidToken, String accountId, String plaidTokenType) throws IOException, InterruptedException {
        return linkAccount(userHandle, userPrivateKey, accountName, plaidToken, accountId, null, null, null,
                plaidTokenType);
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
     *
     * @deprecated This method is deprecated. Please refer to the documentation for
     *             the new implementation.
     */
    @Deprecated(forRemoval = true)
    public ApiResponse linkAccount(String userHandle, String userPrivateKey, String accountName, String publicToken,
            String accountId) throws IOException, InterruptedException {
        return linkAccount(userHandle, userPrivateKey, accountName, publicToken, accountId, null, null, null, null);
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
     *
     * @deprecated This method is deprecated. Please refer to the documentation for
     *             the new implementation.
     */
    @Deprecated(forRemoval = true)
    public ApiResponse linkAccount(String userHandle, String userPrivateKey, String accountName, String accountNumber,
            String routingNumber, String accountType) throws IOException, InterruptedException {
        return linkAccount(userHandle, userPrivateKey, accountName, null, null, accountNumber, routingNumber,
                accountType, null);
    }

    private ApiResponse linkAccount(String userHandle, String userPrivateKey, String accountName, String publicToken,
            String accountId, String accountNumber, String routingNumber, String accountType, String plaidTokenType)
            throws IOException, InterruptedException {
        LinkAccountMsg body = new LinkAccountMsg(userHandle, accountName, publicToken, accountId, accountNumber,
                routingNumber, accountType, this.configuration.getAuthHandle());
        body.setPlaidTokenType(plaidTokenType);
        String path = Endpoints.LINK_ACCOUNT.getUri();
        HttpResponse<?> response = getHttpResponse(path, body,userPrivateKey, this.configuration.getPrivateKey(), null);
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
     *
     */
    public ApiResponse getAccounts(String userHandle, String userPrivateKey) throws IOException, InterruptedException {
        GetAccountsMsg body = new GetAccountsMsg(userHandle, this.configuration.getAuthHandle());
        String path = Endpoints.GET_ACCOUNTS.getUri();
        HttpResponse<?> response = getHttpResponse(path, body,userPrivateKey, this.configuration.getPrivateKey(), null);
        return ResponseUtil.prepareResponse(response, Message.ValueEnum.GET_ACCOUNTS_MSG.getValue());
    }

    /**
     * @param userHandle
     * @param userPrivateKey
     * @param accountName
     * @return ApiResponse
     * @throws IOException
     * @throws InterruptedException
     */
    public ApiResponse getAccountBalance(String userHandle, String userPrivateKey, String accountName)
            throws IOException, InterruptedException {
        GetAccountBalanceMsg body = new GetAccountBalanceMsg(userHandle, this.configuration.getAuthHandle(),
                accountName);
        String path = Endpoints.GET_ACCOUNT_BALANCE.getUri();
        HttpResponse<?> response = getHttpResponse(path, body,userPrivateKey, this.configuration.getPrivateKey(), null);
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
     */
    public ApiResponse issueSila(AccountTransactionMessage message) throws IOException, InterruptedException {
        String path = Endpoints.ISSUE_SILA.getUri();
        AccountTransactionMsg body = new AccountTransactionMsg(this.configuration.getAuthHandle(), message,
                Message.ValueEnum.ISSUE_MSG);
        HttpResponse<?> response = getHttpResponse(path, body,message.getUserPrivateKey(), this.configuration.getPrivateKey(), null);
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
     */
    public ApiResponse transferSila(String userHandle, int amount, String destination, String destinationAddress,
                                    @Nullable String descriptor, @Nullable String businessUuid, String userPrivateKey) throws IOException, InterruptedException {
        return transferSila(userHandle,amount,destination,destinationAddress,descriptor,businessUuid,userPrivateKey,null,null,null);
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
     * @param transactionIdempotencyId
     * @return
     * @throws IOException
     * @throws InterruptedException
     */
    public ApiResponse transferSila(String userHandle, int amount, String destination, String destinationAddress,
                                    @Nullable String descriptor, @Nullable String businessUuid, String userPrivateKey,String transactionIdempotencyId) throws IOException, InterruptedException {
        return transferSila(userHandle, amount, destination, destinationAddress, descriptor, businessUuid, userPrivateKey, null, null,transactionIdempotencyId);
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
     * @param sourceId
     * @param destinationId
     * @return
     * @throws IOException
     * @throws InterruptedException
     */
    public ApiResponse transferSila(String userHandle, int amount, String destination, String destinationAddress,
                                        @Nullable String descriptor, @Nullable String businessUuid, String userPrivateKey,String sourceId,String destinationId)
            throws IOException, InterruptedException {
        return transferSila(userHandle, amount, destination, destinationAddress, descriptor, businessUuid, userPrivateKey, sourceId, destinationId,null);
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
     * @param sourceId
     * @param destinationId
     * @param transactionIdempotencyId
     * @return
     * @throws IOException
     * @throws InterruptedException
     */
    public ApiResponse transferSila(String userHandle, int amount, String destination, String destinationAddress,
                                    @Nullable String descriptor, @Nullable String businessUuid, String userPrivateKey, String sourceId, String destinationId,String transactionIdempotencyId)
            throws IOException, InterruptedException {
        TransferMsg body = new TransferMsg(userHandle, destination, amount, destinationAddress, descriptor,
                businessUuid, this.configuration.getAuthHandle(), sourceId, destinationId, transactionIdempotencyId);
        String path = Endpoints.TRANSFER_SILA.getUri();
        HttpResponse<?> response = getHttpResponse(path, body, userPrivateKey, this.configuration.getPrivateKey(), null);
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
     */
    public ApiResponse redeemSila(AccountTransactionMessage message) throws IOException, InterruptedException {
        AccountTransactionMsg body = new AccountTransactionMsg(this.configuration.getAuthHandle(), message,
                Message.ValueEnum.REDEEM_MSG);
        String path = Endpoints.REDEEM_SILA.getUri();
        HttpResponse<?> response = getHttpResponse(path, body, message.getUserPrivateKey(), this.configuration.getPrivateKey(), null);
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
        HttpResponse<?> response = getHttpResponse(path, body, message.getUserPrivateKey(), this.configuration.getPrivateKey(), null);
        return ResponseUtil.prepareResponse(BaseResponse.class, response);
    }

    /**
     * Gets array of user handle's transactions with detailed status information.
     *
     * @deprecated You don't need to provide the user private key anymore.
     * @param userHandle
     * @param filters
     * @param userPrivateKey
     * @return
     * @throws IOException
     * @throws InterruptedException
     */
    @Deprecated(forRemoval = true)
    public ApiResponse getTransactions(String userHandle, SearchFilters filters, String userPrivateKey)
            throws IOException, InterruptedException {
        return getTransactions(userHandle, filters);
    }
    /**
     * Gets array of transactions with detailed status information.
     *
     * @param filters
     * @return
     * @throws IOException
     * @throws InterruptedException
     *
     */
    public ApiResponse getTransactions(SearchFilters filters)
            throws IOException, InterruptedException {
        return getTransactions(null, filters);
    }
    /**
     * Gets array of user handle's transactions with detailed status information.
     *
     * @param userHandle
     * @param filters
     * @return
     * @throws IOException
     * @throws InterruptedException
     *
     */
    public ApiResponse getTransactions(String userHandle, SearchFilters filters)
            throws IOException, InterruptedException {
        GetTransactionsMsg body = new GetTransactionsMsg(userHandle, this.configuration.getAuthHandle(), filters);
        String path = Endpoints.GET_TRANSACTIONS.getUri();
        HttpResponse<?> response = getHttpResponse(path, body, null, this.configuration.getPrivateKey(), null);
        return ResponseUtil.prepareResponse(response, Message.ValueEnum.GET_TRANSACTIONS_MSG.getValue());
    }

    /**
     * Gets Sila balance for a given blockchain address.
     *
     * @param address
     * @return
     * @throws IOException
     * @throws InterruptedException
     */
    public ApiResponse silaBalance(String address) throws IOException, InterruptedException {
        SilaBalanceMsg body = new SilaBalanceMsg(address);
        String path = Endpoints.GET_SILA_BALANCE.getUri();
        HttpResponse<?> response = getHttpResponse(path, body, null,null, null);
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
     */
    public ApiResponse plaidSameDayAuth(String userHandle, @Nullable String accountName, String userPrivateKey)
            throws IOException, InterruptedException {
        PlaidSameDayAuthMsg body = new PlaidSameDayAuthMsg(userHandle, accountName, this.configuration.getAuthHandle());
        String path = Endpoints.PLAID_SAMEDAY_AUTH.getUri();
        HttpResponse<?> response = getHttpResponse(path, body, userPrivateKey, this.configuration.getPrivateKey(), null);
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
     */
    public ApiResponse getWallet(String userHandle, String userPrivateKey) throws IOException, InterruptedException {
        GetWalletMsg body = new GetWalletMsg(userHandle, this.configuration.getAuthHandle());
        String path = Endpoints.GET_WALLET.getUri();
        HttpResponse<?> response = getHttpResponse(path, body, userPrivateKey, this.configuration.getPrivateKey(), null);
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
     */
    public ApiResponse registerWallet(String userHandle, Wallet wallet, String walletVerificationSignature,
                                      String userPrivateKey) throws IOException, InterruptedException {
        return registerWalletData(userHandle, wallet, walletVerificationSignature, userPrivateKey);
    }
    /**
     * Add another input default
     *
     * * @param userHandle
     * @param wallet
     * @param walletVerificationSignature
     * @param userPrivateKey
     * @return
     * @throws IOException
     * @throws InterruptedException
     */
    public ApiResponse registerWallet(String userHandle, Wallet wallet, String walletVerificationSignature,
                                      String userPrivateKey, Boolean defaultValue) throws IOException, InterruptedException {
        wallet.defaultWallet = defaultValue;
        return registerWalletData(userHandle, wallet, walletVerificationSignature, userPrivateKey);
    }

    private ApiResponse registerWalletData(String userHandle, Wallet wallet, String walletVerificationSignature, String userPrivateKey) throws IOException, InterruptedException {
        RegisterWalletMsg body = new RegisterWalletMsg(userHandle, wallet, walletVerificationSignature,
                this.configuration.getAuthHandle());
        String path = Endpoints.REGISTER_WALLET.getUri();
        HttpResponse<?> response = getHttpResponse(path, body, userPrivateKey, this.configuration.getPrivateKey(), null);
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
     *
     */
    public ApiResponse updateWallet(String userHandle, String nickname, boolean status, String userPrivateKey)
            throws IOException, InterruptedException {
        UpdateWalletMsg body = new UpdateWalletMsg(userHandle, nickname, status, this.configuration.getAuthHandle());
        String path = Endpoints.UPDATE_WALLET.getUri();
        HttpResponse<?> response = getHttpResponse(path, body, userPrivateKey, this.configuration.getPrivateKey(), null);
        return ResponseUtil.prepareResponse(response, Message.ValueEnum.UPDATE_WALLET_MSG.getValue());
    }

    /**
     *
     * @param userHandle
     * @param userPrivateKey
     * @return
     * @throws IOException
     * @throws InterruptedException
     */
    public ApiResponse deleteWallet(String userHandle, String userPrivateKey) throws IOException, InterruptedException {
        DeleteWalletMsg body = new DeleteWalletMsg(userHandle, this.configuration.getAuthHandle());
        String path = Endpoints.DELETE_WALLET.getUri();
        HttpResponse<?> response = getHttpResponse(path, body, userPrivateKey, this.configuration.getPrivateKey(), null);
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
     */
    public ApiResponse getWallets(String userHandle, SearchFilters filters, String userPrivateKey)
            throws IOException, InterruptedException {
        GetWalletsMsg body = new GetWalletsMsg(userHandle, filters, this.configuration.getAuthHandle());
        String path = Endpoints.GET_WALLETS.getUri();
        HttpResponse<?> response = getHttpResponse(path, body, userPrivateKey, this.configuration.getPrivateKey(), null);
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
        return getDocumentTypes(EMPTY_STRING);
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
        StringBuilder path = new StringBuilder().append(Endpoints.GET_DOCUMENT_TYPES.getUri()).append(urlParams);
        Header header=new Header(null,this.configuration.getAuthHandle());
        Map<String, Header> bodyMap = new HashMap<>();
        bodyMap.put(HEADER_STRING, header);
        HttpResponse<?> response = getHttpResponse(path.toString(), bodyMap, null, this.configuration.getPrivateKey(), null);
        return ResponseUtil.prepareResponse(DocumentTypesResponse.class, response);
    }

    /**
     * Gets a list of valid business types that can be registered.
     *
     * @return ApiResponse
     * @throws IOException
     * @throws InterruptedException
     */
    public ApiResponse getBusinessTypes() throws IOException, InterruptedException {
        Header header = new Header(null, this.configuration.getAuthHandle());
        Map<String, Object> bodyMap = new HashMap<>();
        bodyMap.put(HEADER_STRING, header);

        String path = Endpoints.GET_BUSINESS_TYPES.getUri();
        HttpResponse<?> response = getHttpResponse(path, bodyMap, null, this.configuration.getPrivateKey(), null);
        return ResponseUtil.prepareResponse(response, Message.ValueEnum.GET_BUSINESS_TYPES.getValue());
    }

    /**
     * Retrieves the list of pre-defined business roles.
     *
     * @return ApiResponse
     * @throws IOException
     * @throws InterruptedException
     */
    public ApiResponse getBusinessRoles() throws IOException, InterruptedException {
        Header header = new Header(null, this.configuration.getAuthHandle());
        Map<String, Object> bodyMap = new HashMap<>();
        bodyMap.put(HEADER_STRING, header);

        String path = Endpoints.GET_BUSINESS_ROLES.getUri();
        HttpResponse<?> response = getHttpResponse(path, bodyMap, null, this.configuration.getPrivateKey(), null);
        return ResponseUtil.prepareResponse(response, Message.ValueEnum.GET_BUSINESS_ROLES.getValue());
    }

    /**
     * @return ApiResponse
     * @throws IOException
     * @throws InterruptedException
     */
    public ApiResponse getNaicsCategories() throws IOException, InterruptedException {
        Header header = new Header(null, this.configuration.getAuthHandle());
        Map<String, Object> bodyMap = new HashMap<>();
        bodyMap.put(HEADER_STRING, header);

        String path = Endpoints.GET_NAICS_CATEGORIES.getUri();
        HttpResponse<?> response = getHttpResponse(path, bodyMap, null, this.configuration.getPrivateKey(), null);
        return ResponseUtil.prepareResponse(response, Message.ValueEnum.GET_NAICS_CATEGORIES_MSG.getValue());
    }

    /**
     * @param user
     * @return ApiResponse
     * @throws IOException
     * @throws InterruptedException
     */
    public ApiResponse registerBusiness(BusinessUser user) throws IOException, InterruptedException {
        EntityMsg body = new EntityMsg(user, this.configuration.getAuthHandle());
        String path = Endpoints.REGISTER.getUri();
        HttpResponse<?> response = getHttpResponse(path, body, null, this.configuration.getPrivateKey(), null);
        return ResponseUtil.prepareResponse(response, Message.ValueEnum.REGISTER_BUSINESS.getValue());
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
     */
    public ApiResponse linkBusinessMember(String userHandle, String userPrivateKey, String businessHandle,
            String businessPrivateKey, BusinessRole businessRole, String memberHandle, String details,
            Float ownershipStake) throws IOException, InterruptedException {
        BusinessMemberMsg body = new BusinessMemberMsg(userHandle, this.configuration.getAuthHandle(), businessHandle, businessRole.getName(), businessRole.getUuid(), memberHandle, ownershipStake, details);

        String path = Endpoints.LINK_BUSINESS_MEMBER.getUri();
        HttpResponse<?> response = getHttpResponse(path, body, userPrivateKey, this.configuration.getPrivateKey(), businessPrivateKey);
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
     */
    public ApiResponse unlinkBusinessMember(String userHandle, String userPrivateKey, String businessHandle,
            String businessPrivateKey, BusinessRole businessRole) throws IOException, InterruptedException {
        BusinessMemberMsg body = new BusinessMemberMsg(userHandle, this.configuration.getAuthHandle(), businessHandle, businessRole.getName(), businessRole.getUuid());

        String path = Endpoints.UNLINK_BUSINESS_MEMBER.getUri();
        HttpResponse<?> response = getHttpResponse(path, body, userPrivateKey, this.configuration.getPrivateKey(), businessPrivateKey);
        return ResponseUtil.prepareResponse(response, Message.ValueEnum.UNLINK_BUSINESS_MEMBER_MSG.getValue());
    }

    /**
     * @param userHandle
     * @param userPrivateKey
     * @return ApiResponse
     * @throws IOException
     * @throws InterruptedException
     */
    public ApiResponse getEntity(String userHandle, String userPrivateKey) throws IOException, InterruptedException {
        Header header = new Header(userHandle, this.configuration.getAuthHandle());
        Map<String, Object> bodyMap = new HashMap<>();
        bodyMap.put(HEADER_STRING, header);

        String path = Endpoints.GET_ENTITY.getUri();
        HttpResponse<?> response = getHttpResponse(path, bodyMap, userPrivateKey, this.configuration.getPrivateKey(), null);
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
     */
    public ApiResponse certifyBeneficialOwner(String userHandle, String userPrivateKey, String businessHandle,
            String businessPrivateKey, String memberHandle, String certificationToken)
            throws IOException, InterruptedException {
        CertifyBeneficialOwnerMsg body = new CertifyBeneficialOwnerMsg(userHandle, this.configuration.getAuthHandle(), businessHandle, memberHandle, certificationToken);

        String path = Endpoints.CERTIFY_BENEFICIAL_OWNER.getUri();
        HttpResponse<?> response = getHttpResponse(path, body, userPrivateKey, this.configuration.getPrivateKey(), businessPrivateKey);
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
     */
    public ApiResponse certifyBusiness(String userHandle, String userPrivateKey, String businessHandle,
            String businessPrivateKey) throws IOException, InterruptedException {
        Header header=new Header(userHandle,this.configuration.getAuthHandle(),businessHandle);

        Map<String, Object> bodyMap = new HashMap<>();
        bodyMap.put(HEADER_STRING, header);

        String path = Endpoints.CERTIFY_BUSINESS.getUri();
        HttpResponse<?> response = getHttpResponse(path, bodyMap, userPrivateKey, this.configuration.getPrivateKey(), businessPrivateKey);
        return ResponseUtil.prepareResponse(response, Message.ValueEnum.CERTIFY_BUSINESS.getValue());
    }

    public ApiResponse getEntities(String entityType, Integer page, Integer perPage)
            throws IOException, InterruptedException {
        GetEntitiesMsg body = new GetEntitiesMsg(this.configuration.getAuthHandle(), entityType);

        String pageQueryParam = page != null ? PAGE + page : EMPTY_STRING;
        String perPageQueryParam = perPage != null ? PER_PAGE + perPage : EMPTY_STRING;
        StringBuilder path = new StringBuilder().append(Endpoints.GET_ENTITIES.getUri()).append(QUESTION).append(pageQueryParam).append(perPageQueryParam);
        HttpResponse<?> response = getHttpResponse(path.toString(), body, null, this.configuration.getPrivateKey(), null);
        return ResponseUtil.prepareResponse(response, Message.ValueEnum.GET_ENTITIES.getValue());
    }

    /**
     * Upload supporting documentation for KYC
     *
     * @param message
     * @return
     * @throws IOException
     * @throws NoSuchAlgorithmException
     * @throws InterruptedException
     */
    public ApiResponse uploadDocument(UploadDocumentMessage message)
            throws NoSuchAlgorithmException, IOException, InterruptedException {
        String hash = EcdsaUtil.hashFile(message.getFilePath());
        UploadDocumentMsg body = new UploadDocumentMsg(this.configuration.getAuthHandle(), hash, message);
        String path = Endpoints.DOCUMENTS.getUri();
        String sBody = Serialization.serialize(body);
        Map<String, String> headers = new HashMap<>();
        setSignature(message.getUserPrivateKey(), this.configuration.getPrivateKey(), sBody, headers);
        HttpResponse<?> response = this.configuration.getApiClient().callApi(path, headers, sBody,
                message.getFilePath(), message.getMimeType());

        return ResponseUtil.prepareResponse(DocumentsResponse.class, response);
    }
    /**
     * Upload supporting documentation for KYC
     *
     * @param uploadDocumentsMessage
     * @return
     * @throws IOException
     * @throws NoSuchAlgorithmException
     * @throws InterruptedException
     */
    public ApiResponse uploadDocuments(UploadDocumentsMessage uploadDocumentsMessage)
            throws NoSuchAlgorithmException, IOException, InterruptedException {
        HashMap<String, UploadDocumentMsg> message = new HashMap<>();
        if(uploadDocumentsMessage.getUploadDocumentList()!=null&&uploadDocumentsMessage.getUploadDocumentList().size()>0){
            for (int value = 0; value < uploadDocumentsMessage.getUploadDocumentList().size(); value++) {
                String key = "file_" + (value + 1);
                UploadDocument uploadDocument = uploadDocumentsMessage.getUploadDocumentList().get(value);
                UploadDocumentMsg msg = new UploadDocumentMsg(uploadDocument);
                message.put(key, msg);
            }
        }
        UploadDocumentListMsg body = new UploadDocumentListMsg(this.configuration.getAuthHandle(), uploadDocumentsMessage.getUserHandle(), message);
        String path = Endpoints.DOCUMENTS.getUri();
        String sBody = Serialization.serialize(body);
        Map<String, String> headers = new HashMap<>();
        setSignature(uploadDocumentsMessage.getUserPrivateKey(), this.configuration.getPrivateKey(), sBody, headers);
        HttpResponse<?> response = this.configuration.getApiClient().callApi(path, headers, sBody, uploadDocumentsMessage.getUploadDocumentList());

        return ResponseUtil.prepareResponse(UploadDocumentsResponse.class, response);
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
        StringBuilder path = new StringBuilder().append(Endpoints.LIST_DOCUMENTS.getUri()).append(addQueryParameter(EMPTY_STRING, ORDER, message.getSortBy()));
        return listDocuments(path.toString(), message.getUserPrivateKey(), body);
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
        StringBuilder path = new StringBuilder().append(Endpoints.LIST_DOCUMENTS.getUri()).append(addQueryParameter(pagination.getUrlParams(), ORDER, message.getOrder()));
        return listDocuments(path.toString(), message.getUserPrivateKey(), body);
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
        HttpResponse<?> response = getHttpResponse(path, body, message.getUserPrivateKey(), this.configuration.getPrivateKey(), null);
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
     * Add a new device to a registered entity.
     *
     * @param user
     * @param device
     * @return
     * @throws IOException
     * @throws InterruptedException
     */
    public ApiResponse addDevice(UserHandleMessage user, Device device) throws IOException, InterruptedException {
        DeviceMsg body = new DeviceMsg(this.configuration.getAuthHandle(), user, device);
        return registrationData(Endpoints.ADD_REGISTRATION_DATA, RegistrationDataEnum.DEVICE, user.getUserPrivateKey(),
                body, DeviceResponse.class);
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

    /**
     * @param userHandle
     * @return
     * @throws IOException
     * @throws InterruptedException
     */
    public ApiResponse plaidLinkToken(String userHandle) throws IOException, InterruptedException {
        return plaidLinkToken(userHandle, null);
    }

    /**
     * @param userHandle
     * @param androidPackageName
     * @return
     * @throws IOException
     * @throws InterruptedException
     */
    public ApiResponse plaidLinkToken(String userHandle, String androidPackageName) throws IOException, InterruptedException {
        String path = Endpoints.PLAID_LINK_TOKEN.getUri();
        PlaidLinkTokenMsg body = new PlaidLinkTokenMsg(userHandle, this.configuration.getAuthHandle(), androidPackageName);
        HttpResponse<?> response = getHttpResponse(path, body, null, this.configuration.getPrivateKey(), null);
        return ResponseUtil.prepareResponse(response, Message.ValueEnum.PLAID_LINK_TOKEN.getValue());
    }

    /**
     *
     * @param userHandle
     * @param accountName
     * @param userPrivateKey
     * @return
     * @throws IOException
     * @throws InterruptedException
     */
    public ApiResponse deleteAccount(String userHandle, String accountName, String userPrivateKey)
            throws IOException, InterruptedException {
        String path = Endpoints.DELETE_ACCOUNT.getUri();
        AccountRequestMsg body = new AccountRequestMsg(userHandle, this.configuration.getAuthHandle(), accountName);
        HttpResponse<?> response = getHttpResponse(path, body, userPrivateKey, this.configuration.getPrivateKey(), null);
        return ResponseUtil.prepareResponse(response, Message.ValueEnum.DELETE_ACCOUNT.getValue());
    }

    /**
     *
     * @param queryAppHandle
     * @param queryUserHandle
     * @return
     * @throws IOException
     * @throws InterruptedException
     */
    public ApiResponse checkPartnerKyc(String queryAppHandle, String queryUserHandle)
            throws IOException, InterruptedException {
        String path = Endpoints.CHECK_PARTNER_KYC.getUri();
        CheckPartnerKycMsg body = new CheckPartnerKycMsg(this.configuration.getAuthHandle(), queryAppHandle, queryUserHandle);
        HttpResponse<?> response = getHttpResponse(path, body, null, this.configuration.getPrivateKey(), null);
        return ResponseUtil.prepareResponse(response, Message.ValueEnum.CHECK_PARTNER_KYC.getValue());
    }

    /**
     *
     * @param userHandle
     * @param userPrivateKey
     * @param accountName
     * @param newAccountName
     * @return
     * @throws IOException
     * @throws InterruptedException
     */
    public ApiResponse updateAccount(String userHandle, String userPrivateKey, String accountName,
                                     String newAccountName) throws IOException, InterruptedException {
        return updateAccountData(userHandle, userPrivateKey, accountName, newAccountName, false, null);
    }

    /**
     * @param userHandle
     * @param userPrivateKey
     * @param accountName
     * @param newAccountName
     * @param active
     * @return
     * @throws IOException
     * @throws InterruptedException
     */
    public ApiResponse updateAccount(String userHandle, String userPrivateKey, String accountName,
                                     String newAccountName, Boolean active) throws IOException, InterruptedException {
        return updateAccountData(userHandle, userPrivateKey, accountName, newAccountName, active, "active");
    }

    /**
     * @param userHandle
     * @param userPrivateKey
     * @param accountName
     * @param active
     * @return
     * @throws IOException
     * @throws InterruptedException
     */
    public ApiResponse updateAccount(String userHandle, String userPrivateKey, String accountName
            , Boolean active) throws IOException, InterruptedException {
        return updateAccountData(userHandle, userPrivateKey, accountName, null, active, "active");
    }

    public ApiResponse updateAccountData(String userHandle, String userPrivateKey, String accountName,
                                         String newAccountName, boolean active, String activeType) throws IOException, InterruptedException {
        String path = Endpoints.UPDATE_ACCOUNT.getUri();
        UpdateAccountMsg body = new UpdateAccountMsg(userHandle, this.configuration.getAuthHandle(), accountName, newAccountName, active, activeType);
        HttpResponse<?> response = getHttpResponse(path, body, userPrivateKey, this.configuration.getPrivateKey(), null);
        return ResponseUtil.prepareResponse(response, Message.ValueEnum.UPDATE_ACCOUNT.getValue());
    }

    /**
     *
     * @param userHandle
     * @param accountName
     * @return
     * @throws IOException
     * @throws InterruptedException
     */
    public ApiResponse plaidUpdateLinkToken(String userHandle, String accountName)
            throws IOException, InterruptedException {
        String path = Endpoints.PLAID_UPDATE_LINK_TOKEN.getUri();
        AccountRequestMsg body = new AccountRequestMsg(userHandle, this.configuration.getAuthHandle(), accountName);
        HttpResponse<?> response = getHttpResponse(path, body, null, this.configuration.getPrivateKey(), null);
        return ResponseUtil.prepareResponse(response, Message.ValueEnum.PLAID_UPDATE_LINK_TOKEN.getValue());
    }

    /**
     *
     * @param accountName
     * @param userHandle
     * @param userPrivateKey
     * @return
     * @throws IOException
     * @throws InterruptedException
     */
    public ApiResponse checkInstantAch(String accountName, String userHandle, String userPrivateKey)
            throws IOException, InterruptedException {
        return checkInstantAch(accountName,userHandle,userPrivateKey,null);
    }
    /**
     * @param accountName
     * @param userHandle
     * @param userPrivateKey
     * @param kycLevel
     * @return
     * @throws IOException
     * @throws InterruptedException
     */
    public ApiResponse checkInstantAch(String accountName, String userHandle, String userPrivateKey, String kycLevel)
            throws IOException, InterruptedException {
        String path = Endpoints.CHECK_INSTANT_ACH.getUri();
        CheckInstantAchMsg body = new CheckInstantAchMsg(userHandle, this.configuration.getAuthHandle(), accountName, kycLevel);
        HttpResponse<?> response = getHttpResponse(path, body, userPrivateKey, this.configuration.getPrivateKey(), null);
        return ResponseUtil.prepareResponse(response, Message.ValueEnum.CHECK_INSTANT_ACH.getValue());
    }


    public ApiResponse getInstitutions() throws IOException, InterruptedException {
        return getInstitutions(null);
    }


    /**
     * @param searchFilters
     */
    public ApiResponse getInstitutions(InstitutionSearchFilters searchFilters)
            throws IOException, InterruptedException {
        String path = Endpoints.GET_INSTITUTIONS.getUri();
        GetInstitutionsMsg body = new GetInstitutionsMsg(this.configuration.getAuthHandle(), searchFilters);
        HttpResponse<?> response = getHttpResponse(path, body, null, this.configuration.getPrivateKey(), null);
        return ResponseUtil.prepareResponse(GetInstitutionsResponse.class, response);
    }

    private ApiResponse listDocuments(String path, String userPrivateKey, ListDocumentsMsg body)
            throws IOException, InterruptedException {
        HttpResponse<?> response = getHttpResponse(path, body, userPrivateKey, this.configuration.getPrivateKey(), null);
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
        StringBuilder path = new StringBuilder().append(endpoint.getUri()).append(SLASH).append(dataType.getUri());
        HttpResponse<?> response = getHttpResponse(path.toString(), body, userPrivateKey, this.configuration.getPrivateKey(), null);
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
            queryParameters += queryParameters.isEmpty() ? QUESTION : AND;
            queryParameters += name + EQUAL + value;
        }
        return queryParameters;
    }

    /**
     * @param userHandle
     * @param userPrivateKey
     * @param cardName
     * @param token
     * @param accountPostalCode
     * @return {@link ApiResponse}
     * @throws IOException
     * @throws InterruptedException
     */
    public ApiResponse linkCard(String userHandle, String userPrivateKey, String token, String cardName,
                                String accountPostalCode)
            throws IOException, InterruptedException {
        String path = Endpoints.LINK_CARD.getUri();
        LinkCardMsg body = new LinkCardMsg(userHandle, token, cardName, accountPostalCode, this.configuration.getAuthHandle());
        HttpResponse<?> response = getHttpResponse(path, body, userPrivateKey, this.configuration.getPrivateKey(), null);
        return ResponseUtil.prepareResponse(response, Message.ValueEnum.LINK_CARD_MSG.getValue());
    }
    /**
     * Gets card names linked to user handle.
     *
     * @param userHandle
     * @param userPrivateKey
     * @return
     * @throws IOException
     * @throws InterruptedException
     */
    public ApiResponse getCards(String userHandle, String userPrivateKey) throws IOException, InterruptedException {
        String path = Endpoints.GET_CARDS.getUri();
        Map<String, Object> body = new HashMap<>();
        Header header = new Header(userHandle, this.configuration.getAuthHandle());
        body.put(HEADER_STRING, header);
        HttpResponse<?> response = getHttpResponse(path, body, userPrivateKey, this.configuration.getPrivateKey(), null);
        return ResponseUtil.prepareResponse(response, Message.ValueEnum.GET_CARD_MSG.getValue());
    }

    /**
     * @param userHandle
     * @param cardName
     * @param userPrivateKey
     * @return
     * @throws IOException
     * @throws InterruptedException
     */
    public ApiResponse deleteCard(String userHandle, String cardName, String userPrivateKey)
            throws IOException, InterruptedException {
        String path = Endpoints.DELETE_CARD.getUri();
        DeleteCardMsg body = new DeleteCardMsg(userHandle, this.configuration.getAuthHandle(), cardName);
        HttpResponse<?> response = getHttpResponse(path, body, userPrivateKey, this.configuration.getPrivateKey(), null);
        return ResponseUtil.prepareResponse(response, Message.ValueEnum.DELETE_CARD.getValue());
    }
    /**
     * @param userHandle
     */
    public ApiResponse getWebhooks(String userHandle) throws IOException, InterruptedException {
        return getWebhooks(userHandle,null);
    }

    /**
     * @param userHandle
     * @param searchFilters
     */
    public ApiResponse getWebhooks(String userHandle,WebhookSearchFilters searchFilters)
            throws IOException, InterruptedException {
        String path = Endpoints.GET_WEBHOOKS.getUri();
        GetWebhooksMsg body = new GetWebhooksMsg(userHandle, this.configuration.getAuthHandle(), searchFilters);
        HttpResponse<?> response = getHttpResponse(path, body, null, this.configuration.getPrivateKey(), null);
        return ResponseUtil.prepareResponse(response,Message.ValueEnum.GET_WEBHOOKS.getValue());
    }
    /**
     * @param userHandle
     * @param userPrivateKey
     * @param transactionId
     * @return
     * @throws IOException
     * @throws InterruptedException
     */
    public ApiResponse reverseTransaction(String userHandle ,String userPrivateKey, String transactionId)
            throws IOException, InterruptedException {
        String path = Endpoints.REVERSE_TRANSACTION.getUri();
        ReverseTransactionMsg body = new ReverseTransactionMsg(userHandle, this.configuration.getAuthHandle(), transactionId);
        HttpResponse<?> response = getHttpResponse(path, body, userPrivateKey, this.configuration.getPrivateKey(), null);
        return ResponseUtil.prepareResponse(response,Message.ValueEnum.REVERSE_TRANSACTION_MSG.getValue());
    }

    /**
     * Gets a paginated list of "payment methods".
     *
     * @param userHandle
     * @param userPrivateKey
     * @return
     * @throws IOException
     * @throws InterruptedException
     */
    public ApiResponse getPaymentMethods(String userHandle, String userPrivateKey)
            throws IOException, InterruptedException {
        return getPaymentMethods(userHandle, userPrivateKey, null);
    }
    /**
     * Gets a paginated list of "payment methods".
     *
     * @param userHandle
     * @param userPrivateKey
     * @param searchFilters
     * @return
     * @throws IOException
     * @throws InterruptedException
     */
    public ApiResponse getPaymentMethods(String userHandle, String userPrivateKey, PaymentMethodsSearchFilters searchFilters)
            throws IOException, InterruptedException {
        String path = Endpoints.GET_PAYMENT_METHODS.getUri();
        GetPaymentMethodsMsg body = new GetPaymentMethodsMsg(userHandle, this.configuration.getAuthHandle(), searchFilters);
        HttpResponse<?> response = getHttpResponse(path, body, userPrivateKey, this.configuration.getPrivateKey(), null);
        return ResponseUtil.prepareResponse(response, Message.ValueEnum.GET_PAYMENT_METHODS.getValue());
    }

    /**
     * @param userHandle
     * @param userPrivateKey
     * @param virtualAccountName
     * @return {@link ApiResponse}
     * @throws IOException
     * @throws InterruptedException
     */
    public ApiResponse openVirtualAccount(String userHandle, String userPrivateKey, String virtualAccountName)
            throws IOException, InterruptedException {
        return openVirtualAccount(userHandle, userPrivateKey, virtualAccountName, null, null);
    }

    /**
     * @param userHandle
     * @param userPrivateKey
     * @return {@link ApiResponse}
     * @throws IOException
     * @throws InterruptedException
     */
    public ApiResponse openVirtualAccount(String userHandle, String userPrivateKey)
            throws IOException, InterruptedException {
        return openVirtualAccount(userHandle, userPrivateKey, null, null, null);
    }

    /**
     * @param userHandle
     * @param userPrivateKey
     * @param achCreditEnabled
     * @param achDebitEnabled
     * @return {@link ApiResponse}
     * @throws IOException
     * @throws InterruptedException
     */
    public ApiResponse openVirtualAccount(String userHandle, String userPrivateKey, Boolean achCreditEnabled, Boolean achDebitEnabled)
            throws IOException, InterruptedException {
        return openVirtualAccount(userHandle, userPrivateKey, null, achCreditEnabled, achDebitEnabled);
    }

    /**
     * @param userHandle
     * @param userPrivateKey
     * @param virtualAccountName
     * @param achCreditEnabled
     * @param achDebitEnabled
     * @return {@link ApiResponse}
     * @throws IOException
     * @throws InterruptedException
     */
    public ApiResponse openVirtualAccount(String userHandle, String userPrivateKey, String virtualAccountName, Boolean achCreditEnabled, Boolean achDebitEnabled)
            throws IOException, InterruptedException {
        String path = Endpoints.OPEN_VIRTUAL_ACCOUNT.getUri();
        OpenVirtualAccountMsg body = new OpenVirtualAccountMsg(userHandle, this.configuration.getAuthHandle(), virtualAccountName, achCreditEnabled, achDebitEnabled);
        HttpResponse<?> response = getHttpResponse(path, body, userPrivateKey, this.configuration.getPrivateKey(), null);
        return ResponseUtil.prepareResponse(response, Message.ValueEnum.OPEN_VIRTUAL_ACCOUNT.getValue());
    }

    /**
     * @param userHandle
     * @param userPrivateKey
     * @return
     * @throws IOException
     * @throws InterruptedException
     */
    public ApiResponse getVirtualAccounts(String userHandle, String userPrivateKey)
            throws IOException, InterruptedException {
        String path = Endpoints.GET_VIRTUAL_ACCOUNTS.getUri();
        Header header = new Header(userHandle, this.configuration.getAuthHandle());
        Map<String, Object> body = new HashMap<>();
        body.put(HEADER_STRING, header);
        HttpResponse<?> response = getHttpResponse(path, body, userPrivateKey, this.configuration.getPrivateKey(), null);
        return ResponseUtil.prepareResponse(response, Message.ValueEnum.GET_VIRTUAL_ACCOUNTS.getValue());
    }

    /**
     * @param userHandle
     * @param userPrivateKey
     * @param virtualAccountId
     * @return
     * @throws IOException
     * @throws InterruptedException
     */
    public ApiResponse getVirtualAccount(String userHandle, String userPrivateKey, String virtualAccountId)
            throws IOException, InterruptedException {
        String path = Endpoints.GET_VIRTUAL_ACCOUNT.getUri();
        GetVirtualAccountMsg body = new GetVirtualAccountMsg(userHandle, this.configuration.getAuthHandle(), virtualAccountId);
        HttpResponse<?> response = getHttpResponse(path, body, userPrivateKey, this.configuration.getPrivateKey(), null);
        return ResponseUtil.prepareResponse(response, Message.ValueEnum.GET_VIRTUAL_ACCOUNT.getValue());
    }

    /**
     * @param userHandle
     * @param userPrivateKey
     * @param virtualAccountId
     * @param virtualAccountName
     * @param active
     * @return
     * @throws IOException
     * @throws InterruptedException
     */
    public ApiResponse updateVirtualAccount(String userHandle, String userPrivateKey, String virtualAccountId, String virtualAccountName, Boolean active)
            throws IOException, InterruptedException {
        return updateVirtualAccount(userHandle, userPrivateKey, virtualAccountId, virtualAccountName, active, null, null);
    }

    /**
     * @param userHandle
     * @param userPrivateKey
     * @param virtualAccountId
     * @param virtualAccountName
     * @param active
     * @param achCreditEnabled
     * @param achDebitEnabled
     * @return
     * @throws IOException
     * @throws InterruptedException
     */
    public ApiResponse updateVirtualAccount(String userHandle, String userPrivateKey, String virtualAccountId, String virtualAccountName, Boolean active, Boolean achCreditEnabled, Boolean achDebitEnabled)
            throws IOException, InterruptedException {
        String path = Endpoints.UPDATE_VIRTUAL_ACCOUNT.getUri();
        UpdateVirtualAccountMsg body = new UpdateVirtualAccountMsg(userHandle, this.configuration.getAuthHandle(), virtualAccountId, virtualAccountName, active, achCreditEnabled, achDebitEnabled);
        HttpResponse<?> response = getHttpResponse(path, body, userPrivateKey, this.configuration.getPrivateKey(), null);
        return ResponseUtil.prepareResponse(response, Message.ValueEnum.UPDATE_VIRTUAL_ACCOUNT.getValue());
    }

    public ApiResponse retryWebhooks(String userHandle, String eventUuid)
            throws IOException, InterruptedException {
        String path = Endpoints.RETRY_WEBHOOK.getUri();
        ReTryWebhookMsg body = new ReTryWebhookMsg(userHandle, this.configuration.getAuthHandle(), eventUuid);
        HttpResponse<?> response = getHttpResponse(path, body, null, this.configuration.getPrivateKey(), null);
        return ResponseUtil.prepareResponse(response, Message.ValueEnum.RETRY_WEBHOOK.getValue());
    }


    /**
     * @param userHandle
     * @param userPrivateKey
     * @param virtualAccountId
     * @param accountNumber
     * @return
     * @throws IOException
     * @throws InterruptedException
     */
    public ApiResponse closeVirtualAccount(String userHandle, String userPrivateKey, String virtualAccountId, String accountNumber)
            throws IOException, InterruptedException {
        String path = Endpoints.CLOSE_VIRTUAL_ACCOUNT.getUri();
        CloseVirtualAccountMsg body = new CloseVirtualAccountMsg(userHandle, this.configuration.getAuthHandle(), virtualAccountId, accountNumber);
        HttpResponse<?> response = getHttpResponse(path, body, userPrivateKey, this.configuration.getPrivateKey(), null);
        return ResponseUtil.prepareResponse(response, Message.ValueEnum.CLOSE_VIRTUAL_ACCOUNT.getValue());
    }

    /**
     * @param userHandle
     * @param userPrivateKey
     * @param amount
     * @param virtualAccountNumber
     * @param tranCode
     * @param entityName
     *
     * @return
     * @throws IOException
     * @throws InterruptedException
     */
    public ApiResponse createTestVirtualAccountAchTransaction(String userHandle, String userPrivateKey, int amount, String virtualAccountNumber, int tranCode, String entityName)throws IOException, InterruptedException {
        return createTestVirtualAccountAchTransaction(userHandle,userPrivateKey,amount,virtualAccountNumber,null,tranCode,entityName,null,null);
    }
    /**
     * @param userHandle
     * @param userPrivateKey
     * @param amount
     * @param virtualAccountNumber
     * @param date
     * @param tranCode
     * @param entityName
     * @param ced
     * @param achName
     *
     * @return
     * @throws IOException
     * @throws InterruptedException
     */
    public ApiResponse createTestVirtualAccountAchTransaction(String userHandle, String userPrivateKey, int amount, String virtualAccountNumber, Date date, int tranCode, String entityName, String ced, String achName)throws IOException, InterruptedException {
        String path = Endpoints.CREATE_TEST_VIRTUAL_ACCOUNT_ACH_TRANSACTION.getUri();
        CreateTestVirtualAccountAchTransactionMsg body = new CreateTestVirtualAccountAchTransactionMsg(userHandle,this.configuration.getAuthHandle(), amount,virtualAccountNumber,date,tranCode,entityName,ced,achName);
        HttpResponse<?> response = getHttpResponse(path, body, userPrivateKey, this.configuration.getPrivateKey(), null);
        return ResponseUtil.prepareResponse(response, Message.ValueEnum.CREATE_TEST_VIRTUAL_ACCOUNT_ACH_TRANSACTION.getValue());
    }

    /**
     * @param userHandle
     * @param userPrivateKey
     * @param transactionId
     * @param approve
     * @return
     * @throws IOException
     * @throws InterruptedException
     */
    public ApiResponse approveWire(String userHandle, String userPrivateKey, String transactionId, boolean approve)throws IOException, InterruptedException {
        return approveWire(userHandle,userPrivateKey,transactionId,approve,null,null);
    }

    /**
     * @param userHandle
     * @param userPrivateKey
     * @param transactionId
     * @param approve
     * @param notes
     * @return
     * @throws IOException
     * @throws InterruptedException
     */
    public ApiResponse approveWire(String userHandle, String userPrivateKey, String transactionId, boolean approve,String notes)throws IOException, InterruptedException {
        return approveWire(userHandle,userPrivateKey,transactionId,approve,notes,null);
    }

    /**
     * @param userHandle
     * @param userPrivateKey
     * @param transactionId
     * @param approve
     * @param notes
     * @param mockWireAccountName
     * @return
     * @throws IOException
     * @throws InterruptedException
     */
    public ApiResponse approveWire(String userHandle, String userPrivateKey, String transactionId, boolean approve, String notes,String mockWireAccountName)throws IOException, InterruptedException {
        String path = Endpoints.APPROVE_WIRE.getUri();
        ApproveWireMsg body = new ApproveWireMsg(userHandle,this.configuration.getAuthHandle(), transactionId,approve,notes,mockWireAccountName);
        HttpResponse<?> response = getHttpResponse(path, body, userPrivateKey, this.configuration.getPrivateKey(), null);
        return ResponseUtil.prepareResponse(response, Message.ValueEnum.APPROVE_WIRE_MSG.getValue());
    }

    /**
     * @param userHandle
     * @param userPrivateKey
     * @param transactionId
     * @param wireStatus
     * @return
     * @throws IOException
     * @throws InterruptedException
     */
    public ApiResponse mockWireOutFile(String userHandle, String userPrivateKey, String transactionId, String wireStatus)throws IOException, InterruptedException {
        String path = Endpoints.MOCK_WIRE_OUT_FILE.getUri();
        MockWireOutFileMsg body = new MockWireOutFileMsg(userHandle,this.configuration.getAuthHandle(), transactionId,wireStatus);
        HttpResponse<?> response = getHttpResponse(path, body, userPrivateKey, this.configuration.getPrivateKey(), null);
        return ResponseUtil.prepareResponse(response, Message.ValueEnum.MOCK_WIRE_OUT_FILE.getValue());
    }

    private HttpResponse<?> getHttpResponse(String path, Object body, String userSignature, String authSignature, String businessPrivateKey) throws IOException, InterruptedException {
        String sBody = Serialization.serialize(body);
        Map<String, String> headers = new HashMap<>();
        setSignature(userSignature, authSignature, sBody, headers);
        if (businessPrivateKey != null)
            headers.put(BUSINESS_SIGNATURE, EcdsaUtil.sign(sBody, businessPrivateKey));
        HttpResponse<?> response = this.configuration.getApiClient().callApi(path, headers, sBody);
        return response;
    }

    private void setSignature(String userSignature, String authSignature, String sBody, Map<String, String> headers) {
        if (authSignature != null)
            headers.put(AUTH_SIGNATURE, EcdsaUtil.sign(sBody, authSignature));
        if (userSignature != null)
            headers.put(USER_SIGNATURE, EcdsaUtil.sign(sBody, userSignature));
    }
}
