package com.silamoney.client.domain;

import java.util.List;


import com.google.gson.annotations.SerializedName;
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
