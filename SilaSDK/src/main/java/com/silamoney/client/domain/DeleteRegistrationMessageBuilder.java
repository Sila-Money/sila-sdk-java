package com.silamoney.client.domain;

public class DeleteRegistrationMessageBuilder implements Builder<DeleteRegistrationMessage> {
    private final String userHandle;
    private final String userPrivateKey;
    private final String registrationId;

    public DeleteRegistrationMessageBuilder(String userHandle, String userPrivateKey, String registrationId) {
        this.userHandle = userHandle;
        this.userPrivateKey = userPrivateKey;
        this.registrationId = registrationId;
    }

    public DeleteRegistrationMessage build() {
        return new DeleteRegistrationMessage(userHandle, userPrivateKey, registrationId);
    }
}
