package com.silamoney.clientrefactored.configuration;

import java.util.logging.Level;
import java.util.logging.Logger;

import com.silamoney.clientrefactored.apiclient.ApiClient;

import lombok.Getter;

public class SilaApi {

    private static Logger logger = Logger.getLogger(SilaApi.class.getName());
    private static final String INIT_ERROR = "SilaApi is not yet initialized (run the init method).";

    @Getter
    private String authHandle;
    @Getter
    private String privateKey;
    @Getter
    private ApiClient apiClient;

    private static SilaApi instance;

    private SilaApi(Environment environment, String authHandle, String privateKey, int timeout) {
        this.authHandle = authHandle;
        this.privateKey = privateKey;
        this.apiClient = new ApiClient(environment);
        if (timeout > 0)
            this.apiClient.setTimeout(timeout);
    }

    public static void init(Environment environment, String authHandle, String privateKey) {
        if (instance != null)
            logger.log(Level.WARNING, "SilaApi already initialized.");
        else {
            instance = new SilaApi(environment, authHandle, privateKey,0);
        }
    }
    public static void init(Environment environment, String authHandle, String privateKey, int timeout) {
        if (instance != null)
            logger.log(Level.WARNING, "SilaApi already initialized.");
        else {
            instance = new SilaApi(environment, authHandle, privateKey, timeout);
        }
    }
    public static void dispose() {
        instance = null;
    }

    public static SilaApi getInstance() {
        if (instance == null) {
            logger.log(Level.SEVERE, INIT_ERROR);
        }
        return instance;
    }

}
