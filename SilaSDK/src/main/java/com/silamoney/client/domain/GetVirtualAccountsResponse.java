package com.silamoney.client.domain;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;

import java.util.List;

@Getter
public class GetVirtualAccountsResponse extends BaseResponse {
    @SerializedName("virtual_accounts")
    private List<VirtualAccount> virtualAccounts;
    private Pagination pagination;

}
