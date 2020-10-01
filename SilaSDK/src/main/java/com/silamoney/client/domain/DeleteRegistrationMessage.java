package com.silamoney.client.domain;

import lombok.Getter;

public class DeleteRegistrationMessage {
    @Getter
    private final String userHandle;
    @Getter
    private final String userPrivateKey;
    @Getter
    private final String registrationId;

    DeleteRegistrationMessage(String userHandle, String userPrivateKey, String registrationId) {
        this.userHandle = userHandle;
        this.userPrivateKey = userPrivateKey;
        this.registrationId = registrationId;
    }
}
