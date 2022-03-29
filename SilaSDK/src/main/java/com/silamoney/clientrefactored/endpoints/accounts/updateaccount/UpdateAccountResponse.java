package com.silamoney.clientrefactored.endpoints.accounts.updateaccount;

import java.util.List;

import com.google.gson.annotations.SerializedName;
import com.silamoney.clientrefactored.domain.Account;
import com.silamoney.clientrefactored.domain.Change;

import lombok.Getter;

@Getter
public class UpdateAccountResponse {

    private boolean success;
    private String message;
    private String status;
    private Account account;
    private List<Change> changes;
    private String reference;
    @SerializedName("response_time_ms")
    private String responseTimeMs;
}
