package com.silamoney.clientrefactored.endpoints.virtualAccount.getVirtualAccounts;

import com.google.gson.annotations.SerializedName;
import com.silamoney.clientrefactored.domain.BaseResponse;
import com.silamoney.clientrefactored.domain.Pagination;
import com.silamoney.clientrefactored.domain.VirtualAccount;
import lombok.Getter;

import java.util.List;

@Getter
public class GetVirtualAccountsResponse extends BaseResponse {

    private Pagination pagination;
    @SerializedName("virtual_accounts")
    private List<VirtualAccount> virtualAccounts;
}

