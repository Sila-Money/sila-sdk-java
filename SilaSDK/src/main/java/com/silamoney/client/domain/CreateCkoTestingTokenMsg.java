package com.silamoney.client.domain;

import com.google.gson.annotations.SerializedName;

public class CreateCkoTestingTokenMsg {
    @SerializedName("header")
    private final Header header;

    @SerializedName("card_number")
    private final String cardNumber;

    @SerializedName("expiry_month")
    private final int expiryMonth;

    @SerializedName("expiry_year")
    private final int expiryYear;

    @SerializedName("cvv")
    private final int cvv;

    @SerializedName("cko_public_key")
    private final String ckoPublicKey;

    /**
     * Constructor for createCkoTestingToken object.
     *
     * @param message
     */
    public CreateCkoTestingTokenMsg(String authHandle, CreateCkoTestingTokenMessage message) {
        this.header = new Header(message.getUserHandle(), authHandle);
        this.header.setReference(message.getReference());
        this.cardNumber = message.getCardNumber();
        this.expiryMonth = message.getExpiryMonth();
        this.expiryYear = message.getExpiryYear();
        this.cvv = message.getCvv();
        this.ckoPublicKey = message.getCkoPublicKey();
    }

}
