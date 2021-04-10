package com.silamoney.clientrefactored.endpoints.wallets.registerwallet;

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

public class RegisterWallet extends AbstractEndpoint {

        private static Logger logger = Logger.getLogger(RegisterWallet.class.getName());

        private static final String ENDPOINT = "/register_wallet";

        private RegisterWallet() {
        }

        public static RegisterWalletResponse send(RegisterWalletRequest request)
                        throws BadRequestException, InvalidAuthSignatureException, ForbiddenException {
                Map<String, Object> body = new HashMap<>();
                body.put("header", Header.builder()
                        .appHandle(APP_HANDLE)
                        .userHandle(request.getUserHandle())
                        .created(EpochUtils.getEpochTime())
                        .reference(UuidUtils.generateRandomUuid())
                        .build()
                );
                body.put("wallet_verification_signature", request.getWalletVerificationSignature());
                body.put("wallet", request.getWallet());

                String serializedBody = JsonUtils.serialize(body);

                Map<String, String> headers = new HashMap<>();
                HeadersUtils.addAuthSignature(headers, serializedBody);
                HeadersUtils.addContentType(headers, "application/json");
                HeadersUtils.addUserSignature(headers, serializedBody, request.getUserPrivateKey());

                HttpResponse<?> response;
                try {
                        response = API_CLIENT.send(ENDPOINT, serializedBody, headers);
                        if (response.statusCode() == 200)
                                return (RegisterWalletResponse) JsonUtils.deserialize(response.body().toString(),
                                                RegisterWalletResponse.class);
                        else if (response.statusCode() == 400)
                                throw new BadRequestException(response.body().toString());
                        else if (response.statusCode() == 401)
                                throw new ForbiddenException(response.body().toString());
                        else
                                logger.log(Level.SEVERE, response.body().toString());
                } catch (IOException | InterruptedException e) {
                        logger.log(Level.SEVERE, e.getMessage());
                }

                return null;
        }

}
