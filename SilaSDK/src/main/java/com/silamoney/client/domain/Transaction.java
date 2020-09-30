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
}
