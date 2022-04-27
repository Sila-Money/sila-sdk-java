package com.silamoney.clientrefactored.endpoints.accounts.getinstitutions;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import com.silamoney.client.api.ApiResponse;
import com.silamoney.clientrefactored.domain.Header;
import com.silamoney.clientrefactored.endpoints.AbstractEndpoint;
import com.silamoney.clientrefactored.utils.EpochUtils;
import com.silamoney.clientrefactored.utils.HeadersUtils;
import com.silamoney.clientrefactored.utils.JsonUtils;
import com.silamoney.clientrefactored.utils.ResponseUtils;
import com.silamoney.clientrefactored.utils.UuidUtils;

public class GetInstitutions extends AbstractEndpoint {

        private static Logger logger = Logger.getLogger(GetInstitutions.class.getName());

        private static final String ENDPOINT = "/get_institutions";

        private GetInstitutions() {
        }
        public static ApiResponse send(GetInstitutionsRequest request) {
                return sendData(request);
        }

        public static ApiResponse send() {
                return sendData(null);
        }

        public static ApiResponse sendData(GetInstitutionsRequest request) {
                Map<String, Object> body = new HashMap<>();
                body.put("header", Header.builder().appHandle(APP_HANDLE)
                        .created(EpochUtils.getEpochTime()).reference(UuidUtils.generateRandomUuid()).build());
                body.put("message", "header_msg");
                if (request != null)
                        body.put("search_filters", request.getSearchFilters());

                String serializedBody = JsonUtils.serialize(body);

                Map<String, String> headers = new HashMap<>();
                HeadersUtils.addAuthSignature(headers, serializedBody);
                HeadersUtils.addContentType(headers, "application/json");

                try {
                        return ResponseUtils.prepareResponse(GetInstitutionsResponse.class,
                                API_CLIENT.send(ENDPOINT, serializedBody, headers));
                } catch (Exception e) {
                        return null;
                }
        }


}
