package com.silamoney.clientrefactored.endpoints.webhooks.retryWebhook;

import lombok.Getter;

@Getter
public class RetryWebhookResponse {
    private boolean success;
    private String message;
    private String status;
    private String reference;

}
