package com.silamoney.clientrefactored.endpoints.webhooks.getWebhooks;

import com.silamoney.client.api.ApiResponse;
import com.silamoney.client.exceptions.ForbiddenException;
import com.silamoney.clientrefactored.domain.Header;
import com.silamoney.clientrefactored.endpoints.AbstractEndpoint;
import com.silamoney.clientrefactored.exceptions.BadRequestException;
import com.silamoney.clientrefactored.exceptions.InvalidAuthSignatureException;
import com.silamoney.clientrefactored.utils.*;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

public class GetWebhooks extends AbstractEndpoint {

    private static Logger logger = Logger.getLogger(GetWebhooks.class.getName());

    private static final String ENDPOINT = "/get_webhooks";

    private GetWebhooks() {
    }

    public static ApiResponse send(GetWebhooksRequest request)
            throws BadRequestException, InvalidAuthSignatureException, ForbiddenException {
        Map<String, Object> body = new HashMap<>();
        body.put("header", Header.builder()
                .appHandle(APP_HANDLE)
                .userHandle(request.getUserHandle())
                .created(EpochUtils.getEpochTime())
                .reference(UuidUtils.generateRandomUuid())
                .build()
        );

        if (request.getSearchFilters() != null)
            body.put("search_filters", request.getSearchFilters());

        String serializedBody = JsonUtils.serialize(body);

        Map<String, String> headers = new HashMap<>();
        HeadersUtils.addAuthSignature(headers, serializedBody);
        HeadersUtils.addContentType(headers, "application/json");

        try {
            return ResponseUtils.prepareResponse(GetWebhooksResponse.class,
                    API_CLIENT.send(ENDPOINT, serializedBody, headers));
        } catch (Exception e) {
            return null;
        }
    }
}