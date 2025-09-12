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
    private String contactAlias;

    @SerializedName("email")
    private final String email;

    /**
     * smsOptIn will be removed in the next version of this SDK.
     * Please remove all usages of this variable from your system.
     */
    @SerializedName("sms_opt_in")
    @Deprecated(forRemoval = true)
    private final boolean smsOptIn;

    /**
     * Constructor for contact object.
     *
     * @param user
     */
    public Contact(User user) {
        this.contactAlias = "";
        this.email = user.getEmail();
        this.phone = user.getPhone();
        this.smsOptIn = user.isSmsOptIn();
    }

    /**
     * Constructor for contact object.
     *
     * @param user
     */
    public Contact(BusinessUser user) {
        this.email = user.getEmail();
        this.phone = user.getPhone();
        this.smsOptIn = user.isSmsOptIn();
    }
}
