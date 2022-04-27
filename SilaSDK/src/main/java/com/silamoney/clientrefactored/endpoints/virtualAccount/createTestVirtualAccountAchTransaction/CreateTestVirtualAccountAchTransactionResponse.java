package com.silamoney.clientrefactored.endpoints.virtualAccount.createTestVirtualAccountAchTransaction;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;

@Getter
public class CreateTestVirtualAccountAchTransactionResponse {

    private boolean success;
    private String reference;
    private String message;
    private String status;
    @SerializedName("response_time_ms")
    private String responseTimeMs;

}
