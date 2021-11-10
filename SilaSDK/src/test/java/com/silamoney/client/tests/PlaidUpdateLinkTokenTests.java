package com.silamoney.client.tests;

import static org.junit.Assert.assertNotNull;

import com.silamoney.client.api.ApiResponse;
import com.silamoney.client.api.SilaApi;
import com.silamoney.client.domain.PlaidUpdateLinkTokenResponse;
import com.silamoney.client.domain.User;
import com.silamoney.client.testsutils.DefaultConfigurations;

import org.joda.time.LocalDate;
import org.junit.Test;

public class PlaidUpdateLinkTokenTests {

    SilaApi api = new SilaApi(DefaultConfigurations.host, DefaultConfigurations.appHandle,
            DefaultConfigurations.privateKey);

    @Test
    public void Response200() throws Exception {

        LocalDate birthdate = new LocalDate(1900, 01, 31);
        User user = new User(DefaultConfigurations.getUser3Handle(), "Example", "User", "123 Main Street", null,
				"New City", "OR", "97204-1234", "503-123-4567", "example@silamoney.com", "123452383",
				DefaultConfigurations.getUser3CryptoAddress(), birthdate.toDate());

        api.register(user);
        api.linkAccountPlaidToken(DefaultConfigurations.getUser3Handle(),
				DefaultConfigurations.getUser3PrivateKey(), "updatelinktoken", DefaultConfigurations.getPlaidToken(), null, "LEGACY");

        ApiResponse response = api.plaidUpdateLinkToken(DefaultConfigurations.getUser3Handle(),"updatelinktoken");

        PlaidUpdateLinkTokenResponse parsedResponse = (PlaidUpdateLinkTokenResponse) response.getData();

        assertNotNull(parsedResponse.getStatus());
        assertNotNull(parsedResponse.getMessage());

    }

}
