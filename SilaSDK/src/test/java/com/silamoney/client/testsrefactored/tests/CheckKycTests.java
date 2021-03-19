package com.silamoney.client.testsrefactored.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.concurrent.TimeUnit;

import com.silamoney.client.testsutils.DefaultConfigurations;
import com.silamoney.clientrefactored.configuration.Environment;
import com.silamoney.clientrefactored.configuration.SilaApi;
import com.silamoney.clientrefactored.endpoints.entities.checkkyc.CheckKyc;
import com.silamoney.clientrefactored.endpoints.entities.checkkyc.CheckKycRequest;
import com.silamoney.clientrefactored.endpoints.entities.checkkyc.CheckKycResponse;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

public class CheckKycTests {

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

        CheckKycRequest request = CheckKycRequest.builder()
            .userHandle(DefaultConfigurations.getUserHandle())
            .userPrivateKey(DefaultConfigurations.getUserPrivateKey())
            .build();
        
        CheckKycResponse response = CheckKyc.send(request);

        while (!response.getStatus().contains("SUCCESS")) {
            TimeUnit.SECONDS.sleep(5);
            response = CheckKyc.send(request);
        }

        assertEquals("SUCCESS", response.getStatus());
        assertNotNull(response.getEntityType());
        assertNotNull(response.getVerificationStatus());
        assertNotNull(response.getVerificationHistory());
        assertNotNull(response.getVerificationHistory().get(0).getVerificationId());
        assertNotNull(response.getVerificationHistory().get(0).getVerificationStatus());
        assertNotNull(response.getVerificationHistory().get(0).getKycLevel());
        assertNotNull(response.getVerificationHistory().get(0).getRequestAt());
        assertNotNull(response.getVerificationHistory().get(0).getUpdatedAt());
        assertNotNull(response.getVerificationHistory().get(0).getReasons());
        assertNotNull(response.getVerificationHistory().get(0).getTags());

        request = CheckKycRequest.builder()
            .userHandle(DefaultConfigurations.getUser2Handle())
            .userPrivateKey(DefaultConfigurations.getUser2PrivateKey())
            .build();

        response = CheckKyc.send(request);

        while (!response.getStatus().contains("SUCCESS")) {
            TimeUnit.SECONDS.sleep(5);
            response = CheckKyc.send(request);
        }

        assertEquals("SUCCESS", response.getStatus());
        assertNotNull(response.getEntityType());
        assertNotNull(response.getVerificationStatus());
        assertNotNull(response.getVerificationHistory());
        assertNotNull(response.getVerificationHistory().get(0).getVerificationId());
        assertNotNull(response.getVerificationHistory().get(0).getVerificationStatus());
        assertNotNull(response.getVerificationHistory().get(0).getKycLevel());
        assertNotNull(response.getVerificationHistory().get(0).getRequestAt());
        assertNotNull(response.getVerificationHistory().get(0).getUpdatedAt());
        assertNotNull(response.getVerificationHistory().get(0).getReasons());
        assertNotNull(response.getVerificationHistory().get(0).getTags());

        request = CheckKycRequest.builder()
            .userHandle(DefaultConfigurations.getBusinessHandle())
            .userPrivateKey(DefaultConfigurations.getBusinessPrivateKey())
            .build();

        response = CheckKyc.send(request);

        while (!response.getStatus().contains("SUCCESS")) {
            TimeUnit.SECONDS.sleep(5);
            response = CheckKyc.send(request);
        }

        assertEquals("SUCCESS", response.getStatus());
        assertNotNull(response.getEntityType());
        assertNotNull(response.getVerificationStatus());
        assertNotNull(response.getVerificationHistory());
        assertNotNull(response.getVerificationHistory().get(0).getVerificationId());
        assertNotNull(response.getVerificationHistory().get(0).getVerificationStatus());
        assertNotNull(response.getVerificationHistory().get(0).getKycLevel());
        assertNotNull(response.getVerificationHistory().get(0).getRequestAt());
        assertNotNull(response.getVerificationHistory().get(0).getUpdatedAt());
        assertNotNull(response.getVerificationHistory().get(0).getReasons());
        assertNotNull(response.getVerificationHistory().get(0).getTags());
        assertNotNull(response.getCertificationStatus());
        assertNotNull(response.getCertificationHistory());
        assertNotNull(response.getMembers());

    }
    
}
