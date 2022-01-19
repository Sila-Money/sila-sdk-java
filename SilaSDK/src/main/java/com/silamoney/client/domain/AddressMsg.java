package com.silamoney.client.domain;

import com.google.gson.annotations.SerializedName;

public class AddressMsg {
    @SerializedName("header")
    private HeaderBase header;
    @SerializedName("address_alias")
    private String addressAlias;
    @SerializedName("street_address_1")
    private String streetAddress1;
    @SerializedName("street_address_2")
    private String streetAddress2;
    @SerializedName("city")
    private String city;
    @SerializedName("state")
    private String state;
    @SerializedName("country")
    private String country;
    @SerializedName("postal_code")
    private String postalCode;
    @SerializedName("uuid")
    private String uuid;

    public AddressMsg(String authHandle, UserHandleMessage user, AddressMessage message) {
        this.header = new HeaderBuilder(authHandle).withUserHandle(user.getUserHandle()).withReference().build();
        this.addressAlias = message.getAddressAlias();
        this.streetAddress1 = message.getStreetAddress1();
        this.streetAddress2 = message.getStreetAddress2();
        this.city = message.getCity();
        this.state = message.getState();
        this.country = message.getCountry();
        this.postalCode = message.getPostalCode();
        this.uuid = message.getUuid();
    }
}
