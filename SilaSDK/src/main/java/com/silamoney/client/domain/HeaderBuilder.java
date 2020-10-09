package com.silamoney.client.domain;

import java.util.UUID;

import com.silamoney.client.util.EpochUtils;

public class HeaderBuilder {
    private final String authHandle;
    private final Integer created;
    private String userHandle;
    private String reference;
    private String version;
    private String crypto;

    public HeaderBuilder(String authHandle) {
        this.authHandle = authHandle;
        this.created = EpochUtils.getEpoch();
    }

    public HeaderBuilder withUserHandle(String userHandle) {
        this.userHandle = userHandle;
        return this;
    }

    public HeaderBuilder withReference() {
        this.reference = UUID.randomUUID().toString();
        return this;
    }

    public HeaderBuilder useVersion(VersionEnum version) {
        this.version = version.getValue();
        return this;
    }

    public HeaderBuilder withCrypto(CryptoEnum crypto) {
        this.crypto = crypto.getValue();
        return this;
    }

    public HeaderBase build() {
        return new HeaderBase(this.authHandle, this.created, this.userHandle, this.reference, this.version,
                this.crypto);
    }
}
