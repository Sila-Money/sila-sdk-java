package com.silamoney.client.domain;

import java.time.LocalDate;

import lombok.Builder;
import lombok.Getter;

@Builder
public class BusinessEntityMessage {
    @Getter
    private String entityName;
    @Getter
    private LocalDate birthdate;
    @Getter
    private String businessType;
    @Getter
    private Integer naicsCode;
    @Getter
    private String doingBusinessAs;
    @Getter
    private String businessWebsite;
    @Getter
    private String registrationState;
    @Getter
    private LocalDate registrationDate;
}
