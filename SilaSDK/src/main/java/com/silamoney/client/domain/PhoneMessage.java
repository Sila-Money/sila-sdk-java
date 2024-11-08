package com.silamoney.client.domain;

import lombok.Builder;
import lombok.Getter;

@Builder
public class PhoneMessage {
    @Getter
    private String phone;
    @Getter
    private String uuid;
    /**
     * smsOptIn will be removed in the next version of this SDK.
     * Please remove all usages of this variable from your system.
     */
    @Getter
    @Deprecated(forRemoval = true)
    private boolean smsOptIn;
}
