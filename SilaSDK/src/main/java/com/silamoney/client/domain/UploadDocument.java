package com.silamoney.client.domain;

import lombok.Builder;
import lombok.Getter;
@Builder
public class UploadDocument {
    @Getter
    private String name;
    @Getter
    private String filename;
    @Getter
    private String mimeType;
    @Getter
    private String documentType;
    @Getter
    private String description;
    @Getter
    private String filePath;
    @Getter
    private String verificationUuid;

}
