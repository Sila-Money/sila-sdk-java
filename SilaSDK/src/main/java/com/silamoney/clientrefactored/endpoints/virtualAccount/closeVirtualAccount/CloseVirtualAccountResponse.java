package com.silamoney.clientrefactored.endpoints.virtualAccount.closeVirtualAccount;

import com.google.gson.annotations.SerializedName;
import com.silamoney.client.domain.BaseResponse;
import com.silamoney.clientrefactored.domain.Balance;
import com.silamoney.clientrefactored.domain.VirtualAccount;
import lombok.Getter;

@Getter
public class CloseVirtualAccountResponse extends BaseResponse {

    @SerializedName("virtual_account")
    private VirtualAccount virtualAccount;
}

