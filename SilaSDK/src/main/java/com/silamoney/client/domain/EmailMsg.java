package com.silamoney.client.domain;

import com.google.gson.annotations.SerializedName;

public class EmailMsg {
    @SerializedName("header")
    private HeaderBase header;
    @SerializedName("email")
    private String email;

    public EmailMsg(String authHandle, UserHandleMessage user, EmailMessage message) {
        this.header = new HeaderBuilder(authHandle).withUserHandle(user.getUserHandle()).build();
        this.email = message.getEmail();
    }
}
