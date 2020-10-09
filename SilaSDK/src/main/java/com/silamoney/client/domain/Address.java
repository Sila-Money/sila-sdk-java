package com.silamoney.client.domain;

import com.google.gson.annotations.SerializedName;

import lombok.Getter;

/**
 * Object used in the entity msg.
 *
 * @author Karlo Lorenzana
 */
public class Address extends EntityAudit {
    @Getter
    @SerializedName("country")
    private final String country;
    @Getter
    @SerializedName("city")
    private final String city;
    @Getter
    @SerializedName("address_alias")
    private final String addressAlias;
    @Getter
    @SerializedName("street_address_1")
    private final String streetAddress1;
    @Getter
    @SerializedName("street_address_2")
    private final String streetAddress2;
    @Getter
    @SerializedName("state")
    private final String state;
    @Getter
    @SerializedName("postal_code")
    private final String postalCode;
    @Getter
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
        this.country = user.getCountry();
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
        this.country = user.getCountry();
        this.postalCode = user.getZipCode();
    }
}
