package com.silamoney.client.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.concurrent.TimeUnit;

import com.silamoney.client.api.ApiResponse;
import com.silamoney.client.api.SilaApi;
import com.silamoney.client.domain.CheckKYCResponse;
import com.silamoney.client.testsutils.DefaultConfigurations;

import org.junit.Test;

public class CheckKYCTests {

    SilaApi api = new SilaApi(DefaultConfigurations.host, DefaultConfigurations.appHandle,
            DefaultConfigurations.privateKey);

    @Test
    public void Response200() throws Exception {
        
        ApiResponse response = api.checkKYC(DefaultConfigurations.getUserHandle(), DefaultConfigurations.getUserPrivateKey());

        CheckKYCResponse parsedResponse = (CheckKYCResponse) response.getData();

        while (parsedResponse.getStatus().contains("FAILURE") && parsedResponse.getMessage().contains("pending")){
            TimeUnit.SECONDS.sleep(5);
            response = api.checkKYC(DefaultConfigurations.getUserHandle(), DefaultConfigurations.getUserPrivateKey());

            parsedResponse = (CheckKYCResponse) response.getData();
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

        response = api.checkKYC(DefaultConfigurations.getUser2Handle(), DefaultConfigurations.getUser2PrivateKey());

        parsedResponse = (CheckKYCResponse) response.getData();

        while (parsedResponse.getStatus().contains("FAILURE") && parsedResponse.getMessage().contains("pending")) {
            TimeUnit.SECONDS.sleep(5);
            response = api.checkKYC(DefaultConfigurations.getUser2Handle(), DefaultConfigurations.getUser2PrivateKey());

            parsedResponse = (CheckKYCResponse) response.getData();
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

        response = api.checkKYC(DefaultConfigurations.getBusinessHandle(), DefaultConfigurations.getBusinessPrivateKey());

        parsedResponse = (CheckKYCResponse) response.getData();

        while (parsedResponse.getStatus().contains("FAILURE") && parsedResponse.getMessage().contains("pending") && !parsedResponse.getMessage().contains("Business has passed verification")) {
            TimeUnit.SECONDS.sleep(5);
            response = api.checkKYC(DefaultConfigurations.getBusinessHandle(), DefaultConfigurations.getBusinessPrivateKey());

            parsedResponse = (CheckKYCResponse) response.getData();
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
    @Test
    public void Response200withSardine() throws Exception {

        ApiResponse response = api.checkKYC(DefaultConfigurations.getUser4Handle(), DefaultConfigurations.getUser4PrivateKey(),"INSTANT-ACHV2");

        CheckKYCResponse parsedResponse = (CheckKYCResponse) response.getData();

        assertEquals("SUCCESS", parsedResponse.getStatus());
        assertNotNull(parsedResponse.getEntityType());
        assertNotNull(parsedResponse.getVerificationStatus());
        assertNotNull(parsedResponse.getVerificationHistory());
        assertNotNull(parsedResponse.getVerificationHistory().get(0).getVerificationId());
        assertNotNull(parsedResponse.getVerificationHistory().get(0).getVerificationStatus());
        assertNotNull(parsedResponse.getVerificationHistory().get(0).getKycLevel());
        assertNotNull(parsedResponse.getVerificationHistory().get(0).getReasons());
        assertNotNull(parsedResponse.getVerificationHistory().get(0).getTags());


    }

}
