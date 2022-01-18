package com.silamoney.client.domain;

import com.google.gson.annotations.SerializedName;

public class EmailMsg {
    @SerializedName("header")
    private HeaderBase header;
    @SerializedName("email")
    private String email;
    @SerializedName("uuid")
    private String uuid;

    public EmailMsg(String authHandle, UserHandleMessage user, EmailMessage message) {
        this.header = new HeaderBuilder(authHandle).withUserHandle(user.getUserHandle()).withReference().build();
        this.email = message.getEmail();
        this.uuid = message.getUuid();
    }
}
