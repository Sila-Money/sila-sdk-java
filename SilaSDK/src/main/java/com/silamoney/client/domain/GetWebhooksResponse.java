package com.silamoney.client.domain;

import com.google.gson.annotations.SerializedName;
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
