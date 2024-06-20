package com.silamoney.client.domain;

import com.google.gson.annotations.SerializedName;

/**
 * Object sent in the Delete Wallet method
 *
 * @author wzelada
 */
public class DeleteWalletMsg {

    @SerializedName("header")
    private final Header header;

    public DeleteWalletMsg(String userHandle,
            String appHandle,String reference) {
        this.header = new Header(userHandle, appHandle);
        this.header.setReference(reference);
    }
}
