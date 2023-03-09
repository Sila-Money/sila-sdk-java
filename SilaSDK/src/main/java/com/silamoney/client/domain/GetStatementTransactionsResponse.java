package com.silamoney.client.domain;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;

import java.util.List;

@Getter
public class GetStatementTransactionsResponse extends BaseResponse {
    @SerializedName("statement_data")
    private List<Statement> statementData;
    private int page;
    @SerializedName("returned_count")
    private int returnedCount;
    @SerializedName("total_count")
    private int totalCount;
    private Pagination pagination;
}
