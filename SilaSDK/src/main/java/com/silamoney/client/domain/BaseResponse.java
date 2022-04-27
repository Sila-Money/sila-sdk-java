package com.silamoney.client.domain;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;

/**
 * Response used for the majority of endpoints.
 *
 * @author Karlo Lorenzana
 */
public class BaseResponse {

    @Getter
    private String reference;

    @Getter
    private String message;

    @Getter
    private String status;

    @Getter
    @Setter
    private Boolean success;

    @Getter
    @SerializedName("response_time_ms")
    private String responseTimeMs;

}
