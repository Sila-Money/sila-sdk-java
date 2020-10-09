package com.silamoney.client.domain;

import java.time.LocalDate;

import lombok.Builder;
import lombok.Getter;

@Builder
public class IndividualEntityMessage {
    @Getter
    private String firstName;
    @Getter
    private String lastName;
    @Getter
    private String entityName;
    @Getter
    private LocalDate birthdate;
}
