package com.silamoney.client.testsutils;

import java.math.BigInteger;

import com.github.tomakehurst.wiremock.extension.Parameters;
import com.github.tomakehurst.wiremock.http.Request;
import com.github.tomakehurst.wiremock.matching.MatchResult;
import com.github.tomakehurst.wiremock.matching.RequestMatcherExtension;

import org.web3j.crypto.ECKeyPair;
import org.web3j.crypto.Keys;

/**
 * Matches requests whose authsignature header is missing or was not made with the app private key
 * (see EcdsaUtil.sign), so the mock server can answer 401 like the real API.
 */
public class InvalidAuthSignatureMatcher extends RequestMatcherExtension {

    public static final String NAME = "invalid-auth-signature";

    private final String appAddress;

    public InvalidAuthSignatureMatcher(String appPrivateKey) {
        this.appAddress = Keys.getAddress(ECKeyPair.create(new BigInteger(appPrivateKey, 16)));
    }

    @Override
    public String getName() {
        return NAME;
    }

    @Override
    public MatchResult match(Request request, Parameters parameters) {
        String signature = request.getHeader("authsignature");
        if (signature == null || signature.isBlank()) {
            return MatchResult.exactMatch();
        }
        String signer = SignatureRecovery.signerAddress(signature, SignatureRecovery.signedBytes(request));
        return MatchResult.of(!appAddress.equals(signer));
    }
}
