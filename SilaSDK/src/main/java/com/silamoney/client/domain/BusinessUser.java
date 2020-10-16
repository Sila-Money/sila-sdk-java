package com.silamoney.client.domain;

import io.reactivex.annotations.Nullable;
import lombok.Getter;

/**
 * Class used in the registerBusiness method.
 *
 * @author Karlo Lorenzana
 */
public class BusinessUser {
    @Getter
    private final String handle;
    @Getter
    private final String addressAlias;
    @Getter
    private final String address;
    @Getter
    private final String address2;
    @Getter
    private final String city;
    @Getter
    private final String state;
    @Getter
    private final String zipCode;
    @Getter
    private final String phone;
    @Getter
    private final String email;
    @Getter
    private final String identityValue;
    @Getter
    private final String cryptoAddress;
    @Getter
    private final String entityName;
    @Getter
    private final BusinessType businessType;
    @Getter
    private final String businessWebsite;
    @Getter
    private final String doingBusinessAs;
    @Getter
    private final NaicsCategoryDescription naicsCategory;
    @Getter
    private final String country;

    public BusinessUser(String handle, String addressAlias, String address, @Nullable String address2, String city,
            String state, String zipCode, String phone, String email, String identityValue, String cryptoAddress,
            String entityName, BusinessType businessType, String businessWebsite, String doingBusinessAs,
            NaicsCategoryDescription naicsCategory) {
        this.handle = handle;
        this.addressAlias = addressAlias;
        this.address = address;
        this.address2 = address2;
        this.city = city;
        this.state = state;
        this.zipCode = zipCode;
        this.phone = phone;
        this.email = email;
        this.identityValue = identityValue;
        this.cryptoAddress = cryptoAddress;
        this.entityName = entityName;
        this.businessType = businessType;
        this.businessWebsite = businessWebsite;
        this.doingBusinessAs = doingBusinessAs;
        this.naicsCategory = naicsCategory;
        this.country = "US";
    }

    public BusinessUser(String handle, String addressAlias, String address, @Nullable String address2, String city,
            String state, String zipCode, String phone, String email, String identityValue, String cryptoAddress,
            String entityName, BusinessType businessType, String businessWebsite, String doingBusinessAs,
            NaicsCategoryDescription naicsCategory, String country) {
        this.handle = handle;
        this.addressAlias = addressAlias;
        this.address = address;
        this.address2 = address2;
        this.city = city;
        this.state = state;
        this.zipCode = zipCode;
        this.phone = phone;
        this.email = email;
        this.identityValue = identityValue;
        this.cryptoAddress = cryptoAddress;
        this.entityName = entityName;
        this.businessType = businessType;
        this.businessWebsite = businessWebsite;
        this.doingBusinessAs = doingBusinessAs;
        this.naicsCategory = naicsCategory;
        this.country = country;
    }
}
