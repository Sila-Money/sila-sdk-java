package com.silamoney.client.domain;

import com.google.gson.annotations.SerializedName;

/**
 * Object used in the entity msg.
 *
 * @author loren
 */
public class Identity {

    private enum IdentityAliasEnum {
        SSN("SSN"),
        EIN("EIN"),
        ITIN("ITIN");

        private final String value;

        IdentityAliasEnum(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }

    /**
     * String field used for the identity alias.
     */
    @SerializedName("identity_alias")
    public String identityAlias;

    /**
     * String field used for the identity value.
     */
    @SerializedName("identity_value")
    public String identityValue;

    /**
     * Constructor for the Identity object.
     *
     * @param user
     */
    public Identity(User user) {
        this.identityAlias = IdentityAliasEnum.SSN.getValue();
        this.identityValue = user.identityNumber;
    }

}
