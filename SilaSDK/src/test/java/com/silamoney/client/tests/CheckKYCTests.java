package com.silamoney.client.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.concurrent.TimeUnit;

import com.silamoney.client.api.ApiResponse;
import com.silamoney.client.api.SilaApi;
import com.silamoney.client.testsutils.DefaultConfigurations;
import com.silamoney.clientrefactored.endpoints.entities.checkkyc.CheckKycRequest;
import com.silamoney.clientrefactored.endpoints.entities.checkkyc.CheckKycResponse;

import org.junit.Test;

public class CheckKYCTests {

    SilaApi api = new SilaApi(DefaultConfigurations.host, DefaultConfigurations.appHandle,
            DefaultConfigurations.privateKey);

    @Test
    public void Response200() throws Exception {

        CheckKycRequest request = CheckKycRequest.builder().userHandle(DefaultConfigurations.getUserHandle())
                .userPrivateKey(DefaultConfigurations.getUserPrivateKey()).build();

        ApiResponse response = api.checkKYC(request);

        CheckKycResponse parsedResponse = (CheckKycResponse) response.getData();

        while (!parsedResponse.getStatus().contains("SUCCESS")) {
            TimeUnit.SECONDS.sleep(5);
            response = api.checkKYC(request);

            parsedResponse = (CheckKycResponse) response.getData();
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

        request = CheckKycRequest.builder().userHandle(DefaultConfigurations.getUser2Handle())
                .userPrivateKey(DefaultConfigurations.getUser2PrivateKey()).build();

        response = api.checkKYC(request);

        parsedResponse = (CheckKycResponse) response.getData();

        while (!parsedResponse.getStatus().contains("SUCCESS")) {
            TimeUnit.SECONDS.sleep(5);
            response = api.checkKYC(request);

            parsedResponse = (CheckKycResponse) response.getData();
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

        request = CheckKycRequest.builder().userHandle(DefaultConfigurations.getBusinessHandle())
                .userPrivateKey(DefaultConfigurations.getBusinessPrivateKey()).build();

        response = api.checkKYC(request);

        parsedResponse = (CheckKycResponse) response.getData();

        while (!parsedResponse.getStatus().contains("SUCCESS")) {
            TimeUnit.SECONDS.sleep(5);
            response = api.checkKYC(request);

            parsedResponse = (CheckKycResponse) response.getData();
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
