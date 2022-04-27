package com.silamoney.clientrefactored.domain;

import java.util.List;

import com.google.gson.annotations.SerializedName;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Transaction {
    
    @SerializedName("user_handle")
    private String userHAndle;
    @SerializedName("reference_id")
    private String referenceId;
    @SerializedName("transaction_id")
    private String transactionId;
    @SerializedName("transaction_hash")
    private String transactionHash;
    @SerializedName("transaction_type")
    private String transactionType;
    @SerializedName("sila_amount")
    private Long silaAmount;
    private String status;
    @SerializedName("usd_status")
    private String usdStatus;
    @SerializedName("token_status")
    private String tokenStatus;
    private String created;
    @SerializedName("last_update")
    private String lastUpdate;
    @SerializedName("created_epoch")
    private Long createdEpoch;
    @SerializedName("last_update_epoch")
    private Long lastUpdateEpoch;
    private String descriptor;
    @SerializedName("ach_name")
    private String achName;
    @SerializedName("destination_address")
    private String destinationAddress;
    @SerializedName("destination_handle")
    private String destinationHandle;
    @SerializedName("handle_address")
    private String handleAddress;
    @SerializedName("bank_account_name")
    private String bankAccountName;
    @SerializedName("card_name")
    private String cardName;
    @SerializedName("descriptot_ach")
    private String descriptorAch;
    @SerializedName("processing_type")
    private String processingType;
    private String submitted;
    @SerializedName("submitted_epoch")
    private Long submittedEpoch;
    @SerializedName("trace_number")
    private String traceNumber;
    private String addenda;
    @SerializedName("error_code")
    private String erroreCode;
    @SerializedName("error_msg")
    private String errorMsg;
    @SerializedName("return_code")
    private String returnCode;
    @SerializedName("return_desc")
    private String returnDesc;
    private List<Timeline> timeline;

    @SerializedName("sila_ledger_type")
    public String silaLedgerType;
    @SerializedName("destination_sila_ledger_type")
    public String destinationSilaLedgerType;
    @SerializedName("destination_ledger_account_id")
    public String destinationLedgerAccountId;
    @SerializedName("ledger_account_id ")
    public String ledgerAccountId;
    @SerializedName("source_id")
    public String sourceId;
    @SerializedName("destination_id")
    public String destinationId;
    @SerializedName("effective_date")
    public String effectiveDate;
    @SerializedName("effective_epoch")
    public String effectiveEpoch;
}
