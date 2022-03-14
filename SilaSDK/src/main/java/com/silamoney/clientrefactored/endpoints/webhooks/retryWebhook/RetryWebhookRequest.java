package com.silamoney.clientrefactored.endpoints.webhooks.retryWebhook;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class RetryWebhookRequest {
    private String userHandle;
    private String eventUuid;
}
