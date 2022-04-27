package com.silamoney.clientrefactored.endpoints.webhooks.getWebhooks;

import com.google.gson.annotations.SerializedName;
import com.silamoney.clientrefactored.domain.Pagination;
import com.silamoney.clientrefactored.domain.Webhook;
import lombok.Getter;

import java.util.List;
@Getter
public class GetWebhooksResponse {
    private boolean success;
    private String message;
    private String status;
    private String reference;

    @SerializedName("webhooks")
    private List<Webhook> webhooks;
    private int page;
    @SerializedName("returned_count")
    private int returnedCount;
    @SerializedName("total_count")
    private int totalCount;
    private Pagination pagination;
    @SerializedName("response_time_ms")
    private String responseTimeMs;
}
