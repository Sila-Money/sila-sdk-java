package com.silamoney.client.domain;

public class AccountTransactionMessageBuilder {
    private final int amount;
    private final String accountName;
    private final String userHandle;
    private final String userPrivateKey;
    private String descriptor;
    private String businessUuid;
    private String processingType;

    public AccountTransactionMessageBuilder(String userHandle, String userPrivateKey, int amount, String accountName) {
        this.userHandle = userHandle;
        this.userPrivateKey = userPrivateKey;
        this.accountName = accountName;
        this.amount = amount;
    }

    public AccountTransactionMessageBuilder withDescriptor(String descriptor) {
        this.descriptor = descriptor;
        return this;
    }

    public AccountTransactionMessageBuilder withBusinessUuid(String businessUuid) {
        this.businessUuid = businessUuid;
        return this;
    }

    public AccountTransactionMessageBuilder withProcessingType(ProcessingTypeEnum processingType) {
        this.processingType = processingType.getValue();
        return this;
    }

    public AccountTransactionMessage build() {
        return new AccountTransactionMessage(this.userHandle, this.userPrivateKey, this.amount, this.accountName,
                this.descriptor, this.businessUuid, this.processingType);
    }
}
