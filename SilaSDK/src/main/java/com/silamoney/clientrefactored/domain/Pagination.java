package com.silamoney.clientrefactored.domain;

import com.google.gson.annotations.SerializedName;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class Pagination {

    @SerializedName("returned_count")
    private long returnedCount;
    @SerializedName("total_count")
    private long totalCount;
    @SerializedName("current_page")
    private long currentPage;
    @SerializedName("total_pages")
    private long totalPages;
    
}
