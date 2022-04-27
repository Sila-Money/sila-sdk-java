package com.silamoney.client.domain;

import java.time.format.DateTimeFormatter;

import com.google.gson.annotations.SerializedName;

public class BusinessEntityMsg {
    @SerializedName("header")
    private HeaderBase header;
    @SerializedName("entity_name")
    private String entityName;
    @SerializedName("birthdate")
    private String birthdate;
    @SerializedName("business_type")
    private String businessType;
    @SerializedName("naics_code")
    private Integer naicsCode;
    @SerializedName("doing_business_as")
    private String doingBusinessAs;
    @SerializedName("business_website")
    private String businessWebsite;

    public BusinessEntityMsg(String authHandle, UserHandleMessage user, BusinessEntityMessage message) {
        this.header = new HeaderBuilder(authHandle).withUserHandle(user.getUserHandle()).withReference().build();
        this.entityName = message.getEntityName();
        this.birthdate = message.getBirthdate() == null ? null
                : message.getBirthdate().format(DateTimeFormatter.ISO_LOCAL_DATE);
        this.businessType = message.getBusinessType();
        this.naicsCode = message.getNaicsCode();
        this.doingBusinessAs = message.getDoingBusinessAs();
        this.businessWebsite = message.getBusinessWebsite();
    }
}
