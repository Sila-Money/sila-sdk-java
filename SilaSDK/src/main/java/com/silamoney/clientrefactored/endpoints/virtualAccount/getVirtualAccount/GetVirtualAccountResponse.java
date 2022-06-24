package com.silamoney.clientrefactored.endpoints.virtualAccount.getVirtualAccount;

import com.google.gson.annotations.SerializedName;
import com.silamoney.clientrefactored.domain.Balance;
import com.silamoney.clientrefactored.domain.BaseResponse;
import com.silamoney.clientrefactored.domain.VirtualAccount;
import lombok.Getter;

@Getter
public class GetVirtualAccountResponse extends BaseResponse {

    private Balance balance;
    @SerializedName("virtual_account")
    private VirtualAccount virtualAccount;
}

