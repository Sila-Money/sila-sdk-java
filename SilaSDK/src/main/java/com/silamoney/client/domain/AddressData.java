package com.silamoney.client.domain;

import com.google.gson.annotations.SerializedName;

import lombok.Getter;

public class AddressData extends RegistrationData {
    @Getter
    @SerializedName("nickname")
    private String nickname;
    @Getter
    @SerializedName("street_address_1")
    private String streetAddress1;
    @Getter
    @SerializedName("street_address_2")
    private String streetAddress2;
    @Getter
    @SerializedName("city")
    private String city;
    @Getter
    @SerializedName("state")
    private String state;
    @Getter
    @SerializedName("country")
    private String country;
    @Getter
    @SerializedName("postal_code")
    private String postalCode;
}
