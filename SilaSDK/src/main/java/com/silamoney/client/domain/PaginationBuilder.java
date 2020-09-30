package com.silamoney.client.domain;

public class PaginationBuilder implements Pageable<PaginationBuilder> {
    private Integer page;
    private Integer perPage;

    public PaginationBuilder atPage(Integer page) {
        this.page = page;
        return this;
    }

    public PaginationBuilder withPerPage(Integer perPage) {
        this.perPage = perPage;
        return this;
    }

    public PaginationMsg build() {
        return new PaginationMsg(this.page, this.perPage);
    }
}
