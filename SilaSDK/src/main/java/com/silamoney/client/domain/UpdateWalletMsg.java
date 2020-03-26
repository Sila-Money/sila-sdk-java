package com.silamoney.client.domain;

import com.google.gson.annotations.SerializedName;

/**
 * Object sent in the Update Wallet method
 *
 * @author wzelada
 */
public class UpdateWalletMsg {

    @SerializedName("nickname")
    private final String nickname;

    @SerializedName("status")
    private final boolean status;

    @SerializedName("header")
    private final Header header;

    public UpdateWalletMsg(String userHandle, String nickname, boolean status,
            String appHandle) {
        this.nickname = nickname;
        this.status = status;
        this.header = new Header(userHandle, appHandle);
    }
}
