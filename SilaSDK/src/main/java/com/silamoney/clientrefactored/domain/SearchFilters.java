package com.silamoney.clientrefactored.domain;

import java.util.List;

import com.google.gson.annotations.SerializedName;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
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
    private Long maxSilaAmount;
    @SerializedName("min_sila_amount")
    private Long minSilaAmount;
    private List<String> statuses;
    @SerializedName("start_epoch")
    private Long startEpoch;
    @SerializedName("end_epoch")
    private Long endEpoch;
    private Long page;
    @SerializedName("per_page")
    private Long perPage;
    @SerializedName("transaction_types")
    private List<String> transactionTypes;
    @SerializedName("bank_account_name")
    private String bankAccountName;
    @SerializedName("blockchain_address")
    private String blockchainAddress;
    @SerializedName("source_id")
    private String sourceId;
    @SerializedName("destination_id")
    private String destinationId;
    @SerializedName("processing_type")
    private String processingType;
    @SerializedName("payment_method_id")
    private String paymentMethodId;
}
