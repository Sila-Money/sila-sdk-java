package com.silamoney.client.domain;

import lombok.Builder;
import lombok.Getter;

@Builder
public class AddressMessage {
    @Getter
    private String addressAlias;
    @Getter
    private String streetAddress1;
    @Getter
    private String streetAddress2;
    @Getter
    private String city;
    @Getter
    private String state;
    @Getter
    private String country;
    @Getter
    private String postalCode;
    @Getter
    private String uuid;
}
