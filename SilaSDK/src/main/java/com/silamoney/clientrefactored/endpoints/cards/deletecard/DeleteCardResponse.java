package com.silamoney.clientrefactored.endpoints.cards.deletecard;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;

@Getter
public class DeleteCardResponse {

    private boolean success;
    private String reference;
    private String message;
    private String status;
    @SerializedName("response_time_ms")
    private String responseTimeMs;
}
