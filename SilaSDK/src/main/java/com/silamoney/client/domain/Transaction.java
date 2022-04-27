package com.silamoney.client.domain;

import com.google.gson.annotations.SerializedName;
import java.util.List;

/**
 * Object used in the GetTransaction Response object.
 *
 * @author Karlo Lorenzana
 */
public class Transaction {

    /**
     * String field used for the user handle.
     */
    @SerializedName("user_handle")
    public String userHandle;
    /**
     * String field used for the reference id.
     */
    @SerializedName("reference_id")
    public String referenceId;
    /**
     * String field used for the transaction id.
     */
    @SerializedName("transaction_id")
    public String transactionId;
    /**
     * String field used for the transaction hash.
     */
    @SerializedName("transaction_hash")
    public String transactionHash;
    /**
     * String field used for the transaction type.
     */
    @SerializedName("transaction_type")
    public String transactionType;
    /**
     * Integer field used for the sila amount.
     */
    @SerializedName("sila_amount")
    public Integer silaAmount;
    /**
     * String field used for the bank account name.
     */
    @SerializedName("bank_account_name")
    public String bankAccountName;
    /**
     * String field used for the handle address.
     */
    @SerializedName("handle_address")
    public String handleAddress;
    /**
     * String field used for the status.
     */
    @SerializedName("status")
    public String status;
    /**
     * String field used for the usd status.
     */
    @SerializedName("usd_status")
    public String usdStatus;
    /**
     * String field used for the token status.
     */
    @SerializedName("token_status")
    public String tokenStatus;
    /**
     * String field used for the created field.
     */
    @SerializedName("created")
    public String created;
    /**
     * String field used for the last update.
     */
    @SerializedName("last_update")
    public String lastUpdate;
    /**
     * Integer field used for the created epoch.
     */
    @SerializedName("created_epoch")
    public Integer createdEpoch;
    /**
     * Integer field used for the last update epoch.
     */
    @SerializedName("last_update_epoch")
    public Integer lastUpdateEpoch;
    /**
     * TransactionStatus list used for the timeline.
     */
    @SerializedName("timeline")
    public List<TransactionStatus> timeline;

    @SerializedName("descriptor")
    public String descriptor;

    @SerializedName("descriptor_ach")
    public String descriptorAch;

    @SerializedName("ach_name")
    public String achName;

    @SerializedName("destination_address")
    public String destinationAddress;

    @SerializedName("destination_handle")
    public String destinationHandle;

    @SerializedName("processing_type")
    public String processingType;

    @SerializedName("error_code")
    public String errorCode;

    @SerializedName("error_desc")
    public String errorDesc;

    @SerializedName("return_code")
    public String returnCode;

    @SerializedName("return_desc")
    public String returnDesc;

    @SerializedName("trace_number")
    public String traceNumber;

    @SerializedName("addenda")
    public String addenda;

    /**
     * String field used for the card account name.
     */
    @SerializedName("card_name")
    public String cardName;


    @SerializedName("sila_ledger_type")
    public String silaLedgerType;
    @SerializedName("destination_sila_ledger_type")
    public String destinationSilaLedgerType;
    @SerializedName("destination_ledger_account_id")
    public String destinationLedgerAccountId;
    @SerializedName("ledger_account_id")
    public String ledgerAccountId;
    @SerializedName("source_id")
    public String sourceId;
    @SerializedName("destination_id")
    public String destinationId;
    @SerializedName("effective_date")
    public String effectiveDate;
    @SerializedName("effective_epoch")
    public String effectiveEpoch;

    @SerializedName("child_transactions")
    public List<Transaction> childTransactions;
    /**
     * String field used for the submitted.
     */
    @SerializedName("submitted")
    public String submitted;
    /**
     * Integer field used for the submitted epoch.
     */
    @SerializedName("submitted_epoch")
    public Integer submittedEpoch;
    /**
     * String field used for the error message.
     */
    @SerializedName("error_msg")
    public String errorMsg;
}
