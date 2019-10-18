package com.silamoney.client.util;

import com.silamoney.client.api.ApiResponse;
import com.silamoney.client.domain.Account;
import com.silamoney.client.domain.BaseResponse;
import com.silamoney.client.domain.LinkAccountResponse;
import com.silamoney.client.domain.Message;
import com.silamoney.client.exceptions.BadRequestException;
import com.silamoney.client.exceptions.InvalidSignatureException;
import com.silamoney.client.exceptions.ServerSideException;
import java.net.http.HttpResponse;
import java.util.List;

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
     */
    public static ApiResponse prepareResponse(HttpResponse response, String msg)
            throws BadRequestException,
            InvalidSignatureException,
            ServerSideException {
        int statusCode = response.statusCode();

        switch (statusCode) {
            case 400:
                throw new BadRequestException(response.body().toString());
            case 401:
                throw new InvalidSignatureException(response.body().toString());
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
                List<Account> accounts = (List<Account>) Serialization
                        .deserialize(response.body().toString(), List.class);
                return new ApiResponse(statusCode,
                        response.headers().map(),
                        accounts);
            default:
                BaseResponse baseResponse = (BaseResponse) Serialization
                        .deserialize(response.body().toString(), BaseResponse.class);
                return new ApiResponse(statusCode,
                        response.headers().map(),
                        baseResponse);
        }
    }
}
