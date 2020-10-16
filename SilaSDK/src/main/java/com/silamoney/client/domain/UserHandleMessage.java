package com.silamoney.client.domain;

import lombok.Builder;
import lombok.Getter;

@Builder
public class UserHandleMessage {
    @Getter
    private String userHandle;
    @Getter
    private String userPrivateKey;
}
