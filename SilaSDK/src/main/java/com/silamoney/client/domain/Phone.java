package com.silamoney.client.domain;

import com.google.gson.annotations.SerializedName;

import lombok.Getter;

public class Phone extends EntityAudit {
    
    @Getter
    private String phone;
    /**
     * smsConfirmationRequested will be removed in the next version of this SDK.
     * Please remove all usages of this variable from your system.
     */
    @Getter
    @Deprecated(forRemoval = true)
    @SerializedName(value = "sms_confirmation_requested")
    private Boolean smsConfirmationRequested;
    /**
     * smsConfirmed will be removed in the next version of this SDK.
     * Please remove all usages of this variable from your system.
     */
    @Getter
    @Deprecated(forRemoval = true)
    @SerializedName(value = "sms_confirmed")
    private Boolean smsConfirmed;
    
}