package com.silamoney.clientrefactored.endpoints.virtualAccount;

import com.google.gson.annotations.SerializedName;
import com.silamoney.clientrefactored.domain.BaseResponse;
import com.silamoney.clientrefactored.domain.VirtualAccount;
import lombok.Getter;

@Getter
public class VirtualAccountResponse extends BaseResponse {

    @SerializedName("virtual_account")
    private VirtualAccount virtualAccount;
}
