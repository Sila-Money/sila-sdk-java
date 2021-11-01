package com.silamoney.clientrefactored.endpoints.transactions.reversetransaction;

import com.silamoney.clientrefactored.domain.SearchFilters;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ReverseTransactionRequest {
    
    private String userHandle;
    private String userPrivateKey;
    private String transactionId;
    
}
