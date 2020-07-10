package com.silamoney.client.domain;

import com.google.gson.annotations.SerializedName;

public class Pagination {

    @SerializedName(value = "returned_count")
    private int returnedCount;
    @SerializedName(value = "total_count")
    private int totalCount;
    @SerializedName(value = "current_page")
    private int currentPage;
    @SerializedName(value = "total_pages")
    private int totalPages;

    /**
     * @return the returnedCount
     */
    public int getReturnedCount() {
        return returnedCount;
    }

    /**
     * @return the totalCount
     */
    public int getTotalCount() {
        return totalCount;
    }

    /**
     * @return the currentPage
     */
    public int getCurrentPage() {
        return currentPage;
    }

    /**
     * @return the totalPages
     */
    public int getTotalPages() {
        return totalPages;
    }    
}