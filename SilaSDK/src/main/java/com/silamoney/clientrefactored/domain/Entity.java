package com.silamoney.clientrefactored.domain;

import com.google.gson.annotations.SerializedName;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Entity {

    private String birthdate;
    @SerializedName("entity_name")
    private String entityName;
    @SerializedName("first_name")
    private String firstName;
    @SerializedName("last_name")
    private String lastName;
    @SerializedName("type")
    private String type;
    @SerializedName("business_type")
    private String businessType;
    @SerializedName("business_type_uuid")
    private String businessTypeUuid;
    @SerializedName("naics_code")
    private Integer naicsCode;
    @SerializedName("doing_business_as")
    private String doingBusinessAs;
    @SerializedName("business_website")
    private String businessWebsite;
    @SerializedName("registration_state")
    private String registrationState;
}
