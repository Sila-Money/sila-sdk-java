package com.silamoney.client.domain;

import lombok.Builder;
import lombok.Getter;

@Builder
public class IdentityMessage {
    @Getter
    private String identityAlias;
    @Getter
    private String identityValue;
    @Getter
    private String uuid;
}
