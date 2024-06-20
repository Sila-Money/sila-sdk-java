package com.silamoney.client.tests;

import com.silamoney.client.api.ApiResponse;
import com.silamoney.client.api.SilaApi;
import com.silamoney.client.domain.GetStatementTransactionsResponse;
import com.silamoney.client.domain.Pagination;
import com.silamoney.client.domain.Statement;
import com.silamoney.client.domain.TransactionEntity;
import com.silamoney.client.testsutils.DefaultConfigurations;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import static org.junit.Assert.*;

public class GetStatementTransactionsTests {

    SilaApi api = new SilaApi(DefaultConfigurations.host, DefaultConfigurations.appHandle,
            DefaultConfigurations.privateKey);

    @Test
    public void Response200() throws Exception {

        Calendar cal = Calendar.getInstance();
        String defaultMonth = new SimpleDateFormat("MM-yyyy").format(cal.getTime());
        Integer page = 1;
        Integer perPage = 1000;

        ApiResponse response = api.getStatementTransactions(DefaultConfigurations.getUserHandle(), DefaultConfigurations.getUserPrivateKey(), DefaultConfigurations.getWalletId(), defaultMonth, page, perPage);
        assertEquals(200, response.getStatusCode());
        GetStatementTransactionsResponse parsedResponse = (GetStatementTransactionsResponse) response.getData();

        assertEquals("SUCCESS", parsedResponse.getStatus());
        assertEquals(true, parsedResponse.getSuccess());

        // Get the most important objects in the response for testing
        Pagination pages = parsedResponse.getPagination();
        List<Statement> statements = parsedResponse.getStatementData();
        Statement statement = statements.get(0);
        TransactionEntity transaction = statement.getTransactions().get(0);

        // Test pagination and response size
        assertNotNull(pages);
        assertNotEquals(pages.getReturnedCount(), 0);
        assertNotNull(parsedResponse.getReference());
        assertNotNull(parsedResponse.getResponseTimeMs());
        assertNotNull(parsedResponse.getStatementData());

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
        assertNotNull(transaction);
        assertNotNull(transaction.getSettledDate());
        assertNotNull(transaction.getDescription());
        assertNotNull(transaction.getCategory());
        assertNotNull(transaction.getAmount());
        assertNotNull(transaction.getCategory());
        assertNotNull(transaction.getRunningBalance());
        assertNotNull(transaction.getDescriptor());
    }

    @Test
    public void Response200WithNoMonth() throws Exception {
        ApiResponse response = api.getStatementTransactions(
                DefaultConfigurations.getUserHandle(),
                DefaultConfigurations.getUserPrivateKey(), DefaultConfigurations.getWalletId());
        assertEquals(200, response.getStatusCode());
        GetStatementTransactionsResponse parsedResponse = (GetStatementTransactionsResponse) response.getData();

        assertEquals("SUCCESS", parsedResponse.getStatus());
        assertEquals(true, parsedResponse.getSuccess());
    }

}
