package com.silamoney.client.tests;

import com.silamoney.client.api.ApiResponse;
import com.silamoney.client.api.SilaApi;
import com.silamoney.client.domain.GetStatementsResponse;
import com.silamoney.client.domain.StatementSearchFilters;
import com.silamoney.client.testsutils.DefaultConfigurations;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class GetStatementsDataTests {

    SilaApi api = new SilaApi(DefaultConfigurations.host, DefaultConfigurations.appHandle,
            DefaultConfigurations.privateKey);

    @Test
    public void Response200() throws Exception {
        StatementSearchFilters searchFilters = new StatementSearchFilters();
        searchFilters.setPage(1);
        searchFilters.setPerPage(20);
        searchFilters.setMonth("11-2022");

        ApiResponse response = api.getStatementsData(DefaultConfigurations.getUserHandle(), DefaultConfigurations.getUserPrivateKey(), searchFilters);
        assertEquals(200, response.getStatusCode());
        GetStatementsResponse parsedResponse = (GetStatementsResponse) response.getData();
        assertTrue(parsedResponse.getStatements().size() > 0);
        assertEquals("SUCCESS", parsedResponse.getStatus());
    }

}