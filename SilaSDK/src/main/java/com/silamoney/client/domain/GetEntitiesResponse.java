package com.silamoney.client.domain;

public class GetEntitiesResponse {

    private boolean success;
    private Entities entities;
    private Pagination pagination;

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