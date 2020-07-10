package com.silamoney.client.domain;

import com.google.gson.annotations.SerializedName;

/**
 * Object used in the entity msg.
 *
 * @author Karlo Lorenzana
 */
public class Identity extends EntityAudit {

    private enum IdentityAliasEnum {
        SSN("SSN"), EIN("EIN"), ITIN("ITIN");

        private final String value;

        IdentityAliasEnum(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }

    @SerializedName("identity_alias")
    private final String identityAlias;
    @SerializedName("identity_value")
    private final String identityValue;
    @SerializedName("identity")
    private String identity;

    /**
     * Constructor for the Identity object.
     *
     * @param user
     */
    public Identity(User user) {
        this.identityAlias = IdentityAliasEnum.SSN.getValue();
        this.identityValue = user.getIdentityNumber();
    }

    /**
     * Constructor for the Identity object.
     *
     * @param user
     */
    public Identity(BusinessUser user) {
        this.identityAlias = IdentityAliasEnum.EIN.getValue();
        this.identityValue = user.getIdentityValue();
    }

    /**
     * @return the identityAlias
     */
    public String getIdentityAlias() {
        return identityAlias;
    }

    /**
     * @return the identityValue
     */
    public String getIdentityValue() {
        return identityValue;
    }

    /**
     * @return the identity
     */
    public String getIdentity() {
        return identity;
    }
}
