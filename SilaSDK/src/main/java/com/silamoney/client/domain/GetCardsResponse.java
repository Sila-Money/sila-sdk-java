package com.silamoney.client.domain;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
public class GetCardsResponse {
    @SerializedName("status")
    private String status;
    @Setter
    @SerializedName("success")
    private Boolean success;
    @SerializedName("cards")
    private List<Card> cards;
    @SerializedName("reference")
    private String reference;
    @SerializedName("message")
    private String message;
    @SerializedName("pagination")
    private Pagination pagination;

}
