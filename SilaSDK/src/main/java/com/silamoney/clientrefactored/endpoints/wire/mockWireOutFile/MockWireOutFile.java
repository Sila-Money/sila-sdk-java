package com.silamoney.clientrefactored.endpoints.wire.mockWireOutFile;

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

public class MockWireOutFile extends AbstractEndpoint {

    private static Logger logger = Logger.getLogger(MockWireOutFile.class.getName());

    private static final String ENDPOINT = "/mock_wire_out_file";

    private MockWireOutFile() {
    }

    public static ApiResponse send(MockWireOutFileRequest request)
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
        body.put("wire_status", request.getWireStatus());

        String serializedBody = JsonUtils.serialize(body);

        Map<String, String> headers = new HashMap<>();
        HeadersUtils.addAuthSignature(headers, serializedBody);
        HeadersUtils.addUserSignature(headers, serializedBody, request.getUserPrivateKey());
        HeadersUtils.addContentType(headers, "application/json");

        try {
            return ResponseUtils.prepareResponse(MockWireOutFileResponse.class,
                    API_CLIENT.send(ENDPOINT, serializedBody, headers));
        } catch (Exception e) {
            return null;
        }
    }
}
