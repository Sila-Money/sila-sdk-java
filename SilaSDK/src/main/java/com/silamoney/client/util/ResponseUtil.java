package com.silamoney.client.util;

import com.google.gson.reflect.TypeToken;
import com.silamoney.client.api.ApiResponse;
import com.silamoney.client.domain.Account;
import com.silamoney.client.domain.BaseResponse;
import com.silamoney.client.domain.GetTransactionsResponse;
import com.silamoney.client.domain.LinkAccountResponse;
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
    public static ApiResponse prepareResponse(HttpResponse response, String msg)
            throws BadRequestException,
            InvalidSignatureException,
            ServerSideException,
            ForbiddenException {
        int statusCode = response.statusCode();

        switch (statusCode) {
            case 400:
                throw new BadRequestException(response.body().toString());
            case 401:
                throw new InvalidSignatureException(response.body().toString());
            case 403:
                throw new ForbiddenException(response.body().toString());
            case 500:
                throw new ServerSideException(response.body().toString());
            default:
                break;
        }

        switch (msg) {
            case "link_account_msg":
                LinkAccountResponse linkAccountResponse = (LinkAccountResponse) Serialization
                        .deserialize(response.body().toString(), LinkAccountResponse.class);
                return new ApiResponse(statusCode,
                        response.headers().map(),
                        linkAccountResponse);
            case "get_accounts_msg":
                TypeToken<ArrayList<Account>> token = new TypeToken<ArrayList<Account>>(){};
                ArrayList<Account> accounts = (ArrayList<Account>) Serialization
                        .deserialize(response.body().toString(), token);
                return new ApiResponse(statusCode,
                        response.headers().map(),
                        accounts);
            case "get_transactions_msg":
                GetTransactionsResponse getTransactionsResponse = (GetTransactionsResponse) Serialization
                        .deserialize(response.body().toString(), GetTransactionsResponse.class);
                return new ApiResponse(statusCode,
                        response.headers().map(),
                        getTransactionsResponse);
            case "SilaBalance":
                return new ApiResponse(statusCode,
                        response.headers().map(),
                        response.body());
            default:
                BaseResponse baseResponse = (BaseResponse) Serialization
                        .deserialize(response.body().toString(), BaseResponse.class);
                return new ApiResponse(statusCode,
                        response.headers().map(),
                        baseResponse);
        }
    }
}
