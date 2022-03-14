package com.silamoney.client.tests;

import com.silamoney.client.api.ApiResponse;
import com.silamoney.client.api.SilaApi;
import com.silamoney.client.domain.BaseResponse;
import com.silamoney.client.domain.GetWebhooksResponse;
import com.silamoney.client.domain.WebhookSearchFilters;
import com.silamoney.client.testsutils.DefaultConfigurations;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @author Anuj
 */
public class RetryWebhooksTests {

    SilaApi api = new SilaApi(DefaultConfigurations.host, DefaultConfigurations.appHandle,
            DefaultConfigurations.privateKey);

    @Test
    public void Response200() throws Exception {
        WebhookSearchFilters searchFilters = new WebhookSearchFilters();
        searchFilters.setPage(1);
        searchFilters.setPerPage(20);
        ApiResponse response = api.getWebhooks(DefaultConfigurations.getUserHandle(), searchFilters);
        assertEquals(200, response.getStatusCode());
        GetWebhooksResponse parsedResponse = (GetWebhooksResponse) response.getData();
        if (parsedResponse.getWebhooks() != null && parsedResponse.getWebhooks().size() > 0) {
             response = api.retryWebhooks(DefaultConfigurations.getUserHandle(), parsedResponse.getWebhooks().get(0).getUuid());
            assertEquals(200, response.getStatusCode());
            BaseResponse parsedResponseRetry = (BaseResponse) response.getData();
            assertEquals("SUCCESS", parsedResponseRetry.getStatus());
        }
    }
}
