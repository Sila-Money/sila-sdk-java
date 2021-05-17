package com.silamoney.client.testsrefactored.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.concurrent.TimeUnit;

import com.silamoney.client.api.ApiResponse;
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
        
        ApiResponse response = CheckKyc.send(request);
        CheckKycResponse parsedResponse = (CheckKycResponse) response.getData();

        while (!parsedResponse.getStatus().contains("SUCCESS")) {
            TimeUnit.SECONDS.sleep(5);
            response = CheckKyc.send(request);
        }

        assertEquals("SUCCESS", parsedResponse.getStatus());
        assertNotNull(parsedResponse.getEntityType());
        assertNotNull(parsedResponse.getVerificationStatus());
        assertNotNull(parsedResponse.getVerificationHistory());
        assertNotNull(parsedResponse.getVerificationHistory().get(0).getVerificationId());
        assertNotNull(parsedResponse.getVerificationHistory().get(0).getVerificationStatus());
        assertNotNull(parsedResponse.getVerificationHistory().get(0).getKycLevel());
        assertNotNull(parsedResponse.getVerificationHistory().get(0).getReasons());
        assertNotNull(parsedResponse.getVerificationHistory().get(0).getTags());

        request = CheckKycRequest.builder()
            .userHandle(DefaultConfigurations.getUser2Handle())
            .userPrivateKey(DefaultConfigurations.getUser2PrivateKey())
            .build();

        response = CheckKyc.send(request);
        parsedResponse = (CheckKycResponse) response.getData();

        while (!parsedResponse.getStatus().contains("SUCCESS")) {
            TimeUnit.SECONDS.sleep(5);
            response = CheckKyc.send(request);
        }

        assertEquals("SUCCESS", parsedResponse.getStatus());
        assertNotNull(parsedResponse.getEntityType());
        assertNotNull(parsedResponse.getVerificationStatus());
        assertNotNull(parsedResponse.getVerificationHistory());
        assertNotNull(parsedResponse.getVerificationHistory().get(0).getVerificationId());
        assertNotNull(parsedResponse.getVerificationHistory().get(0).getVerificationStatus());
        assertNotNull(parsedResponse.getVerificationHistory().get(0).getKycLevel());
        assertNotNull(parsedResponse.getVerificationHistory().get(0).getReasons());
        assertNotNull(parsedResponse.getVerificationHistory().get(0).getTags());

        request = CheckKycRequest.builder()
            .userHandle(DefaultConfigurations.getBusinessHandle())
            .userPrivateKey(DefaultConfigurations.getBusinessPrivateKey())
            .build();

        response = CheckKyc.send(request);
        parsedResponse = (CheckKycResponse) response.getData();

        while (!parsedResponse.getMessage().contains("passed")) {
            TimeUnit.SECONDS.sleep(5);
            response = CheckKyc.send(request);
        }

        assertNotNull(parsedResponse.getEntityType());
        assertNotNull(parsedResponse.getVerificationStatus());
        assertNotNull(parsedResponse.getVerificationHistory());
        assertNotNull(parsedResponse.getVerificationHistory().get(0).getVerificationId());
        assertNotNull(parsedResponse.getVerificationHistory().get(0).getVerificationStatus());
        assertNotNull(parsedResponse.getVerificationHistory().get(0).getKycLevel());
        assertNotNull(parsedResponse.getVerificationHistory().get(0).getReasons());
        assertNotNull(parsedResponse.getVerificationHistory().get(0).getTags());
        assertNotNull(parsedResponse.getCertificationStatus());
        assertNotNull(parsedResponse.getCertificationHistory());
        assertNotNull(parsedResponse.getMembers());

    }
    
}
