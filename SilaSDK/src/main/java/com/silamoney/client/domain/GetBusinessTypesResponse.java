package com.silamoney.client.domain;

import java.util.List;

import com.google.gson.annotations.SerializedName;

import lombok.Getter;

@Getter
public class GetBusinessTypesResponse {

    private boolean success;
    @SerializedName(value = "business_types")
    private List<BusinessType> businessTypes;
    private String message;
    private String status;
    private String reference;
    @SerializedName("response_time_ms")
    private String responseTimeMs;
}