package com.silamoney.clientrefactored.endpoints.webhooks.retryWebhook;

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

public class RetryWebhook extends AbstractEndpoint {

    private static Logger logger = Logger.getLogger(RetryWebhook.class.getName());

    private static final String ENDPOINT = "/retry_webhook";

    private RetryWebhook() {
    }

    public static ApiResponse send(RetryWebhookRequest request)
            throws BadRequestException, InvalidAuthSignatureException, ForbiddenException {
        Map<String, Object> body = new HashMap<>();
        body.put("header", Header.builder()
                .appHandle(APP_HANDLE)
                .userHandle(request.getUserHandle())
                .created(EpochUtils.getEpochTime())
                .reference(UuidUtils.generateRandomUuid())
                .build()
        );
        body.put("message","header_msg");
        body.put("event_uuid",request.getEventUuid());
        String serializedBody = JsonUtils.serialize(body);

        Map<String, String> headers = new HashMap<>();
        HeadersUtils.addAuthSignature(headers, serializedBody);
        HeadersUtils.addContentType(headers, "application/json");

        try {
            return ResponseUtils.prepareResponse(RetryWebhookResponse.class,
                    API_CLIENT.send(ENDPOINT, serializedBody, headers));
        } catch (Exception e) {
            return null;
        }
    }
}