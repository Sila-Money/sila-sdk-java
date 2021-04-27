package com.silamoney.client.tests;

import static org.junit.Assert.assertTrue;

import com.silamoney.client.api.ApiResponse;
import com.silamoney.client.api.SilaApi;
import com.silamoney.client.testsutils.DefaultConfigurations;
import com.silamoney.clientrefactored.endpoints.accounts.getaccounts.GetAccountsRequest;
import com.silamoney.clientrefactored.endpoints.accounts.getaccounts.GetAccountsResponse;

import org.junit.Test;

public class GetAccountsTests {

        SilaApi api = new SilaApi(DefaultConfigurations.host, DefaultConfigurations.appHandle,
                        DefaultConfigurations.privateKey);

        @Test
        public void Response200() throws Exception {

                GetAccountsRequest request = GetAccountsRequest.builder()
                                .userHandle(DefaultConfigurations.getUserHandle())
                                .userPrivateKey(DefaultConfigurations.getUserPrivateKey()).build();

                ApiResponse response = api.getAccounts(request);

                GetAccountsResponse parsedResponse = (GetAccountsResponse)response.getData();

                assertTrue(parsedResponse.getAccounts().size() > 0);
        }

}
