package com.silamoney.client.domain;

import com.google.gson.annotations.SerializedName;

/**
 * Object used in the entity msg.
 *
 * @author Karlo Lorenzana
 */
public class Address {

    private enum CountryEnum {
        US("US");

        private final String value;

        CountryEnum(String value) {
            this.value = value;
        }

        private String getValue() {
            return value;
        }
    }

    @SerializedName("country")
    private final String country;

    @SerializedName("city")
    private final String city;

    @SerializedName("address_alias")
    private final String addressAlias;

    @SerializedName("street_address_1")
    private final String streetAddress1;

    @SerializedName("street_address_2")
    private final String streetAddress2;

    @SerializedName("state")
    private final String state;

    @SerializedName("postal_code")
    private final String postalCode;

    /**
     * Constructor for address object.
     *
     * @param user
     */
    public Address(User user) {
        this.addressAlias = "";
        this.streetAddress1 = user.getAddress();
        this.streetAddress2 = user.getAddress2();
        this.city = user.getCity();
        this.state = user.getState();
        this.country = CountryEnum.US.getValue();
        this.postalCode = user.getZipCode();
    }
}
