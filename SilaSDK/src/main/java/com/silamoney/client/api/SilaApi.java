package com.silamoney.client.api;

import com.silamoney.client.config.Configuration;
import com.silamoney.client.domain.EntityMsg;
import com.silamoney.client.domain.Environments;
import com.silamoney.client.domain.GetAccountsMsg;
import com.silamoney.client.domain.GetTransactionsMsg;
import com.silamoney.client.domain.HeaderMsg;
import com.silamoney.client.domain.IssueMsg;
import com.silamoney.client.domain.LinkAccountMsg;
import com.silamoney.client.domain.Message;
import com.silamoney.client.domain.RedeemMsg;
import com.silamoney.client.domain.SearchFilters;
import com.silamoney.client.domain.TransferMsg;
import com.silamoney.client.domain.User;
import com.silamoney.client.exceptions.BadRequestException;
import com.silamoney.client.exceptions.ForbiddenException;
import com.silamoney.client.exceptions.InvalidSignatureException;
import com.silamoney.client.exceptions.ServerSideException;
import com.silamoney.client.security.EcdsaUtil;
import com.silamoney.client.util.ResponseUtil;
import com.silamoney.client.util.Serialization;
import io.reactivex.annotations.Nullable;
import java.io.IOException;
import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Karlo Lorenzana
 */
public class SilaApi {

    private final Configuration configuration;
    private final String AUTH_SIGNATURE = "authsignature";
    private final String USER_SIGNATURE = "usersignature";
    private final String defaultEnvironment = Environments.SilaEnvironment.SANDBOX.getUrl();

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
    public SilaApi(Environments.SilaEnvironment environment,
            String appHandler,
            String privateKey) {
        this.configuration = new Configuration(environment.getUrl(), privateKey, appHandler);
    }

    /**
     * Constructor for SilaApi using sandbox environment.
     *
     * @param appHandler
     * @param privateKey
     */
    public SilaApi(String appHandler, String privateKey) {
        this.configuration = new Configuration(defaultEnvironment, privateKey, appHandler);
    }

    /**
     * Checks if a specific handle is already taken.
     *
     * @param handle
     * @return API response.
     * @throws com.silamoney.client.exceptions.BadRequestException
     * @throws com.silamoney.client.exceptions.InvalidSignatureException
     * @throws com.silamoney.client.exceptions.ServerSideException
     * @throws java.io.IOException
     * @throws java.lang.InterruptedException
     */
    public ApiResponse CheckHandle(String handle) throws
            BadRequestException,
            InvalidSignatureException,
            ServerSideException,
            IOException,
            InterruptedException,
            ForbiddenException {
        HeaderMsg body = new HeaderMsg(handle,
                this.configuration.getAuthHandle());
        String path = "/check_handle";
        String _body = Serialization.serialize(body);
        Map<String, String> headers = new HashMap<>();

        headers.put(AUTH_SIGNATURE, EcdsaUtil.sign(_body,
                this.configuration.getPrivateKey()));

        HttpResponse response = this.configuration.getApiClient()
                .CallApi(path, headers, _body);

        return ResponseUtil.prepareResponse(response, Message.ValueEnum.HEADER_MSG.getValue());
    }

    /**
     * Attaches KYC data and specified blockchain address to an assigned handle.
     *
     * @param user
     * @return API response.
     * @throws java.io.IOException
     * @throws java.lang.InterruptedException
     * @throws com.silamoney.client.exceptions.ServerSideException
     * @throws com.silamoney.client.exceptions.BadRequestException
     * @throws com.silamoney.client.exceptions.InvalidSignatureException
     * @throws com.silamoney.client.exceptions.ForbiddenException
     */
    public ApiResponse Register(User user) throws
            IOException,
            InterruptedException,
            BadRequestException,
            InvalidSignatureException,
            ServerSideException,
            ForbiddenException {
        EntityMsg body = new EntityMsg(user,
                this.configuration.getAuthHandle());
        String path = "/register";
        String _body = Serialization.serialize(body);
        Map<String, String> headers = new HashMap<>();

        headers.put(AUTH_SIGNATURE, EcdsaUtil.sign(_body,
                this.configuration.getPrivateKey()));

        HttpResponse response = this.configuration.getApiClient()
                .CallApi(path, headers, _body);

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
     */
    public ApiResponse RequestKYC(String userHandle,
            String userPrivateKey) throws
            IOException,
            InterruptedException,
            BadRequestException,
            InvalidSignatureException,
            ServerSideException,
            ForbiddenException {
        HeaderMsg body = new HeaderMsg(userHandle, this.configuration.getAuthHandle());
        String path = "/request_kyc";
        String _body = Serialization.serialize(body);
        Map<String, String> headers = new HashMap<>();

        headers.put(AUTH_SIGNATURE, EcdsaUtil.sign(_body,
                this.configuration.getPrivateKey()));
        headers.put(USER_SIGNATURE, EcdsaUtil.sign(_body,
                userPrivateKey));

        HttpResponse response = this.configuration.getApiClient()
                .CallApi(path, headers, _body);

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
     */
    public ApiResponse CheckKYC(String userHandle, String userPrivateKey) throws
            IOException,
            InterruptedException,
            BadRequestException,
            InvalidSignatureException,
            ServerSideException,
            ForbiddenException {
        HeaderMsg body = new HeaderMsg(userHandle, this.configuration.getAuthHandle());
        String path = "/check_kyc";
        String _body = Serialization.serialize(body);
        Map<String, String> headers = new HashMap<>();

        headers.put(AUTH_SIGNATURE, EcdsaUtil.sign(_body,
                this.configuration.getPrivateKey()));
        headers.put(USER_SIGNATURE, EcdsaUtil.sign(_body,
                userPrivateKey));

        HttpResponse response = this.configuration.getApiClient()
                .CallApi(path, headers, _body);

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
     */
    public ApiResponse LinkAccount(String userHandle,
            String accountName,
            String publicToken,
            String userPrivateKey) throws
            IOException,
            InterruptedException,
            BadRequestException,
            InvalidSignatureException,
            ServerSideException,
            ForbiddenException {
        LinkAccountMsg body = new LinkAccountMsg(userHandle,
                accountName,
                publicToken, userPrivateKey,
                this.configuration.getAuthHandle());
        String path = "/link_account";
        String _body = Serialization.serialize(body);
        Map<String, String> headers = new HashMap<>();

        headers.put(AUTH_SIGNATURE, EcdsaUtil.sign(_body,
                this.configuration.getPrivateKey()));
        headers.put(USER_SIGNATURE, EcdsaUtil.sign(_body,
                userPrivateKey));

        HttpResponse response = this.configuration.getApiClient()
                .CallApi(path, headers, _body);

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
     */
    public ApiResponse GetAccounts(String userHandle,
            String userPrivateKey) throws
            IOException,
            InterruptedException,
            BadRequestException,
            InvalidSignatureException,
            ServerSideException,
            ForbiddenException {
        GetAccountsMsg body = new GetAccountsMsg(userHandle,
                this.configuration.getAuthHandle());
        String path = "/get_accounts";
        String _body = Serialization.serialize(body);
        Map<String, String> headers = new HashMap<>();

        headers.put(AUTH_SIGNATURE, EcdsaUtil.sign(_body,
                this.configuration.getPrivateKey()));
        headers.put(USER_SIGNATURE, EcdsaUtil.sign(_body,
                userPrivateKey));

        HttpResponse response = this.configuration.getApiClient()
                .CallApi(path, headers, _body);

        return ResponseUtil.prepareResponse(response, Message.ValueEnum.GET_ACCOUNTS_MSG.getValue());
    }

    /**
     * Debits a specified account and issues tokens to the address belonging to
     * the requested handle.
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
     */
    public ApiResponse IssueSila(String userHandle,
            int amount,
            @Nullable String accountName,
            String userPrivateKey) throws
            IOException,
            InterruptedException,
            BadRequestException,
            InvalidSignatureException,
            ServerSideException,
            ForbiddenException {
        if (accountName == null || accountName.isBlank()) {
            accountName = "default";
        }
        IssueMsg body = new IssueMsg(userHandle,
                accountName,
                amount,
                this.configuration.getAuthHandle());
        String path = "/issue_sila";
        String _body = Serialization.serialize(body);
        Map<String, String> headers = new HashMap<>();

        headers.put(AUTH_SIGNATURE, EcdsaUtil.sign(_body,
                this.configuration.getPrivateKey()));
        headers.put(USER_SIGNATURE, EcdsaUtil.sign(_body,
                userPrivateKey));

        HttpResponse response = this.configuration.getApiClient()
                .CallApi(path, headers, _body);

        return ResponseUtil.prepareResponse(response, Message.ValueEnum.ISSUE_MSG.getValue());
    }

    /**
     * Starts a transfer of the requested amount of SILA to the requested
     * destination handle.
     *
     * @param userHandle
     * @param amount
     * @param destination
     * @param userPrivateKey
     * @return
     * @throws IOException
     * @throws InterruptedException
     * @throws BadRequestException
     * @throws InvalidSignatureException
     * @throws ServerSideException
     */
    public ApiResponse TransferSila(String userHandle,
            int amount,
            String destination,
            String userPrivateKey) throws
            IOException,
            InterruptedException,
            BadRequestException,
            InvalidSignatureException,
            ServerSideException,
            ForbiddenException {
        TransferMsg body = new TransferMsg(userHandle,
                destination,
                amount,
                this.configuration.getAuthHandle());
        String path = "/transfer_sila";
        String _body = Serialization.serialize(body);
        Map<String, String> headers = new HashMap<>();

        headers.put(AUTH_SIGNATURE, EcdsaUtil.sign(_body,
                this.configuration.getPrivateKey()));
        headers.put(USER_SIGNATURE, EcdsaUtil.sign(_body,
                userPrivateKey));

        HttpResponse response = this.configuration.getApiClient()
                .CallApi(path, headers, _body);

        return ResponseUtil.prepareResponse(response,
                Message.ValueEnum.TRANSFER_MSG.getValue());
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
     */
    public ApiResponse RedeemSila(String userHandle,
            int amount,
            @Nullable String accountName,
            String userPrivateKey) throws
            IOException,
            InterruptedException,
            BadRequestException,
            InvalidSignatureException,
            ServerSideException,
            ForbiddenException {
        if (accountName == null || accountName.isBlank()) {
            accountName = "default";
        }
        RedeemMsg body = new RedeemMsg(userHandle,
                amount,
                accountName,
                this.configuration.getAuthHandle());
        String path = "/redeem_sila";
        String _body = Serialization.serialize(body);
        Map<String, String> headers = new HashMap<>();

        headers.put(AUTH_SIGNATURE, EcdsaUtil.sign(_body,
                this.configuration.getPrivateKey()));
        headers.put(USER_SIGNATURE, EcdsaUtil.sign(_body,
                userPrivateKey));

        HttpResponse response = this.configuration.getApiClient()
                .CallApi(path, headers, _body);

        return ResponseUtil.prepareResponse(response,
                Message.ValueEnum.TRANSFER_MSG.getValue());
    }

    public ApiResponse GetTransactions(String userHandle,
            SearchFilters filters,
            String userPrivateKey) throws
            IOException,
            InterruptedException,
            BadRequestException,
            InvalidSignatureException,
            ServerSideException,
            ForbiddenException {
        GetTransactionsMsg body = new GetTransactionsMsg(userHandle,
                this.configuration.getAuthHandle(),
                filters);
        String path = "/get_transactions";
        String _body = Serialization.serialize(body);
        Map<String, String> headers = new HashMap<>();

        headers.put(AUTH_SIGNATURE, EcdsaUtil.sign(_body,
                this.configuration.getPrivateKey()));
        headers.put(USER_SIGNATURE, EcdsaUtil.sign(_body,
                userPrivateKey));

        HttpResponse response = this.configuration.getApiClient()
                .CallApi(path, headers, _body);

        return ResponseUtil.prepareResponse(response,
                Message.ValueEnum.GET_TRANSACTIONS_MSG.getValue());
    }
}
