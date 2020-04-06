package com.silamoney.client.tests;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import com.silamoney.client.api.ApiResponse;
import com.silamoney.client.api.SilaApi;
import com.silamoney.client.domain.BaseResponse;
import com.silamoney.client.exceptions.BadRequestException;
import com.silamoney.client.exceptions.ForbiddenException;
import com.silamoney.client.exceptions.InvalidSignatureException;
import com.silamoney.client.exceptions.ServerSideException;
import com.silamoney.client.testsutils.DefaultConfigurations;

import org.junit.Test;

/**
 *
 * @author Karlo Lorenzana
 */
public class CheckKYCTests {

    SilaApi api = new SilaApi(DefaultConfigurations.host, DefaultConfigurations.appHandle,
            DefaultConfigurations.privateKey);

    String userHandle = "javasdk-893748932";
    String userHandle2Failure = "javasdk-349425739";
    String userPrivateKey = "f6b5751234d4586873714066c538b9ddaa51ee5e3188a58236be1671f0be0ed3";
    String userPrivateKeyFailure = "f6406f347993b09ee3760e8ef0fb70abdeaa90265dc02de78f86da5eff9b6272";

    @Test
    public void Response200() throws Exception {
        // KYCID2
        if (DefaultConfigurations.getUserHandle() == null) {
            DefaultConfigurations.setUserHandle(userHandle);
        }
        if (DefaultConfigurations.getUserPrivateKey() == null) {
            DefaultConfigurations.setUserPrivateKey(userPrivateKey);
        }
        ApiResponse response = api.checkKYC(DefaultConfigurations.getUserHandle(),
                DefaultConfigurations.getUserPrivateKey());

        assertEquals(200, response.getStatusCode());
        while (!((BaseResponse) response.getData()).getStatus().contains("SUCCESS")) {
            TimeUnit.SECONDS.sleep(5);
            response = api.checkKYC(DefaultConfigurations.getUserHandle(), DefaultConfigurations.getUserPrivateKey());
        }
        assertEquals("SUCCESS", ((BaseResponse) response.getData()).getStatus());
    }

    @Test
    public void Response200Failure() throws Exception {
        // KYCID3
        ApiResponse response = api.checkKYC(userHandle2Failure, userPrivateKeyFailure);
        assertEquals(200, response.getStatusCode());
        assertEquals("FAILURE", ((BaseResponse) response.getData()).getStatus());
    }

    @Test
    public void Response400() throws BadRequestException, InvalidSignatureException, ServerSideException, IOException,
            InterruptedException, ForbiddenException {
        if (DefaultConfigurations.getUserHandle() == null) {
            DefaultConfigurations.setUserHandle(userHandle2Failure);
        }
        if (DefaultConfigurations.getUserPrivateKey() == null) {
            DefaultConfigurations.setUserPrivateKey(userPrivateKeyFailure);
        }
        ApiResponse response = api.checkKYC("", DefaultConfigurations.getUserPrivateKey());
        assertEquals(400, response.getStatusCode());
        // System.out.println(GsonUtils.objectToJsonStringFormato(response));
    }

    @Test
    public void Response401User() throws BadRequestException, InvalidSignatureException, ServerSideException,
            IOException, InterruptedException, ForbiddenException {
        if (DefaultConfigurations.getUserHandle() == null) {
            DefaultConfigurations.setUserHandle(userHandle2Failure);
        }
        if (DefaultConfigurations.getUserPrivateKey() == null) {
            DefaultConfigurations.setUserPrivateKey(userPrivateKeyFailure);
        }
        api = new SilaApi(DefaultConfigurations.host, DefaultConfigurations.appHandle,
                "3a1076bf45ab87712ad64ccb3b10217737f7faacbf2872e88fdd9a537d8fe266");

        ApiResponse response = api.checkKYC(DefaultConfigurations.getUserHandle(),
                DefaultConfigurations.getUserPrivateKey());
        assertEquals(401, response.getStatusCode());
        // System.out.println(GsonUtils.objectToJsonStringFormato(response));
    }
}
