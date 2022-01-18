package com.silamoney.client.domain;

import com.google.gson.annotations.SerializedName;

public class DeleteRegistrationMsg {
    @SerializedName("header")
    private final HeaderBase header;
    @SerializedName("uuid")
    private final String uuid;

    public DeleteRegistrationMsg(String appHandle, DeleteRegistrationMessage message) {
        this.header = new HeaderBuilder(appHandle).withUserHandle(message.getUserHandle()).withReference().build();
        this.uuid = message.getUuid();
    }
}
