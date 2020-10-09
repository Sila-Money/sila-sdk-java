package com.silamoney.client.domain;

import java.time.LocalDate;
import java.util.List;

import lombok.Builder;
import lombok.Getter;

@Builder
public class ListDocumentsMessage {
    @Getter
    private String userHandle;
    @Getter
    private String userPrivateKey;
    @Getter
    private List<String> docTypes;
    @Getter
    private String search;
    @Getter
    private String sortBy;
    @Getter
    private LocalDate startDate;
    @Getter
    private LocalDate endDate;
    @Getter
    private String order;
}
