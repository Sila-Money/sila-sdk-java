package com.silamoney.client.domain;

import com.google.gson.annotations.SerializedName;

import lombok.Getter;

@Getter
public class DeleteAccountResponse extends BaseResponse {

    @SerializedName("account_nickname")
    private String accountName;
    
}
