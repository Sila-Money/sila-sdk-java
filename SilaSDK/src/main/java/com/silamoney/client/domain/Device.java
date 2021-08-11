package com.silamoney.client.domain;

import com.google.gson.annotations.SerializedName;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Device {
    @SerializedName("device_fingerprint")
    private String deviceFingerPrint;
}
