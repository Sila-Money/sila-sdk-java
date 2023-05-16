package com.silamoney.clientrefactored.endpoints.wire.mockWireOutFile;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class MockWireOutFileRequest {
    private String userHandle;
    private String userPrivateKey;
    private String transactionId;
    private String wireStatus;
    private String reference;
}
