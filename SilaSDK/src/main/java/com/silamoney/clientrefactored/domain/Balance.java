package com.silamoney.clientrefactored.domain;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;

@Getter
public class Balance {
    @SerializedName("pending_sila_balance")
    private double pendingSilaBalance;

    @SerializedName("available_sila_balance")
    private double availableSilaBalance;
}
