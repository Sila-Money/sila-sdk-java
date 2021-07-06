package com.silamoney.clientrefactored.domain;

import com.google.gson.annotations.SerializedName;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Address {

    @SerializedName("address_alias")
    private String addressAlias;
    @SerializedName("street_address_1")
    private String streetAddress1;
    @SerializedName("street_address_2")
    private String streetAddress2;
    private String city;
    private String state;
    private final String country = "US";
    @SerializedName("postal_code")
    private String postalCode;
    
}
