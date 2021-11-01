package com.silamoney.client.tests;

import com.silamoney.client.api.ApiResponse;
import com.silamoney.client.api.SilaApi;
import com.silamoney.client.domain.BaseResponse;
import com.silamoney.client.testsutils.DefaultConfigurations;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class DeleteCardTests {

    SilaApi api = new SilaApi(DefaultConfigurations.host, DefaultConfigurations.appHandle,
            DefaultConfigurations.privateKey);

    @Test
    public void Response200() throws IOException, InterruptedException {

        ApiResponse response = api.deleteCard(DefaultConfigurations.getUserHandle(), "visa",
                DefaultConfigurations.getUserPrivateKey());
        assertEquals(200, response.getStatusCode());
    }

}
