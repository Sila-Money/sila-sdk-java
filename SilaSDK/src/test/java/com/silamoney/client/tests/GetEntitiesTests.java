package com.silamoney.client.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import com.silamoney.client.api.ApiResponse;
import com.silamoney.client.api.SilaApi;
import com.silamoney.client.domain.GetEntitiesResponse;
import com.silamoney.client.testsutils.DefaultConfigurations;

import org.junit.Test;

/**
 *
 * @author Karlo Lorenzana
 */
public class GetEntitiesTests {

    SilaApi api = new SilaApi(DefaultConfigurations.host, DefaultConfigurations.appHandle,
            DefaultConfigurations.privateKey);

    @Test
    public void Response200() throws Exception {
        ApiResponse response = api.getEntities(null, 1, 50);

        assertEquals(200, response.getStatusCode());
        assertNotNull(((GetEntitiesResponse)response.getData()).getEntities());
        assertNotNull(((GetEntitiesResponse)response.getData()).getPagination());
        assertNotNull(((GetEntitiesResponse)response.getData()).getEntities().getIndividuals());
        assertNotNull(((GetEntitiesResponse)response.getData()).getEntities().getBusinesses());
    }
}
