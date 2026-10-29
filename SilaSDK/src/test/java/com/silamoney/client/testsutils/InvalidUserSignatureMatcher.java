package com.silamoney.client.testsutils;

import java.util.Set;

import com.github.tomakehurst.wiremock.extension.Parameters;
import com.github.tomakehurst.wiremock.http.Request;
import com.github.tomakehurst.wiremock.matching.MatchResult;
import com.github.tomakehurst.wiremock.matching.RequestMatcherExtension;

/**
 * Matches requests whose usersignature header was not made with a key registered for the
 * header's user_handle, so the mock server can answer 401 like the real API. A handle that never
 * registered has no keys, so its requests always match.
 *
 * The DefaultConfigurations users are registered when that class loads, so their checks also work when a
 * test class runs alone. A blank handle is not checked: the per-endpoint stubs answer it with a 400.
 * Requests to UNCHECKED_PATHS are not checked either.
 */
public class InvalidUserSignatureMatcher extends RequestMatcherExtension {

    public static final String NAME = "invalid-user-signature";

    // The sandbox answers these with a 200 for any usersignature: for a handle that never registered, and
    // also with a registered user's statements for a wrong key (checked 2026-10-29, /statements too). Their
    // tests use handles that never registered and expect that 200. /statements is checked here, because
    // its tests sign as the seeded DefaultConfigurations.statementsUserHandle.
    private static final Set<String> UNCHECKED_PATHS = Set.of("/get_statements_data",
            "/get_wallet_statement_data");

    private final UserAddressRegistry registry;

    public InvalidUserSignatureMatcher(UserAddressRegistry registry) {
        this.registry = registry;
    }

    @Override
    public String getName() {
        return NAME;
    }

    @Override
    public MatchResult match(Request request, Parameters parameters) {
        String signature = request.getHeader("usersignature");
        if (signature == null || signature.isBlank() || UNCHECKED_PATHS.contains(path(request.getUrl()))) {
            return MatchResult.noMatch();
        }
        byte[] signed = SignatureRecovery.signedBytes(request);
        String handle = UserAddressRegistry.userHandle(signed);
        if (handle == null || handle.isBlank()) {
            return MatchResult.noMatch();
        }
        String signer = SignatureRecovery.signerAddress(signature, signed);
        return MatchResult.of(signer == null || !registry.hasAddress(handle, signer));
    }

    private static String path(String url) {
        int query = url.indexOf('?');
        return query < 0 ? url : url.substring(0, query);
    }
}
