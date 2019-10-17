package com.silamoney.client.domain;

import io.reactivex.annotations.Nullable;
import java.util.Date;

/**
 * Class used in the register method.
 *
 * @author Karlo Lorenzana
 */
public class User {

    /**
     * String field use for handle value.
     */
    public String handle;

    /**
     * String field use for first name.
     */
    public String firstName;

    /**
     * String field use for last name.
     */
    public String lastName;

    /**
     * String field use for address.
     */
    public String address;

    /**
     **String field use for address 2.
     */
    public String address2;

    /**
     * String field use for city.
     */
    public String city;

    /**
     * String field use for state.
     */
    public String state;

    /**
     * String field use for first zip code.
     */
    public String zipCode;

    /**
     * String field use for phone.
     */
    public String phone;

    /**
     * String field use for email.
     */
    public String email;

    /**
     * String field use for identity number.
     */
    public String identityNumber;

    /**
     * String field use for crypto address.
     */
    public String cryptoAddress;
    
    /**
     *Date field use for the birthdate.
     */
    public Date birthdate;

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
     */
    public User(String handle, String firstName, String lastName, String address, @Nullable String address2, String city, String state, String zipCode, String phone, String email, String identityNumber, String cryptoAddress, Date birthdate) {
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
    }
}
