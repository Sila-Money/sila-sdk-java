package com.silamoney.clientrefactored.domain;

import com.google.gson.annotations.SerializedName;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Device {

    @SerializedName("device_fingerprint")
    private String deivceFingerprint;

    @SerializedName("session_identifier")
    private String sessionIdentifier;
}
