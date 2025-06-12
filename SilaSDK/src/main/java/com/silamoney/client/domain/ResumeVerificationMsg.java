package com.silamoney.client.domain;

import com.google.gson.annotations.SerializedName;

import java.util.List;


/**
 * Object sent in the Get Verifications method
 *
 */
public class ResumeVerificationMsg {
  @SerializedName("header")
  private final Header header;

  @SerializedName("verification_uuid")
  private final String verificationUuid;

  @SerializedName("update")
  private final String update;

  @SerializedName("doc_ids")
  private final List<String> docIds;

  public ResumeVerificationMsg(String userHandle,
                               String appHandle,
                               String reference,
                               String verificationUuid,
                               String update,
                               List<String> docIds) {
    this.header = new Header(userHandle, appHandle);
    this.header.setReference(reference);
    this.verificationUuid = verificationUuid;
    this.update = update;
    this.docIds = docIds;
  }
}
