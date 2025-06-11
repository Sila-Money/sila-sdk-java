package com.silamoney.client.domain;

import com.google.gson.annotations.SerializedName;

public class PriorityVerification {
  @SerializedName(value = "verification_uuid")
  private String verificationUuid;

  private String flow;

  @SerializedName(value = "verification_status")
  private String verificationStatus;

  @SerializedName(value = "requested_at")
  private int requestedAt;

  /**
   * @return the verificationUuid
   */
  public String getVerificationUuid() {
    return verificationUuid;
  }

  /**
   * @param verificationUuid the verificationUuid to set
   */
  public void setVerificationUuid(String verificationUuid) {
    this.verificationUuid = verificationUuid;
  }

  /**
   * @return the flow
   */
  public String getFlow() {
    return flow;
  }

  /**
   * @param flow the flow to set
   */
  public void setFlow(String flow) {
    this.flow = flow;
  }

  /**
   * @return the verificationStatus
   */
  public String getVerificationStatus() {
    return verificationStatus;
  }

  /**
   * @param verificationStatus the verificationStatus to set
   */
  public void setVerificationStatus(String verificationStatus) {
    this.verificationStatus = verificationStatus;
  }

  /**
   * @return the requestedAt
   */
  public int getRequestedAt() {
    return requestedAt;
  }

  /**
   * @param requestedAt the requestedAt to set
   */
  public void setRequestedAt(int requestedAt) {
    this.requestedAt = requestedAt;
  }
}
