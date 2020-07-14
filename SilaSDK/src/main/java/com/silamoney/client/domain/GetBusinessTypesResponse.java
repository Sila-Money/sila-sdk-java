package com.silamoney.client.domain;

import java.util.List;

import com.google.gson.annotations.SerializedName;

public class GetBusinessTypesResponse {

    private boolean success;
    @SerializedName(value = "business_types")
    private List<BusinessType> businessTypes;
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
     * @return the businessTypes
     */
    public List<BusinessType> getBusinessTypes() {
        return businessTypes;
    }

    /**
     * @param businessTypes the businessTypes to set
     */
    public void setBusinessTypes(List<BusinessType> businessTypes) {
        this.businessTypes = businessTypes;
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