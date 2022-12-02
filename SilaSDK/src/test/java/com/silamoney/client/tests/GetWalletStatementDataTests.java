package com.silamoney.client.tests;

import com.silamoney.client.api.ApiResponse;
import com.silamoney.client.api.SilaApi;
import com.silamoney.client.domain.GetStatementsResponse;
import com.silamoney.client.domain.StatementSearchFilters;
import com.silamoney.client.testsutils.DefaultConfigurations;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @author Aditi
 */
public class GetWalletStatementDataTests {

    SilaApi api = new SilaApi(DefaultConfigurations.host, DefaultConfigurations.appHandle,
            DefaultConfigurations.privateKey);

    @Test
    public void Response200() throws Exception {
        StatementSearchFilters searchFilters = new StatementSearchFilters();
        searchFilters.setPage(1);
        searchFilters.setPerPage(20);
        searchFilters.setStartMonth("1-2022");
        searchFilters.setEndMonth("11-2022");

        ApiResponse response = api.getWalletStatementData(DefaultConfigurations.getUserHandle(),DefaultConfigurations.getUserPrivateKey(),DefaultConfigurations.getWalletId(),searchFilters);
        assertEquals(200, response.getStatusCode());
        GetStatementsResponse parsedResponse = (GetStatementsResponse) response.getData();
        assertEquals("SUCCESS", parsedResponse.getStatus());
    }

}
