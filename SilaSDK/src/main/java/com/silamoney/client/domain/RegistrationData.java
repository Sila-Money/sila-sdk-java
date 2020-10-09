package com.silamoney.client.domain;

import com.google.gson.annotations.SerializedName;

import lombok.Getter;

public class RegistrationData {
    @Getter
    @SerializedName("added_epoch")
    protected Integer addedEpoch;
    @Getter
    @SerializedName("modified_epoch")
    protected Integer modifiedEpoch;
    @Getter
    @SerializedName("uuid")
    protected String uuid;
}
