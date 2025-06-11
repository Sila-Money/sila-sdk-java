package com.silamoney.client.domain;

import com.google.gson.annotations.SerializedName;



/**
 * Object sent in the Get Verifications method
 *
 */
public class GetVerificationsMsg {
  @SerializedName("header")
  private final Header header;

  public GetVerificationsMsg(String userHandle,
                      String appHandle,String reference) {
    this.header = new Header(userHandle, appHandle);
    this.header.setReference(reference);
  }
}
