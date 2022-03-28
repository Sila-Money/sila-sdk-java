package com.silamoney.clientrefactored.endpoints.accounts.getinstitutions;

import java.util.List;

import com.google.gson.annotations.SerializedName;
import com.silamoney.clientrefactored.domain.Institution;
import com.silamoney.clientrefactored.domain.Pagination;

import lombok.Getter;

@Getter
public class GetInstitutionsResponse {

    private boolean success;
    private String message;
    private String status;
    private String reference;
    @SerializedName("response_time_ms")
    private String responseTimeMs;

    private List<Institution> institutions;
    private int page;
    @SerializedName("returned_count")
    private int returnedCount;
    @SerializedName("total_count")
    private int totalCount;
    private Pagination pagination;

}
