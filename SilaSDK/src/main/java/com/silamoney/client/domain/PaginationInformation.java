package com.silamoney.client.domain;

import com.google.gson.annotations.SerializedName;

public class PaginationInformation {

    /**
     * Integer field used for the page.
     */
    @SerializedName("page")
    public Integer page;
    /**
     * Integer field used for the returned count.
     */
    @SerializedName("returned_count")
    public Integer returnedCount;
    /**
     * Integer field used for the total count.
     */
    @SerializedName("total_count")
    public Integer totalCount;
    @SerializedName("total_page_count")
    public Integer totalPageCount;
    
}