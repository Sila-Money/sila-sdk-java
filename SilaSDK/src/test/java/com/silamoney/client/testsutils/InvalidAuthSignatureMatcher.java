package com.silamoney.client.testsutils;

import java.math.BigInteger;
import java.util.Arrays;

import com.github.tomakehurst.wiremock.extension.Parameters;
import com.github.tomakehurst.wiremock.http.Request;
import com.github.tomakehurst.wiremock.matching.MatchResult;
import com.github.tomakehurst.wiremock.matching.RequestMatcherExtension;

import org.web3j.crypto.ECKeyPair;
import org.web3j.crypto.Keys;
import org.web3j.crypto.Sign;
import org.web3j.utils.Numeric;

/**
 * Matches requests whose authsignature header was not made with the app private key
 * (see EcdsaUtil.sign), so the mock server can answer 401 like the real API.
 */
public class InvalidAuthSignatureMatcher extends RequestMatcherExtension {

    public static final String NAME = "invalid-auth-signature";

    private String appAddress;

    @Override
    public String getName() {
        return NAME;
    }

    @Override
    public MatchResult match(Request request, Parameters parameters) {
        String signature = request.getHeader("authsignature");
        if (signature == null || signature.isBlank()) {
            return MatchResult.noMatch();
        }
        // For multipart uploads the SDK signs only the JSON "data" part.
        String signed = request.isMultipart() && request.getPart("data") != null
                ? request.getPart("data").getBody().asString()
                : request.getBodyAsString();
        return MatchResult.of(!appAddress().equals(signerAddress(signature, signed)));
    }

    // Read the key on first use: this class is created while DefaultConfigurations is still initializing.
    private synchronized String appAddress() {
        if (appAddress == null) {
            appAddress = Keys.getAddress(ECKeyPair.create(new BigInteger(DefaultConfigurations.privateKey, 16)));
        }
        return appAddress;
    }

    private static String signerAddress(String signature, String message) {
        try {
            byte[] bytes = Numeric.hexStringToByteArray(signature);
            Sign.SignatureData data = new Sign.SignatureData(bytes[64], Arrays.copyOfRange(bytes, 0, 32),
                    Arrays.copyOfRange(bytes, 32, 64));
            return Keys.getAddress(Sign.signedMessageToKey(message.getBytes(), data));
        } catch (Exception e) {
            return null;
        }
    }
}
