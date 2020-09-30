package com.silamoney.client.domain;

import lombok.Getter;

public class IssueSilaMsg {
    @Getter
    private final String userHandle;
    @Getter
    private final String userPrivateKey;
    @Getter
    private final int amount;
    @Getter
    private final String accountName;
    @Getter
    private final String descriptor;
    @Getter
    private final String businessUuid;
    @Getter
    private final String processingType;

    IssueSilaMsg(String userHandle, String userPrivateKey, int amount, String accountName, String descriptor,
            String businessUuid, String processingType) {
        this.userHandle = userHandle;
        this.userPrivateKey = userPrivateKey;
        this.amount = amount;
        this.accountName = accountName;
        this.descriptor = descriptor;
        this.businessUuid = businessUuid;
        this.processingType = processingType;
    }
}
