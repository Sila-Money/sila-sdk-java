package com.silamoney.client.domain;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
public class GetEntitiesResponse {

    private boolean success;
    private Entities entities;
    private Pagination pagination;
    @Getter
    private String reference;
    @Getter
    @SerializedName("response_time_ms")
    private String responseTimeMs;
    /**
     * @return the success
     */
    public boolean isSuccess() {
        return success;
    }

    /**
     * @return the entities
     */
    public Entities getEntities() {
        return entities;
    }

    /**
     * @return the pagination
     */
    public Pagination getPagination() {
        return pagination;
    }
}