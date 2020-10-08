package com.silamoney.client.domain;

import lombok.Builder;
import lombok.Getter;

@Builder
public class UploadDocumentMessage {
    @Getter
    private String userHandle;
    @Getter
    private String userPrivateKey;
    @Getter
    private String name;
    @Getter
    private String filename;
    @Getter
    private String mimeType;
    @Getter
    private String documentType;
    @Getter
    private String identityType;
    @Getter
    private String description;
    @Getter
    private String filePath;
}
