package com.silamoney.client.domain;

import com.google.gson.annotations.SerializedName;

public class DeleteCardMsg {
    @SerializedName("header")
    private final Header header;
    @SerializedName("card_name")
    private final String cardName;

    public DeleteCardMsg(String userHandle, String authHandle, String cardName) {
        this.header = new Header(userHandle, authHandle);
        this.cardName = cardName;
    }
}
