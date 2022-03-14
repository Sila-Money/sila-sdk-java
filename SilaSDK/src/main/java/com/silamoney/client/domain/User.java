package com.silamoney.client.domain;

import io.reactivex.annotations.Nullable;
import lombok.Getter;

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
    @Getter
    private final boolean smsOptIn;
    @Getter
    private final String deviceFingerprint;
    @Getter
    private final String sessionIdentifier;

    /**
     * Constructor for user object.
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
     * @param smsOptIn
     */
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
        this.smsOptIn = smsOptIn;
        this.deviceFingerprint = null;
        this.sessionIdentifier=null;
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
        this.smsOptIn = smsOptIn;
        this.deviceFingerprint = null;
        this.sessionIdentifier=null;
    }

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
        this.deviceFingerprint = null;
        this.sessionIdentifier=null;
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
        this.deviceFingerprint = null;
        this.sessionIdentifier=null;
    }

    /**
     * Constructor for user object.
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
     * @param smsOptIn
     */
    public User(String handle, String firstName, String lastName, String address, @Nullable String address2,
            String city, String state, String zipCode, String phone, String email, String identityNumber,
            String cryptoAddress, Date birthdate, boolean smsOptIn, String deviceFingerprint) {
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
        this.smsOptIn = smsOptIn;
        this.deviceFingerprint = deviceFingerprint;
        this.sessionIdentifier=null;
    }

    public User(String handle, String firstName, String lastName, String address, @Nullable String address2,
            String city, String state, String zipCode, String phone, String email, String identityNumber,
            String cryptoAddress, Date birthdate, String country, boolean smsOptIn,String deviceFingerprint) {
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
        this.smsOptIn = smsOptIn;
        this.deviceFingerprint = deviceFingerprint;
        this.sessionIdentifier=null;
    }

    public User(String handle, String firstName, String lastName, String address, @Nullable String address2,
            String city, String state, String zipCode, String phone, String email, String identityNumber,
            String cryptoAddress, Date birthdate, String country, String deviceFingerprint) {
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
        this.deviceFingerprint = deviceFingerprint;
        this.sessionIdentifier=null;
    }
    //Sardine
    public User(String handle, String firstName, String lastName, String address, @Nullable String address2,
                String city, String state, String zipCode, String phone, String email, String identityNumber,
                String cryptoAddress, Date birthdate, boolean smsOptIn, String deviceFingerprint,String sessionIdentifier) {
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
        this.smsOptIn = smsOptIn;
        this.deviceFingerprint = deviceFingerprint;
        this.sessionIdentifier=sessionIdentifier;
    }

    public User(String handle, String firstName, String lastName, String address, @Nullable String address2,
                String city, String state, String zipCode, String phone, String email, String identityNumber,
                String cryptoAddress, Date birthdate, String country, boolean smsOptIn,String deviceFingerprint,String sessionIdentifier) {
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
        this.smsOptIn = smsOptIn;
        this.deviceFingerprint = deviceFingerprint;
        this.sessionIdentifier=sessionIdentifier;
    }

    public User(String handle, String firstName, String lastName, String address, @Nullable String address2,
                String city, String state, String zipCode, String phone, String email, String identityNumber,
                String cryptoAddress, Date birthdate, String country, String deviceFingerprint,String sessionIdentifier) {
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
        this.deviceFingerprint = deviceFingerprint;
        this.sessionIdentifier=sessionIdentifier;
    }
}
