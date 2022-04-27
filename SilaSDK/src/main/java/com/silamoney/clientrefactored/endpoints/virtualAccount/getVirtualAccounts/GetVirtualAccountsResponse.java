package com.silamoney.clientrefactored.endpoints.virtualAccount.getVirtualAccounts;

import com.google.gson.annotations.SerializedName;
import com.silamoney.clientrefactored.domain.Pagination;
import com.silamoney.clientrefactored.domain.VirtualAccount;
import lombok.Getter;

import java.util.List;

@Getter
public class GetVirtualAccountsResponse {

    private boolean success;
    private String status;
    private Pagination pagination;
    @SerializedName("virtual_accounts")
    private List<VirtualAccount> virtualAccounts;
    private String reference;
    @SerializedName("response_time_ms")
    private String responseTimeMs;
}

