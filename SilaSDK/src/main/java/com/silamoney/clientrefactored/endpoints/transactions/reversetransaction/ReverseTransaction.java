package com.silamoney.clientrefactored.endpoints.transactions.reversetransaction;

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

public class ReverseTransaction extends AbstractEndpoint {

        private static Logger logger = Logger.getLogger(ReverseTransaction.class.getName());

        private static final String ENDPOINT = "/reverse_transaction";

        private ReverseTransaction() {
        }

        public static ApiResponse send(ReverseTransactionRequest request)
                        throws BadRequestException, InvalidAuthSignatureException, ForbiddenException {
                Map<String, Object> body = new HashMap<>();
                body.put("header", Header.builder()
                    .appHandle(APP_HANDLE)
                    .userHandle(request.getUserHandle())
                    .created(EpochUtils.getEpochTime())
                    .reference(UuidUtils.generateRandomUuid())
                    .build()
                );
                body.put("transaction_id", request.getTransactionId());

                String serializedBody = JsonUtils.serialize(body);

                Map<String, String> headers = new HashMap<>();
                HeadersUtils.addAuthSignature(headers, serializedBody);
                HeadersUtils.addUserSignature(headers, serializedBody, request.getUserPrivateKey());
                HeadersUtils.addContentType(headers, "application/json");

                try {
                        return ResponseUtils.prepareResponse(ReverseTransactionResponse.class,
                                        API_CLIENT.send(ENDPOINT, serializedBody, headers));
                } catch (Exception e) {
                        return null;
                }
        }

}
