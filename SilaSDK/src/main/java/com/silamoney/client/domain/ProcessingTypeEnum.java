package com.silamoney.client.domain;

public enum ProcessingTypeEnum {
    STANDARD("STANDARD_ACH"), SAME_DAY("SAME_DAY_ACH"), INSTANT_ACH("INSTANT_ACH"),CARD("CARD"),INSTANT_SETTLEMENT("INSTANT_SETTLEMENT");

    private final String value;

    ProcessingTypeEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
