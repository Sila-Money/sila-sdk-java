package com.silamoney.client.domain;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;

@Getter
public class LinkBusinessOperationResponse {

    private String message;
    private boolean success;
    private String role;
    private String status;
    private String reference;
    @SerializedName("response_time_ms")
    private String responseTimeMs;
}