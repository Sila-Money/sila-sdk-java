package com.silamoney.client.domain;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.Getter;

@Getter
public class CheckInstantAchResponse extends BaseResponse {
    @Expose
    @SerializedName("maximum_amount")
    private String maximum_amount;
}
