package com.silamoney.clientrefactored.endpoints.wire.approveWire;

import com.silamoney.client.api.ApiResponse;
import com.silamoney.client.exceptions.ForbiddenException;
import com.silamoney.clientrefactored.domain.BaseResponse;
import com.silamoney.clientrefactored.domain.Header;
import com.silamoney.clientrefactored.endpoints.AbstractEndpoint;
import com.silamoney.clientrefactored.exceptions.BadRequestException;
import com.silamoney.clientrefactored.exceptions.InvalidAuthSignatureException;
import com.silamoney.clientrefactored.utils.*;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

public class ApproveWire extends AbstractEndpoint {

    private static Logger logger = Logger.getLogger(ApproveWire.class.getName());

    private static final String ENDPOINT = "/approve_wire";

    private ApproveWire() {
    }

    public static ApiResponse send(ApproveWireRequest request)
            throws BadRequestException, InvalidAuthSignatureException, ForbiddenException {
        Map<String, Object> body = new HashMap<>();
        body.put("header", Header.builder()
                .appHandle(APP_HANDLE)
                .userHandle(request.getUserHandle())
                .created(EpochUtils.getEpochTime())
                .reference(request.getReference()!=null?request.getReference():UuidUtils.generateRandomUuid())
                .build()
        );
        body.put("transaction_id", request.getTransactionId());
        body.put("approve", request.getApprove());
        body.put("notes", request.getNotes());
        body.put("notes", request.getNotes());
        body.put("mock_wire_account_name",request.getMockWireAccountName());

        String serializedBody = JsonUtils.serialize(body);

        Map<String, String> headers = new HashMap<>();
        HeadersUtils.addAuthSignature(headers, serializedBody);
        HeadersUtils.addUserSignature(headers, serializedBody, request.getUserPrivateKey());
        HeadersUtils.addContentType(headers, "application/json");

        try {
            return ResponseUtils.prepareResponse(BaseResponse.class,
                    API_CLIENT.send(ENDPOINT, serializedBody, headers));
        } catch (Exception e) {
            return null;
        }
    }
}
