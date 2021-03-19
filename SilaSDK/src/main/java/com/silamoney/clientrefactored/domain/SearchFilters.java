package com.silamoney.clientrefactored.domain;

import java.util.List;

import com.google.gson.annotations.SerializedName;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class SearchFilters {
    
    @SerializedName("transaction_id")
    private String transactionId;
    @SerializedName("reference_id")
    private String referenceId;
    @SerializedName("show_timelines")
    private boolean showTimelines;
    @SerializedName("sort_ascending")
    private boolean sortAscending;
    @SerializedName("max_sila_amount")
    private long maxSilaAmount;
    @SerializedName("min_sila_amount")
    private long minSilaAmount;
    private List<String> statuses;
    @SerializedName("start_epoch")
    private long startEpoch;
    @SerializedName("end_epoch")
    private long endEpoch;
    private long page;
    @SerializedName("per_page")
    private long perPage;
    @SerializedName("transaction_types")
    private List<String> transactionTypes;
    @SerializedName("bank_account_name")
    private String bankAccountName;

}
