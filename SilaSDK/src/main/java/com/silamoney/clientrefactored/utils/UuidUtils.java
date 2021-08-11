package com.silamoney.clientrefactored.utils;

import java.util.UUID;

public class UuidUtils {
    private UuidUtils() {
    }

    public static String generateRandomUuid() {
        UUID uuid = UUID.randomUUID();
        return uuid.toString();
    }
}
