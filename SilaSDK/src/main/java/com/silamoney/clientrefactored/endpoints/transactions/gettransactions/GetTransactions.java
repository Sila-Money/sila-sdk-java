package com.silamoney.clientrefactored.endpoints.transactions.gettransactions;

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

public class GetTransactions extends AbstractEndpoint {

        private static Logger logger = Logger.getLogger(GetTransactions.class.getName());

        private static final String ENDPOINT = "/get_transactions";

        private GetTransactions() {
        }

        public static ApiResponse send(GetTransactionsRequest request)
                        throws BadRequestException, InvalidAuthSignatureException, ForbiddenException {
                Map<String, Object> body = new HashMap<>();
                body.put("header", Header.builder()
                    .appHandle(APP_HANDLE)
                    .userHandle(request.getUserHandle())
                    .created(EpochUtils.getEpochTime())
                    .reference(UuidUtils.generateRandomUuid())
                    .build()
                );
                body.put("message", "get_transactions_msg");
                body.put("search_filters", request.getSearchFilters());

                String serializedBody = JsonUtils.serialize(body);

                Map<String, String> headers = new HashMap<>();
                HeadersUtils.addAuthSignature(headers, serializedBody);
                HeadersUtils.addUserSignature(headers, serializedBody, request.getUserPrivateKey());
                HeadersUtils.addContentType(headers, "application/json");

                try {
                        return ResponseUtils.prepareResponse(GetTransactionsResponse.class,
                                        API_CLIENT.send(ENDPOINT, serializedBody, headers));
                } catch (Exception e) {
                        return null;
                }
        }

}
