package com.silamoney.clientrefactored.endpoints.wire.approveWire;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ApproveWireRequest {
    private String userHandle;
    private String userPrivateKey;
    private String transactionId;
    private Boolean approve;
    private String notes;
    private String mockWireAccountName;
}
