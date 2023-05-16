package com.silamoney.client.domain;

import com.google.gson.annotations.SerializedName;

/**
 * Object sent in the Get Wallet method
 *
 * @author wzelada
 */
public class GetWalletMsg {

    @SerializedName("header")
    private final Header header;

    public GetWalletMsg(String userHandle,
                        String appHandle,String reference) {
        this.header = new Header(userHandle, appHandle);
        this.header.setReference(reference);
    }
}
