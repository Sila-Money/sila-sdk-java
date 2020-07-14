package com.silamoney.client.domain;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import java.util.Map;

public class BadRequestResponse extends BaseResponse {
    @Getter
    @SerializedName(value = "validation_details")
    private Map<String, Object> validationDetails;
}
