package com.silamoney.client.domain;

import com.google.gson.annotations.SerializedName;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
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
