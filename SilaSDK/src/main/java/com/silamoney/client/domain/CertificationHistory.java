package com.silamoney.client.domain;

import java.util.List;

import com.google.gson.annotations.SerializedName;

public class CertificationHistory {
    @SerializedName(value = "administrator_user_handle")
    private String adminUserHandle;
    @SerializedName(value = "created")
    private int createdAt;
    @SerializedName(value = "created_epoch")
    private int createdEpoch;
    @SerializedName(value = "expires_after")
    private int expiresAfter;
    @SerializedName(value = "beneficial_owner_certifications")
    private List<String> beneficialOwnerCerts;

    /**
     * @return the adminUserHandle
     */
    public String getAdminUserHandle() { return adminUserHandle; }

    /**
     * @return the createdAt
     */
    public int getCreatedAt() { return createdAt; }

    /**
     * @return the createdEpoch
     */
    public int getCreatedEpoch() { return createdEpoch; }

    /**
     * @return the expiresAfter
     */
    public int getExpiresAfter() { return expiresAfter; }

    /**
     * @return the beneficialOwnerCerts
     */
    public List<String> getBeneficialOwnerCerts() { return beneficialOwnerCerts; }
}
