package com.silamoney.client.domain;

import java.util.ArrayList;
import java.util.Map;

import com.google.gson.annotations.SerializedName;

import lombok.Getter;

@Getter
public class GetNaicsCategoriesResponse {

    private boolean success;
    @SerializedName(value = "naics_categories")
    private Map<String, ArrayList<NaicsCategoryDescription>> naicsCategories;
    private String status;
    private String reference;
    @SerializedName("response_time_ms")
    private String responseTimeMs;
}