package com.silamoney.clientrefactored.endpoints.cards.getcards;

import com.google.gson.annotations.SerializedName;
import com.silamoney.clientrefactored.domain.BaseResponse;
import com.silamoney.clientrefactored.domain.Card;
import com.silamoney.clientrefactored.domain.Pagination;
import lombok.Getter;

import java.util.List;

@Getter
public class GetCardsResponse extends BaseResponse {

    @SerializedName("cards")
    private List<Card> cards;
    private Pagination pagination;
}
