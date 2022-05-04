package com.silamoney.clientrefactored.endpoints.webhooks.getWebhooks;

import com.google.gson.annotations.SerializedName;
import com.silamoney.clientrefactored.domain.BaseResponse;
import com.silamoney.clientrefactored.domain.Pagination;
import com.silamoney.clientrefactored.domain.Webhook;
import lombok.Getter;

import java.util.List;
@Getter
public class GetWebhooksResponse extends BaseResponse {

    @SerializedName("webhooks")
    private List<Webhook> webhooks;
    private int page;
    @SerializedName("returned_count")
    private int returnedCount;
    @SerializedName("total_count")
    private int totalCount;
    private Pagination pagination;
}
