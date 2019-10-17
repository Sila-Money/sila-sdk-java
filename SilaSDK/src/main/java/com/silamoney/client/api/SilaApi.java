package com.silamoney.client.api;

import com.silamoney.client.config.Configuration;
import com.silamoney.client.domain.Environments;
import com.silamoney.client.domain.HeaderMsg;
import com.silamoney.client.exceptions.BadRequestException;
import com.silamoney.client.exceptions.InvalidSignatureException;
import com.silamoney.client.exceptions.ServerSideException;
import com.silamoney.client.security.EcdsaUtil;
import com.silamoney.client.util.ResponseUtil;
import com.silamoney.client.util.Serialization;
import java.io.IOException;
import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Karlo Lorenzana
 */
public class SilaApi {

    private final Configuration configuration;
    private final String AUTH_SIGNATURE = "authsignature";
    private final String USER_SIGNATURE = "usersignature";
    private final String defaultEnvironment = Environments.SilaEnvironment.SANDBOX.getUrl();

    /**
     * Constructor for SilaApi using custom environment.
     *
     * @param environment
     * @param appHandler
     * @param privateKey
     */
    public SilaApi(String environment, String appHandler, String privateKey) {
        this.configuration = new Configuration(environment, privateKey, appHandler);
    }

    /**
     * Constructor for SilaApi using specified environment.
     *
     * @param environment
     * @param appHandler
     * @param privateKey
     */
    public SilaApi(Environments.SilaEnvironment environment, String appHandler, String privateKey) {
        this.configuration = new Configuration(environment.getUrl(), privateKey, appHandler);
    }

    /**
     * Constructor for SilaApi using sandbox environment.
     *
     * @param appHandler
     * @param privateKey
     */
    public SilaApi(String appHandler, String privateKey) {
        this.configuration = new Configuration(defaultEnvironment, privateKey, appHandler);
    }

    /**
     * Checks if a specific handle is already taken.
     *
     * @param handle
     * @return API response.
     * @throws com.silamoney.client.exceptions.BadRequestException
     * @throws com.silamoney.client.exceptions.InvalidSignatureException
     * @throws com.silamoney.client.exceptions.ServerSideException
     * @throws java.io.IOException
     * @throws java.lang.InterruptedException
     */
    public ApiResponse CheckHandle(String handle) throws 
            BadRequestException, 
            InvalidSignatureException, 
            ServerSideException, 
            IOException, 
            InterruptedException {
        HeaderMsg body = new HeaderMsg(handle,
                this.configuration.getAuthHandle());
        String path = "/check_handle";
        String _body = Serialization.serialize(body);
        Map<String, String> headers = new HashMap<>();

        headers.put(AUTH_SIGNATURE, EcdsaUtil.sign(_body,
                this.configuration.getPrivateKey()));

        HttpResponse response = this.configuration.getApiClient()
                .CallApi(path, headers, _body);

        return ResponseUtil.prepareResponse(response);
    }
}
