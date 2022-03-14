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
import com.silamoney.client.domain.BadRequestResponse;
import com.silamoney.client.domain.BaseResponse;
import com.silamoney.client.domain.GetTransactionsResponse;
import com.silamoney.client.domain.AccountTransactionMessage;
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
public class IssueSilaTests {

    SilaApi api = new SilaApi(DefaultConfigurations.host, DefaultConfigurations.appHandle,
            DefaultConfigurations.privateKey);

    @Test
    public void Response200Success() throws Exception {
        AccountTransactionMessage issue = AccountTransactionMessage.builder()
                .userHandle(DefaultConfigurations.getUserHandle())
                .userPrivateKey(DefaultConfigurations.getUserPrivateKey()).amount(1000).accountName("default")
                .descriptor("test descriptor").businessUuid(DefaultConfigurations.correctUuid).build();
        ApiResponse response = api.issueSila(issue);
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
        while (!((GetTransactionsResponse) response.getData()).transactions.get(0).status.equals("success")) {
            TimeUnit.SECONDS.sleep(20);
            response = api.getTransactions(DefaultConfigurations.getUserHandle(), filters,
                    DefaultConfigurations.getUserPrivateKey());
        }

        assertEquals("success", ((GetTransactionsResponse) response.getData()).transactions.get(0).status);
        assertNotNull(((GetTransactionsResponse) response.getData()).transactions.get(0).ledgerAccountId);
    }

    @Test
    public void Response200SuccessSameDay() throws Exception {
        AccountTransactionMessage issue = AccountTransactionMessage.builder()
                .userHandle(DefaultConfigurations.getUserHandle())
                .userPrivateKey(DefaultConfigurations.getUserPrivateKey()).amount(500).accountName("default")
                .processingType(ProcessingTypeEnum.SAME_DAY).build();
        ApiResponse response = api.issueSila(issue);

        assertEquals(200, response.getStatusCode());
        TransactionResponse parsedResponse = (TransactionResponse) response.getData();
        assertTrue(parsedResponse.getSuccess());
        assertEquals("SUCCESS", parsedResponse.getStatus());
        assertNotNull(parsedResponse.getTransactionId());
    }
    @Test
    public void Response200SuccessWithCardName() throws Exception {
        AccountTransactionMessage issue = AccountTransactionMessage.builder()
                .userHandle(DefaultConfigurations.getUserHandle())
                .userPrivateKey(DefaultConfigurations.getUserPrivateKey()).amount(1000).cardName("visa")
                .descriptor("test descriptor").businessUuid(DefaultConfigurations.correctUuid).build();
        ApiResponse response = api.issueSila(issue);
        assertEquals(200, response.getStatusCode());
        assertTrue(((TransactionResponse) response.getData()).getSuccess());
    }
    @Test
    public void Response400() throws BadRequestException, InvalidSignatureException, ServerSideException, IOException,
            InterruptedException, ForbiddenException {
        AccountTransactionMessage issue = AccountTransactionMessage.builder().userHandle("")
                .userPrivateKey(DefaultConfigurations.getUserPrivateKey()).amount(1000).accountName(null).build();
        ApiResponse response = api.issueSila(issue);
        assertEquals(400, response.getStatusCode());
        BadRequestResponse parsedResponse = (BadRequestResponse) response.getData();
        assertFalse(parsedResponse.getSuccess());
        assertEquals("FAILURE", parsedResponse.getStatus());
        assertThat("issue sila - bad request", parsedResponse.getMessage(),
                stringContainsInOrder(Arrays.asList("Bad request")));
    }

    @Test
    public void Response400WrongUuid() throws BadRequestException, InvalidSignatureException, ServerSideException,
            IOException, InterruptedException, ForbiddenException {
        AccountTransactionMessage issue = AccountTransactionMessage.builder()
                .userHandle(DefaultConfigurations.getUserHandle())
                .userPrivateKey(DefaultConfigurations.getUserPrivateKey()).amount(1000).accountName(null)
                .businessUuid(DefaultConfigurations.wrongUuid).build();
        ApiResponse response = api.issueSila(issue);
        assertEquals(400, response.getStatusCode());
        BadRequestResponse parsedResponse = (BadRequestResponse) response.getData();
        assertFalse(parsedResponse.getSuccess());
        assertEquals("FAILURE", parsedResponse.getStatus());
        assertThat("issue sila - bad request", parsedResponse.getMessage(),
                stringContainsInOrder(Arrays.asList("Bad request")));
    }

    @Test
    public void Response401User() throws BadRequestException, InvalidSignatureException, ServerSideException,
            IOException, InterruptedException, ForbiddenException {
        AccountTransactionMessage issue = AccountTransactionMessage.builder()
                .userHandle(DefaultConfigurations.getUserHandle()).userPrivateKey(DefaultConfigurations.privateKey)
                .amount(1000).accountName("default").build();
        ApiResponse response = api.issueSila(issue);
        assertEquals(401, response.getStatusCode());
        BaseResponse parsedResponse = (BaseResponse) response.getData();
        assertFalse(parsedResponse.getSuccess());
        assertEquals("FAILURE", parsedResponse.getStatus());
    }

    @Test
    public void Response401() throws BadRequestException, InvalidSignatureException, ServerSideException, IOException,
            InterruptedException, ForbiddenException {
        SilaApi badApi = new SilaApi(DefaultConfigurations.host, DefaultConfigurations.appHandle,
                "3a1076bf45ab87712ad64ccb3b10217737f7faacbf2872e88fdd9a537d8fe266");
        AccountTransactionMessage issue = AccountTransactionMessage.builder()
                .userHandle(DefaultConfigurations.getUserHandle())
                .userPrivateKey(DefaultConfigurations.getUserPrivateKey()).amount(1000).accountName("default").build();
        ApiResponse response = badApi.issueSila(issue);
        assertEquals(401, response.getStatusCode());
        BaseResponse parsedResponse = (BaseResponse) response.getData();
        assertFalse(parsedResponse.getSuccess());
        assertEquals("FAILURE", parsedResponse.getStatus());
    }

    @Test
    public void Response200WithVirtualAccount() throws Exception {
        AccountTransactionMessage issue = AccountTransactionMessage.builder()
                .userHandle(DefaultConfigurations.getUserHandle())
                .userPrivateKey(DefaultConfigurations.getUserPrivateKey()).amount(1000).accountName("default")
                .descriptor("test descriptor").businessUuid(DefaultConfigurations.correctUuid).destinationId(DefaultConfigurations.getVirtualAccounts().get(0).getVirtualAccountId()).build();
        ApiResponse response = api.issueSila(issue);
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
        while (!((GetTransactionsResponse) response.getData()).transactions.get(0).status.equals("success")) {
            TimeUnit.SECONDS.sleep(20);
            response = api.getTransactions(DefaultConfigurations.getUserHandle(), filters,
                    DefaultConfigurations.getUserPrivateKey());
        }

        assertEquals("success", ((GetTransactionsResponse) response.getData()).transactions.get(0).status);
        assertNotNull(((GetTransactionsResponse) response.getData()).transactions.get(0).silaLedgerType);
        assertNotNull(((GetTransactionsResponse) response.getData()).transactions.get(0).sourceId);
        assertNotNull(((GetTransactionsResponse) response.getData()).transactions.get(0).destinationId);
    }

    @Test
    public void Response200SuccessInstantSettlement() throws Exception {
        AccountTransactionMessage issue = AccountTransactionMessage.builder()
                .userHandle(DefaultConfigurations.getUserHandle())
                .userPrivateKey(DefaultConfigurations.getUserPrivateKey()).amount(200).accountName("default")
                .processingType(ProcessingTypeEnum.INSTANT_SETTLEMENT).build();
        ApiResponse response = api.issueSila(issue);

        assertEquals(200, response.getStatusCode());
        TransactionResponse parsedResponse = (TransactionResponse) response.getData();
        assertTrue(parsedResponse.getSuccess());
        assertEquals("SUCCESS", parsedResponse.getStatus());
        assertNotNull(parsedResponse.getTransactionId());
    }

}
