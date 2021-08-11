package com.silamoney.client.domain;

import com.google.gson.annotations.SerializedName;

import lombok.Getter;

/**
 * Object used in the entity msg.
 *
 * @author Karlo Lorenzana
 */
@Getter
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
    @SerializedName("identity_type")
    private String identityType;

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
}
