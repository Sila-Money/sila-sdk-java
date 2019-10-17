package com.silamoney.client.tests;

import org.mockserver.client.MockServerClient;
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
        //<editor-fold defaultstate="collapsed" desc="Response 200 success">
        new MockServerClient("localhost", 1080)
                .when(
                        HttpRequest.request()
                                .withMethod("POST")
                                .withPath("/check_handle")
                                .withBody(
                                        JsonBody.json(
                                                "{\"header\":"
                                                + "{"
                                                + "\"auth_handle\":\"handle.silamoney.eth\","
                                                + "\"user_handle\":\"user.silamoney.eth\","
                                                + "\"version\":\"0.2\","
                                                + "\"crypto\":\"ETH\","
                                                + "\"reference\":\"ref\""
                                                + "}"
                                                + "}",
                                                MatchType.ONLY_MATCHING_FIELDS)
                                )
                )
                .respond(
                        HttpResponse.response()
                                .withStatusCode(200)
                                .withBody("{\"reference\":\"ref\","
                                        + "\"message\":\"user.silamoney."
                                        + "eth is available.\",\"status"
                                        + "\":\"SUCCESS\"}")
                );
        //</editor-fold>
        //<editor-fold defaultstate="collapsed" desc="Response 200 failure">
        new MockServerClient("localhost", 1080)
                .when(
                        HttpRequest.request()
                                .withMethod("POST")
                                .withPath("/check_handle")
                                .withBody(
                                        JsonBody.json(
                                                "{\"header\":"
                                                + "{"
                                                + "\"auth_handle\":\"handle.silamoney.eth\","
                                                + "\"user_handle\":\"taken.silamoney.eth\","
                                                + "\"version\":\"0.2\","
                                                + "\"crypto\":\"ETH\","
                                                + "\"reference\":\"ref\""
                                                + "}"
                                                + "}",
                                                MatchType.ONLY_MATCHING_FIELDS)
                                )
                )
                .respond(
                        HttpResponse.response()
                                .withStatusCode(200)
                                .withBody("{\"reference\":\"ref\","
                                        + "\"message\":\"taken.silamoney."
                                        + "eth is already taken.\",\"status"
                                        + "\":\"FAILURE\"}")
                );
        //</editor-fold>
        //<editor-fold defaultstate="collapsed" desc="Response 400">
        new MockServerClient("localhost", 1080)
                .when(
                        HttpRequest.request()
                                .withMethod("POST")
                                .withPath("/check_handle")
                                .withBody(
                                        JsonBody.json(
                                                "{\"header\":"
                                                + "{"
                                                + "\"auth_handle\":\"handle.silamoney.eth\","
                                                + "\"user_handle\":\"badrequest.silamoney.eth\","
                                                + "\"version\":\"0.2\","
                                                + "\"crypto\":\"ETH\","
                                                + "\"reference\":\"ref\""
                                                + "}"
                                                + "}",
                                                MatchType.ONLY_MATCHING_FIELDS)
                                )
                )
                .respond(
                        HttpResponse.response()
                                .withStatusCode(400)
                                .withBody("{\"reference\":\"ref\","
                                        + "\"message\":\"Bad request message.\","
                                        + "\"status\":\"FAILURE\"}")
                );
        //</editor-fold>
        //<editor-fold defaultstate="collapsed" desc="Response 401">
        new MockServerClient("localhost", 1080)
                .when(
                        HttpRequest.request()
                                .withMethod("POST")
                                .withPath("/check_handle")
                                .withBody(
                                        JsonBody.json(
                                                "{\"header\":"
                                                + "{"
                                                + "\"auth_handle\":\"handle.silamoney.eth\","
                                                + "\"user_handle\":\"invalidsignature.silamoney.eth\","
                                                + "\"version\":\"0.2\","
                                                + "\"crypto\":\"ETH\","
                                                + "\"reference\":\"ref\""
                                                + "}"
                                                + "}",
                                                MatchType.ONLY_MATCHING_FIELDS)
                                )
                )
                .respond(
                        HttpResponse.response()
                                .withStatusCode(401)
                                .withBody("{\"reference\":\"ref\","
                                        + "\"message\":\"Invalid signature message.\","
                                        + "\"status\":\"FAILURE\"}")
                );
        //</editor-fold>
    }
}
