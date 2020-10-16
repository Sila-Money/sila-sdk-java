package com.silamoney.client.domain;

import lombok.Builder;
import lombok.Getter;

@Builder
public class PhoneMessage {
    @Getter
    private String phone;
    @Getter
    private String uuid;
}
