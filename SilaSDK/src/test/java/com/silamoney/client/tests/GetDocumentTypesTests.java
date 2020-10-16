package com.silamoney.client.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;

import com.silamoney.client.api.ApiResponse;
import com.silamoney.client.api.SilaApi;
import com.silamoney.client.domain.BaseResponse;
import com.silamoney.client.domain.DocumentTypesResponse;
import com.silamoney.client.domain.PaginationMessage;
import com.silamoney.client.testsutils.DefaultConfigurations;

import org.junit.Test;

/**
 * @author José Morales
 */
public class GetDocumentTypesTests {
    SilaApi api = new SilaApi(DefaultConfigurations.host, DefaultConfigurations.appHandle,
            DefaultConfigurations.privateKey);

    @Test
    public void Response200Success() throws Exception {
        ApiResponse response = api.getDocumentTypes();
        assertEquals(200, response.getStatusCode());
        DocumentTypesResponse parsedResponse = (DocumentTypesResponse) response.getData();
        assertTrue(parsedResponse.getSuccess());
        assertEquals("SUCCESS", parsedResponse.getStatus());
        assertThat("document types - message", parsedResponse.getMessage(),
                stringContainsInOrder(Arrays.asList("Document type details returned")));
        assertEquals(20, parsedResponse.getDocumentTypes().size());
        assertThat("document types - name", parsedResponse.getDocumentTypes().get(0).getName(),
                not(isEmptyOrNullString()));
        DefaultConfigurations.setDocumentTypes(parsedResponse.getDocumentTypes());
        assertThat("document types - name", parsedResponse.getDocumentTypes().get(0).getLabel(),
                not(isEmptyOrNullString()));
        assertThat("document types - name", parsedResponse.getDocumentTypes().get(0).getIdentityType(),
                not(isEmptyOrNullString()));
        assertEquals(1, parsedResponse.getPagination().getCurrentPage());
        assertEquals(20, parsedResponse.getPagination().getReturnedCount());
        assertThat("document types - pages", parsedResponse.getPagination().getTotalPages(), greaterThan(1));
        assertThat("document types - total count", parsedResponse.getPagination().getTotalCount(), greaterThan(20));
    }

    @Test
    public void Response200SuccessWithPagination() throws Exception {
        PaginationMessage pagination = PaginationMessage.builder().page(1).perPage(40).build();
        ApiResponse response = api.getDocumentTypes(pagination);
        assertEquals(200, response.getStatusCode());
        DocumentTypesResponse parsedResponse = (DocumentTypesResponse) response.getData();
        assertTrue(parsedResponse.getSuccess());
        assertEquals("SUCCESS", parsedResponse.getStatus());
        assertThat("document types - message", parsedResponse.getMessage(),
                stringContainsInOrder(Arrays.asList("Document type details returned")));
        assertThat("document types - list size", parsedResponse.getDocumentTypes().size(), greaterThan(20));
        assertThat("document types - name", parsedResponse.getDocumentTypes().get(0).getName(),
                not(isEmptyOrNullString()));
        assertThat("document types - name", parsedResponse.getDocumentTypes().get(0).getLabel(),
                not(isEmptyOrNullString()));
        assertThat("document types - name", parsedResponse.getDocumentTypes().get(0).getIdentityType(),
                not(isEmptyOrNullString()));
        assertEquals(1, parsedResponse.getPagination().getCurrentPage());
        assertThat("document types - page count", parsedResponse.getPagination().getReturnedCount(), greaterThan(20));
        assertThat("document types - pages", parsedResponse.getPagination().getTotalPages(), greaterThanOrEqualTo(1));
        assertThat("document types - total count", parsedResponse.getPagination().getTotalCount(), greaterThan(20));
    }

    @Test
    public void Response403Success() throws Exception {
        SilaApi badApi = new SilaApi(DefaultConfigurations.appHandle,
                "3a1076bf45ab87712ad64ccb3b10217737f7faacbf2872e88fdd9a537d8fe266");
        ApiResponse response = badApi.getDocumentTypes();
        assertEquals(403, response.getStatusCode());
        BaseResponse parsedResponse = (BaseResponse) response.getData();
        assertFalse(parsedResponse.getSuccess());
        assertEquals("FAILURE", parsedResponse.getStatus());
        assertThat("document types - bad signature", parsedResponse.getMessage(),
                stringContainsInOrder(Arrays.asList("Failed to authenticate app signature.")));
    }
}
