package com.silamoney.client.domain;

import com.google.gson.annotations.SerializedName;
import java.util.List;

/**
 * Object used to map the get transactions method response.
 * 
 * @author Karlo Lorenzana
 */
public class GetTransactionsResponse extends PaginationInformation {
    /**
     * Boolean field used for success.
     */
    @SerializedName("success")
    public Boolean success;

    @SerializedName("status")
    public String status;
    /**
     * Transactions list used for the transactions.
     */
    @SerializedName("transactions")
    public List<Transaction> transactions;
}
