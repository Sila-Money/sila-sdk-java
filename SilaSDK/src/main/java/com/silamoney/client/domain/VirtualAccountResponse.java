package com.silamoney.client.domain;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;

@Getter
public class VirtualAccountResponse extends BaseResponse {
    @SerializedName("virtual_account")
    private VirtualAccount virtualAccount;
}