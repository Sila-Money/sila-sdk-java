package com.silamoney.clientrefactored.endpoints.transactions.reversetransaction;

import java.util.List;

import com.google.gson.annotations.SerializedName;
import com.silamoney.clientrefactored.domain.Pagination;
import com.silamoney.clientrefactored.domain.Transaction;

import lombok.Getter;

@Getter
public class ReverseTransactionResponse {

    private boolean success;
    private String message;
    private String status;
    private String reference;

    @SerializedName("error_code")
    private String errorCode;
    @SerializedName("response_time_ms")
    private String responseTimeMs;

}

