package com.silamoney.client.util;

import java.lang.reflect.Type;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.reflect.TypeToken;
import com.silamoney.client.api.ApiResponse;
import com.silamoney.client.domain.*;

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
        case "delete_account":
            DeleteAccountResponse deleteAccountResponse = (DeleteAccountResponse) Serialization
                    .deserialize(response.body().toString(), DeleteAccountResponse.class);

            return new ApiResponse(statusCode, response.headers().map(), deleteAccountResponse,
                    deleteAccountResponse.getSuccess());
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
        case "link_card_msg":
                LinkCardResponse linkCardResponse = (LinkCardResponse) Serialization
                        .deserialize(response.body().toString(), LinkCardResponse.class);

                if (success && !"SUCCESS".equals(linkCardResponse.getStatus())) {
                    success = false;
                }

                linkCardResponse.setSuccess(success);

                return new ApiResponse(statusCode, response.headers().map(), linkCardResponse, success);
        case "get_cards":
                GetCardsResponse getCardsResponse = (GetCardsResponse) Serialization
                        .deserialize(response.body().toString(), GetCardsResponse.class);

                if (success && !"SUCCESS".equals(getCardsResponse.getStatus())) {
                    success = false;
                }

                getCardsResponse.setSuccess(success);

                return new ApiResponse(statusCode, response.headers().map(), getCardsResponse, success);
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
            CheckKYCResponse checkKYCResponse = (CheckKYCResponse) Serialization.deserialize(response.body().toString(),
                    CheckKYCResponse.class);

            return new ApiResponse(statusCode, response.headers().map(), checkKYCResponse, success);
        case "request_kyc":
            RequestKycResponse requestKycResponse = (RequestKycResponse) Serialization.deserialize(response.body().toString(),
                    RequestKycResponse.class);

            return new ApiResponse(statusCode, response.headers().map(), requestKycResponse, success);
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
        case "plaid_link_token":
            PlaidLinkTokenResponse plaidLinkTokenResponse = (PlaidLinkTokenResponse) Serialization
                    .deserialize(response.body().toString(), PlaidLinkTokenResponse.class);

            return new ApiResponse(statusCode, response.headers().map(), plaidLinkTokenResponse,
                    plaidLinkTokenResponse.isSuccess());
        case "register_wallet_msg":
            RegisterWalletResponse registerWalletResponse = (RegisterWalletResponse) Serialization
                    .deserialize(response.body().toString(), RegisterWalletResponse.class);

            return new ApiResponse(statusCode, response.headers().map(), registerWalletResponse,
                    registerWalletResponse.getSuccess());
        case "update_wallet_msg":
            UpdateWalletResponse updateWalletResponse = (UpdateWalletResponse) Serialization
                    .deserialize(response.body().toString(), UpdateWalletResponse.class);

            return new ApiResponse(statusCode, response.headers().map(), updateWalletResponse,
                    updateWalletResponse.getSuccess());
        case "check_partner_kyc":
            CheckPartnerKycResponse checkPartnerKycResponse = (CheckPartnerKycResponse) Serialization
                    .deserialize(response.body().toString(), CheckPartnerKycResponse.class);

            return new ApiResponse(statusCode, response.headers().map(), checkPartnerKycResponse,
                    checkPartnerKycResponse.isSuccess());
        case "update_account":
            UpdateAccountResponse updateAccountResponse = (UpdateAccountResponse) Serialization
                    .deserialize(response.body().toString(), UpdateAccountResponse.class);

            return new ApiResponse(statusCode, response.headers().map(), updateAccountResponse,
                    updateAccountResponse.isSuccess());
        case "plaid_update_link_token":
            PlaidUpdateLinkTokenResponse plaidUpdateLinkTokenResponse = (PlaidUpdateLinkTokenResponse) Serialization
                    .deserialize(response.body().toString(), PlaidUpdateLinkTokenResponse.class);

            return new ApiResponse(statusCode, response.headers().map(), plaidUpdateLinkTokenResponse,
                    plaidUpdateLinkTokenResponse.isSuccess());
        case "get_webhooks":
                GetWebhooksResponse getWebhooksResponse = (GetWebhooksResponse) Serialization
                        .deserialize(response.body().toString(), GetWebhooksResponse.class);

                return new ApiResponse(statusCode, response.headers().map(), getWebhooksResponse,
                        getWebhooksResponse.getSuccess());
        case "register_business":
            RegisterBusinessResponse registerBusinessResponse = (RegisterBusinessResponse) Serialization.deserialize(response.body().toString(),
                    RegisterBusinessResponse.class);

            if (success && (!"SUCCESS".equals(registerBusinessResponse.getStatus()) && registerBusinessResponse.getStatus() != null)) {
                success = false;
            }

            registerBusinessResponse.setSuccess(success);

            return new ApiResponse(statusCode, response.headers().map(), registerBusinessResponse, success);
            case "check_instant_ach":
                CheckInstantAchResponse checkInstantAchResponse = (CheckInstantAchResponse) Serialization.deserialize(response.body().toString(),
                        CheckInstantAchResponse.class);

                if (success && (!"SUCCESS".equals(checkInstantAchResponse.getStatus()) && checkInstantAchResponse.getStatus() != null)) {
                    success = false;
                }

                checkInstantAchResponse.setSuccess(success);

                return new ApiResponse(statusCode, response.headers().map(), checkInstantAchResponse, success);
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
