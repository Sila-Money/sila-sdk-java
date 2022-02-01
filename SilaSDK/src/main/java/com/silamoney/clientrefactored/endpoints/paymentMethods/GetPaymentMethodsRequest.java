package com.silamoney.clientrefactored.endpoints.paymentMethods;

import com.silamoney.clientrefactored.domain.PaymentMethodsSearchFilters;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class GetPaymentMethodsRequest {
    
    private String userHandle;
    private String userPrivateKey;
    private PaymentMethodsSearchFilters searchFilters;
    
}
