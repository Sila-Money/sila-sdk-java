package com.silamoney.clientrefactored.endpoints.accounts.checkinstantach;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;

@Getter
public class CheckInstantACHResponse {

    private boolean success;
    private String message;
    private String status;
    private String reference;
    @SerializedName("response_time_ms")
    private String responseTimeMs;
}
