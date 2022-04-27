package com.silamoney.client.testsrefactored.tests;

import com.silamoney.client.api.ApiResponse;
import com.silamoney.client.testsutils.DefaultConfigurations;
import com.silamoney.clientrefactored.configuration.Environment;
import com.silamoney.clientrefactored.configuration.SilaApi;
import com.silamoney.clientrefactored.domain.WebhookSearchFilters;
import com.silamoney.clientrefactored.endpoints.webhooks.getWebhooks.GetWebhooks;
import com.silamoney.clientrefactored.endpoints.webhooks.getWebhooks.GetWebhooksRequest;
import com.silamoney.clientrefactored.endpoints.webhooks.getWebhooks.GetWebhooksResponse;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class GetWebhooksTests {
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

        GetWebhooksRequest request = GetWebhooksRequest.builder()
                .userHandle(DefaultConfigurations.getUserHandle())
                .searchFilters(WebhookSearchFilters.builder().page(1).perPage(20).build())
                .build();

        ApiResponse response = GetWebhooks.send(request);
        GetWebhooksResponse parsedResponse = (GetWebhooksResponse) response.getData();

        assertEquals("SUCCESS", parsedResponse.getStatus());

    }

}
