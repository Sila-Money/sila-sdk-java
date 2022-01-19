package com.silamoney.client.domain;

import lombok.Getter;
public class GetEntitiesResponse {

    private boolean success;
    private Entities entities;
    private Pagination pagination;
    @Getter
    private String reference;

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