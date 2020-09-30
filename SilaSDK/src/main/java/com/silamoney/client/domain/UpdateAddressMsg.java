package com.silamoney.client.domain;

import com.google.gson.annotations.SerializedName;

import java.util.Map;

public class UpdateAddressMsg {

    @SerializedName("address_alias")
    private final String addressAlias;

    @SerializedName("street_address_1")
    private final String address;

    @SerializedName("street_address_2")
    private final String address2;

    @SerializedName("city")
    private final String city;

    @SerializedName("state")
    private final String state;

    @SerializedName("postal_code")
    private final String zipCode;

    @SerializedName("country")
    private final String country;

    @SerializedName("uuid")
    private final String uuid;

    @SerializedName("header")
    private final Map<String, String> header;

    /**
     * Constructor for user object.
     *
     * @param addressAlias
     * @param address
     * @param address2
     * @param city
     * @param state
     * @param zipCode
     * @param uuid
     * @param header
     */
    public UpdateAddressMsg(String addressAlias, String address, String address2, String city, String state, String zipCode, String country, String uuid, Map<String, String> header) {
        this.addressAlias = addressAlias;
        this.address = address;
        this.address2 = address2;
        this.city = city;
        this.state = state;
        this.zipCode = zipCode;
        this.country = country;
        this.uuid = uuid;
        this.header = header;
    }
}
