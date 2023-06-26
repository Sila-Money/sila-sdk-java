package com.silamoney.client.tests;

import com.silamoney.client.api.ApiResponse;
import com.silamoney.client.api.SilaApi;
import com.silamoney.client.domain.DeliveryAttempts;
import com.silamoney.client.domain.Pagination;
import com.silamoney.client.domain.StatementsResponse;
import com.silamoney.client.domain.StatementsSearchFilters;
import com.silamoney.client.testsutils.DefaultConfigurations;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class StatementsTests {

    SilaApi api = new SilaApi(DefaultConfigurations.host, "arc_sandbox_test_app01",
            "9c17e7b767b8f4a63863caf1619ef3e9967a34b287ce58542f3eb19b5a72f076");
    String userHandle="user_handle1_1686776339cudgjmzwckh4ohh";
    String privateKey="c087fb917b921f930355b97edda0ab29a5ea40963cef376be999e6aedf0efe0e";

    @Test
    public void Response200() throws Exception {
        StatementsSearchFilters searchFilters = new StatementsSearchFilters();
        int expectedStatementSize = 10;
        searchFilters.setPage(1);
        searchFilters.setPerPage(expectedStatementSize);


        ApiResponse response = api.statements(userHandle, privateKey, searchFilters);
        assertEquals(200, response.getStatusCode());
        StatementsResponse parsedResponse = (StatementsResponse) response.getData();

        assertEquals("SUCCESS", parsedResponse.getStatus());
        assertEquals(true, parsedResponse.getSuccess());

        // Get the most important objects in the response for testing
        Pagination pages = parsedResponse.getPagination();
        List<DeliveryAttempts> deliveryAttempts = parsedResponse.getDeliveryAttempts();
        DeliveryAttempts deliveryAttempt = deliveryAttempts.get(0);

        // Test pagination and response size
        assertNotNull(pages);
        assertNotEquals(pages.getReturnedCount(), 0);
        assertNotNull(parsedResponse.getReference());
        assertNotNull(parsedResponse.getResponseTimeMs());
        assertNotNull(parsedResponse.getDeliveryAttempts());
        assertEquals(pages.getReturnedCount(), expectedStatementSize);
        assertEquals(pages.getReturnedCount(), deliveryAttempts.size());

        // Test delivery attempt fields
        assertNotNull(deliveryAttempt);
        assertNotNull(deliveryAttempt.getUserHandle());
        assertNotNull(deliveryAttempt.getStatementId());
        assertNotNull(deliveryAttempt.getUserName());
        assertNotNull(deliveryAttempt.getAccountType());
        assertNotNull(deliveryAttempt.getEmail());
        assertNotNull(deliveryAttempt.getCreated());
        DefaultConfigurations.setStatementId(deliveryAttempt.getStatementId());
    }

    @Test
    public void NoSearchFiltersResponse200() throws Exception {
        ApiResponse response = api.statements(
                userHandle,
                privateKey);
        assertEquals(200, response.getStatusCode());
        StatementsResponse parsedResponse = (StatementsResponse) response.getData();

        assertEquals("SUCCESS", parsedResponse.getStatus());
        assertEquals(true, parsedResponse.getSuccess());
    }

        @Test
    public void Response400WithoutPageFilter() throws Exception {
        StatementsSearchFilters searchFilters = new StatementsSearchFilters();
        searchFilters.setPerPage(20);
        ApiResponse response = api.statements(userHandle,
                privateKey, searchFilters);
        assertEquals(400, response.getStatusCode());
    }

    @Test
    public void Response400WithoutPerPageFilter() throws Exception {
        StatementsSearchFilters searchFilters = new StatementsSearchFilters();
        searchFilters.setPage(1);
        ApiResponse response = api.statements(userHandle,
                privateKey, searchFilters);
        assertEquals(400, response.getStatusCode());
    }

}