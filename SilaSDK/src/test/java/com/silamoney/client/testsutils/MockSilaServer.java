package com.silamoney.client.testsutils;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.any;
import static com.github.tomakehurst.wiremock.client.WireMock.anyUrl;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.options;

import com.github.tomakehurst.wiremock.WireMockServer;

/**
 * Local stand-in for the Sila API so tests run without network access or sandbox state.
 *
 * Stubs are loaded from src/test/resources/wiremock/mappings, response bodies from
 * src/test/resources/wiremock/__files. One server is shared by every test in the JVM.
 */
public class MockSilaServer {

    private static WireMockServer server;

    private MockSilaServer() {
    }

    /**
     * Starts the mock server on first use.
     *
     * @return base url the SDK should send requests to.
     */
    public static synchronized String getBaseUrl() {
        if (server == null) {
            server = new WireMockServer(options()
                    .dynamicPort()
                    .usingFilesUnderClasspath("wiremock")
                    .extensions(new InvalidAuthSignatureMatcher()));
            server.start();
            // Like the real API, reject any request that is not signed with the app private key.
            server.stubFor(any(anyUrl())
                    .atPriority(1)
                    .andMatching(InvalidAuthSignatureMatcher.NAME)
                    .willReturn(aResponse()
                            .withStatus(401)
                            .withHeader("Content-Type", "application/json")
                            .withBodyFile("common/401_invalid_auth_signature.json")));
            Runtime.getRuntime().addShutdownHook(new Thread(server::stop));
        }
        return server.baseUrl();
    }
}
