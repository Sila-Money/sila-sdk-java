package com.silamoney.client.tests;

import com.silamoney.client.api.ApiResponse;
import com.silamoney.client.api.SilaApi;
import com.silamoney.client.domain.*;
import com.silamoney.client.testsutils.DefaultConfigurations;
import org.junit.Test;

import java.util.List;
import java.util.UUID;

import static org.junit.Assert.*;
import static org.junit.Assert.assertNotNull;

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
        searchFilters.setStartMonth("06-2023");
        searchFilters.setEndMonth("06-2023");

        ApiResponse response = api.getWalletStatementData(
                DefaultConfigurations.getUserHandle(),
                DefaultConfigurations.getUserPrivateKey(),
                DefaultConfigurations.walletWithStatements,
                searchFilters);

        assertEquals(200, response.getStatusCode());
        GetStatementsResponse parsedResponse = (GetStatementsResponse) response.getData();
        assertEquals("SUCCESS", parsedResponse.getStatus());

        // Get the most important objects in the response for testing
        Pagination pages = parsedResponse.getPagination();
        List<Statement> statements = parsedResponse.getStatements();
        Statement statement = statements.get(0);
        TransactionEntity transaction = statement.getTransactions().get(0);


        // Test pagination and response size
        assertNotNull(pages);
        assertNotEquals(pages.getReturnedCount(), 0);
        assertNotNull(parsedResponse.getReference());
        assertNotNull(parsedResponse.getResponseTimeMs());
        assertNotNull(parsedResponse.getStatements());
        assertEquals(pages.getReturnedCount(), statements.size());
        assertEquals(pages.getTotalCount(), parsedResponse.getTotalCount());
        assertEquals(pages.getReturnedCount(), parsedResponse.getReturnedCount());

        // Test statement fields
        assertNotNull(statement);
        assertNotNull(statement.getUserHandle());
        assertNotNull(statement.getDate());
        assertNotNull(statement.getFirstName());
        assertNotNull(statement.getLastName());
        assertNotNull(statement.getWalletId());
        assertNotNull(statement.getBeginningBalance());
        assertNotNull(statement.getEndingBalance());

        // Test transaction fields
        assertNotNull(transaction); assertNotNull(transaction.getSettledDate()); assertNotNull(transaction.getDescription());
        assertNotNull(transaction.getCategory());
        assertNotNull(transaction.getAmount());
        assertNotNull(transaction.getCategory());
        assertNotNull(transaction.getRunningBalance());
        assertNotNull(transaction.getDescriptor());
    }

    @Test
    public void NoSearchFilterResponse200() throws Exception {
        ApiResponse response = api.getWalletStatementData(
                DefaultConfigurations.getUserHandle(),
                DefaultConfigurations.getUserPrivateKey(),
                DefaultConfigurations.walletWithStatements);

        assertEquals(200, response.getStatusCode());
        GetStatementsResponse parsedResponse = (GetStatementsResponse) response.getData();
        assertEquals("SUCCESS", parsedResponse.getStatus());
    }

    @Test
    public void BadRequestMissingEndMonthResponse400() throws Exception {
        StatementSearchFilters searchFilters = new StatementSearchFilters();
        searchFilters.setPage(1);
        searchFilters.setPerPage(20);
        ApiResponse response = api.getWalletStatementData(
                DefaultConfigurations.getUserHandle(),
                DefaultConfigurations.getUserPrivateKey(),
                DefaultConfigurations.walletWithStatements,
                searchFilters);
        assertEquals(400, response.getStatusCode());
    }

    @Test
    public void UnregisteredUserResponse200() throws Exception {
        StatementSearchFilters searchFilters = new StatementSearchFilters();
        searchFilters.setPage(1);
        searchFilters.setPerPage(20);
        searchFilters.setStartMonth("10-2022");
        searchFilters.setEndMonth("11-2022");
        ApiResponse response = api.getWalletStatementData(
                UUID.randomUUID().toString(),
                DefaultConfigurations.getUserPrivateKey(),
                DefaultConfigurations.walletWithStatements,
                searchFilters);
        assertEquals(200, response.getStatusCode());
    }

}
