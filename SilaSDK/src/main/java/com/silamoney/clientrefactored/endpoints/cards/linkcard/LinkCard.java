package com.silamoney.clientrefactored.endpoints.cards.linkcard;

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

public class LinkCard extends AbstractEndpoint {

    private static Logger logger = Logger.getLogger(LinkCard.class.getName());

    private static final String ENDPOINT = "/link_card";

    private LinkCard() {
    }

    public static ApiResponse send(LinkCardRequest request)
            throws BadRequestException, InvalidAuthSignatureException, ForbiddenException {
        Map<String, Object> body = new HashMap<>();
        body.put("header", Header.builder().appHandle(APP_HANDLE).userHandle(request.getUserHandle())
                .created(EpochUtils.getEpochTime()).reference(UuidUtils.generateRandomUuid()).build());
        body.put("token", request.getToken());
        body.put("card_name", request.getCardName());
        body.put("account_postal_code", request.getAccountPostalCode());

        String serializedBody = JsonUtils.serialize(body);

        Map<String, String> headers = new HashMap<>();
        HeadersUtils.addAuthSignature(headers, serializedBody);
        HeadersUtils.addUserSignature(headers, serializedBody, request.getUserPrivateKey());
        HeadersUtils.addContentType(headers, "application/json");

        try {
            return ResponseUtils.prepareResponse(LinkCardResponse.class,
                    API_CLIENT.send(ENDPOINT, serializedBody, headers));
        } catch (Exception e) {
            return null;
        }
    }

}
