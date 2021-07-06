package com.silamoney.clientrefactored.domain;

import com.google.gson.annotations.SerializedName;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Pagination {

    @SerializedName("returned_count")
    private Long returnedCount;
    @SerializedName("total_count")
    private Long totalCount;
    @SerializedName("current_page")
    private Long currentPage;
    @SerializedName("total_pages")
    private Long totalPages;
    
}
