package com.silamoney.client.domain;

import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;
import java.util.List;

/**
 * Object used in the GetTransactionsMsg object.
 *
 * @author loren
 */
public class SearchFilters {

    /**
     * Available values for the Transaction Types.
     */
    public enum TransactionTypesEnum {
        ISSUE("issue"),
        REDEEM("redeem"),
        TRANSFER("transfer");

        private final String value;

        TransactionTypesEnum(String value) {
            this.value = value;
        }

        /**
         * Gets the enum value.
         *
         * @return string value.
         */
        public String getValue() {
            return value;
        }
    }

    /**
     * Available values for the Statuses.
     */
    public enum StatusesEnum {
        PENDING("pending"),
        COMPLETE("complete"),
        SUCCESSFUL("successful"),
        FAILED("failed");

        private final String value;

        StatusesEnum(String value) {
            this.value = value;
        }

        /**
         * Gets the enum value.
         *
         * @return string value.
         */
        public String getValue() {
            return value;
        }
    }

    @SerializedName("transaction_id")
    private final String transactionId;

    @SerializedName("per_page")
    private final Integer perPage;

    @SerializedName("transaction_types")
    private List<String> transactionTypes = null;

    @SerializedName("max_sila_amount")
    private final Integer maxSilaAmount;

    @SerializedName("reference_id")
    private final String referenceId;

    @SerializedName("show_timelines")
    private final Boolean showTimelines;

    @SerializedName("sort_ascending")
    private final Boolean sortAscending;

    @SerializedName("end_epoch")
    private final Integer endEpoch;

    @SerializedName("start_epoch")
    private final Integer startEpoch;

    @SerializedName("statuses")
    private List<String> statuses;

    @SerializedName("page")
    private final Integer page;

    @SerializedName("min_sila_amount")
    private final Integer minSilaAmount;

    /**
     * Constructor for SearchFilters object.
     *
     * @param transactionId
     * @param perPage
     * @param transactionTypes
     * @param maxSilaAmount
     * @param referenceId
     * @param showTimelines
     * @param sortAscending
     * @param endEpoch
     * @param startEpoch
     * @param statuses
     * @param page
     * @param minSilaAmount
     */
    public SearchFilters(String transactionId, Integer perPage, List<TransactionTypesEnum> transactionTypes, Integer maxSilaAmount, String referenceId, Boolean showTimelines, Boolean sortAscending, Integer endEpoch, Integer startEpoch, List<StatusesEnum> statuses, Integer page, Integer minSilaAmount) {
        this.transactionId = transactionId;
        this.perPage = perPage;
        for (TransactionTypesEnum type : transactionTypes) {
            if (this.transactionTypes == null) {
                this.transactionTypes = new ArrayList<>();
            }
            this.transactionTypes.add(type.getValue());
        }
        this.maxSilaAmount = maxSilaAmount;
        this.referenceId = referenceId;
        this.showTimelines = showTimelines;
        this.sortAscending = sortAscending;
        this.endEpoch = endEpoch;
        this.startEpoch = startEpoch;
        for (StatusesEnum status : statuses) {
            if (this.statuses == null) {
                this.statuses = new ArrayList<>();
            }
            this.statuses.add(status.getValue());
        }
        this.page = page;
        this.minSilaAmount = minSilaAmount;
    }
}
