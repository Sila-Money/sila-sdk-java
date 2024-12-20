package com.silamoney.client.util;

import java.lang.reflect.Type;
import java.net.http.HttpResponse;
import java.util.ArrayList;

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
    public static ApiResponse prepareRefundResponse(Type messageClass, HttpResponse<?> response) {
        int statusCode = response.statusCode();
        if (statusCode == 400) {
            return new ApiResponse(statusCode, response.headers().map(),
                    Serialization.deserialize(response.body().toString(), BadRequestResponse.class), false);
        } else if (statusCode == 200||statusCode==202) {
            return new ApiResponse(statusCode, response.headers().map(),
                    Serialization.deserialize(response.body().toString(), messageClass), true);
        }else{
            return new ApiResponse(statusCode, response.headers().map(),
                    Serialization.deserialize(response.body().toString(), BaseResponse.class), false);
        }

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
                ArrayList<Account> listAccounts = new ArrayList<Account>();
                if (accounts instanceof ArrayList<?>) {
                    ArrayList<?> arrayAccounts = (ArrayList<?>) accounts;
                    if (!arrayAccounts.isEmpty()) {
                        for (int accountItems = 0; accountItems < arrayAccounts.size(); accountItems++) {
                            Object account = arrayAccounts.get(accountItems);
                            if (account instanceof Account) {
                                Account accountValue = (Account) account;
                                listAccounts.add(accountValue);
                            }
                        }
                    }
                }

                return new ApiResponse(statusCode, response.headers().map(), listAccounts, success);
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
        case "get_payment_methods":
             GetPaymentMethodsResponse getPaymentMethodsResponse = (GetPaymentMethodsResponse) Serialization
                     .deserialize(response.body().toString(), GetPaymentMethodsResponse.class);

             return new ApiResponse(statusCode, response.headers().map(), getPaymentMethodsResponse, success);
        case "open_virtual_account":
            VirtualAccountResponse openVirtualAccountResponse = (VirtualAccountResponse) Serialization
                    .deserialize(response.body().toString(), VirtualAccountResponse.class);

            return new ApiResponse(statusCode, response.headers().map(), openVirtualAccountResponse, success);
        case "get_virtual_accounts":
            GetVirtualAccountsResponse getVirtualAccountsResponse = (GetVirtualAccountsResponse) Serialization
                    .deserialize(response.body().toString(), GetVirtualAccountsResponse.class);

            return new ApiResponse(statusCode, response.headers().map(), getVirtualAccountsResponse, success);
        case "get_virtual_account":
            GetVirtualAccountResponse getVirtualAccountResponse = (GetVirtualAccountResponse) Serialization
                    .deserialize(response.body().toString(), GetVirtualAccountResponse.class);

            return new ApiResponse(statusCode, response.headers().map(), getVirtualAccountResponse, success);
        case "close_virtual_account":
        case "update_virtual_account":
            VirtualAccountResponse virtualAccountResponse = (VirtualAccountResponse) Serialization
                    .deserialize(response.body().toString(), VirtualAccountResponse.class);

            return new ApiResponse(statusCode, response.headers().map(), virtualAccountResponse, success);
        case "get_statements_data_msg":
        case "get_wallet_statement_data_msg":
             GetStatementsResponse getStatementsResponse = (GetStatementsResponse) Serialization
                     .deserialize(response.body().toString(), GetStatementsResponse.class);
             return new ApiResponse(statusCode, response.headers().map(), getStatementsResponse, success);
        case "get_statement_transactions_msg":
            GetStatementTransactionsResponse getStatementTransactionsResponse = (GetStatementTransactionsResponse) Serialization
                    .deserialize(response.body().toString(), GetStatementTransactionsResponse.class);
            return new ApiResponse(statusCode, response.headers().map(), getStatementTransactionsResponse, success);
        case "statements_msg":
            StatementsResponse statementsResponse = (StatementsResponse) Serialization
                    .deserialize(response.body().toString(), StatementsResponse.class);
            return new ApiResponse(statusCode, response.headers().map(), statementsResponse, success);
        case "refund_debit_card_msg":
            RefundDebitCardResponse refundDebitCardResponse = (RefundDebitCardResponse) Serialization
                    .deserialize(response.body().toString(), RefundDebitCardResponse.class);
            return new ApiResponse(statusCode, response.headers().map(), refundDebitCardResponse, success);
        case "create_cko_testing_token_msg":
            CreateCkoTestingTokenResponse createCkoTestingTokenResponse = (CreateCkoTestingTokenResponse) Serialization
                    .deserialize(response.body().toString(), CreateCkoTestingTokenResponse.class);
            return new ApiResponse(statusCode, response.headers().map(), createCkoTestingTokenResponse, success);
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
