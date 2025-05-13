package com.silamoney.client.domain;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;

public class IdentityDocument {
  private static final String PASSPORT = "id_passport";
  private static final String PASSPORT_CARD = "id_passport_card";
  @Getter
  @SerializedName("doc_type")
  private String docType;
  @Getter
  @SerializedName("doc_id")
  private String docId;
  @Getter
  @SerializedName("doc_country")
  private String docCountry;
  @Getter
  @SerializedName("doc_state")
  private String docState;

  public IdentityDocument(String docType, String docId, String docLocation) {
    this.docType = docType;
    this.docId = docId;
    if (docType.equals(PASSPORT) || docType.equals(PASSPORT_CARD)) {
      this.docCountry = docLocation;
    } else {
      this.docState = docLocation;
    }
  }
}
