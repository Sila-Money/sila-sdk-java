package com.silamoney.client.domain;

import com.google.gson.annotations.SerializedName;

public class IdentityMsg {
    @SerializedName("header")
    private Header header;
    @SerializedName("identity_alias")
    private String identityAlias;
    @SerializedName("identity_value")
    private String identityValue;
    @SerializedName("uuid")
    private String uuid;

    public IdentityMsg(String authHandle, UserHandleMessage user, IdentityMessage message) {
        this.header = new Header(user.getUserHandle(),authHandle);
        this.header.setReference(user.getReference());
        this.identityAlias = message.getIdentityAlias();
        this.identityValue = message.getIdentityValue();
        this.uuid = message.getUuid();
    }
}
