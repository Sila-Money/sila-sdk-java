package com.silamoney.clientrefactored.endpoints.webhooks.retryWebhook;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;

@Getter
public class RetryWebhookResponse {
    private boolean success;
    private String message;
    private String status;
    private String reference;
    @SerializedName("response_time_ms")
    private String responseTimeMs;
}
