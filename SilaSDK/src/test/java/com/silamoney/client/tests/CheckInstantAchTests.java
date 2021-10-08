package com.silamoney.client.tests;

import static org.junit.Assert.assertNotNull;

import com.silamoney.client.api.ApiResponse;
import com.silamoney.client.api.SilaApi;
import com.silamoney.client.domain.CheckInstantAchResponse;
import com.silamoney.client.testsutils.DefaultConfigurations;

import org.junit.Test;

public class CheckInstantAchTests {

    SilaApi api = new SilaApi(DefaultConfigurations.host, DefaultConfigurations.appHandle,
            DefaultConfigurations.privateKey);

    @Test
    public void CheckInstantAchTest() throws Exception {

        ApiResponse response = api.checkInstantAch("defaultpt", DefaultConfigurations.getUserHandle(),
                DefaultConfigurations.getUserPrivateKey());

        CheckInstantAchResponse parsedResponse = (CheckInstantAchResponse) response.getData();

        assertNotNull(parsedResponse.getStatus());
        assertNotNull(parsedResponse.getMessage());
        assertNotNull(parsedResponse.getSuccess());
    }

}
