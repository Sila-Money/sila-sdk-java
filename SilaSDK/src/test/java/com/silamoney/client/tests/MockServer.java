package com.silamoney.client.tests;

import org.junit.Rule;
import org.mockserver.client.MockServerClient;
import org.mockserver.junit.MockServerRule;
import org.mockserver.matchers.MatchType;
import org.mockserver.model.HttpRequest;
import org.mockserver.model.HttpResponse;
import org.mockserver.model.JsonBody;

/**
 * Sets the responses of the mock server.
 *
 * @author Karlo Lorenzana
 */
public class MockServer {

    /**
     * Sets the server for the check handle endpoint.
     */
    public static void checkHandleServer() {
        System.out.println("Mock Server init");
        new MockServerClient("localhost", 1080)
                .when(
                        HttpRequest.request()
                                .withMethod("POST")
                                .withPath("/check_handle")
                                .withBody(
                                        JsonBody.json(
                                                "{\\\"header\\\":{\\\"created\\\":12"
                                                + "34567890,\\\"auth_handle\\\":\\\"ha"
                                                + "ndle.silamoney.eth\\\",\\\"user_han"
                                                + "dle\\\":\\\"user.silamoney.eth\\\","
                                                + "\\\"version\\\":\\\"0.2\\\",\\\"cry"
                                                + "pto\\\":\\\"ETH\\\",\\\"reference\\"
                                                + "\":\\\"ref\\\"},\\\"message\\\":\\"
                                                + "\"header_msg\\\"}",
                                                MatchType.ONLY_MATCHING_FIELDS)
                                )
                )
                .respond(
                        HttpResponse.response()
                                .withStatusCode(200)
                                .withBody("{\\\"reference\\\":\\\"ref\\\",\\"
                                        + "\"message\\\":\\\"user.silamoney."
                                        + "eth is available.\\\",\\\"status\\"
                                        + "\":\\\"SUCCESS\\\"}")
                );
        System.out.println("Mock Server up");
    }
}
