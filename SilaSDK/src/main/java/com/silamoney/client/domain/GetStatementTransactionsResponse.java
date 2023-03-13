package com.silamoney.client.domain;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;

import java.util.List;
/**
 * Response used for the '/get_statement_transactions' endpoint.
 *
 * @author Anuj Kalal
 */
@Getter
public class GetStatementTransactionsResponse extends BaseResponse {
    /**
     * List<Statement> used for array of statement_data.
     */
    @SerializedName("statement_data")
    private List<Statement> statementData;

    /**
     * int field used for the page.
     */
    private int page;

    /**
     * int field used for the returned count.
     */
    @SerializedName("returned_count")
    private int returnedCount;

    /**
     * int field used for the total count.
     */
    @SerializedName("total_count")
    private int totalCount;

    /**
     * Pagination Entity used for the pagination.
     */
    private Pagination pagination;
}

