package com.silamoney.client.tests;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import com.silamoney.client.api.ApiResponse;
import com.silamoney.client.api.SilaApi;
import com.silamoney.client.exceptions.BadRequestException;
import com.silamoney.client.exceptions.ForbiddenException;
import com.silamoney.client.exceptions.InvalidSignatureException;
import com.silamoney.client.exceptions.ServerSideException;
import com.silamoney.client.testsutils.DefaultConfigurations;

import org.junit.Test;

/**
 *
 * @author Karlo Lorenzana
 */
public class GetAccountsTests {

        SilaApi api = new SilaApi(DefaultConfigurations.host, DefaultConfigurations.appHandle,
                        DefaultConfigurations.privateKey);

        String userHandle = "javasdk-893748932";
        String userPrivateKey = "f6b5751234d4586873714066c538b9ddaa51ee5e3188a58236be1671f0be0ed3";

        @Test
        public void Response200Success() throws Exception {
                // BANKACCOUNT5
                ApiResponse response = api.getAccounts(DefaultConfigurations.getUserHandle(),
                                DefaultConfigurations.getUserPrivateKey());

                assertEquals(200, response.getStatusCode());
        }

        @Test
        public void Response400() throws BadRequestException, InvalidSignatureException, ServerSideException,
                        IOException, InterruptedException, ForbiddenException {
                ApiResponse response = api.getAccounts("", DefaultConfigurations.getUserPrivateKey());
                assertEquals(400, response.getStatusCode());
        }

        @Test
        public void Response401() throws BadRequestException, InvalidSignatureException, ServerSideException,
                        IOException, InterruptedException, ForbiddenException {
                api = new SilaApi(DefaultConfigurations.host, DefaultConfigurations.appHandle,
                                "3a1076bf45ab87712ad64ccb3b10217737f7faacbf2872e88fdd9a537d8fe266");

                ApiResponse response = api.getAccounts(DefaultConfigurations.getUserHandle(),
                                DefaultConfigurations.getUserPrivateKey());
                assertEquals(401, response.getStatusCode());
        }
}
