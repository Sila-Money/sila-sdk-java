package com.silamoney.clientrefactored.domain;

import com.google.gson.annotations.SerializedName;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Timeline {
    
    private String date;
    @SerializedName("date_epoch")
    private String dateEpoch;
    private String status;
    @SerializedName("usd_status")
    private String usdStatus;
    @SerializedName("token_status")
    private String tokenStatus;
    /**
     * String field used for the provider status.
     */
    @SerializedName("provider_status")
    public String providerStatus;
}
