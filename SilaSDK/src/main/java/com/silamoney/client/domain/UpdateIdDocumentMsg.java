package com.silamoney.client.domain;

import com.google.gson.annotations.SerializedName;

/**
 * Message used for updating ID documents.
 *
 * @author Icon Systems
 */
public class UpdateIdDocumentMsg {
    
    @SerializedName("header")
    private final HeaderMsg header;
    
    @SerializedName("uuid")
    private final String uuid;
    
    @SerializedName("doc_type")
    private final String docType;
    
    @SerializedName("doc_id")
    private final String docId;
    
    @SerializedName("doc_state")
    private final String docState;

    /**
     * Constructor for UpdateIdDocumentMsg.
     *
     * @param userHandle The user handle
     * @param appHandle The app handle
     * @param uuid The document UUID
     * @param docType The document type
     * @param docId The document ID
     * @param docState The document state
     */
    public UpdateIdDocumentMsg(String userHandle, String appHandle, String uuid, String docType, String docId, String docState) {
        this(userHandle, appHandle, uuid, docType, docId, docState, null);
    }

    /**
     * Constructor for UpdateIdDocumentMsg.
     *
     * @param userHandle The user handle
     * @param appHandle The app handle
     * @param uuid The document UUID
     * @param docType The document type
     * @param docId The document ID
     * @param docState The document state
     * @param reference The reference (optional)
     */
    public UpdateIdDocumentMsg(String userHandle, String appHandle, String uuid, String docType, String docId, String docState, String reference) {
        this.header = new HeaderMsg(userHandle, appHandle, reference);
        this.uuid = uuid;
        this.docType = docType;
        this.docId = docId;
        this.docState = docState;
    }

    /**
     * Gets the header.
     *
     * @return the header
     */
    public HeaderMsg getHeader() {
        return header;
    }

    /**
     * Gets the document UUID.
     *
     * @return the document UUID
     */
    public String getUuid() {
        return uuid;
    }

    /**
     * Gets the document type.
     *
     * @return the document type
     */
    public String getDocType() {
        return docType;
    }

    /**
     * Gets the document ID.
     *
     * @return the document ID
     */
    public String getDocId() {
        return docId;
    }

    /**
     * Gets the document state.
     *
     * @return the document state
     */
    public String getDocState() {
        return docState;
    }
} 