package com.silamoney.client.domain;

import com.google.gson.annotations.SerializedName;

/**
 * Object used in the entity msg.
 *
 * @author Karlo Lorenzana
 */
public class Address extends EntityAudit{

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

    @SerializedName("nickname")
    private String nickname;

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

    /**
     * Constructor for address object.
     *
     * @param user
     */
    public Address(BusinessUser user) {
        this.addressAlias = "";
        this.streetAddress1 = user.getAddress();
        this.streetAddress2 = user.getAddress2();
        this.city = user.getCity();
        this.state = user.getState();
        this.country = CountryEnum.US.getValue();
        this.postalCode = user.getZipCode();
    }

    /**
     * @return the country
     */
    public String getCountry() {
        return country;
    }

    /**
     * @return the city
     */
    public String getCity() {
        return city;
    }

    /**
     * @return the addressAlias
     */
    public String getAddressAlias() {
        return addressAlias;
    }

    /**
     * @return the streetAddress1
     */
    public String getStreetAddress1() {
        return streetAddress1;
    }

    /**
     * @return the streetAddress2
     */
    public String getStreetAddress2() {
        return streetAddress2;
    }

    /**
     * @return the state
     */
    public String getState() {
        return state;
    }

    /**
     * @return the postalCode
     */
    public String getPostalCode() {
        return postalCode;
    }

    /**
     * @return the nickname
     */
    public String getNickname() {
        return nickname;
    }
}
