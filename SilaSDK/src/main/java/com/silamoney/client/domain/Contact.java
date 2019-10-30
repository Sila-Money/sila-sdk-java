package com.silamoney.client.domain;

import com.google.gson.annotations.SerializedName;

/**
 * Object used in the entity msg.
 *
 * @author Karlo Lorenzana
 */
public class Contact {

    @SerializedName("phone")
    private final String phone;

    @SerializedName("contact_alias")
    private final String contactAlias;

    @SerializedName("email")
    private final String email;

    /**
     * Constructor for contact object.
     *
     * @param user
     */
    public Contact(User user) {
        this.contactAlias = "";
        this.email = user.getEmail();
        this.phone = user.getPhone();
    }
}
