package com.silamoney.client.tests;

import static org.junit.Assert.assertTrue;

import java.util.List;

import com.silamoney.client.api.ApiResponse;
import com.silamoney.client.api.SilaApi;
import com.silamoney.client.domain.Account;
import com.silamoney.client.testsutils.DefaultConfigurations;

import org.junit.Test;

public class GetAccountsTests {

        SilaApi api = new SilaApi(DefaultConfigurations.host, DefaultConfigurations.appHandle,
                        DefaultConfigurations.privateKey);

        @Test
        public void Response200() throws Exception {

                ApiResponse response = api.getAccounts(DefaultConfigurations.getUserHandle(), DefaultConfigurations.getUserPrivateKey());

                List<Account> parsedResponse = (List<Account>)response.getData();

                assertTrue(parsedResponse.size() > 0);
        }

}
