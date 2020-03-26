package com.silamoney.client.domain;

import com.google.gson.annotations.SerializedName;

/**
 * Object sent in the Register Wallet method
 *
 * @author wzelada
 */
public class RegisterWalletMsg {

    @SerializedName("wallet_verification_signature")
    private final String wallet_verification_signature;

    @SerializedName("wallet")
    private final Wallet wallet;

    @SerializedName("header")
    private final Header header;

    public RegisterWalletMsg(String userHandle, Wallet wallet, String wallet_verification_signature,
            String appHandle) {
        this.wallet_verification_signature = wallet_verification_signature;
        this.wallet = wallet;
        this.header = new Header(userHandle, appHandle);
    }
}
