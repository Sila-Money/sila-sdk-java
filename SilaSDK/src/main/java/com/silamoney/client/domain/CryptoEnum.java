package com.silamoney.client.domain;

public enum CryptoEnum {
    ETH("ETH");

    private String value;

    private CryptoEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
