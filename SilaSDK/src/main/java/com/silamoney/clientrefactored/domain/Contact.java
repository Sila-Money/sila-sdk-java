package com.silamoney.clientrefactored.domain;

import com.google.gson.annotations.SerializedName;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Contact {
    
    private String phone;
    @SerializedName("contact_alias")
    private String contactAlias;
    private String email;
    /**
     * smsOptIn will be removed in the next version of this SDK.
     * Please remove all usages of this variable from your system.
     */
    @SerializedName("sms_opt_in")
    @Deprecated(forRemoval = true)
    private boolean smsOptIn;

}
