package com.silamoney.client.domain;

import java.util.List;

import com.google.gson.annotations.SerializedName;

import lombok.Getter;

@Getter
public class GetBusinessRolesResponse {

    private boolean success;
    @SerializedName(value = "business_roles")
    private List<BusinessRole> businessRoles;
    private String message;
    private String status;
    private String reference;
    @SerializedName("response_time_ms")
    private String responseTimeMs;
}