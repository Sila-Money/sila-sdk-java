package com.silamoney.client.domain;

import io.reactivex.annotations.Nullable;
import java.util.Date;

/**
 * Class used in the register method.
 *
 * @author Karlo Lorenzana
 */
public class User {

    private final String handle;

    private final String firstName;

    private final String lastName;

    private final String address;

    private final String address2;

    private final String city;

    private final String state;

    private final String zipCode;

    private final String phone;

    private final String email;

    private final String identityNumber;

    private final String cryptoAddress;
    
    private final Date birthdate;

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

    /**
     * Gets the user handle.
     * @return handle
     */
    public String getHandle() {
        return handle;
    }

    /**
     * Gets the user first name.
     * @return first name
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Gets the user last name.
     * @return last name
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Gets the user street address 1.
     * @return address
     */
    public String getAddress() {
        return address;
    }

    /**
     * Gets the user street address 2.
     * @return street address 2
     */
    public String getAddress2() {
        return address2;
    }

    /**
     * Gets the user city.
     * @return city
     */
    public String getCity() {
        return city;
    }

    /**
     * Gets the user state.
     * @return state
     */
    public String getState() {
        return state;
    }

    /**
     * Gets the user zip code.
     * @return zip code
     */
    public String getZipCode() {
        return zipCode;
    }

    /**
     * Gets the user phone.
     * @return phone
     */
    public String getPhone() {
        return phone;
    }

    /**
     * Gets the user email.
     * @return email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Gets the user identity number.
     * @return identity number
     */
    public String getIdentityNumber() {
        return identityNumber;
    }

    /**
     * Gets the user crypto address.
     * @return crypto address
     */
    public String getCryptoAddress() {
        return cryptoAddress;
    }

    /**
     * Gets the user birthdate.
     * @return birthdate
     */
    public Date getBirthdate() {
        return birthdate;
    }
    
    
}
