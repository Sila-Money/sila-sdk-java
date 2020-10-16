package com.silamoney.client.domain;

import com.google.gson.annotations.SerializedName;

import lombok.Getter;

public class IndividualEntity extends EntityBase {
    @Getter
    @SerializedName("first_name")
    private String firstName;
    @Getter
    @SerializedName("last_name")
    private String lastName;
}
