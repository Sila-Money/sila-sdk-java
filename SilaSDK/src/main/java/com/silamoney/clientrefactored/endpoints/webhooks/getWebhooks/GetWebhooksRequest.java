package com.silamoney.clientrefactored.endpoints.webhooks.getWebhooks;

import com.silamoney.clientrefactored.domain.WebhookSearchFilters;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class GetWebhooksRequest {
    private String userHandle;
    private WebhookSearchFilters searchFilters;
}
