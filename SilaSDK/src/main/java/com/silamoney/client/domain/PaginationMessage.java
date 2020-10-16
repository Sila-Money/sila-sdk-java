package com.silamoney.client.domain;

import lombok.Builder;

@Builder
public class PaginationMessage {
    private Integer page;
    private Integer perPage;

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
