package com.silamoney.clientrefactored.domain;

import com.google.gson.annotations.SerializedName;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Identity {
    
    @SerializedName("identity_alias")
    private String identityAlias;
    @SerializedName("identity_value")
    private String identityValue;

}
