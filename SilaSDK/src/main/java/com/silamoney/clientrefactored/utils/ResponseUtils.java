package com.silamoney.clientrefactored.utils;

import java.awt.List;
import java.lang.reflect.Type;
import java.net.http.HttpResponse;
import java.util.ArrayList;

import com.silamoney.client.api.ApiResponse;
import com.silamoney.client.domain.BadRequestResponse;
import com.silamoney.client.domain.BaseResponse;
import com.silamoney.client.util.Serialization;
import com.silamoney.clientrefactored.domain.Account;
import com.silamoney.clientrefactored.endpoints.accounts.getaccounts.GetAccountsResponse;

public class ResponseUtils {

    private ResponseUtils() {
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

    public static ApiResponse prepareResponseGetAccounts(HttpResponse<?> response) {
        int statusCode = response.statusCode();
        if (statusCode == 400) {
            return new ApiResponse(statusCode, response.headers().map(),
                    Serialization.deserialize(response.body().toString(), BadRequestResponse.class), false);
        } else if (statusCode != 200) {
            return new ApiResponse(statusCode, response.headers().map(),
                    Serialization.deserialize(response.body().toString(), BaseResponse.class), false);
        }

        GetAccountsResponse parsedResponse = new GetAccountsResponse((ArrayList<Account>) Serialization.deserialize(response.body().toString(), List.class));

        return new ApiResponse(statusCode, response.headers().map(),
                parsedResponse, statusCode == 200);
    }

}
