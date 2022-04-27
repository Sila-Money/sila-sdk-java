package com.silamoney.client.domain;

import com.google.gson.annotations.SerializedName;

public class PhoneMsg {
    @SerializedName("header")
    private HeaderBase header;
    @SerializedName("phone")
    private String phone;
    @SerializedName("uuid")
    private String uuid;
    @SerializedName("sms_opt_in")
    private boolean smsOptIn;

    public PhoneMsg(String authHandle, UserHandleMessage user, PhoneMessage message) {
        this.header = new HeaderBuilder(authHandle).withUserHandle(user.getUserHandle()).withReference().build();
        this.phone = message.getPhone();
        this.uuid = message.getUuid();
        this.smsOptIn = message.isSmsOptIn();
    }
}
