package com.silamoney.client.domain;

import com.google.gson.annotations.SerializedName;

import java.util.Map;

public class UpdateBusinessEntityMsg {

    @SerializedName("business_type")
    private final String businessType;

    @SerializedName("naics_code")
    private final String naicsCode;

    @SerializedName("doing_business_as")
    private final String doingBusinessAs;

    @SerializedName("business_website")
    private final String businessWebsite;

    @SerializedName("fist_name")
    private final String firstName;

    @SerializedName("last_name")
    private final String lastName;

    @SerializedName("entity_name")
    private final String entityName;

    @SerializedName("birthdate")
    private final String birthdate;

    @SerializedName("uuid")
    private final String uuid;

    @SerializedName("header")
    private final Map<String, String> header;

    /**
     * Constructor for user object.
     *
     * @param firstName
     * @param lastName
     * @param entityName
     * @param birthdate
     * @param businessType
     * @param naicsCode
     * @param doingBusinessAs
     * @param businessWebsite
     * @param uuid
     * @param header
     */
    public UpdateBusinessEntityMsg(String firstName, String lastName, String entityName, String birthdate, String businessType, String naicsCode, String doingBusinessAs, String businessWebsite, String uuid, Map<String, String> header) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.entityName = entityName;
        this.birthdate = birthdate;
        this.uuid = uuid;
        this.header = header;
        this.businessType = businessType;
        this.naicsCode = naicsCode;
        this.doingBusinessAs = doingBusinessAs;
        this.businessWebsite = businessWebsite;
    }
}
