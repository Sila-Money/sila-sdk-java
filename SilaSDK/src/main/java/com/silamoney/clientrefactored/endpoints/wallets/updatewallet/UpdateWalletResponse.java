package com.silamoney.clientrefactored.endpoints.wallets.updatewallet;

import java.util.List;

import com.google.gson.annotations.SerializedName;
import com.silamoney.clientrefactored.domain.Change;
import com.silamoney.clientrefactored.domain.Wallet;

import lombok.Getter;

@Getter
public class UpdateWalletResponse {

    private boolean success;
    private String message;
    private String status;
    @SerializedName("wallet")
    private Wallet wallet;
    private List<Change> changes;
    private String reference;
    @SerializedName("response_time_ms")
    private String responseTimeMs;
}
