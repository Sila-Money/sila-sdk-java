package com.silamoney.client.testsrefactored.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import com.silamoney.client.testsutils.DefaultConfigurations;
import com.silamoney.clientrefactored.configuration.Environment;
import com.silamoney.clientrefactored.configuration.SilaApi;
import com.silamoney.clientrefactored.endpoints.entities.requestkyc.RequestKyc;
import com.silamoney.clientrefactored.endpoints.entities.requestkyc.RequestKycRequest;
import com.silamoney.clientrefactored.endpoints.entities.requestkyc.RequestKycResponse;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

public class RequestKycTests {

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
	public void Response200Individual() throws Exception {

		RequestKycRequest request = RequestKycRequest.builder()
            .userHandle(DefaultConfigurations.getUserHandle())
            .userPrivateKey(DefaultConfigurations.getUserPrivateKey())
            .kycLevel("INSTANT-ACH")
            .build();

        RequestKycResponse response = RequestKyc.send(request);

        assertEquals("SUCCESS", response.getStatus());
        assertNotNull(response.getMessage());
        assertNotNull(response.getReference());
        assertNotNull(response.getVerificationUuid());
        assertTrue(response.isSuccess());

        request = RequestKycRequest.builder()
            .userHandle(DefaultConfigurations.getUser2Handle())
            .userPrivateKey(DefaultConfigurations.getUser2PrivateKey())
            .build();

        response = RequestKyc.send(request);

        assertEquals("SUCCESS", response.getStatus());
        assertNotNull(response.getMessage());
        assertNotNull(response.getReference());
        assertNotNull(response.getVerificationUuid());
        assertTrue(response.isSuccess());

	}

    @Test
	public void Response200Business() throws Exception {

		RequestKycRequest request = RequestKycRequest.builder()
            .userHandle(DefaultConfigurations.getBusinessHandle())
            .userPrivateKey(DefaultConfigurations.getBusinessPrivateKey())
            .build();

        RequestKycResponse response = RequestKyc.send(request);

        assertEquals("SUCCESS", response.getStatus());
        assertNotNull(response.getMessage());
        assertNotNull(response.getReference());
        assertNotNull(response.getVerificationUuid());
        assertTrue(response.isSuccess());

	}
    
}
