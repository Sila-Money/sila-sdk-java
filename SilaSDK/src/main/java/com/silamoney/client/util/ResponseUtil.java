package com.silamoney.client.util;

import java.lang.reflect.Type;
import java.net.http.HttpResponse;
import java.util.ArrayList;

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

/**
 * Class to manage the different kinds of responses.
 *
 * @author Karlo Lorenzana
 */
public class ResponseUtil {

    private ResponseUtil() {

    }

    public static ApiResponse prepareResponse(Type messageClass, HttpResponse<?> response) {
        int statusCode = response.statusCode();
        if (statusCode == 400) {
            return new ApiResponse(statusCode, response.headers().map(),
                    Serialization.deserialize(response.body().toString(), BadRequestResponse.class), false);
        } else if (statusCode != 200) {
            return new ApiResponse(statusCode, response.headers().map(),
                    Serialization.deserialize(response.body().toString(), BaseResponse.class), false);
        }
        return new ApiResponse(statusCode, response.headers().map(),
                Serialization.deserialize(response.body().toString(), messageClass), statusCode == 200);
    }

    public static ApiResponse prepareFileResponse(HttpResponse<?> response) {
        int statusCode = response.statusCode();
        if (statusCode == 400) {
            return new ApiResponse(statusCode, response.headers().map(),
                    Serialization.deserialize(response.body().toString(), BadRequestResponse.class), false);
        } else if (statusCode != 200) {
            return new ApiResponse(statusCode, response.headers().map(),
                    Serialization.deserialize(response.body().toString(), BaseResponse.class), false);
        }
        return new ApiResponse(statusCode, response.headers().map(), response.body().toString(), statusCode == 200);
    }

    /**
     * Creates an ApiResponse based on the sent HttpResponse.
     *
     * @param response
     * @param msg
     * @return ApiResponse
     */
    public static ApiResponse prepareResponse(HttpResponse<?> response, String msg) {
        int statusCode = response.statusCode();

        boolean success = true;
        if (statusCode != 200) {
            success = false;
        }

        if (statusCode == 400) {
            BadRequestResponse badRequestResponse = (BadRequestResponse) Serialization
                    .deserialize(response.body().toString(), BadRequestResponse.class);
            return new ApiResponse(statusCode, response.headers().map(), badRequestResponse, success);
        }

        switch (msg) {
            case "get_account_balance_msg":
                AccountBalanceResponse accountBalanceResponse = (AccountBalanceResponse) Serialization
                        .deserialize(response.body().toString(), AccountBalanceResponse.class);

                return new ApiResponse(statusCode, response.headers().map(), accountBalanceResponse,
                        accountBalanceResponse.getSuccess());
            case "plaid_sameday_auth_msg":
                PlaidSameDayAuthResponse plaidSameDayAuthResponse = (PlaidSameDayAuthResponse) Serialization
                        .deserialize(response.body().toString(), PlaidSameDayAuthResponse.class);

                return new ApiResponse(statusCode, response.headers().map(), plaidSameDayAuthResponse,
                        plaidSameDayAuthResponse.getSuccess());
            case "get_wallets_msg":
                GetWalletsResponse getWalletsResponse = (GetWalletsResponse) Serialization
                        .deserialize(response.body().toString(), GetWalletsResponse.class);

                return new ApiResponse(statusCode, response.headers().map(), getWalletsResponse,
                        getWalletsResponse.isSuccess());
            case "get_wallet_msg":
                GetWalletResponse getWalletResponse = (GetWalletResponse) Serialization
                        .deserialize(response.body().toString(), GetWalletResponse.class);

                return new ApiResponse(statusCode, response.headers().map(), getWalletResponse,
                        getWalletResponse.getSuccess());
            case "link_account_msg":
                LinkAccountResponse linkAccountResponse = (LinkAccountResponse) Serialization
                        .deserialize(response.body().toString(), LinkAccountResponse.class);

                if (success && !"SUCCESS".equals(linkAccountResponse.getStatus())) {
                    success = false;
                }

                linkAccountResponse.setSuccess(success);

                return new ApiResponse(statusCode, response.headers().map(), linkAccountResponse, success);
            case "get_accounts_msg":
                TypeToken<ArrayList<Account>> token = new TypeToken<ArrayList<Account>>() {
                };
                try {
                    Object accounts = (Object) Serialization.deserialize(response.body().toString(), token);
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
                    BaseResponse baseResponse = (BaseResponse) Serialization.deserialize(response.body().toString(),
                            BaseResponse.class);

                    if (success && !"SUCCESS".equals(baseResponse.getStatus())) {
                        success = false;
                    }

                    baseResponse.setSuccess(success);

                    return new ApiResponse(statusCode, response.headers().map(), baseResponse, success);
                }
            case "get_transactions_msg":
                GetTransactionsResponse getTransactionsResponse = (GetTransactionsResponse) Serialization
                        .deserialize(response.body().toString(), GetTransactionsResponse.class);

                return new ApiResponse(statusCode, response.headers().map(), getTransactionsResponse, success);
            case "get_sila_balance":
                GetSilaBalanceResponse getSilaBalanceResponse = (GetSilaBalanceResponse) Serialization
                        .deserialize(response.body().toString(), GetSilaBalanceResponse.class);

                return new ApiResponse(statusCode, response.headers().map(), getSilaBalanceResponse,
                        getSilaBalanceResponse.getSuccess());
            case "get_business_types":
                GetBusinessTypesResponse businessTypesResponse = (GetBusinessTypesResponse) Serialization
                        .deserialize(response.body().toString(), GetBusinessTypesResponse.class);

                return new ApiResponse(statusCode, response.headers().map(), businessTypesResponse, success);
            case "get_business_roles":
                GetBusinessRolesResponse businessRolesResponse = (GetBusinessRolesResponse) Serialization
                        .deserialize(response.body().toString(), GetBusinessRolesResponse.class);

                return new ApiResponse(statusCode, response.headers().map(), businessRolesResponse, success);
            case "get_naics_categories":
                GetNaicsCategoriesResponse getNaicsCategoriesResponse = (GetNaicsCategoriesResponse) Serialization
                        .deserialize(response.body().toString(), GetNaicsCategoriesResponse.class);

                return new ApiResponse(statusCode, response.headers().map(), getNaicsCategoriesResponse, success);
            case "link_business_member":
                LinkBusinessMemberResponse linkBusinessMemberResponse = (LinkBusinessMemberResponse) Serialization
                        .deserialize(response.body().toString(), LinkBusinessMemberResponse.class);

                return new ApiResponse(statusCode, response.headers().map(), linkBusinessMemberResponse, success);
            case "check_kyc":
                CheckKYCResponse checkKYCResponse = (CheckKYCResponse) Serialization
                        .deserialize(response.body().toString(), CheckKYCResponse.class);

                return new ApiResponse(statusCode, response.headers().map(), checkKYCResponse, success);
            case "unlink_business_member":
                LinkBusinessOperationResponse LinkBusinessOperationResponse = (LinkBusinessOperationResponse) Serialization
                        .deserialize(response.body().toString(), LinkBusinessOperationResponse.class);

                return new ApiResponse(statusCode, response.headers().map(), LinkBusinessOperationResponse, success);
            case "get_entity":
                GetEntityResponse getEntityResponse = (GetEntityResponse) Serialization
                        .deserialize(response.body().toString(), GetEntityResponse.class);

                return new ApiResponse(statusCode, response.headers().map(), getEntityResponse, success);
            case "get_entities":
                GetEntitiesResponse getEntitiesResponse = (GetEntitiesResponse) Serialization
                        .deserialize(response.body().toString(), GetEntitiesResponse.class);

                return new ApiResponse(statusCode, response.headers().map(), getEntitiesResponse, success);
            case "issue_msg":
            case "redeem_msg":
                TransactionResponse issueSilaResponse = (TransactionResponse) Serialization
                        .deserialize(response.body().toString(), TransactionResponse.class);

                if (success && !"SUCCESS".equals(issueSilaResponse.getStatus())) {
                    success = false;
                }

                issueSilaResponse.setSuccess(success);

                return new ApiResponse(statusCode, response.headers().map(), issueSilaResponse, success);
            case "transfer_msg":
                TransferSilaResponse transferSilaResponse = (TransferSilaResponse) Serialization
                        .deserialize(response.body().toString(), TransferSilaResponse.class);

                if (success && !"SUCCESS".equals(transferSilaResponse.getStatus())) {
                    success = false;
                }

                transferSilaResponse.setSuccess(success);

                return new ApiResponse(statusCode, response.headers().map(), transferSilaResponse, success);
            default:
                BaseResponse baseResponse = (BaseResponse) Serialization.deserialize(response.body().toString(),
                        BaseResponse.class);

                if (success && (!"SUCCESS".equals(baseResponse.getStatus()) && baseResponse.getStatus() != null)) {
                    success = false;
                }

                baseResponse.setSuccess(success);

                return new ApiResponse(statusCode, response.headers().map(), baseResponse, success);
        }
    }
}
