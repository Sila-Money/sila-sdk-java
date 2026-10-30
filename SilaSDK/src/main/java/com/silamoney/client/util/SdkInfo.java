package com.silamoney.client.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * SDK identity sent to the API. The version is copied from the pom into
 * silamoney-sdk.properties at build time, so it always matches the release.
 */
public final class SdkInfo {

    public static final String PRODUCT = "SilaSDK-java";
    public static final String VERSION = loadVersion();
    public static final String USER_AGENT = PRODUCT + '/' + VERSION;

    private SdkInfo() {
    }

    private static String loadVersion() {
        try (InputStream in = SdkInfo.class.getResourceAsStream("/silamoney-sdk.properties")) {
            if (in == null) {
                return "unknown";
            }
            Properties properties = new Properties();
            properties.load(in);
            return properties.getProperty("version", "unknown");
        } catch (IOException e) {
            return "unknown";
        }
    }
}
