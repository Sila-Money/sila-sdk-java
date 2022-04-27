package com.silamoney.clientrefactored.endpoints.accounts.updateaccount;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import com.silamoney.client.api.ApiResponse;
import com.silamoney.client.exceptions.ForbiddenException;
import com.silamoney.clientrefactored.domain.Header;
import com.silamoney.clientrefactored.endpoints.AbstractEndpoint;
import com.silamoney.clientrefactored.exceptions.BadRequestException;
import com.silamoney.clientrefactored.exceptions.InvalidAuthSignatureException;
import com.silamoney.clientrefactored.utils.EpochUtils;
import com.silamoney.clientrefactored.utils.HeadersUtils;
import com.silamoney.clientrefactored.utils.JsonUtils;
import com.silamoney.clientrefactored.utils.ResponseUtils;
import com.silamoney.clientrefactored.utils.UuidUtils;

public class UpdateAccount extends AbstractEndpoint {

        private static Logger logger = Logger.getLogger(UpdateAccount.class.getName());

        private static final String ENDPOINT = "/update_account";

        private UpdateAccount() {
        }

    public static ApiResponse send(UpdateAccountRequest request)
            throws BadRequestException, InvalidAuthSignatureException, ForbiddenException {
        Map<String, Object> body = new HashMap<>();
        body.put("header", Header.builder().appHandle(APP_HANDLE).userHandle(request.getUserHandle())
                .created(EpochUtils.getEpochTime()).reference(UuidUtils.generateRandomUuid()).build());
        body.put("account_name", request.getAccountName());
        if (request.getNewAccountName() != null)
            body.put("new_account_name", request.getNewAccountName());
        if (request.getActive()!=null) {
            body.put("active", request.getActive());
        }

        String serializedBody = JsonUtils.serialize(body);

        Map<String, String> headers = new HashMap<>();
        HeadersUtils.addAuthSignature(headers, serializedBody);
        HeadersUtils.addUserSignature(headers, serializedBody, request.getUserPrivateKey());
        HeadersUtils.addContentType(headers, "application/json");

        try {
            return ResponseUtils.prepareResponse(UpdateAccountResponse.class,
                    API_CLIENT.send(ENDPOINT, serializedBody, headers));
        } catch (Exception e) {
            return null;
        }
    }

}
