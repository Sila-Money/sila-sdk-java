package com.silamoney.client.domain;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;

@Getter
public class GetVirtualAccountResponse extends BaseResponse {
    @SerializedName("balance")
    private Balance balance;
    @SerializedName("virtual_account")
    private VirtualAccount virtualAccount;
}
