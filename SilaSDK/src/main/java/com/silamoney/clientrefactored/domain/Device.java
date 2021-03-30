package com.silamoney.clientrefactored.domain;

import com.google.gson.annotations.SerializedName;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class Device {

    @SerializedName("device_fingerprint")
    private String deivceFingerprint;

}
