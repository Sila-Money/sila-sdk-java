package com.silamoney.client.tests;

import com.silamoney.client.api.ApiResponse;
import com.silamoney.client.api.SilaApi;
import com.silamoney.client.domain.*;
import com.silamoney.client.testsutils.DefaultConfigurations;
import org.junit.Test;
import org.web3j.crypto.*;
import org.web3j.crypto.exception.CipherException;
import org.web3j.crypto.Wallet;

import java.math.BigInteger;
import java.security.InvalidAlgorithmParameterException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

public class GetStatementsDataTests {

    SilaApi api = new SilaApi(DefaultConfigurations.host, DefaultConfigurations.appHandle,
            DefaultConfigurations.privateKey);

    @Test
    public void Response200() throws Exception {
        StatementSearchFilters searchFilters = new StatementSearchFilters();
        int expectedStatementSize = 10;
        searchFilters.setPage(1);
        searchFilters.setPerPage(expectedStatementSize);
        searchFilters.setMonth("06-2023");

        ApiResponse response = api.getStatementsData(DefaultConfigurations.getUserHandle(), DefaultConfigurations.getUserPrivateKey(), searchFilters);
        assertEquals(200, response.getStatusCode());
        GetStatementsResponse parsedResponse = (GetStatementsResponse) response.getData();

        assertEquals("SUCCESS", parsedResponse.getStatus());
        assertEquals(true, parsedResponse.getSuccess());

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
        assertEquals(pages.getReturnedCount(), expectedStatementSize);
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
    public void NoSearchFiltersResponse200() throws Exception {
        ApiResponse response = api.getStatementsData(
                DefaultConfigurations.getUserHandle(),
                DefaultConfigurations.getUserPrivateKey());
        assertEquals(200, response.getStatusCode());
        GetStatementsResponse parsedResponse = (GetStatementsResponse) response.getData();

        assertEquals("SUCCESS", parsedResponse.getStatus());
        assertEquals(true, parsedResponse.getSuccess());
    }

        @Test
    public void Response400WithoutPageFilter() throws Exception {
        StatementSearchFilters searchFilters = new StatementSearchFilters();
        searchFilters.setPerPage(20);
        searchFilters.setMonth("11-2022");
        ApiResponse response = api.getStatementsData(DefaultConfigurations.getUserHandle(),
                DefaultConfigurations.getUserPrivateKey(), searchFilters);
        assertEquals(400, response.getStatusCode());
    }

    @Test
    public void Response400WithoutPerPageFilter() throws Exception {
        StatementSearchFilters searchFilters = new StatementSearchFilters();
        searchFilters.setPage(1);
        searchFilters.setMonth("11-2022");
        ApiResponse response = api.getStatementsData(DefaultConfigurations.getUserHandle(),
                DefaultConfigurations.getUserPrivateKey(), searchFilters);
        assertEquals(400, response.getStatusCode());
    }

    @Test
    public void Response400WithoutMonthFilter() throws Exception {
        StatementSearchFilters searchFilters = new StatementSearchFilters();
        searchFilters.setPage(1);
        searchFilters.setPerPage(20);
        ApiResponse response = api.getStatementsData(DefaultConfigurations.getUserHandle(),
                DefaultConfigurations.getUserPrivateKey(), searchFilters);
        assertEquals(400, response.getStatusCode());
    }

    @Test
    public void NewUser200Response() throws Exception {
        StatementSearchFilters searchFilters = new StatementSearchFilters();
        searchFilters.setPage(1);
        searchFilters.setPerPage(20);
        searchFilters.setMonth("11-2022");

        String newUserHandle = "javaSDK-UserNew-" + new Random().nextInt();
        Date birthdate = Date.from(
                LocalDate.of(2001, 6, 22).atStartOfDay(ZoneId.systemDefault()).toInstant()
        );
        String userPrivateKey = "";

        try {
            ECKeyPair ecKeyPair = Keys.createEcKeyPair();
            BigInteger privateKeyInDec = ecKeyPair.getPrivateKey();
            userPrivateKey = privateKeyInDec.toString(16);
            Wallet.createLight(UUID.randomUUID().toString(), ecKeyPair);
        } catch (InvalidAlgorithmParameterException | NoSuchAlgorithmException |
                 NoSuchProviderException | CipherException e) {
            e.printStackTrace();
        }

        new User(newUserHandle, "Example", "User", "123 Main Street",
                null, "New City", "OR", "97204-1234", "503-123-4567",
                newUserHandle + "@silamoney.com", "975865809", userPrivateKey, birthdate
        );

        ApiResponse response = api.getStatementsData(newUserHandle, userPrivateKey, searchFilters);
        assertEquals(200, response.getStatusCode());
    }

    @Test
    public void UnregisteredUserResponse401() throws Exception {
        ApiResponse response = api.getVirtualAccounts(DefaultConfigurations.getBusinessHandle(),
                DefaultConfigurations.getUserPrivateKey());
        assertEquals(401, response.getStatusCode());
    }

}