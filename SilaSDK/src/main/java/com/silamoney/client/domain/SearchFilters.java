package com.silamoney.client.domain;

import com.google.gson.annotations.SerializedName;

import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * Object used in the GetTransactionsMsg, GetWalletsMsg object.
 *
 * @author loren
 */
public class SearchFilters {

    /**
     * Available values for the Transaction Types.
     */
    public enum TransactionTypesEnum {

        /**
         * String value for issue type.
         */
        ISSUE("issue"),
        /**
         * String value for redeem type.
         */
        REDEEM("redeem"),
        /**
         * String value for transfer type.
         */
        TRANSFER("transfer");

        private final String value;

        TransactionTypesEnum(String value) {
            this.value = value;
        }

        /**
         * Gets the enum value.
         *
         * @return SearchFilters  string value.
         */
        public String getValue() {
            return value;
        }
    }

    /**
     * Available values for the Statuses.
     */
    public enum StatusesEnum {
        /**
         * String value for queued status.
         */
        QUEUED("queued"),
        /**
         * String value for pending status.
         */
        PENDING("pending"),
        /**
         * String value for pending_confirmation status.
         */
        PENDING_CONFIRMATION("pending_confirmation"),
        /**
         * String value for reversed status.
         */
        REVERSED("reversed"),
        /**
         * String value for failed status.
         */
        FAILED("failed"),
        /**
         * String value for success status.
         */
        SUCCESS("success"),
        /**
         * String value for rollback status.
         */
        ROLLBACK("rollback"),
        /**
         * String value for review status.
         */
        REVIEW("review");

        private final String value;

        StatusesEnum(String value) {
            this.value = value;
        }

        /**
         * Gets the enum value.
         *
         * @return SearchFilters  string value.
         */
        public String getValue() {
            return value;
        }
    }

    @SerializedName("transaction_id")
    private String transactionId = null;

    @SerializedName("per_page")
    private Integer perPage = null;

    @SerializedName("transaction_types")
    private List<String> transactionTypes = null;

    @SerializedName("max_sila_amount")
    private Integer maxSilaAmount = null;

    @SerializedName("reference_id")
    private String referenceId = null;

    @SerializedName("show_timelines")
    private Boolean showTimelines = false;

    @SerializedName("sort_ascending")
    private Boolean sortAscending = false;

    @SerializedName("end_epoch")
    private Integer endEpoch = null;

    @SerializedName("start_epoch")
    private Integer startEpoch = null;

    @SerializedName("statuses")
    private List<String> statuses = null;

    @SerializedName("page")
    private Integer page = null;

    @SerializedName("min_sila_amount")
    private Integer minSilaAmount = null;

    /**
     * Fields for get wallets endpoint
     */
    @SerializedName("blockchain_address")
    public String blockChainAddress;

    @SerializedName("blockchain_network")
    public String blockChainNetwork;

    @SerializedName("nickname")
    public String nickname;

    @SerializedName("bank_account_name")
    @Setter
    public String bankAccountName;

    @SerializedName("wallet_id")
    public String uuid;

    @SerializedName("source_id")
    private String sourceId;

    @SerializedName("destination_id")
    private String destinationId;

    @SerializedName("processing_type")
    public String processingType;

    @SerializedName("payment_method_id")
    private String paymentMethodId;


    public String getPaymentMethodId() {
        return paymentMethodId;
    }

    public void setPaymentMethodId(String paymentMethodId) {
        this.paymentMethodId = paymentMethodId;
    }

    public String getProcessingType() {
        return processingType;
    }

    public void setProcessingType(ProcessingTypeEnum processingType) {
        this.processingType = processingType == null ? null : processingType.getValue();
    }

    public String getSourceId() {
        return sourceId;
    }

    public void setSourceId(String sourceId) {
        this.sourceId = sourceId;
    }

    public String getDestinationId() {
        return destinationId;
    }

    public void setDestinationId(String destinationId) {
        this.destinationId = destinationId;
    }

    /**
     * Sets the transaction id to the filters.
     *
     * @param transactionId
     * @return SearchFilters
     */
    public SearchFilters setTransactionId(String transactionId) {
        this.transactionId = transactionId;
        return this;
    }

    /**
     * Sets the transactions per page to the filters.
     *
     * @param perPage
     * @return SearchFilters
     */
    public SearchFilters setPerPage(Integer perPage) {
        this.perPage = perPage;
        return this;
    }

    /**
     * Sets the transaction types to the filters.
     *
     * @param transactionTypes
     * @return SearchFilters
     */
    public SearchFilters setTransactionTypes(List<TransactionTypesEnum> transactionTypes) {
        if (transactionTypes != null) {
            this.transactionTypes = new ArrayList<>();
            transactionTypes.forEach(transactionType ->
                    this.transactionTypes.add(transactionType.getValue())
            );
        }
        return this;
    }

    /**
     * Sets the max amount to the filters.
     *
     * @param maxSilaAmount
     * @return SearchFilters
     */
    public SearchFilters setMaxSilaAmount(Integer maxSilaAmount) {
        this.maxSilaAmount = maxSilaAmount;
        return this;
    }

    /**
     * Sets the reference id to the filters.
     *
     * @param referenceId
     * @return SearchFilters
     */
    public SearchFilters setReferenceId(String referenceId) {
        this.referenceId = referenceId;
        return this;
    }

    /**
     * Sets the show time lines to true in the filters.
     *
     * @return SearchFilters
     */
    public SearchFilters showTimelines() {
        this.showTimelines = true;
        return this;
    }

    /**
     * Sets the sort ascending to true in the filters.
     *
     * @return SearchFilters
     */
    public SearchFilters sortAscending() {
        this.sortAscending = true;
        return this;
    }

    /**
     * Sets the end epoch to the filters.
     *
     * @param endEpoch
     * @return SearchFilters
     */
    public SearchFilters setEndEpoch(Integer endEpoch) {
        this.endEpoch = endEpoch;
        return this;
    }

    /**
     * Sets the start epoch to the filters.
     *
     * @param startEpoch
     * @return SearchFilters
     */
    public SearchFilters setStartEpoch(Integer startEpoch) {
        this.startEpoch = startEpoch;
        return this;
    }

    /**
     * Sets the statuses to the filters.
     *
     * @param statuses
     * @return SearchFilters
     */
    public SearchFilters setStatuses(List<StatusesEnum> statuses) {
        if (statuses != null) {
            this.statuses = new ArrayList<>();
            statuses.forEach(status ->
                    this.statuses.add(status.getValue())
            );
        }
        return this;
    }

    /**
     * Sets the page to be retreived to the filters.
     *
     * @param page
     * @return SearchFilters
     */
    public SearchFilters setPage(Integer page) {
        this.page = page;
        return this;
    }

    /**
     * Sets the min amount to the filters.
     *
     * @param minSilaAmount
     * @return SearchFilters
     */
    public SearchFilters setMinSilaAmount(Integer minSilaAmount) {
        this.minSilaAmount = minSilaAmount;
        return this;
    }

    /**
     * Sets the block chain address for get wallets endpoint filter
     *
     * @param blockChainAddress
     * @return SearchFilters
     */
    public SearchFilters setBlockChainAddress(String blockChainAddress) {
        this.blockChainAddress = blockChainAddress;
        return this;
    }

    /**
     * Sets the block chain network for get wallets endpoint filter
     *
     * @param blockChainNetwork
     * @return SearchFilters
     */
    public SearchFilters setBlockChainNetwork(String blockChainNetwork) {
        this.blockChainNetwork = blockChainNetwork;
        return this;
    }

    /**
     * Sets the nickname for get wallets endpoint filter
     *
     * @param nickname
     * @return SearchFilters
     */
    public SearchFilters setNickname(String nickname) {
        this.nickname = nickname;
        return this;
    }

    /**
     * Sets the uuid for get wallets endpoint filter
     *
     * @param uuid
     * @return SearchFilters
     */
    public SearchFilters setUuid(String uuid) {
        this.uuid = uuid;
        return this;
    }
}
