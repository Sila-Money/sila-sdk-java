package com.silamoney.clientrefactored.endpoints.virtualAccount.updateVirtualAccount;

import com.silamoney.client.api.ApiResponse;
import com.silamoney.client.exceptions.ForbiddenException;
import com.silamoney.clientrefactored.domain.Header;
import com.silamoney.clientrefactored.endpoints.AbstractEndpoint;
import com.silamoney.clientrefactored.endpoints.virtualAccount.VirtualAccountResponse;
import com.silamoney.clientrefactored.exceptions.BadRequestException;
import com.silamoney.clientrefactored.exceptions.InvalidAuthSignatureException;
import com.silamoney.clientrefactored.utils.*;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

public class UpdateVirtualAccount extends AbstractEndpoint {

    private static Logger logger = Logger.getLogger(UpdateVirtualAccount.class.getName());

    private static final String ENDPOINT = "/update_virtual_account";

    private UpdateVirtualAccount() {
    }

    public static ApiResponse send(UpdateVirtualAccountRequest request)
            throws BadRequestException, InvalidAuthSignatureException, ForbiddenException {
        Map<String, Object> body = new HashMap<>();
        body.put("header", Header.builder().appHandle(APP_HANDLE).userHandle(request.getUserHandle())
                .created(EpochUtils.getEpochTime()).reference(UuidUtils.generateRandomUuid()).build());
        body.put("virtual_account_id", request.getVirtualAccountId());
        body.put("virtual_account_name", request.getVirtualAccountName());
        body.put("active", request.getActive());
        body.put("ach_credit_enabled", request.getAchCreditEnabled());
        body.put("ach_debit_enabled", request.getAchDebitEnabled());
        String serializedBody = JsonUtils.serialize(body);

        Map<String, String> headers = new HashMap<>();
        HeadersUtils.addAuthSignature(headers, serializedBody);
        HeadersUtils.addContentType(headers, "application/json");
        HeadersUtils.addUserSignature(headers, serializedBody, request.getUserPrivateKey());

        try {
            return ResponseUtils.prepareResponse(VirtualAccountResponse.class,
                    API_CLIENT.send(ENDPOINT, serializedBody, headers));
        } catch (Exception e) {
            return null;
        }
    }

}
