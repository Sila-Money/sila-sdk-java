package com.silamoney.client.domain;

import java.util.ArrayList;
import java.util.Map;

import com.google.gson.annotations.SerializedName;

public class GetNaicsCategoriesResponse {

    private boolean success;
    @SerializedName(value = "naics_categories")
    private Map<String, ArrayList<NaicsCategoryDescription>> naicsCategories;

    /**
     * @return the success
     */
    public boolean isSuccess() {
        return success;
    }

    /**
     * @param success the success to set
     */
    public void setSuccess(boolean success) {
        this.success = success;
    }

    /**
     * @return the naicsCategories
     */
    public Map<String, ArrayList<NaicsCategoryDescription>> getNaicsCategories() {
        return naicsCategories;
    }

    /**
     * @param naicsCategories the naicsCategories to set
     */
    public void  setNaicsCategories(Map<String, ArrayList<NaicsCategoryDescription>> naicsCategories) {
        this.naicsCategories = naicsCategories;
    }

}