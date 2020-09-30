package com.silamoney.client.domain;

public enum ProcessingTypeEnum {
    STANDARD("STANDARD_ACH"), SAME_DAY("SAME_DAY_ACH");

    private final String value;

    ProcessingTypeEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
