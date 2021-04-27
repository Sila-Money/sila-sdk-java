package com.silamoney.client.tests;

import static org.junit.Assert.assertEquals;

import com.silamoney.client.api.ApiResponse;
import com.silamoney.client.api.SilaApi;
import com.silamoney.client.testsutils.DefaultConfigurations;
import com.silamoney.clientrefactored.domain.SearchFilters;
import com.silamoney.clientrefactored.endpoints.transactions.gettransactions.GetTransactionsRequest;
import com.silamoney.clientrefactored.endpoints.transactions.gettransactions.GetTransactionsResponse;

import org.junit.Test;

public class GetTransactionsTests {

    SilaApi api = new SilaApi(DefaultConfigurations.host, DefaultConfigurations.appHandle,
			DefaultConfigurations.privateKey);

    @Test
	public void Response200() throws Exception {

		GetTransactionsRequest request = GetTransactionsRequest.builder()
            .userHandle(DefaultConfigurations.getUserHandle())
            .userPrivateKey(DefaultConfigurations.getUserPrivateKey())
            .searchFilters(SearchFilters.builder()
                .build()
            )
            .build();

        ApiResponse response = api.getTransactions(request);

		GetTransactionsResponse parsedResponse = (GetTransactionsResponse) response.getData();

        assertEquals("SUCCESS", parsedResponse.getStatus());

	}

}
