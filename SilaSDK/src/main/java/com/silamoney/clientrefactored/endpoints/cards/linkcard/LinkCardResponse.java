package com.silamoney.clientrefactored.endpoints.cards.linkcard;

import com.google.gson.annotations.SerializedName;
import com.silamoney.clientrefactored.domain.Card;
import lombok.Getter;

@Getter
public class LinkCardResponse {

    private boolean success;
    private String message;
    private String status;
    private String reference;
    @SerializedName("account_name")
    private String accountName;
    @SerializedName("card_name")
    private String cardName;
    @SerializedName("avs")
    private String avs;
    @SerializedName("card_details")
    private Card cardDetails;
    @SerializedName("response_time_ms")
    private String responseTimeMs;
}

