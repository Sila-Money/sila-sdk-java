package com.silamoney.client.domain;

import java.util.List;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;

public class GetWalletsResponse extends PaginationInformation {

    @Getter
    private boolean success;
    @Getter
    private List<Wallet> wallets;

    @Getter
    private String reference;
    @Getter
    @SerializedName("response_time_ms")
    private String responseTimeMs;
}