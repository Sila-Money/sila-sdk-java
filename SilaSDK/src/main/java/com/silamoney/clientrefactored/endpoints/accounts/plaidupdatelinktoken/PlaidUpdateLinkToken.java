package com.silamoney.clientrefactored.endpoints.accounts.plaidupdatelinktoken;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import com.silamoney.client.api.ApiResponse;
import com.silamoney.clientrefactored.domain.Header;
import com.silamoney.clientrefactored.endpoints.AbstractEndpoint;
import com.silamoney.clientrefactored.exceptions.BadRequestException;
import com.silamoney.clientrefactored.exceptions.NotFoundException;
import com.silamoney.clientrefactored.utils.EpochUtils;
import com.silamoney.clientrefactored.utils.HeadersUtils;
import com.silamoney.clientrefactored.utils.JsonUtils;
import com.silamoney.clientrefactored.utils.ResponseUtils;
import com.silamoney.clientrefactored.utils.UuidUtils;

public class PlaidUpdateLinkToken extends AbstractEndpoint {

        private static Logger logger = Logger.getLogger(PlaidUpdateLinkToken.class.getName());

        private static final String ENDPOINT = "/update_account";

        private PlaidUpdateLinkToken() {
        }

        public static ApiResponse send(PlaidUpdateLinkTokenRequest request)
                        throws BadRequestException, NotFoundException {
                Map<String, Object> body = new HashMap<>();
                body.put("header", Header.builder().appHandle(APP_HANDLE).userHandle(request.getUserHandle())
                                .created(EpochUtils.getEpochTime()).reference(UuidUtils.generateRandomUuid()).build());
                body.put("account_name", request.getAccountName());

                String serializedBody = JsonUtils.serialize(body);

                Map<String, String> headers = new HashMap<>();
                HeadersUtils.addAuthSignature(headers, serializedBody);
                HeadersUtils.addContentType(headers, "application/json");

                try {
                        return ResponseUtils.prepareResponse(PlaidUpdateLinkTokenResponse.class,
                                        API_CLIENT.send(ENDPOINT, serializedBody, headers));
                } catch (Exception e) {
                        return null;
                }
        }

}
