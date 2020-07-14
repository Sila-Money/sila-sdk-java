package com.silamoney.client.domain;

import com.google.gson.annotations.SerializedName;

public class EntityAudit {

    @SerializedName("added_epoch")
    private int addedEpoch;
    @SerializedName("modified_epoch")
    private int modifiedEpoch;
    @SerializedName("uuid")
    private String uuid;

    /**
     * @return the addedEpoch
     */
    public int getAddedEpoch() {
        return addedEpoch;
    }

    /**
     * @return the modifiedEpoch
     */
    public int getModifiedEpoch() {
        return modifiedEpoch;
    }

    /**
     * @return the uuid
     */
    public String getUuid() {
        return uuid;
    }
}