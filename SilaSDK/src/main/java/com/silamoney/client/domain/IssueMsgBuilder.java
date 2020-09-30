package com.silamoney.client.domain;

public class IssueMsgBuilder {
    private final int amount;
    private final String accountName;
    private final String userHandle;
    private final String userPrivateKey;
    private String descriptor;
    private String businessUuid;
    private String processingType;

    public IssueMsgBuilder(String userHandle, String userPrivateKey, int amount, String accountName) {
        this.userHandle = userHandle;
        this.userPrivateKey = userPrivateKey;
        this.accountName = accountName;
        this.amount = amount;
    }

    public IssueMsgBuilder withDescriptor(String descriptor) {
        this.descriptor = descriptor;
        return this;
    }

    public IssueMsgBuilder withBusinessUuid(String businessUuid) {
        this.businessUuid = businessUuid;
        return this;
    }

    public IssueMsgBuilder withProcessingType(ProcessingTypeEnum processingType) {
        this.processingType = processingType.getValue();
        return this;
    }

    public IssueSilaMsg build() {
        return new IssueSilaMsg(this.userHandle, this.userPrivateKey, this.amount, this.accountName, this.descriptor,
                this.businessUuid, this.processingType);
    }
}
