package com.silamoney.client.testsutils;

import java.security.SignatureException;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

import com.github.tomakehurst.wiremock.http.Request;

import org.web3j.crypto.Keys;
import org.web3j.crypto.Sign;
import org.web3j.utils.Numeric;

/**
 * Recovers the address that made a request signature (see EcdsaUtil.sign).
 */
final class SignatureRecovery {

    private static final int CACHE_SIZE = 256;

    // The same request can be checked by several stubs (a 403 stub and the global 401), so keep recent results.
    private static final Map<CacheKey, String> CACHE = Collections.synchronizedMap(
            new LinkedHashMap<CacheKey, String>(CACHE_SIZE, 0.75f, true) {
                private static final long serialVersionUID = 1L;

                @Override
                protected boolean removeEldestEntry(Map.Entry<CacheKey, String> eldest) {
                    return size() > CACHE_SIZE;
                }
            });

    private SignatureRecovery() {
    }

    /**
     * @return the bytes the SDK signed: the JSON "data" part for multipart uploads, else the body.
     */
    static byte[] signedBytes(Request request) {
        if (request.isMultipart()) {
            Request.Part data = request.getPart("data");
            if (data != null) {
                return data.getBody().asBytes();
            }
        }
        return request.getBody();
    }

    /**
     * @return the lowercase hex address (no 0x) that made the signature, or null if it is not a valid signature.
     */
    static String signerAddress(String signatureHex, byte[] message) {
        CacheKey key = new CacheKey(signatureHex, message);
        String cached = CACHE.get(key);
        if (cached != null) {
            return cached;
        }
        String address = recover(signatureHex, message);
        if (address != null) {
            CACHE.put(key, address);
        }
        return address;
    }

    static String normalize(String address) {
        String hex = Numeric.cleanHexPrefix(address);
        return hex == null ? null : hex.toLowerCase();
    }

    private static String recover(String signatureHex, byte[] message) {
        try {
            byte[] bytes = Numeric.hexStringToByteArray(signatureHex);
            if (bytes.length != 65) {
                return null;
            }
            Sign.SignatureData data = new Sign.SignatureData(bytes[64], Arrays.copyOfRange(bytes, 0, 32),
                    Arrays.copyOfRange(bytes, 32, 64));
            return Keys.getAddress(Sign.signedMessageToKey(message, data));
        } catch (SignatureException | IllegalArgumentException e) {
            return null;
        }
    }

    private static final class CacheKey {
        private final String signature;
        private final byte[] message;
        private final int hash;

        CacheKey(String signature, byte[] message) {
            this.signature = signature;
            this.message = message;
            this.hash = 31 * signature.hashCode() + Arrays.hashCode(message);
        }

        @Override
        public boolean equals(Object o) {
            if (!(o instanceof CacheKey)) {
                return false;
            }
            CacheKey other = (CacheKey) o;
            return Objects.equals(signature, other.signature) && Arrays.equals(message, other.message);
        }

        @Override
        public int hashCode() {
            return hash;
        }
    }
}
