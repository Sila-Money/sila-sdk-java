package com.silamoney.clientrefactored.domain;

import java.util.List;

import com.google.gson.annotations.SerializedName;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class InstitutionSearchFilters {
    
    @SerializedName("institution_name")
    private String institutionName;
    @SerializedName("routing_number")
    private String routingNumber;
    @SerializedName("page")
    private int page;
    @SerializedName("per_page")
    private int perPage;

}
