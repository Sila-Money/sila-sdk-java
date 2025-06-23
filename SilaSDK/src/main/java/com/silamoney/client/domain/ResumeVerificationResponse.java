package com.silamoney.client.domain;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;

import java.util.List;

@Getter
public class ResumeVerificationResponse extends BaseResponse {
  @SerializedName(value = "verification_uuid")
  private String verificationUuid;

}
