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

        public String getValue() {
            return value;
        }
    }

    /**
     * String field used for the country.
     */
    @SerializedName("country")
    public String country;

    /**
     * String field used for the city.
     */
    @SerializedName("city")
    public String city;

    /**
     * String field used for the address alias.
     */
    @SerializedName("address_alias")
    public String addressAlias;

    /**
     * String field used for the street address 1.
     */
    @SerializedName("street_address_1")
    public String streetAddress1;

    /**
     * String field used for the street address 2.
     */
    @SerializedName("street_address_2")
    public String streetAddress2;

    /**
     * String field used for the state.
     */
    @SerializedName("state")
    public String state;

    /**
     * String field used for the postal code.
     */
    @SerializedName("postal_code")
    public String postalCode;

    /**
     * Constructor for address object.
     *
     * @param user
     */
    public Address(User user) {
        this.addressAlias = "";
        this.streetAddress1 = user.address;
        this.streetAddress2 = user.address2;
        this.city = user.city;
        this.state = user.state;
        this.country = CountryEnum.US.getValue();
        this.postalCode = user.zipCode;
    }
}
