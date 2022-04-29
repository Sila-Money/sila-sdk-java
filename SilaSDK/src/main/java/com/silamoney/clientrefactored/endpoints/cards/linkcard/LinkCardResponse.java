package com.silamoney.clientrefactored.endpoints.cards.linkcard;

import com.google.gson.annotations.SerializedName;
import com.silamoney.clientrefactored.domain.BaseResponse;
import com.silamoney.clientrefactored.domain.Card;
import lombok.Getter;

@Getter
public class LinkCardResponse extends BaseResponse {

    @SerializedName("account_name")
    private String accountName;
    @SerializedName("card_name")
    private String cardName;
    @SerializedName("avs")
    private String avs;
    @SerializedName("card_details")
    private Card cardDetails;
}

