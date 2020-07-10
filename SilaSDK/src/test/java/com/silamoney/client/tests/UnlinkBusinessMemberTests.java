package com.silamoney.client.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import com.silamoney.client.api.ApiResponse;
import com.silamoney.client.api.SilaApi;
import com.silamoney.client.domain.LinkBusinessOperationResponse;
import com.silamoney.client.testsutils.DefaultConfigurations;

import org.junit.Test;

/**
 *
 * @author Karlo Lorenzana
 */
public class UnlinkBusinessMemberTests {

        SilaApi api = new SilaApi(DefaultConfigurations.host, DefaultConfigurations.appHandle,
                        DefaultConfigurations.privateKey);

        @Test
        public void Response200() throws Exception {
                ApiResponse response = api.unlinkBusinessMember(DefaultConfigurations.getUser2Handle().toLowerCase(),
                                DefaultConfigurations.getUser2PrivateKey(), DefaultConfigurations.getBusinessHandle(),
                                DefaultConfigurations.getBusinessPrivateKey(),
                                DefaultConfigurations.getBusinessRole("administrator"));

                assertEquals(200, response.getStatusCode());
                assertTrue(((LinkBusinessOperationResponse) response.getData()).isSuccess());
                assertNotNull(((LinkBusinessOperationResponse) response.getData()).getMessage());
                assertEquals(DefaultConfigurations.getBusinessRole("administrator").getName(),
                                ((LinkBusinessOperationResponse) response.getData()).getRole());
        }

}