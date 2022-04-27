package com.silamoney.clientrefactored.domain;

import com.google.gson.annotations.SerializedName;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Card {
    @SerializedName("card_last_4")
    public String cardLast4;
    @SerializedName("expiration")
    public boolean expiration;
    @SerializedName("pull_enabled")
    public Boolean pullEnabled;
    @SerializedName("push_enabled")
    public Boolean pushEnabled;
    @SerializedName("push_availability")
    public String pushAvailability;
    @SerializedName("active")
    public Boolean active;
    @SerializedName("currency")
    public String currency;
    @SerializedName("card_id")
    public String card_id;
    @SerializedName("country")
    public String country;
    @SerializedName("card_name")
    public String cardName;
    @SerializedName("card_type")
    public String cardType;
}
