package com.silamoney.client.testsrefactored.tests;

import static org.junit.Assert.assertEquals;

import com.silamoney.client.testsutils.DefaultConfigurations;
import com.silamoney.clientrefactored.configuration.Environment;
import com.silamoney.clientrefactored.configuration.SilaApi;
import com.silamoney.clientrefactored.domain.SearchFilters;
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
	public void Response200PublicToken() throws Exception {

		GetTransactionsRequest request = GetTransactionsRequest.builder()
            .userHandle(DefaultConfigurations.getUserHandle())
            .userPrivateKey(DefaultConfigurations.getUserPrivateKey())
            .searchFilters(SearchFilters.builder()
                .build()
            )
            .build();

        GetTransactionsResponse response = GetTransactions.send(request);

        assertEquals("SUCCESS", response.getStatus());

	}

}
