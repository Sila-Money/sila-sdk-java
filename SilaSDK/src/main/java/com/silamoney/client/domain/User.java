package com.silamoney.client.domain;

import io.reactivex.annotations.Nullable;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * Class used in the register method.
 *
 * @author Karlo Lorenzana
 */
public class User {
    @Getter
    private final String handle;
    @Getter
    private final String firstName;
    @Getter
    private final String lastName;
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
    private final String identityNumber;
    @Getter
    private final String cryptoAddress;
    @Getter
    private final Date birthdate;
    @Getter
    private final String country;
    /**
     * smsOptIn will be removed in the next version of this SDK.
     * Please remove all usages of this variable from your system.
     */
    @Getter
    @Deprecated(forRemoval = true)
    private final boolean smsOptIn;
    @Getter
    @Setter
    private String reference;
    @Getter
    @Setter
    private String docType;
    @Getter
    @Setter
    private String docId;
    @Getter
    @Setter
    private String docState;
    @Getter
    @Setter
    private String docCountry;

    /**
     * Constructor for user object.
     * docType, docId, and docState or docCountry are only needed for users who
     * will be linked as business members.
     *
     * @param handle
     * @param firstName
     * @param lastName
     * @param address
     * @param address2
     * @param city
     * @param state
     * @param zipCode
     * @param phone
     * @param email
     * @param identityNumber
     * @param cryptoAddress
     * @param birthdate
     */
    public User(String handle, String firstName, String lastName, String address, @Nullable String address2,
            String city, String state, String zipCode, String phone, String email, String identityNumber,
            String cryptoAddress, Date birthdate) {
        this.handle = handle;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.address2 = address2;
        this.city = city;
        this.state = state;
        this.zipCode = zipCode;
        this.phone = phone;
        this.email = email;
        this.identityNumber = identityNumber;
        this.cryptoAddress = cryptoAddress;
        this.birthdate = birthdate;
        this.country = "US";
        this.smsOptIn = false;
    }

    public User(String handle, String firstName, String lastName, String address, @Nullable String address2,
            String city, String state, String zipCode, String phone, String email, String identityNumber,
            String cryptoAddress, Date birthdate, String country) {
        this.handle = handle;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.address2 = address2;
        this.city = city;
        this.state = state;
        this.zipCode = zipCode;
        this.phone = phone;
        this.email = email;
        this.identityNumber = identityNumber;
        this.cryptoAddress = cryptoAddress;
        this.birthdate = birthdate;
        this.country = country;
        this.smsOptIn = false;
    }

    public User(String handle, String firstName, String lastName, String address, @Nullable String address2,
                String city, String state, String zipCode, String phone, String email, String identityNumber,
                String cryptoAddress, Date birthdate, boolean smsOptIn) {
        this.handle = handle;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.address2 = address2;
        this.city = city;
        this.state = state;
        this.zipCode = zipCode;
        this.phone = phone;
        this.email = email;
        this.identityNumber = identityNumber;
        this.cryptoAddress = cryptoAddress;
        this.birthdate = birthdate;
        this.country = "US";
        this.smsOptIn = false;
    }

    public User(String handle, String firstName, String lastName, String address, @Nullable String address2,
                String city, String state, String zipCode, String phone, String email, String identityNumber,
                String cryptoAddress, Date birthdate, String country, boolean smsOptIn) {
        this.handle = handle;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.address2 = address2;
        this.city = city;
        this.state = state;
        this.zipCode = zipCode;
        this.phone = phone;
        this.email = email;
        this.identityNumber = identityNumber;
        this.cryptoAddress = cryptoAddress;
        this.birthdate = birthdate;
        this.country = country;
        this.smsOptIn = false;
    }
    public User(String handle, String firstName, String lastName, String address, @Nullable String address2,
                String city, String state, String zipCode, String phone, String email, String identityNumber,
                String cryptoAddress, Date birthdate, @Nullable String country, @Nullable String docType,
                @Nullable String docId, @Nullable String docState, @Nullable String docCountry) {
        this.handle = handle;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.address2 = address2;
        this.city = city;
        this.state = state;
        this.zipCode = zipCode;
        this.phone = phone;
        this.email = email;
        this.identityNumber = identityNumber;
        this.cryptoAddress = cryptoAddress;
        this.birthdate = birthdate;
        this.country = country != null ? country : "US";
        this.docType = docType;
        this.docId = docId;
        this.docState = docState;
        this.docCountry = docCountry;
        this.smsOptIn = false;
    }
}
