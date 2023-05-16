package com.silamoney.client.domain;

import lombok.Builder;
import lombok.Getter;

import java.util.ArrayList;

@Builder
public class UploadDocumentsMessage {

        @Getter
        private String userHandle;
        @Getter
        private String userPrivateKey;
        @Getter
        private ArrayList<UploadDocument> uploadDocumentList;
        @Getter
        private String reference;
    }

