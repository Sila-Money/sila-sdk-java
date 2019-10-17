package com.silamoney.client.domain;

import com.google.gson.annotations.SerializedName;

/**
 * Object used in the entity msg.
 *
 * @author Karlo Lorenzana
 */
public class Contact {

    /**
     * String field used for the phone.
     */
    @SerializedName("phone")
    public String phone;

    /**
     * String field used for the contact alias.
     */
    @SerializedName("contact_alias")
    public String contactAlias;

    /**
     * String field used for the email.
     */
    @SerializedName("email")
    public String email;

    /**
     * Constructor for contact object.
     *
     * @param user
     */
    public Contact(User user) {
        this.contactAlias = "";
        this.email = user.email;
        this.phone = user.phone;
    }
}
