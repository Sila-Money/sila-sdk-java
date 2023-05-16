package com.silamoney.client.domain;

import com.google.gson.annotations.SerializedName;

public class DeleteCardMsg {
    @SerializedName("header")
    private final Header header;
    @SerializedName("card_name")
    private final String cardName;
    /*
    String field used for provider
    */
    @SerializedName("provider")
    private final String provider;

    public DeleteCardMsg(String userHandle, String authHandle, String cardName,String provider,String reference) {
        this.header = new Header(userHandle, authHandle);
        this.header.setReference(reference);
        this.cardName = cardName;
        this.provider=provider;
    }
}
