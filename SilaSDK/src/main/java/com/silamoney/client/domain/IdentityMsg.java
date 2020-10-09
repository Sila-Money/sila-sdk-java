package com.silamoney.client.domain;

import com.google.gson.annotations.SerializedName;

public class IdentityMsg {
    @SerializedName("header")
    private HeaderBase header;
    @SerializedName("identity_alias")
    private String identityAlias;
    @SerializedName("identity_value")
    private String identityValue;

    public IdentityMsg(String authHandle, UserHandleMessage user, IdentityMessage message) {
        this.header = new HeaderBuilder(authHandle).withUserHandle(user.getUserHandle()).build();
        this.identityAlias = message.getIdentityAlias();
        this.identityValue = message.getIdentityValue();
    }
}
