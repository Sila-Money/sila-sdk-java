package com.silamoney.client.domain;

import com.google.gson.annotations.SerializedName;

public class PhoneMsg {
    @SerializedName("header")
    private Header header;
    @SerializedName("phone")
    private String phone;
    @SerializedName("uuid")
    private String uuid;
    /**
     * smsOptIn will be removed in the next version of this SDK.
     * Please remove all usages of this variable from your system.
     */
    @SerializedName("sms_opt_in")
    @Deprecated(forRemoval = true)
    private boolean smsOptIn;

    public PhoneMsg(String authHandle, UserHandleMessage user, PhoneMessage message) {
        this.header = new Header(user.getUserHandle(),authHandle);
        this.header.setReference(user.getReference());
        this.phone = message.getPhone();
        this.uuid = message.getUuid();
        this.smsOptIn = message.isSmsOptIn();
    }
}
