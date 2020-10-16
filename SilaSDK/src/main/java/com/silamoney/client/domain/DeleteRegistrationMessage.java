package com.silamoney.client.domain;

import lombok.Builder;
import lombok.Getter;

@Builder
public class DeleteRegistrationMessage {
    @Getter
    private String userHandle;
    @Getter
    private String userPrivateKey;
    @Getter
    private String uuid;
}
