package com.silamoney.clientrefactored.endpoints.virtualAccount.getVirtualAccount;

import com.google.gson.annotations.SerializedName;
import com.silamoney.clientrefactored.domain.Balance;
import com.silamoney.clientrefactored.domain.VirtualAccount;
import lombok.Getter;

@Getter
public class GetVirtualAccountResponse {

    private boolean success;
    private String status;
    private Balance balance;
    @SerializedName("virtual_account")
    private VirtualAccount virtualAccount;
    private String reference;
    @SerializedName("response_time_ms")
    private String responseTimeMs;
}

