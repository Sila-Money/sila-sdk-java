package com.silamoney.clientrefactored.endpoints.accounts.getinstitutions;

import java.util.List;

import com.google.gson.annotations.SerializedName;
import com.silamoney.clientrefactored.domain.BaseResponse;
import com.silamoney.clientrefactored.domain.Institution;
import com.silamoney.clientrefactored.domain.Pagination;

import lombok.Getter;

@Getter
public class GetInstitutionsResponse extends BaseResponse {


    private List<Institution> institutions;
    private int page;
    @SerializedName("returned_count")
    private int returnedCount;
    @SerializedName("total_count")
    private int totalCount;
    private Pagination pagination;

}
