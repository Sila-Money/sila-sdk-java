package com.silamoney.clientrefactored.endpoints.wallets.registerwallet;

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

public class RegisterWallet extends AbstractEndpoint {

        private static Logger logger = Logger.getLogger(RegisterWallet.class.getName());

        private static final String ENDPOINT = "/register_wallet";

        private RegisterWallet() {
        }

        public static ApiResponse send(RegisterWalletRequest request)
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
                request.getWallet().setDefaultWallet(request.isDefaultWallet());
                body.put("wallet", request.getWallet());

                String serializedBody = JsonUtils.serialize(body);

                Map<String, String> headers = new HashMap<>();
                HeadersUtils.addAuthSignature(headers, serializedBody);
                HeadersUtils.addContentType(headers, "application/json");
                HeadersUtils.addUserSignature(headers, serializedBody, request.getUserPrivateKey());

                try {
                        return ResponseUtils.prepareResponse(RegisterWalletResponse.class,
                                        API_CLIENT.send(ENDPOINT, serializedBody, headers));
                } catch (Exception e) {
                        return null;
                }
        }

}
