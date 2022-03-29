package com.silamoney.clientrefactored.endpoints.cards.getcards;

import com.google.gson.annotations.SerializedName;
import com.silamoney.clientrefactored.domain.Card;
import com.silamoney.clientrefactored.domain.Pagination;
import lombok.Getter;

import java.util.List;

@Getter
public class GetCardsResponse {

    private String status;
    private Boolean success;
    @SerializedName("cards")
    private List<Card> cards;
    private String reference;
    private String message;
    private Pagination pagination;
    @SerializedName("response_time_ms")
    private String responseTimeMs;
}
