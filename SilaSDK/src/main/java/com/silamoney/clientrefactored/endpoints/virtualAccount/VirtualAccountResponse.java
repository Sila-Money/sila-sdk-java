package com.silamoney.clientrefactored.endpoints.virtualAccount;

import com.google.gson.annotations.SerializedName;
import com.silamoney.clientrefactored.domain.VirtualAccount;
import lombok.Getter;

@Getter
public class VirtualAccountResponse {

    private boolean success;
    private String message;
    private String status;
    @SerializedName("virtual_account")
    private VirtualAccount virtualAccount;
    private String reference;
    @SerializedName("response_time_ms")
    private String responseTimeMs;
}
