package com.silamoney.client.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.hamcrest.Matchers.*;

import java.io.IOException;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import com.silamoney.client.api.ApiResponse;
import com.silamoney.client.api.SilaApi;
import com.silamoney.client.domain.AccountTransactionMessage;
import com.silamoney.client.domain.BadRequestResponse;
import com.silamoney.client.domain.BaseResponse;
import com.silamoney.client.domain.GetTransactionsResponse;
import com.silamoney.client.domain.ProcessingTypeEnum;
import com.silamoney.client.domain.SearchFilters;
import com.silamoney.client.domain.TransactionResponse;
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
public class RedeemSilaTests {

        SilaApi api = new SilaApi(DefaultConfigurations.host, DefaultConfigurations.appHandle,
                        DefaultConfigurations.privateKey);

        @Test
        public void Response200Success() throws Exception {
                AccountTransactionMessage redeem = AccountTransactionMessage.builder()
                                .userHandle(DefaultConfigurations.getUserHandle())
                                .userPrivateKey(DefaultConfigurations.getUserPrivateKey()).amount(200)
                                .accountName("default").descriptor("test descriptor")
                                .businessUuid(DefaultConfigurations.correctUuid).build();
                ApiResponse response = api.redeemSila(redeem);

                assertEquals(200, response.getStatusCode());
                assertTrue(((TransactionResponse) response.getData()).getSuccess());
                assertEquals("test descriptor", ((TransactionResponse) response.getData()).getDescriptor());
                assertEquals("SUCCESS", ((TransactionResponse) response.getData()).getStatus());
                assertNotNull(((TransactionResponse) response.getData()).getTransactionId());

                String transactionId = ((TransactionResponse) response.getData()).getTransactionId();
                SearchFilters filters = new SearchFilters();
                filters.setTransactionId(transactionId);
                response = api.getTransactions(DefaultConfigurations.getUserHandle(), filters,
                                DefaultConfigurations.getUserPrivateKey());
                while (((GetTransactionsResponse) response.getData()).transactions.get(0).status.equals("queued")
                                || ((GetTransactionsResponse) response.getData()).transactions.get(0).status
                                                .equals("pending")) {
                        TimeUnit.SECONDS.sleep(20);
                        response = api.getTransactions(DefaultConfigurations.getUserHandle(), filters,
                                        DefaultConfigurations.getUserPrivateKey());
                }

                assertEquals("success", ((GetTransactionsResponse) response.getData()).transactions.get(0).status);

//                redeem = AccountTransactionMessage.builder().userHandle(DefaultConfigurations.getUserHandle())
//                                .userPrivateKey(DefaultConfigurations.getUserPrivateKey()).amount(420)
//                                .accountName("default").descriptor("test descriptor")
//                                .businessUuid(DefaultConfigurations.correctUuid).build();
//                response = api.redeemSila(redeem);
        }

        @Test
        public void Response200SuccessSameDay() throws Exception {
                AccountTransactionMessage redeem = AccountTransactionMessage.builder()
                                .userHandle(DefaultConfigurations.getUserHandle())
                                .userPrivateKey(DefaultConfigurations.getUserPrivateKey()).amount(200)
                                .accountName("default").processingType(ProcessingTypeEnum.SAME_DAY).build();
                ApiResponse response = api.redeemSila(redeem);

                assertEquals(200, response.getStatusCode());
                TransactionResponse parsedResponse = (TransactionResponse) response.getData();
                assertTrue(parsedResponse.getSuccess());
                assertEquals("SUCCESS", parsedResponse.getStatus());
                assertNotNull(parsedResponse.getTransactionId());
        }
        @Test
        public void Response200SuccessWithCardName() throws Exception {
                AccountTransactionMessage redeem = AccountTransactionMessage.builder()
                        .userHandle(DefaultConfigurations.getUserHandle())
                        .userPrivateKey(DefaultConfigurations.getUserPrivateKey()).amount(200)
                        .cardName("visa").descriptor("test descriptor")
                        .businessUuid(DefaultConfigurations.correctUuid).processingType(ProcessingTypeEnum.CARD).build();
                ApiResponse response = api.redeemSila(redeem);

                assertEquals(200, response.getStatusCode());
                assertTrue(((TransactionResponse) response.getData()).getSuccess());
                assertEquals("test descriptor", ((TransactionResponse) response.getData()).getDescriptor());
                assertEquals("SUCCESS", ((TransactionResponse) response.getData()).getStatus());
                assertNotNull(((TransactionResponse) response.getData()).getTransactionId());

        }


        @Test
        public void Response400() throws BadRequestException, InvalidSignatureException, ServerSideException,
                        IOException, InterruptedException, ForbiddenException {
                AccountTransactionMessage redeem = AccountTransactionMessage.builder().userHandle("")
                                .userPrivateKey(DefaultConfigurations.getUserPrivateKey()).amount(200).accountName(null)
                                .build();
                ApiResponse response = api.redeemSila(redeem);
                assertEquals(400, response.getStatusCode());
                BadRequestResponse parsedResponse = (BadRequestResponse) response.getData();
                assertFalse(parsedResponse.getSuccess());
                assertEquals("FAILURE", parsedResponse.getStatus());
                assertThat("issue sila - bad request", parsedResponse.getMessage(),
                                stringContainsInOrder(Arrays.asList("Bad request")));
        }

        @Test
        public void Response400WrongUuiud() throws BadRequestException, InvalidSignatureException, ServerSideException,
                        IOException, InterruptedException, ForbiddenException {
                AccountTransactionMessage redeem = AccountTransactionMessage.builder()
                                .userHandle(DefaultConfigurations.getUserHandle())
                                .userPrivateKey(DefaultConfigurations.getUserPrivateKey()).amount(200).accountName(null)
                                .businessUuid(DefaultConfigurations.wrongUuid).build();
                ApiResponse response = api.redeemSila(redeem);
                assertEquals(400, response.getStatusCode());
                BadRequestResponse parsedResponse = (BadRequestResponse) response.getData();
                assertFalse(parsedResponse.getSuccess());
                assertEquals("FAILURE", parsedResponse.getStatus());
                assertThat("issue sila - bad request", parsedResponse.getMessage(),
                                stringContainsInOrder(Arrays.asList("Bad request")));
        }

        @Test
        public void Response401() throws BadRequestException, InvalidSignatureException, ServerSideException,
                        IOException, InterruptedException, ForbiddenException {
                api = new SilaApi(DefaultConfigurations.host, DefaultConfigurations.appHandle,
                                "3a1076bf45ab87712ad64ccb3b10217737f7faacbf2872e88fdd9a537d8fe266");
                AccountTransactionMessage redeem = AccountTransactionMessage.builder()
                                .userHandle(DefaultConfigurations.getUserHandle())
                                .userPrivateKey(DefaultConfigurations.getUserPrivateKey()).amount(200)
                                .accountName("default").build();
                ApiResponse response = api.redeemSila(redeem);
                assertEquals(401, response.getStatusCode());
                BaseResponse parsedResponse = (BaseResponse) response.getData();
                assertFalse(parsedResponse.getSuccess());
                assertEquals("FAILURE", parsedResponse.getStatus());
        }

        @Test
        public void Response200SuccessWithVirtualAccount() throws Exception {
                AccountTransactionMessage redeem = AccountTransactionMessage.builder()
                        .userHandle(DefaultConfigurations.getUserHandle())
                        .userPrivateKey(DefaultConfigurations.getUserPrivateKey()).amount(200)
                        .accountName("default").descriptor("test descriptor").sourceId(DefaultConfigurations.getVirtualAccounts().get(0).getVirtualAccountId())
                        .businessUuid(DefaultConfigurations.correctUuid).build();
                ApiResponse response = api.redeemSila(redeem);
                assertEquals(200, response.getStatusCode());
                assertTrue(((TransactionResponse) response.getData()).getSuccess());
                assertEquals("test descriptor", ((TransactionResponse) response.getData()).getDescriptor());
                assertEquals("SUCCESS", ((TransactionResponse) response.getData()).getStatus());
                assertNotNull(((TransactionResponse) response.getData()).getTransactionId());

        }

        @Test
        public void Response200SuccessInstantSettlement() throws Exception {
                AccountTransactionMessage redeem = AccountTransactionMessage.builder()
                        .userHandle(DefaultConfigurations.getUserHandle())
                        .userPrivateKey(DefaultConfigurations.getUserPrivateKey()).amount(200)
                        .accountName("default").build();
                ApiResponse response = api.redeemSila(redeem);

                assertEquals(200, response.getStatusCode());
                TransactionResponse parsedResponse = (TransactionResponse) response.getData();
                assertTrue(parsedResponse.getSuccess());
                assertEquals("SUCCESS", parsedResponse.getStatus());
                assertNotNull(parsedResponse.getTransactionId());
        }
}
