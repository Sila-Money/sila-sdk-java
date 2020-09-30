package com.silamoney.client.domain;

import com.google.gson.annotations.SerializedName;

import lombok.Getter;

public class Pagination {
    @Getter
    @SerializedName(value = "returned_count")
    private int returnedCount;
    @Getter
    @SerializedName(value = "total_count")
    private int totalCount;
    @Getter
    @SerializedName(value = "current_page")
    private int currentPage;
    @Getter
    @SerializedName(value = "total_pages")
    private int totalPages;
}