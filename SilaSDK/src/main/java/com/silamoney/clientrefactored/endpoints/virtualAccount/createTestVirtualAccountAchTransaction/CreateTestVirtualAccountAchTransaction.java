package com.silamoney.clientrefactored.endpoints.virtualAccount.createTestVirtualAccountAchTransaction;

import com.silamoney.client.api.ApiResponse;
import com.silamoney.client.exceptions.ForbiddenException;
import com.silamoney.clientrefactored.domain.Header;
import com.silamoney.clientrefactored.endpoints.AbstractEndpoint;
import com.silamoney.clientrefactored.exceptions.BadRequestException;
import com.silamoney.clientrefactored.exceptions.InvalidAuthSignatureException;
import com.silamoney.clientrefactored.utils.*;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

public class CreateTestVirtualAccountAchTransaction extends AbstractEndpoint {

    private static Logger logger = Logger.getLogger(CreateTestVirtualAccountAchTransaction.class.getName());

    private static final String ENDPOINT = "/create_test_virtual_account_ach_transaction";

    private CreateTestVirtualAccountAchTransaction() {
    }

    public static ApiResponse send(CreateTestVirtualAccountAchTransactionRequest request)
            throws BadRequestException, InvalidAuthSignatureException, ForbiddenException {
        Map<String, Object> body = new HashMap<>();
        body.put("header", Header.builder()
                .appHandle(APP_HANDLE)
                .userHandle(request.getUserHandle())
                .created(EpochUtils.getEpochTime())
                .reference(UuidUtils.generateRandomUuid())
                .build()
        );
        body.put("virtual_account_number", request.getVirtualAccountNumber());
        body.put("amount", request.getAmount());
        body.put("tran_code", request.getTranCode());
        if( request.getEffectiveDate()!=null){
        String pattern = "yyyy-MM-dd";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        String effectiveDate = simpleDateFormat.format(request.getEffectiveDate());
        body.put("effective_date", effectiveDate);
        }
        body.put("entity_name", request.getEntityName());
        body.put("ced", request.getCed());
        body.put("ach_name", request.getAchName());

        String serializedBody = JsonUtils.serialize(body);
        Map<String, String> headers = new HashMap<>();
        HeadersUtils.addAuthSignature(headers, serializedBody);
        HeadersUtils.addContentType(headers, "application/json");
        HeadersUtils.addUserSignature(headers, serializedBody, request.getUserPrivateKey());

        try {
            return ResponseUtils.prepareResponse(CreateTestVirtualAccountAchTransactionResponse.class,
                    API_CLIENT.send(ENDPOINT, serializedBody, headers));
        } catch (Exception e) {
            return null;
        }
    }

}
