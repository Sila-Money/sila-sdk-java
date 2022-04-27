package com.silamoney.client.domain;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
public class GetCardsResponse extends BaseResponse {
    @SerializedName("cards")
    private List<Card> cards;
    @SerializedName("pagination")
    private Pagination pagination;
}