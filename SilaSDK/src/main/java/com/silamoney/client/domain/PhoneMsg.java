package com.silamoney.client.domain;

import com.google.gson.annotations.SerializedName;

public class PhoneMsg {
    @SerializedName("header")
    private HeaderBase header;
    @SerializedName("phone")
    private String phone;
    @SerializedName("uuid")
    private String uuid;

    public PhoneMsg(String authHandle, UserHandleMessage user, PhoneMessage message) {
        this.header = new HeaderBuilder(authHandle).withUserHandle(user.getUserHandle()).build();
        this.phone = message.getPhone();
        this.uuid = message.getUuid();
    }
}
