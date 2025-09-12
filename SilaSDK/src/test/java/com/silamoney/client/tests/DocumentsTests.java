package com.silamoney.client.tests;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

import com.silamoney.client.api.ApiResponse;
import com.silamoney.client.api.SilaApi;
import com.silamoney.client.domain.*;
import com.silamoney.client.testsutils.DefaultConfigurations;

import org.junit.Test;

public class DocumentsTests {
    SilaApi api = new SilaApi(DefaultConfigurations.host, DefaultConfigurations.appHandle,
            DefaultConfigurations.privateKey);

    @Test
    public void Response200Success() throws Exception {
        File f = new File(DocumentsTests.class.getClassLoader().getResource("images/logo-geko.png").getFile());
        DocumentType dt = DefaultConfigurations.getDocumentTypes().get(0);
        String v = DefaultConfigurations.getVerificationUuid();
        UploadDocumentMessage message = UploadDocumentMessage.builder()
                .userHandle(DefaultConfigurations.getUserHandle())
                .userPrivateKey(DefaultConfigurations.getUserPrivateKey()).filePath(f.getAbsolutePath())
                .filename("logo-geko").mimeType("image/png").documentType(dt.getName())
                .identityType(dt.getIdentityType()).verificationUuid(v).build();
        ApiResponse response = api.uploadDocument(message);
        assertEquals(200, response.getStatusCode());
        DocumentsResponse parsedResponse = (DocumentsResponse) response.getData();
        assertTrue(parsedResponse.getSuccess());
        assertEquals("SUCCESS", parsedResponse.getStatus());
        assertThat("documents - message", parsedResponse.getMessage(),
                stringContainsInOrder(Arrays.asList("File uploaded successfully")));
        assertThat("documents - document id", parsedResponse.getDocumentId(), not(isEmptyOrNullString()));
        assertThat("documents - reference id", parsedResponse.getReferenceId(), not(isEmptyOrNullString()));
    }

    @Test
    public void Response400() throws Exception {
        File f = new File(DocumentsTests.class.getClassLoader().getResource("images/logo-geko.png").getFile());
        UploadDocumentMessage message = UploadDocumentMessage.builder()
                .userHandle(DefaultConfigurations.getUserHandle())
                .userPrivateKey(DefaultConfigurations.getUserPrivateKey()).filePath(f.getAbsolutePath())
                .filename("logo-geko").mimeType("image/png").build();
        ApiResponse response = api.uploadDocument(message);
        assertEquals(400, response.getStatusCode());
        BadRequestResponse parsedResponse = (BadRequestResponse) response.getData();
        assertFalse(parsedResponse.getSuccess());
        assertEquals("FAILURE", parsedResponse.getStatus());
        assertThat(parsedResponse.getMessage(), stringContainsInOrder(Arrays.asList("Bad request")));
    }

    @Test
    public void Response403() throws Exception {
        SilaApi badApi = new SilaApi(DefaultConfigurations.host, DefaultConfigurations.appHandle,
                "3a1076bf45ab87712ad64ccb3b10217737f7faacbf2872e88fdd9a537d8fe266");
        File f = new File(DocumentsTests.class.getClassLoader().getResource("images/logo-geko.png").getFile());
        DocumentType dt = DefaultConfigurations.getDocumentTypes().get(0);
        UploadDocumentMessage message = UploadDocumentMessage.builder()
                .userHandle(DefaultConfigurations.getUserHandle())
                .userPrivateKey(DefaultConfigurations.getUserPrivateKey()).filePath(f.getAbsolutePath())
                .filename("logo-geko").mimeType("image/png").documentType(dt.getName())
                .identityType(dt.getIdentityType()).build();
        ApiResponse response = badApi.uploadDocument(message);
        assertEquals(403, response.getStatusCode());
        BaseResponse parsedResponse = (BaseResponse) response.getData();
        assertFalse(parsedResponse.getSuccess());
        assertEquals("FAILURE", parsedResponse.getStatus());
    }
    @Test
    public void Response200SuccessForMultiple() throws Exception {
        File file1 = new File(DocumentsTests.class.getClassLoader().getResource("images/logo-geko.png").getFile());
        File file2 = new File(DocumentsTests.class.getClassLoader().getResource("images/tricolor.png").getFile());
        DocumentType dt = DefaultConfigurations.getDocumentTypes().get(0);

        ArrayList<UploadDocument> uploadDocumentList = new ArrayList<>();
        uploadDocumentList.add(UploadDocument.builder().filePath(file1.getAbsolutePath())
                .filename("logo-geko").mimeType("image/png").documentType(dt.getName())
                .build());
        uploadDocumentList.add(UploadDocument.builder().filePath(file2.getAbsolutePath())
                .filename("tricolor").mimeType("image/png").documentType(dt.getName())
                .build());

        UploadDocumentsMessage message = UploadDocumentsMessage.builder()
                .userHandle(DefaultConfigurations.getUserHandle())
                .userPrivateKey(DefaultConfigurations.getUserPrivateKey()).uploadDocumentList(uploadDocumentList).build();
        ApiResponse response = api.uploadDocuments(message);
        assertEquals(200, response.getStatusCode());
        UploadDocumentsResponse parsedResponse = (UploadDocumentsResponse) response.getData();
        assertTrue(parsedResponse.getSuccess());
        assertEquals("SUCCESS", parsedResponse.getStatus());
        assertThat("documents - message", parsedResponse.getMessage(),
                stringContainsInOrder(Arrays.asList("File uploaded successfully")));
    }
}
