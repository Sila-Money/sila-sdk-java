package com.silamoney.client.domain;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;

@Getter
public class Webhook {
    @SerializedName("created_epoch")
    private String createdEpoch;
    @SerializedName("last_update_epoch")
    private String lastUpdateEpoch;
    @SerializedName("next_attempt_epoch")
    private String nextAttemptEpoch;
    @SerializedName("event_type")
    private String eventType;
    @SerializedName("endpoint_name")
    private String endpointName;
    @SerializedName("endpoint_url")
    private String endpointUrl;
    @SerializedName("uuid")
    private String uuid;
    @SerializedName("delivered")
    private Boolean delivered;
    @SerializedName("details")
    private Details details;
    @SerializedName("attempts")
    private int attempts;
    @SerializedName("responses")
    private Object responses;
    @SerializedName("processing")
    private Boolean processing;
    @SerializedName("user_handle")
    private String userHandle;

}
