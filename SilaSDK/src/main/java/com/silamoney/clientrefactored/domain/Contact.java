package com.silamoney.clientrefactored.domain;

import com.google.gson.annotations.SerializedName;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class Contact {
    
    private String phone;
    @SerializedName("contact_alias")
    private String contactAlias;
    private String email;
    @SerializedName("sms_opt_in")
    private boolean smsOptIn;

}
