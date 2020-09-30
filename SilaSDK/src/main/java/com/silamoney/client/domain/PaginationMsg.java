package com.silamoney.client.domain;

public class PaginationMsg {
    private Integer page;
    private Integer perPage;

    PaginationMsg(Integer page, Integer perPage) {
        this.page = page;
        this.perPage = perPage;
    }

    public String getUrlParams() {
        String params = "";
        if (this.page != null) {
            params += String.format("?page=%d", this.page);
        }
        if (this.perPage != null) {
            params += params.isEmpty() ? "?" : "&";
            params += String.format("per_page=%d", this.perPage);
        }
        return params;
    }
}
