package com.silamoney.clientrefactored.utils;

import java.util.Date;

public class EpochUtils {

    private EpochUtils() {
    }

    public static int getEpochTime() {
        return (int) (((new Date()).getTime() / 1000) - 100);
    }

}
