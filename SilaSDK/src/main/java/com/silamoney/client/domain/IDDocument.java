package com.silamoney.client.domain;

import com.google.gson.annotations.SerializedName;

import lombok.Getter;

/**
 * Object used in the entity msg.
 *
 * @author Karlo Lorenzana
 */
@Getter
 public class IDDocument {

    public boolean isEmpty() {
        return (docType == null || docType.isEmpty()) &&
                (docId == null || docId.isEmpty()) &&
                (docState == null || docState.isEmpty()) &&
                (docCountry == null || docCountry.isEmpty());
    }

    @SerializedName("doc_type")
    private final String docType;
    @SerializedName("doc_id")
    private final String docId;
    @SerializedName("doc_state")
    private String docState;
    @SerializedName("doc_country")
    private String docCountry;

    /**
     * Constructor for the IDDocument object.
     *
     * @param user
     */
    public IDDocument(User user) {
        this.docType = user.getDocType();
        this.docId = user.getDocId();
        this.docState = user.getDocState();
        this.docCountry = user.getDocCountry();
    }
}