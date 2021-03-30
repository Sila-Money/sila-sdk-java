package com.silamoney.clientrefactored.configuration;

public enum Environment {
    SANDBOX("https://sandbox.silamoney.com/0.2"), PRODUCTION("https://api.silamoney.com/0.2"),
    STAGING("https://stageapi.silamoney.com/0.2");

    private final String url;

    /**
     * Gets the selected environment url.
     *
     * @return environment url.
     */
    public String getUrl() {
        return url;
    }

    private Environment(String url) {
        this.url = url;
    }
}
