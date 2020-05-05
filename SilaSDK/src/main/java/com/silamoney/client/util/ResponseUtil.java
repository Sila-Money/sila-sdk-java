package com.silamoney.client.util;

import com.google.gson.reflect.TypeToken;
import com.silamoney.client.api.ApiResponse;
import com.silamoney.client.domain.Account;
import com.silamoney.client.domain.BaseResponse;
import com.silamoney.client.domain.GetTransactionsResponse;
import com.silamoney.client.domain.LinkAccountResponse;
import com.silamoney.client.domain.RedeemSilaResponse;
import com.silamoney.client.exceptions.BadRequestException;
import com.silamoney.client.exceptions.ForbiddenException;
import com.silamoney.client.exceptions.InvalidSignatureException;
import com.silamoney.client.exceptions.ServerSideException;
import java.net.http.HttpResponse;
import java.util.ArrayList;

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
                TypeToken<ArrayList<Account>> token = new TypeToken<ArrayList<Account>>() {};
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
            case "redeem_msg":
            	RedeemSilaResponse redeemSilaResponse = (RedeemSilaResponse) Serialization.deserialize(response.body().toString(),
            			RedeemSilaResponse.class);

                if (success && !"SUCCESS".equals(redeemSilaResponse.getStatus())) {
                    success = false;
                }
                
                return new ApiResponse(statusCode, response.headers().map(), redeemSilaResponse, success);
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
