package com.silamoney.clientrefactored.endpoints.entities.checkpartnerkyc;

import java.io.IOException;
import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.silamoney.client.exceptions.ForbiddenException;
import com.silamoney.clientrefactored.endpoints.AbstractEndpoint;
import com.silamoney.clientrefactored.exceptions.BadRequestException;
import com.silamoney.clientrefactored.exceptions.InvalidAuthSignatureException;
import com.silamoney.clientrefactored.domain.Header;
import com.silamoney.clientrefactored.utils.EpochUtils;
import com.silamoney.clientrefactored.utils.HeadersUtils;
import com.silamoney.clientrefactored.utils.JsonUtils;
import com.silamoney.clientrefactored.utils.UuidUtils;

public class CheckPartnerKyc extends AbstractEndpoint {

        private static Logger logger = Logger.getLogger(CheckPartnerKyc.class.getName());

        private static final String ENDPOINT = "/check_partner_kyc";

        private CheckPartnerKyc() {
        }

        public static CheckPartnerKycResponse send(CheckPartnerKycRequest request)
                        throws BadRequestException, InvalidAuthSignatureException, ForbiddenException {
                Map<String, Object> body = new HashMap<>();
                body.put("header", Header.builder()
                    .appHandle(APP_HANDLE)
                    .created(EpochUtils.getEpochTime())
                    .reference(UuidUtils.generateRandomUuid())
                    .build()
                );
                body.put("query_app_handle", request.getQueryAppHandle());
                body.put("query_user_handle", request.getQueryUserHandle());

                String serializedBody = JsonUtils.serialize(body);

                Map<String, String> headers = new HashMap<>();
                HeadersUtils.addAuthSignature(headers, serializedBody);
                HeadersUtils.addContentType(headers, "application/json");

                HttpResponse<?> response;
                try {
                        response = API_CLIENT.send(ENDPOINT, serializedBody, headers);
                        if (response.statusCode() == 200)
                                return (CheckPartnerKycResponse) JsonUtils.deserialize(response.body().toString(),
                                                CheckPartnerKycResponse.class);
                        else if (response.statusCode() == 400)
                                throw new BadRequestException(response.body().toString());
                        else if (response.statusCode() == 401)
                                throw new InvalidAuthSignatureException(response.body().toString());
                        else if (response.statusCode() == 403 || response.statusCode() == 404)
                                throw new ForbiddenException(response.body().toString());
                        else logger.log(Level.SEVERE, response.body().toString());
                } catch (IOException | InterruptedException e) {
                        logger.log(Level.SEVERE, e.getMessage());
                }

                return null;
        }

}
