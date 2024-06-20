package com.silamoney.client.domain;

import com.google.gson.annotations.SerializedName;

import java.util.Map;

public class UpdateEntityMsg {

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
     * @param uuid
     * @param header
     */
    public UpdateEntityMsg(String firstName, String lastName, String entityName, String birthdate, String uuid, Map<String, String> header) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.entityName = entityName;
        this.birthdate = birthdate;
        this.uuid = uuid;
        this.header = header;
    }
}
