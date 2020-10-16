package com.silamoney.client.domain;

import lombok.Builder;
import lombok.Getter;

@Builder
public class EmailMessage {
    @Getter
    private String email;
    @Getter
    private String uuid;
}
