package com.silamoney.client.domain;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;

import java.util.List;
/**
 * Entity used in the GetStatementsResponse and GetStatementTransactionsResponse.
 *
 * @author Anuj Kalal
 */
@Getter
public class Statement {

    /**
     * String field used for the user handle.
     */
    @SerializedName("user_handle")
    private String userHandle;

    /**
     * String field used for the date.
     */
    @SerializedName("date")
    private String date;

    /**
     * String field used for the first name.
     */
    @SerializedName("first_name")
    private String firstName;

    /**
     * String field used for the last name.
     */
    @SerializedName("last_name")
    private String lastName;

    /**
     * String field used for the wallet id.
     */
    @SerializedName("wallet_id")
    private String walletId;

    /**
     * String field used for the beginning balance.
     */
    @SerializedName("beginning_balance")
    private String beginningBalance;

    /**
     * String field used for the ending balance.
     */
    @SerializedName("ending_balance")
    private String endingBalance;

    /**
     * List<TransactionEntity> used for the array of transactions.
     */
    @SerializedName("transactions")
    private List<TransactionEntity> transactions;

}
