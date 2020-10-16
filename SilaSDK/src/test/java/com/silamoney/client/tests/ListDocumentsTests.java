package com.silamoney.client.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.*;

import com.silamoney.client.api.ApiResponse;
import com.silamoney.client.api.SilaApi;
import com.silamoney.client.domain.BadRequestResponse;
import com.silamoney.client.domain.BaseResponse;
import com.silamoney.client.domain.ListDocumentsMessage;
import com.silamoney.client.domain.ListDocumentsResponse;
import com.silamoney.client.domain.PaginationMessage;
import com.silamoney.client.testsutils.DefaultConfigurations;

import org.junit.Test;

public class ListDocumentsTests {
    SilaApi api = new SilaApi(DefaultConfigurations.host, DefaultConfigurations.appHandle,
            DefaultConfigurations.privateKey);

    @Test
    public void Response200Success() throws Exception {
        ListDocumentsMessage message = ListDocumentsMessage.builder().userHandle(DefaultConfigurations.getUserHandle())
                .userPrivateKey(DefaultConfigurations.getUserPrivateKey()).build();
        ApiResponse response = api.listDocuments(message);
        assertEquals(200, response.getStatusCode());
        ListDocumentsResponse parsedResponse = (ListDocumentsResponse) response.getData();
        assertTrue(parsedResponse.getSuccess());
        assertEquals("SUCCESS", parsedResponse.getStatus());
        assertThat(parsedResponse.getPagination(), notNullValue());
        assertEquals(1, parsedResponse.getPagination().getCurrentPage());
        assertEquals(1, parsedResponse.getPagination().getReturnedCount());
        assertEquals(1, parsedResponse.getPagination().getTotalCount());
        assertEquals(1, parsedResponse.getPagination().getTotalPages());
        assertThat(parsedResponse.getDocuments(), notNullValue());
        assertEquals("logo-geko", parsedResponse.getDocuments().get(0).getFilename());
        DefaultConfigurations.setDocumentId(parsedResponse.getDocuments().get(0).getDocumentId());
        assertThat(parsedResponse.getDocuments().get(0).getUserHandle(),
                stringContainsInOrder(Arrays.asList(DefaultConfigurations.getUserHandle().toLowerCase())));

        List<String> docTypes = new ArrayList<String>();
        docTypes.add(DefaultConfigurations.getDocumentTypes().get(0).getName());
        message = ListDocumentsMessage.builder().userHandle(DefaultConfigurations.getUserHandle())
                .userPrivateKey(DefaultConfigurations.getUserPrivateKey()).order("asc").search("logo").sortBy("name")
                .docTypes(docTypes).startDate(LocalDate.now()).endDate(LocalDate.now().plusDays(1)).build();
        PaginationMessage pagination = PaginationMessage.builder().page(1).perPage(1).build();
        response = api.listDocuments(message, pagination);
        assertEquals(200, response.getStatusCode());
        parsedResponse = (ListDocumentsResponse) response.getData();
        assertTrue(parsedResponse.getSuccess());
        assertEquals("SUCCESS", parsedResponse.getStatus());
        assertThat(parsedResponse.getPagination(), notNullValue());
        assertEquals(1, parsedResponse.getPagination().getCurrentPage());
        assertEquals(1, parsedResponse.getPagination().getReturnedCount());
        assertEquals(1, parsedResponse.getPagination().getTotalCount());
        assertEquals(1, parsedResponse.getPagination().getTotalPages());
        assertThat(parsedResponse.getDocuments(), notNullValue());
        assertEquals("logo-geko", parsedResponse.getDocuments().get(0).getFilename());
        assertThat(parsedResponse.getDocuments().get(0).getUserHandle(),
                stringContainsInOrder(Arrays.asList(DefaultConfigurations.getUserHandle().toLowerCase())));
    }

    @Test
    public void Response400() throws Exception {
        ListDocumentsMessage message = ListDocumentsMessage.builder().userHandle("")
                .userPrivateKey(DefaultConfigurations.getUserPrivateKey()).build();
        ApiResponse response = api.listDocuments(message);
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
        ListDocumentsMessage message = ListDocumentsMessage.builder().userHandle(DefaultConfigurations.getUserHandle())
                .userPrivateKey(DefaultConfigurations.getUserPrivateKey()).build();
        ApiResponse response = badApi.listDocuments(message);
        assertEquals(403, response.getStatusCode());
        BaseResponse parsedResponse = (BaseResponse) response.getData();
        assertFalse(parsedResponse.getSuccess());
        assertEquals("FAILURE", parsedResponse.getStatus());
        assertThat("document types - bad signature", parsedResponse.getMessage(),
                stringContainsInOrder(Arrays.asList("Failed to authenticate app signature.")));
    }
}