package com.silamoney.client.testsutils;

import com.github.tomakehurst.wiremock.extension.Parameters;
import com.github.tomakehurst.wiremock.http.Request;
import com.github.tomakehurst.wiremock.matching.MatchResult;
import com.github.tomakehurst.wiremock.matching.RequestMatcherExtension;

/**
 * Matches requests whose usersignature header was not made with a key registered for the
 * header's user_handle, so the mock server can answer 401 like the real API.
 *
 * The DefaultConfigurations users are registered when that class loads, so their checks also work when a
 * test class runs alone. Other handles are checked only after they register through /register.
 */
public class InvalidUserSignatureMatcher extends RequestMatcherExtension {

    public static final String NAME = "invalid-user-signature";

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
        if (signature == null || signature.isBlank()) {
            return MatchResult.noMatch();
        }
        byte[] signed = SignatureRecovery.signedBytes(request);
        String handle = UserAddressRegistry.userHandle(signed);
        if (handle == null || !registry.isRegistered(handle)) {
            return MatchResult.noMatch();
        }
        String signer = SignatureRecovery.signerAddress(signature, signed);
        return MatchResult.of(signer == null || !registry.hasAddress(handle, signer));
    }
}
