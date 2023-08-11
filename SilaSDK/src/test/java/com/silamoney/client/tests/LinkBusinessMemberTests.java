package com.silamoney.client.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import com.silamoney.client.api.ApiResponse;
import com.silamoney.client.api.SilaApi;
import com.silamoney.client.domain.LinkBusinessMemberResponse;
import com.silamoney.client.domain.LinkBusinessOperationResponse;
import com.silamoney.client.testsutils.DefaultConfigurations;

import org.junit.Test;

import java.util.concurrent.TimeUnit;

/**
 *
 * @author Karlo Lorenzana
 */
public class LinkBusinessMemberTests {

        SilaApi api = new SilaApi(DefaultConfigurations.host, DefaultConfigurations.appHandle,
                        DefaultConfigurations.privateKey);

        @Test
        public void Response200() throws Exception {
                ApiResponse response = api.linkBusinessMember(DefaultConfigurations.getUserHandle(),
                                DefaultConfigurations.getUserPrivateKey(), DefaultConfigurations.getBusinessHandle(),
                                DefaultConfigurations.getBusinessPrivateKey(),
                                DefaultConfigurations.getBusinessRole("administrator"), null, "test details", null);

                assertEquals(200, response.getStatusCode());
                assertTrue(((LinkBusinessMemberResponse) response.getData()).isSuccess());
                assertNotNull(((LinkBusinessMemberResponse) response.getData()).getMessage());
                assertEquals(DefaultConfigurations.getBusinessRole("administrator").getName(),
                                ((LinkBusinessMemberResponse) response.getData()).getRole());
                assertEquals("test details", ((LinkBusinessMemberResponse) response.getData()).getDetails());

                response = api.linkBusinessMember(DefaultConfigurations.getUserHandle(),
                                DefaultConfigurations.getUserPrivateKey(), DefaultConfigurations.getBusinessHandle(),
                                DefaultConfigurations.getBusinessPrivateKey(),
                                DefaultConfigurations.getBusinessRole("controlling_officer"), null, "test details",
                                null);

                assertEquals(200, response.getStatusCode());
                assertTrue(((LinkBusinessMemberResponse) response.getData()).isSuccess());
                assertNotNull(((LinkBusinessMemberResponse) response.getData()).getMessage());
                assertEquals(DefaultConfigurations.getBusinessRole("controlling_officer").getName(),
                                ((LinkBusinessMemberResponse) response.getData()).getRole());
                assertEquals("test details", ((LinkBusinessMemberResponse) response.getData()).getDetails());

                response = api.linkBusinessMember(DefaultConfigurations.getUser2Handle(),
                                DefaultConfigurations.getUser2PrivateKey(), DefaultConfigurations.getBusinessHandle(),
                                DefaultConfigurations.getBusinessPrivateKey(),
                                DefaultConfigurations.getBusinessRole("administrator"), null, "test details", null);

                assertEquals(200, response.getStatusCode());
                assertTrue(((LinkBusinessMemberResponse) response.getData()).isSuccess());
                assertNotNull(((LinkBusinessMemberResponse) response.getData()).getMessage());
                assertEquals(DefaultConfigurations.getBusinessRole("administrator").getName(),
                                ((LinkBusinessMemberResponse) response.getData()).getRole());
                assertEquals("test details", ((LinkBusinessMemberResponse) response.getData()).getDetails());

                TimeUnit.SECONDS.sleep(40);

                response = api.unlinkBusinessMember(DefaultConfigurations.getUser2Handle().toLowerCase(),
                        DefaultConfigurations.getUser2PrivateKey(), DefaultConfigurations.getBusinessHandle(),
                        DefaultConfigurations.getBusinessPrivateKey(),
                        DefaultConfigurations.getBusinessRole("administrator"));

                assertEquals(200, response.getStatusCode());
                assertTrue(((LinkBusinessOperationResponse) response.getData()).isSuccess());
                assertNotNull(((LinkBusinessOperationResponse) response.getData()).getMessage());
                assertEquals(DefaultConfigurations.getBusinessRole("administrator").getName(),
                        ((LinkBusinessOperationResponse) response.getData()).getRole());

        }

        @Test
        public void Response200BeneficialOwner() throws Exception {
                ApiResponse response = api.linkBusinessMember(DefaultConfigurations.getUser2Handle(),
                                DefaultConfigurations.getUser2PrivateKey(), DefaultConfigurations.getBusinessHandle(),
                                DefaultConfigurations.getBusinessPrivateKey(),
                                DefaultConfigurations.getBusinessRole("beneficial_owner"), null, "test details", (float)0.66);

                assertEquals(200, response.getStatusCode());
                assertTrue(((LinkBusinessMemberResponse) response.getData()).isSuccess());
                assertNotNull(((LinkBusinessMemberResponse) response.getData()).getMessage());
                assertEquals(DefaultConfigurations.getBusinessRole("beneficial_owner").getName(),
                                ((LinkBusinessMemberResponse) response.getData()).getRole());
                assertEquals("test details", ((LinkBusinessMemberResponse) response.getData()).getDetails());
        }

}

