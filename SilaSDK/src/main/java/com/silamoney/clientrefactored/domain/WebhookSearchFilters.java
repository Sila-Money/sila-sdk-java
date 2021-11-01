package com.silamoney.clientrefactored.domain;

import com.google.gson.annotations.SerializedName;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Builder
@Data
public class WebhookSearchFilters {

    @SerializedName("uuid")
    private String uuid;
    @SerializedName("event_type")
    private String eventType;
    @SerializedName("endpoint_name")
    private String endpointName;
    @SerializedName("user_handle")
    private String userHandle;
    @SerializedName("start_epoch")
    private String startEpoch;
    @SerializedName("end_epoch")
    private String endEpoch;
    @SerializedName("delivered")
    private Boolean delivered;
    @SerializedName("sort_ascending")
    private Boolean sortAscending;
    @SerializedName("page")
    private int page;
    @SerializedName("per_page")
    private int perPage;
}
