package com.silamoney.clientrefactored.endpoints;

import com.silamoney.clientrefactored.apiclient.ApiClient;
import com.silamoney.clientrefactored.configuration.SilaApi;

public abstract class AbstractEndpoint {

    public static final ApiClient API_CLIENT = SilaApi.getInstance().getApiClient();
    public static final String APP_HANDLE = SilaApi.getInstance().getAuthHandle();
    public static final String PRIVATE_KEY = SilaApi.getInstance().getPrivateKey();

}
