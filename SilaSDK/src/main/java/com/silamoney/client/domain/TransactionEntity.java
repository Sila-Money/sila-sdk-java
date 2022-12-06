package com.silamoney.client.domain;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;

@Getter
public class TransactionEntity {
    @SerializedName("settled_date")
    private String settledDate;
    private String description;
    private String category;
    private String amount;
    @SerializedName("running_balance")
    private String runningBalance;


}
