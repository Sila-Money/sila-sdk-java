package com.silamoney.client.util;

import java.util.Date;

public class EpochUtils {

    public static int getEpoch(){
        return (int) (((new Date()).getTime() / 1000) - 100);
    }
    
}