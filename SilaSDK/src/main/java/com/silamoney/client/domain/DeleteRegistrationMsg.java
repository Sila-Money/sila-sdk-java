package com.silamoney.client.domain;

import com.google.gson.annotations.SerializedName;

public class DeleteRegistrationMsg {
    @SerializedName("header")
    private final Header header;
    @SerializedName("uuid")
    private final String uuid;

    public DeleteRegistrationMsg(String appHandle, DeleteRegistrationMessage message) {
        this.header = new Header(message.getUserHandle(),appHandle);
        this.uuid = message.getUuid();
    }
}
