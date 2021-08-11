package com.silamoney.clientrefactored.utils;

import java.util.Map;

import com.silamoney.clientrefactored.configuration.SilaApi;

public class HeadersUtils {

    private HeadersUtils() {
    }

    public static Map<String, String> addContentType(Map<String, String> headers, String contentType) {
        headers.put("Content-Type", contentType);
        return headers;
    }

    public static Map<String, String> addAuthSignature(Map<String, String> headers, String body) {
        headers.put("authsignature", SignatureUtils.sign(body, SilaApi.getInstance().getPrivateKey()));
        return headers;
    }

    public static Map<String, String> addUserSignature(Map<String, String> headers, String body, String privateKey) {
        headers.put("usersignature", SignatureUtils.sign(body, privateKey));
        return headers;
    }

}
