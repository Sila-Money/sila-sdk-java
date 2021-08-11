package com.silamoney.client.domain;

import com.google.gson.annotations.SerializedName;

import lombok.Getter;

public class Phone extends EntityAudit {
    
    @Getter
    private String phone;
    @Getter
    @SerializedName(value = "sms_confirmation_requested")
    private Boolean smsConfirmationRequested;
    @Getter
    @SerializedName(value = "sms_confirmed")
    private Boolean smsConfirmed;
    
}