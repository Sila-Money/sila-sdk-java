package com.silamoney.client.domain;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;

@Getter
public class TransactionEntity {
    /**
     * String field used for the settled date.
     */
    @SerializedName("settled_date")
    private String settledDate;

    /**
     * String field used for the description.
     */
    private String description;

    /**
     * String field used for the category.
     */
    private String category;

    /**
     * String field used for the amount.
     */
    private String amount;

    /**
     * String field used for the running balance.
     */
    @SerializedName("running_balance")
    private String runningBalance;

    /**
     * String field used for the descriptor.
     */
    private String descriptor;
}
