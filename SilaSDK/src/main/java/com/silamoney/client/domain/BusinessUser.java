package com.silamoney.client.domain;

import io.reactivex.annotations.Nullable;

/**
 * Class used in the registerBusiness method.
 *
 * @author Karlo Lorenzana
 */
public class BusinessUser {

    private final String handle;

    private final String addressAlias;

    private final String address;

    private final String address2;

    private final String city;

    private final String state;

    private final String zipCode;

    private final String phone;

    private final String email;

    private final String identityValue;

    private final String cryptoAddress;

    private final String entityName;

    private final BusinessType businessType;

    private final String businessWebsite;

    private final String doingBusinessAs;

    private final NaicsCategoryDescription naicsCategory;

    public BusinessUser(String handle, String addressAlias, String address, @Nullable String address2, String city, String state,
            String zipCode, String phone, String email, String identityValue, String cryptoAddress, String entityName,
            BusinessType businessType, String businessWebsite, String doingBusinessAs,
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
    }

    /**
     * @return the handle
     */
    public String getHandle() {
        return handle;
    }

    /**
     * @return the address
     */
    public String getAddress() {
        return address;
    }

    /**
     * @return the address2
     */
    public String getAddress2() {
        return address2;
    }

    /**
     * @return the city
     */
    public String getCity() {
        return city;
    }

    /**
     * @return the state
     */
    public String getState() {
        return state;
    }

    /**
     * @return the zipCode
     */
    public String getZipCode() {
        return zipCode;
    }

    /**
     * @return the phone
     */
    public String getPhone() {
        return phone;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @return the identityValue
     */
    public String getIdentityValue() {
        return identityValue;
    }

    /**
     * @return the cryptoAddress
     */
    public String getCryptoAddress() {
        return cryptoAddress;
    }

    /**
     * @return the entityName
     */
    public String getEntityName() {
        return entityName;
    }

    /**
     * @return the businessType
     */
    public BusinessType getBusinessType() {
        return businessType;
    }

    /**
     * @return the businessWebsite
     */
    public String getBusinessWebsite() {
        return businessWebsite;
    }

    /**
     * @return the doingBusinessAs
     */
    public String getDoingBusinessAs() {
        return doingBusinessAs;
    }

    /**
     * @return the naicsCategory
     */
    public NaicsCategoryDescription getNaicsCategory() {
        return naicsCategory;
    }

    /**
     * @return the addressAlias
     */
    public String getAddressAlias() {
        return addressAlias;
    }

}
