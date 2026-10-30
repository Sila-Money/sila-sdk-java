package com.silamoney.client.testsutils;

import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.options;

import com.github.tomakehurst.wiremock.WireMockServer;

/**
 * Local stand-in for the Sila API so tests run without network access or sandbox state.
 *
 * Stubs are loaded from src/test/resources/sila-mock/mappings, response bodies from
 * src/test/resources/sila-mock/__files. One server is shared by every test in the JVM.
 */
public class MockSilaServer {

    private static WireMockServer server;
    private static UserAddressRegistry registry;

    private MockSilaServer() {
    }

    /**
     * Starts the mock server on first use.
     *
     * @param appPrivateKey the key the app signs requests with; other authsignatures get a 401.
     * @return base url the SDK should send requests to.
     */
    public static synchronized String getBaseUrl(String appPrivateKey) {
        if (server == null) {
            UserAddressRegistry addresses = new UserAddressRegistry();
            WireMockServer started = null;
            try {
                // The constructor loads the mappings, so a bad mapping file fails here.
                started = new WireMockServer(options()
                        .dynamicPort()
                        .usingFilesUnderClasspath("sila-mock")
                        .maxRequestJournalEntries(100)
                        .extensions(new InvalidAuthSignatureMatcher(appPrivateKey),
                                new InvalidUserSignatureMatcher(addresses),
                                addresses));
                started.start();
            } catch (RuntimeException e) {
                if (started != null) {
                    started.stop();
                }
                throw new IllegalStateException("Mock Sila server did not start (check the sila-mock mappings)", e);
            }
            WireMockServer running = started;
            Runtime.getRuntime().addShutdownHook(new Thread(running::stop));
            registry = addresses;
            server = running;
        }
        return server.baseUrl();
    }

    /**
     * Registers a user with the mock server, as if it had called /register. Does nothing when the
     * mock server is not in use (SILA_TEST_HOST is set).
     *
     * @param handle the user handle.
     * @param cryptoAddress the address of the key the user signs requests with.
     */
    public static synchronized void registerUser(String handle, String cryptoAddress) {
        if (registry != null) {
            registry.register(handle, cryptoAddress);
        }
    }
}
