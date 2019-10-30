package com.silamoney.client.config;

import com.silamoney.client.api.ApiClient;

/**
 *
 * @author Karlo Lorenzana
 */
public class Configuration {

    private String basePath;
    private String privateKey;
    private String authHandle;
    private ApiClient apiClient;
    private String userAgent;
    private int timeout;

    /**
     * Constructor for client configuration.
     *
     * @param basePath
     * @param privateKey
     * @param authHandle
     */
    public Configuration(String basePath, String privateKey, String authHandle) {
        this.basePath = basePath;
        this.privateKey = privateKey;
        this.authHandle = authHandle;

        this.timeout = 10000;

        this.apiClient = new ApiClient();
        this.apiClient.setBasePath(basePath);
    }

    /**
     * Gets the environment base path.
     *
     * @return base path set in the client configuration.
     */
    public String getBasePath() {
        return basePath;
    }

    /**
     * Sets the environment base path.
     *
     * @param basePath
     */
    public void setBasePath(String basePath) {
        this.basePath = basePath;
        this.apiClient.setBasePath(basePath);
    }

    /**
     * Gets the configured private key.
     *
     * @return app handle private key
     */
    public String getPrivateKey() {
        return privateKey;
    }

    /**
     * Sets the app handle private key.
     *
     * @param privateKey
     */
    public void setPrivateKey(String privateKey) {
        this.privateKey = privateKey;
    }

    /**
     * Gets the configured app handle.
     *
     * @return the app handle
     */
    public String getAuthHandle() {
        return authHandle;
    }

    /**
     * Sets the app handle.
     *
     * @param authHandle
     */
    public void setAuthHandle(String authHandle) {
        this.authHandle = authHandle;
    }

    /**
     * Gets the configured api client.
     *
     * @return the api client.
     */
    public ApiClient getApiClient() {
        return apiClient;
    }

    /**
     * Sets the api client.
     *
     * @param apiClient
     */
    public void setApiClient(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    /**
     * Gets the configured user agent.
     *
     * @return the configured user agent.
     */
    public String getUserAgent() {
        return userAgent;
    }

    /**
     * Sets the user agent.
     *
     * @param userAgent
     */
    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }

    /**
     * Gets the configured timeout.
     *
     * @return the configured timeout.
     */
    public int getTimeout() {
        return timeout;
    }

    /**
     * Sets the calls timeout.
     *
     * @param timeout
     */
    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }
}
