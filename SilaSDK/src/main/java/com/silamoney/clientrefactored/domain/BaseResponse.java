package com.silamoney.clientrefactored.domain;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;

@Getter
public class BaseResponse {
    protected boolean success;
    protected String message;
    protected String status;
    protected String reference;
    @SerializedName("response_time_ms")
    protected String responseTimeMs;
}
