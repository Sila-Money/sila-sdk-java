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
    public String reference;

    @Getter
    public String message;

    @Getter
    public String status;

    @Getter
    @Setter
    public Boolean success;

    @Getter
    @SerializedName("response_time_ms")
    public String responseTimeMs;

}
