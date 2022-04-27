package com.silamoney.clientrefactored.endpoints.virtualAccount.createTestVirtualAccountAchTransaction;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;

import java.util.Date;

@Builder
@Data
public class CreateTestVirtualAccountAchTransactionRequest {
    
    private String userHandle;
    private String userPrivateKey;
    private int amount;
    private String virtualAccountNumber;
    private int tranCode;
    private Date effectiveDate;
    private String entityName;
    private String ced;
    private String achName;
}
