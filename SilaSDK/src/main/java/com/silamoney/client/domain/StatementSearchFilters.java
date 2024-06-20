package com.silamoney.client.domain;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class StatementSearchFilters {

    @SerializedName("start_month")
    private String startMonth;
    @SerializedName("end_month")
    private String endMonth;
    @SerializedName("month")
    private String month;
    @SerializedName("page")
    private int page;
    @SerializedName("per_page")
    private int perPage;

}
