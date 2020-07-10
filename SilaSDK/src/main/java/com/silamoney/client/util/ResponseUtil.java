package com.silamoney.client.util;

import java.net.http.HttpResponse;
import java.util.ArrayList;

import com.google.gson.reflect.TypeToken;
import com.silamoney.client.api.ApiResponse;
import com.silamoney.client.domain.Account;
import com.silamoney.client.domain.BaseResponse;
import com.silamoney.client.domain.CheckKYCResponse;
import com.silamoney.client.domain.GetBusinessRolesResponse;
import com.silamoney.client.domain.GetBusinessTypesResponse;
import com.silamoney.client.domain.GetEntitiesResponse;
import com.silamoney.client.domain.GetEntityResponse;
import com.silamoney.client.domain.GetNaicsCategoriesResponse;
import com.silamoney.client.domain.GetTransactionsResponse;
import com.silamoney.client.domain.LinkAccountResponse;
import com.silamoney.client.domain.LinkBusinessMemberResponse;
import com.silamoney.client.domain.LinkBusinessOperationResponse;
import com.silamoney.client.exceptions.BadRequestException;
import com.silamoney.client.exceptions.ForbiddenException;
import com.silamoney.client.exceptions.InvalidSignatureException;
import com.silamoney.client.exceptions.ServerSideException;

/**
 * Class to manage the different kinds of responses.
 *
 * @author Karlo Lorenzana
 */
public class ResponseUtil {

    private ResponseUtil() {

    }

    /**
     * Creates an ApiResponse based on the sent HttpResponse.
     *
     * @param response
     * @param msg
     * @return ApiResponse
     * @throws com.silamoney.client.exceptions.BadRequestException
     * @throws com.silamoney.client.exceptions.InvalidSignatureException
     * @throws com.silamoney.client.exceptions.ServerSideException
     * @throws com.silamoney.client.exceptions.ForbiddenException
     */
    public static ApiResponse prepareResponse(HttpResponse<?> response, String msg)
            throws BadRequestException, InvalidSignatureException, ServerSideException, ForbiddenException {
        int statusCode = response.statusCode();

        boolean success = true;
        if (statusCode != 200) {
            success = false;
        }

        switch (msg) {
            case "link_account_msg":
                LinkAccountResponse linkAccountResponse = (LinkAccountResponse) Serialization
                        .deserialize(response.body().toString(), LinkAccountResponse.class);

                if (success && !"SUCCESS".equals(linkAccountResponse.getStatus())) {
                    success = false;
                }

                return new ApiResponse(statusCode, response.headers().map(), linkAccountResponse, success);
            case "get_accounts_msg":
                TypeToken<ArrayList<Account>> token = new TypeToken<ArrayList<Account>>() {
                };
                try {
                    Object accounts = (Object) Serialization.deserialize(response.body().toString(), token);
                    ArrayList<Account> list = new ArrayList<Account>();
                    if (accounts instanceof ArrayList<?>) {
                        ArrayList<?> arrayAccounts = (ArrayList<?>) accounts;
                        if (arrayAccounts.size() > 0) {
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

                    return new ApiResponse(statusCode, response.headers().map(), baseResponse, success);
                }
            case "get_transactions_msg":
                GetTransactionsResponse getTransactionsResponse = (GetTransactionsResponse) Serialization
                        .deserialize(response.body().toString(), GetTransactionsResponse.class);

                return new ApiResponse(statusCode, response.headers().map(), getTransactionsResponse, success);
            case "SilaBalance":
                return new ApiResponse(statusCode, response.headers().map(), response.body(), success);
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
            default:
                BaseResponse baseResponse = (BaseResponse) Serialization.deserialize(response.body().toString(),
                        BaseResponse.class);

                if (success && !"SUCCESS".equals(baseResponse.getStatus())) {
                    success = false;
                }

                return new ApiResponse(statusCode, response.headers().map(), baseResponse, success);
        }
    }
}
