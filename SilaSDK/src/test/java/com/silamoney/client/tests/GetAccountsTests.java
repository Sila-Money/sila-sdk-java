package com.silamoney.client.tests;

import static org.junit.Assert.assertEquals;
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

        @Test
        public void Response200_WithAccountNumberFilter() throws Exception {
            String accountNumber = "3136824589";
            String last4 = accountNumber.substring(accountNumber.length() - 4);
            String maskedLast4 = "*" + last4;

            ApiResponse response = api.getAccounts(
                DefaultConfigurations.getUserHandle(),
                DefaultConfigurations.getUserPrivateKey(),
                null,
                accountNumber
            );

            List<Account> parsed = (List<Account>) response.getData();
            assertEquals(1, parsed.size());
            assertEquals(maskedLast4, parsed.get(0).getAccountNumber());
        }

        @Test
        public void Response200_FindNoAccount() throws Exception {
            ApiResponse response = api.getAccounts(
                DefaultConfigurations.getUserHandle(),
                DefaultConfigurations.getUserPrivateKey(),
                null,
                "123"
            );

            List<Account> parsed = (List<Account>) response.getData();
            assertEquals(0, parsed.size());
        }

}
