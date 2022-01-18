package com.silamoney.client.domain;

import java.time.format.DateTimeFormatter;

import com.google.gson.annotations.SerializedName;

public class IndividualEntityMsg {
    @SerializedName("header")
    private HeaderBase header;
    @SerializedName("first_name")
    private String firstName;
    @SerializedName("last_name")
    private String lastName;
    @SerializedName("entity_name")
    private String entityName;
    @SerializedName("birthdate")
    private String birthdate;

    public IndividualEntityMsg(String authHandle, UserHandleMessage user, IndividualEntityMessage message) {
        this.header = new HeaderBuilder(authHandle).withUserHandle(user.getUserHandle()).withReference().build();
        this.firstName = message.getFirstName();
        this.lastName = message.getLastName();
        this.entityName = message.getEntityName();
        this.birthdate = message.getBirthdate() == null ? null
                : message.getBirthdate().format(DateTimeFormatter.ISO_LOCAL_DATE);
    }
}
