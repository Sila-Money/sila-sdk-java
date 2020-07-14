package com.silamoney.client.domain;

import com.google.gson.annotations.SerializedName;

public class Membership {

    @SerializedName(value = "business_handle")
    private String businessHandle;
    @SerializedName(value = "entity_name")
    private String entityName;
    private String role;
    private String details;
    @SerializedName(value = "ownership_stake")
    private Float ownershipStake;
    @SerializedName(value = "certification_token")
    private String certificationToken;

    /**
     * @return the businessHandle
     */
    public String getBusinessHandle() {
        return businessHandle;
    }

    /**
     * @return the entityName
     */
    public String getEntityName() {
        return entityName;
    }

    /**
     * @return the role
     */
    public String getRole() {
        return role;
    }

    /**
     * @return the details
     */
    public String getDetails() {
        return details;
    }

    /**
     * @return the ownershipStake
     */
    public Float getOwnershipStake() {
        return ownershipStake;
    }

    /**
     * @return the certificationToken
     */
    public String getCertificationToken() {
        return certificationToken;
    }

}