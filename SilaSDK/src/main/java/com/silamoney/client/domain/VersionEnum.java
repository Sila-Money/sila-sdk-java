package com.silamoney.client.domain;

public enum VersionEnum {
    V0_2("0.2");

    private String value;

    private VersionEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
