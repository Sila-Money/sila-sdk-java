package com.silamoney.client.domain;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;

import java.util.List;

@Getter
public class GetVerificationsResponse extends BaseResponse {
  @SerializedName(value = "verifications")
  private List<PriorityVerification> verifications;

}
