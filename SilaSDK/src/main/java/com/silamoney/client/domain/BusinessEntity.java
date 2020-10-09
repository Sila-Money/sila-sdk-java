package com.silamoney.client.domain;

import com.google.gson.annotations.SerializedName;

import lombok.Getter;

public class BusinessEntity extends EntityBase {
    @Getter
    @SerializedName("business_type")
    private String businessType;
    @Getter
    @SerializedName("naics_code")
    private String naicsCode;
    @Getter
    @SerializedName("business_uuid")
    private String businessUuid;
    @Getter
    @SerializedName("naics_category")
    private String naicsCategory;
    @Getter
    @SerializedName("naics_subcategory")
    private String naicsSubcategory;
    @Getter
    @SerializedName("doing_business_as")
    private String doingBusinessAs;
    @Getter
    @SerializedName("business_website")
    private String businessWebsite;
}
