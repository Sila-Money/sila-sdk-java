package com.silamoney.clientrefactored.endpoints.entities.register;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import com.silamoney.client.api.ApiResponse;
import com.silamoney.clientrefactored.domain.Header;
import com.silamoney.clientrefactored.endpoints.AbstractEndpoint;
import com.silamoney.clientrefactored.exceptions.BadRequestException;
import com.silamoney.clientrefactored.exceptions.InvalidAuthSignatureException;
import com.silamoney.clientrefactored.utils.EpochUtils;
import com.silamoney.clientrefactored.utils.HeadersUtils;
import com.silamoney.clientrefactored.utils.JsonUtils;
import com.silamoney.clientrefactored.utils.ResponseUtils;
import com.silamoney.clientrefactored.utils.UuidUtils;

public class Register extends AbstractEndpoint {

        private static Logger logger = Logger.getLogger(Register.class.getName());

        private static final String ENDPOINT = "/register";

        private Register() {
        }

        public static ApiResponse send(RegisterRequest request)
                        throws BadRequestException, InvalidAuthSignatureException {
                Map<String, Object> body = new HashMap<>();
                body.put("header", Header.builder()
                    .appHandle(APP_HANDLE)
                    .userHandle(request.getUserHandle())
                    .created(EpochUtils.getEpochTime())
                    .reference(request.getReference()!=null?request.getReference():UuidUtils.generateRandomUuid())
                    .build()
                );
                body.put("address", request.getAddress());
                body.put("contact", request.getContact());
                body.put("crypto_entry", request.getCryptoEntry());
                body.put("entity", request.getEntity());
                body.put("identity", request.getIdentity());
                body.put("message", "entity_msg");
                String serializedBody = JsonUtils.serialize(body);

                Map<String, String> headers = new HashMap<>();
                HeadersUtils.addAuthSignature(headers, serializedBody);
                HeadersUtils.addContentType(headers, "application/json");

                try {
                        return ResponseUtils.prepareResponse(RegisterResponse.class,
                                        API_CLIENT.send(ENDPOINT, serializedBody, headers));
                } catch (Exception e) {
                        return null;
                }
        }

}
