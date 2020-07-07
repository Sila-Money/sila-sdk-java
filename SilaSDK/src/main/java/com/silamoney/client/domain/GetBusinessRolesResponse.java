package com.silamoney.client.domain;

import java.util.List;

import com.google.gson.annotations.SerializedName;

public class GetBusinessRolesResponse {

    private boolean success;
    @SerializedName(value = "business_roles")
    private List<BusinessRole> businessRoles;
    private String message;

    /**
     * @return the success
     */
    public boolean isSuccess() {
        return success;
    }

    /**
     * @param success the success to set
     */
    public void setSuccess(boolean success) {
        this.success = success;
    }

    /**
     * @return the businessRoles
     */
    public List<BusinessRole> getBusinessRoles() {
        return businessRoles;
    }

    /**
     * @param businessRoles the businessRoles to set
     */
    public void setBusinessRoles(List<BusinessRole> businessRoles) {
        this.businessRoles = businessRoles;
    }

    /**
     * @return the message
     */
    public String getMessage() {
        return message;
    }

    /**
     * @param message the message to set
     */
    public void setMessage(String message) {
        this.message = message;
    }

}