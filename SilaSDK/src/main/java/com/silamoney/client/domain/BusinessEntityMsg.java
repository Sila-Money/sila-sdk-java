package com.silamoney.client.domain;

import java.time.format.DateTimeFormatter;

import com.google.gson.annotations.SerializedName;

public class BusinessEntityMsg {
    @SerializedName("header")
    private Header header;
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
    @SerializedName("registration_state")
    private String registrationState;
    @SerializedName("registration_date")
    private String registrationDate;

    public BusinessEntityMsg(String authHandle, UserHandleMessage user, BusinessEntityMessage message) {
        this.header = new Header(user.getUserHandle(),authHandle);
        this.header.setReference(user.getReference());
        this.entityName = message.getEntityName();
        this.birthdate = message.getBirthdate() == null ? null
                : message.getBirthdate().format(DateTimeFormatter.ISO_LOCAL_DATE);
        this.businessType = message.getBusinessType();
        this.naicsCode = message.getNaicsCode();
        this.doingBusinessAs = message.getDoingBusinessAs();
        this.businessWebsite = message.getBusinessWebsite();
        this.registrationState=message.getRegistrationState();
        this.registrationDate = message.getRegistrationDate() == null ? null
                : message.getRegistrationDate().format(DateTimeFormatter.ISO_LOCAL_DATE);
    }
}
