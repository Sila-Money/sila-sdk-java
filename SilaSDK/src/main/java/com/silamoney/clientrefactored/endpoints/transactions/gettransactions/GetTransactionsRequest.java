package com.silamoney.clientrefactored.endpoints.transactions.gettransactions;

import com.silamoney.clientrefactored.domain.SearchFilters;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class GetTransactionsRequest {
    
    private String userHandle;
    private String userPrivateKey;
    private SearchFilters searchFilters;
    private String reference;
    
}
