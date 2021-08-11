package com.silamoney.client.testsrefactored.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import com.silamoney.client.api.ApiResponse;
import com.silamoney.client.testsutils.DefaultConfigurations;
import com.silamoney.clientrefactored.configuration.Environment;
import com.silamoney.clientrefactored.configuration.SilaApi;
import com.silamoney.clientrefactored.domain.SearchFilters;
import com.silamoney.clientrefactored.domain.Transaction;
import com.silamoney.clientrefactored.endpoints.transactions.gettransactions.GetTransactions;
import com.silamoney.clientrefactored.endpoints.transactions.gettransactions.GetTransactionsRequest;
import com.silamoney.clientrefactored.endpoints.transactions.gettransactions.GetTransactionsResponse;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

public class GetTransactionsTests {

    @BeforeClass
	public static void init() {
		SilaApi.init(Environment.SANDBOX, DefaultConfigurations.appHandle,
				DefaultConfigurations.privateKey);
	}

    @AfterClass
	public static void dispose() {
		SilaApi.dispose();
	}

    @Test
	public void Response200() throws Exception {

		GetTransactionsRequest request = GetTransactionsRequest.builder()
            .userHandle(DefaultConfigurations.getUserHandle())
            .userPrivateKey(DefaultConfigurations.getUserPrivateKey())
            .searchFilters(SearchFilters.builder()
                .build()
            )
            .build();

        ApiResponse response = GetTransactions.send(request);
        GetTransactionsResponse parsedResponse = (GetTransactionsResponse) response.getData();

        assertEquals("SUCCESS", parsedResponse.getStatus());

	}

    @Test
	public void Response200Redeem() throws Exception {

        List<String> transactionTypes = new ArrayList<>();
        transactionTypes.add("redeem");

		GetTransactionsRequest request = GetTransactionsRequest.builder()
            .userHandle(DefaultConfigurations.getUserHandle())
            .userPrivateKey(DefaultConfigurations.getUserPrivateKey())
            .searchFilters(SearchFilters.builder()
                .transactionTypes(transactionTypes)
                .build()
            )
            .build();

            ApiResponse response = GetTransactions.send(request);
            GetTransactionsResponse parsedResponse = (GetTransactionsResponse) response.getData();

        assertTrue(parsedResponse.getTransactions().size() > 0);
        for (Transaction transaction : parsedResponse.getTransactions()) {
            assertEquals("redeem", transaction.getTransactionType());
        }

	}

    @Test
	public void Response200Issue() throws Exception {

        List<String> transactionTypes = new ArrayList<>();
        transactionTypes.add("issue");

		GetTransactionsRequest request = GetTransactionsRequest.builder()
            .userHandle(DefaultConfigurations.getUserHandle())
            .userPrivateKey(DefaultConfigurations.getUserPrivateKey())
            .searchFilters(SearchFilters.builder()
                .transactionTypes(transactionTypes)
                .build()
            )
            .build();

            ApiResponse response = GetTransactions.send(request);
            GetTransactionsResponse parsedResponse = (GetTransactionsResponse) response.getData();

        assertTrue(parsedResponse.getTransactions().size() > 0);
        for (Transaction transaction : parsedResponse.getTransactions()) {
            assertEquals("issue", transaction.getTransactionType());
        }

	}

    @Test
	public void Response200Transfer() throws Exception {

        List<String> transactionTypes = new ArrayList<>();
        transactionTypes.add("transfer");

		GetTransactionsRequest request = GetTransactionsRequest.builder()
            .userHandle(DefaultConfigurations.getUserHandle())
            .userPrivateKey(DefaultConfigurations.getUserPrivateKey())
            .searchFilters(SearchFilters.builder()
                .transactionTypes(transactionTypes)
                .build()
            )
            .build();

            ApiResponse response = GetTransactions.send(request);
            GetTransactionsResponse parsedResponse = (GetTransactionsResponse) response.getData();

        assertTrue(parsedResponse.getTransactions().size() > 0);
        for (Transaction transaction : parsedResponse.getTransactions()) {
            assertEquals("transfer", transaction.getTransactionType());
        }

	}

}
