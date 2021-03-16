package com.silamoney.client.util;

import com.google.gson.reflect.TypeToken;
import com.silamoney.client.api.ApiResponse;
import com.silamoney.client.domain.Account;
import com.silamoney.client.domain.AccountBalanceResponse;
import com.silamoney.client.domain.BadRequestResponse;
import com.silamoney.client.domain.BaseResponse;
import com.silamoney.client.domain.CheckKYCResponse;
import com.silamoney.client.domain.GetBusinessRolesResponse;
import com.silamoney.client.domain.GetBusinessTypesResponse;
import com.silamoney.client.domain.GetEntitiesResponse;
import com.silamoney.client.domain.GetEntityResponse;
import com.silamoney.client.domain.GetNaicsCategoriesResponse;
import com.silamoney.client.domain.GetSilaBalanceResponse;
import com.silamoney.client.domain.GetTransactionsResponse;
import com.silamoney.client.domain.GetWalletResponse;
import com.silamoney.client.domain.GetWalletsResponse;
import com.silamoney.client.domain.LinkAccountResponse;
import com.silamoney.client.domain.LinkBusinessMemberResponse;
import com.silamoney.client.domain.LinkBusinessOperationResponse;
import com.silamoney.client.domain.PlaidSameDayAuthResponse;
import com.silamoney.client.domain.TransactionResponse;
import com.silamoney.client.domain.TransferSilaResponse;
import java.lang.reflect.Type;
import java.net.http.HttpHeaders;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Class to manage the different kinds of responses.
 *
 * @author Karlo Lorenzana
 */
public class ResponseUtil {

    private static final Log log = LogFactory.getFactory().getInstance(ResponseUtil.class);
    private static final Set<String> substringsForHeaderExcludes = Set.of("secret", "key", "password", "auth", "cred");

    private ResponseUtil() {

    }

    public static ApiResponse prepareResponse(Type messageClass, HttpResponse<?> response) {
        Object body = response.body();
        String bodyString = body != null ? body.toString() : "";
        log.info(Map.of("http_response_uri",response.request().uri(), "headers",
            normHeaderMap(response.headers()),
            "body", bodyString));
        int statusCode = response.statusCode();
        if (statusCode == 400) {
            return new ApiResponse(statusCode, response.headers().map(),
                    Serialization.deserialize(bodyString, BadRequestResponse.class), false);
        } else if (statusCode != 200) {
            return new ApiResponse(statusCode, response.headers().map(),
                    Serialization.deserialize(bodyString, BaseResponse.class), false);
        }
        return new ApiResponse(statusCode, response.headers().map(),
                Serialization.deserialize(bodyString, messageClass), statusCode == 200);
    }

    public static ApiResponse prepareFileResponse(HttpResponse<?> response) {
        Object body = response.body();
        String bodyString = body != null ? body.toString() : "";
        log.info(Map.of("http_response_uri",response.request().uri(),
            "statusCode", response.statusCode(),
            "headers", normHeaderMap(response.headers()), 
            "body", bodyString));
        int statusCode = response.statusCode();
        if (statusCode == 400) {
            return new ApiResponse(statusCode, response.headers().map(),
                    Serialization.deserialize(bodyString, BadRequestResponse.class), false);
        } else if (statusCode != 200) {
            return new ApiResponse(statusCode, response.headers().map(),
                    Serialization.deserialize(bodyString, BaseResponse.class), false);
        }
        return new ApiResponse(statusCode, response.headers().map(), bodyString, statusCode == 200);
    }

    /**
     * Creates an ApiResponse based on the sent HttpResponse.
     *
     * @param response
     * @param msg
     * @return ApiResponse
     */
    public static ApiResponse prepareResponse(HttpResponse<?> response, String msg) {
        Object body = response.body();
        String bodyString = body != null ? body.toString() : "";
        log.info(Map.of("http_response_uri",response.request().uri(),
            "headers", normHeaderMap(response.headers()),
            "body", bodyString));
        
        int statusCode = response.statusCode();

        boolean success = true;
        if (statusCode != 200) {
            success = false;
        }

        try {
            if (statusCode == 400) {
                BadRequestResponse badRequestResponse = (BadRequestResponse) Serialization
                        .deserialize(bodyString, BadRequestResponse.class);
                return new ApiResponse(statusCode, response.headers().map(), badRequestResponse, success);
            }

            switch (msg) {
                case "get_account_balance_msg":
                    AccountBalanceResponse accountBalanceResponse = (AccountBalanceResponse) Serialization
                            .deserialize(bodyString, AccountBalanceResponse.class);

                    return new ApiResponse(statusCode, response.headers().map(), accountBalanceResponse,
                            accountBalanceResponse.getSuccess());
                case "plaid_sameday_auth_msg":
                    PlaidSameDayAuthResponse plaidSameDayAuthResponse = (PlaidSameDayAuthResponse) Serialization
                            .deserialize(bodyString, PlaidSameDayAuthResponse.class);

                    return new ApiResponse(statusCode, response.headers().map(), plaidSameDayAuthResponse,
                            plaidSameDayAuthResponse.getSuccess());
                case "get_wallets_msg":
                    GetWalletsResponse getWalletsResponse = (GetWalletsResponse) Serialization
                            .deserialize(bodyString, GetWalletsResponse.class);

                    return new ApiResponse(statusCode, response.headers().map(), getWalletsResponse,
                            getWalletsResponse.isSuccess());
                case "get_wallet_msg":
                    GetWalletResponse getWalletResponse = (GetWalletResponse) Serialization
                            .deserialize(bodyString, GetWalletResponse.class);

                    return new ApiResponse(statusCode, response.headers().map(), getWalletResponse,
                            getWalletResponse.getSuccess());
                case "link_account_msg":
                    LinkAccountResponse linkAccountResponse = (LinkAccountResponse) Serialization
                            .deserialize(bodyString, LinkAccountResponse.class);

                    if (success && !"SUCCESS".equals(linkAccountResponse.getStatus())) {
                        success = false;
                    }

                    linkAccountResponse.setSuccess(success);

                    return new ApiResponse(statusCode, response.headers().map(), linkAccountResponse, success);
                case "get_accounts_msg":
                    TypeToken<ArrayList<Account>> token = new TypeToken<ArrayList<Account>>() {
                    };
                    try {
                        Object accounts = (Object) Serialization.deserialize(bodyString, token);
                        ArrayList<Account> list = new ArrayList<Account>();
                        if (accounts instanceof ArrayList<?>) {
                            ArrayList<?> arrayAccounts = (ArrayList<?>) accounts;
                            if (!arrayAccounts.isEmpty()) {
                                for (int i = 0; i < arrayAccounts.size(); i++) {
                                    Object account = arrayAccounts.get(i);
                                    if (account instanceof Account) {
                                        Account a = (Account) account;
                                        list.add(a);
                                    }
                                }
                            }
                        }

                        return new ApiResponse(statusCode, response.headers().map(), list, success);
                    } catch (Exception e) {
                        BaseResponse baseResponse = (BaseResponse) Serialization.deserialize(bodyString,
                                BaseResponse.class);

                        if (success && !"SUCCESS".equals(baseResponse.getStatus())) {
                            success = false;
                        }

                        baseResponse.setSuccess(success);

                        return new ApiResponse(statusCode, response.headers().map(), baseResponse, success);
                    }
                case "get_transactions_msg":
                    GetTransactionsResponse getTransactionsResponse = (GetTransactionsResponse) Serialization
                            .deserialize(bodyString, GetTransactionsResponse.class);

                    return new ApiResponse(statusCode, response.headers().map(), getTransactionsResponse, success);
                case "get_sila_balance":
                    GetSilaBalanceResponse getSilaBalanceResponse = (GetSilaBalanceResponse) Serialization
                            .deserialize(bodyString, GetSilaBalanceResponse.class);

                    return new ApiResponse(statusCode, response.headers().map(), getSilaBalanceResponse,
                            getSilaBalanceResponse.getSuccess());
                case "get_business_types":
                    GetBusinessTypesResponse businessTypesResponse = (GetBusinessTypesResponse) Serialization
                            .deserialize(bodyString, GetBusinessTypesResponse.class);

                    return new ApiResponse(statusCode, response.headers().map(), businessTypesResponse, success);
                case "get_business_roles":
                    GetBusinessRolesResponse businessRolesResponse = (GetBusinessRolesResponse) Serialization
                            .deserialize(bodyString, GetBusinessRolesResponse.class);

                    return new ApiResponse(statusCode, response.headers().map(), businessRolesResponse, success);
                case "get_naics_categories":
                    GetNaicsCategoriesResponse getNaicsCategoriesResponse = (GetNaicsCategoriesResponse) Serialization
                            .deserialize(bodyString, GetNaicsCategoriesResponse.class);

                    return new ApiResponse(statusCode, response.headers().map(), getNaicsCategoriesResponse, success);
                case "link_business_member":
                    LinkBusinessMemberResponse linkBusinessMemberResponse = (LinkBusinessMemberResponse) Serialization
                            .deserialize(bodyString, LinkBusinessMemberResponse.class);

                    return new ApiResponse(statusCode, response.headers().map(), linkBusinessMemberResponse, success);
                case "check_kyc":
                    CheckKYCResponse checkKYCResponse = (CheckKYCResponse) Serialization
                            .deserialize(bodyString, CheckKYCResponse.class);

                    return new ApiResponse(statusCode, response.headers().map(), checkKYCResponse, success);
                case "unlink_business_member":
                    LinkBusinessOperationResponse LinkBusinessOperationResponse = (LinkBusinessOperationResponse) Serialization
                            .deserialize(bodyString, LinkBusinessOperationResponse.class);

                    return new ApiResponse(statusCode, response.headers().map(), LinkBusinessOperationResponse, success);
                case "get_entity":
                    GetEntityResponse getEntityResponse = (GetEntityResponse) Serialization
                            .deserialize(bodyString, GetEntityResponse.class);

                    return new ApiResponse(statusCode, response.headers().map(), getEntityResponse, success);
                case "get_entities":
                    GetEntitiesResponse getEntitiesResponse = (GetEntitiesResponse) Serialization
                            .deserialize(bodyString, GetEntitiesResponse.class);

                    return new ApiResponse(statusCode, response.headers().map(), getEntitiesResponse, success);
                case "issue_msg":
                case "redeem_msg":
                    TransactionResponse issueSilaResponse = (TransactionResponse) Serialization
                            .deserialize(bodyString, TransactionResponse.class);

                    if (success && !"SUCCESS".equals(issueSilaResponse.getStatus())) {
                        success = false;
                    }

                    issueSilaResponse.setSuccess(success);

                    return new ApiResponse(statusCode, response.headers().map(), issueSilaResponse, success);
                case "transfer_msg":
                    TransferSilaResponse transferSilaResponse = (TransferSilaResponse) Serialization
                            .deserialize(bodyString, TransferSilaResponse.class);

                    if (success && !"SUCCESS".equals(transferSilaResponse.getStatus())) {
                        success = false;
                    }

                    transferSilaResponse.setSuccess(success);

                    return new ApiResponse(statusCode, response.headers().map(), transferSilaResponse, success);
                default:
                    BaseResponse baseResponse = (BaseResponse) Serialization.deserialize(bodyString,
                            BaseResponse.class);

                    if (success && (!"SUCCESS".equals(baseResponse.getStatus()) && baseResponse.getStatus() != null)) {
                        success = false;
                    }

                    baseResponse.setSuccess(success);

                    return new ApiResponse(statusCode, response.headers().map(), baseResponse, success);
            }
        } catch (Exception ex) {
            log.error(Map.of("message", "Error preparing response", "error", ex,
                "statusCode", response.statusCode(),
                "responseBody", bodyString,
                "responseMessage", msg));
            throw new RuntimeException(ex);
        }
    }

    public static Map<String, Object> normHeaderMap(HttpHeaders headers) {
        Map<String, Object> normMap = new LinkedHashMap<>();
        final Map<String, List<String>> headerMap = headers.map();
        for (String key : headerMap.keySet()) {
            String normKey = key.toLowerCase();
            String normValue = String.join(",", headerMap.get(key));
            if (substringsForHeaderExcludes.stream().anyMatch(normKey::contains)) {
                normValue = "**************";
            }
            normMap.put(key, normValue);
        }
        return normMap;
    }

    public static Map<String, Object> normHeaderMap(Map<String, ?> headers) {
        Map<String, Object> normMap = new LinkedHashMap<>();
        for (String key : headers.keySet()) {
            String normKey = key.toLowerCase();
            String normValue = String.valueOf(headers.get(key));
            if (substringsForHeaderExcludes.stream().anyMatch(normKey::contains)) {
                normValue = "**************";
            }
            normMap.put(key, normValue);
        }
        return normMap;
    }


}
