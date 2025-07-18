package com.silamoney.client.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import com.silamoney.client.api.ApiResponse;
import com.silamoney.client.api.SilaApi;
import com.silamoney.client.testsutils.DefaultConfigurations;

import org.junit.Test;

/**
 *
 * @author Icon Systems
 */
public class UpdateIdDocumentTests {

    SilaApi api = new SilaApi(DefaultConfigurations.host, DefaultConfigurations.appHandle,
            DefaultConfigurations.privateKey);

    @Test
    public void Response200() throws Exception {

        String userHandle = DefaultConfigurations.getUserHandle();
        String userPrivateKey = DefaultConfigurations.getUserPrivateKey();
        String uuid = "test-uuid-" + System.currentTimeMillis(); // This will be invalid
        String docType = "id_drivers_license";
        String docId = "TEST123456789";
        String docState = "CA";

        ApiResponse response = api.updateIdDocument(userHandle, userPrivateKey, uuid, docType, docId, docState);

        assertNotNull(response);
        assertNotNull(response.getStatusCode());
    }
} 