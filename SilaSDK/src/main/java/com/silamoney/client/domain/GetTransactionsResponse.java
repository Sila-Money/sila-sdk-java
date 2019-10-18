package com.silamoney.client.domain;

import com.google.gson.annotations.SerializedName;
import java.util.List;

/**
 *Object used to map the get transactions method response.
 * @author Karlo Lorenzana
 */
public class GetTransactionsResponse {
    /**
     * Boolean field used for success.
     */
    @SerializedName("success")
    public Boolean success;
    /**
     * Integer field used for the page.
     */
    @SerializedName("page")
    public Integer page;
    /**
     * Integer field used for the returned count.
     */
    @SerializedName("returned_count")
    public Integer returnedCount;
    /**
     * Integer field used for the total count.
     */
    @SerializedName("total_count")
    public Integer totalCount;
    /**
     * Transactions list used for the transactions.
     */
    @SerializedName("transactions")
    public List<Transaction> transactions;
}
