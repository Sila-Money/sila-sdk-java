package com.silamoney.client.domain;

import lombok.Builder;
import lombok.Getter;

@Builder
public class AccountTransactionMessage {
    @Getter
    private String userHandle;
    @Getter
    private String userPrivateKey;
    @Getter
    private int amount;
    @Getter
    private String accountName;
    @Getter
    private String descriptor;
    @Getter
    private String businessUuid;
    @Getter
    private ProcessingTypeEnum processingType;
    @Getter
    private String cardName;
    @Getter
    private String sourceId;
    @Getter
    private String destinationId;
}
