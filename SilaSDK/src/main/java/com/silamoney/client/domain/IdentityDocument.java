package com.silamoney.client.domain;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;

public class IdentityDocument {
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
}
