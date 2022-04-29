package com.silamoney.clientrefactored.endpoints.accounts.plaidupdatelinktoken;

import com.google.gson.annotations.SerializedName;

import com.silamoney.clientrefactored.domain.BaseResponse;
import lombok.Getter;

@Getter
public class PlaidUpdateLinkTokenResponse extends BaseResponse {

    @SerializedName(value = "link_token")
    private String linkToken;
}
