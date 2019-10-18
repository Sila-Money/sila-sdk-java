package com.silamoney.client.tests;

import com.silamoney.client.api.ApiResponse;
import com.silamoney.client.api.SilaApi;
import com.silamoney.client.domain.BaseResponse;
import com.silamoney.client.domain.GetTransactionsResponse;
import com.silamoney.client.domain.SearchFilters;
import com.silamoney.client.exceptions.BadRequestException;
import com.silamoney.client.exceptions.ForbiddenException;
import com.silamoney.client.exceptions.InvalidSignatureException;
import com.silamoney.client.exceptions.ServerSideException;
import com.silamoney.client.testsutils.DefaultConfigurations;
import java.io.IOException;
import java.util.ArrayList;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Karlo Lorenzana
 */
public class GetTransactionsTests {

    SilaApi api = new SilaApi(DefaultConfigurations.host,
            DefaultConfigurations.appHandle,
            DefaultConfigurations.privateKey);

    @Test
    public void Response200() throws Exception {
        SearchFilters filters = new SearchFilters("some UUID string assigned by Sila", 
                20, 
                new ArrayList(){
                    {
                        add(SearchFilters.TransactionTypesEnum.ISSUE);
                        add(SearchFilters.TransactionTypesEnum.REDEEM);
                        add(SearchFilters.TransactionTypesEnum.TRANSFER);
                    }
                }, 
                1300, 
                "the reference string sent in the header object when transaction request was made", 
                Boolean.TRUE, 
                Boolean.FALSE, 
                1234567891, 
                1234567860, 
                new ArrayList(){
                    {
                        add(SearchFilters.StatusesEnum.PENDING);
                        add(SearchFilters.StatusesEnum.SUCCESSFUL);
                        add(SearchFilters.StatusesEnum.FAILED);
                        add(SearchFilters.StatusesEnum.COMPLETE);
                    }
                }, 
                1, 
                1000);
        ApiResponse response = api.GetTransactions(
                DefaultConfigurations.userHandle,
                filters,
                DefaultConfigurations.userPrivateKey
        );

        assertEquals(200, response.statusCode);
        assertTrue(((GetTransactionsResponse) response.data).success);
    }

    @Test(expected = BadRequestException.class)
    public void Response400() throws Exception {
        SearchFilters filters = new SearchFilters("some UUID string assigned by Sila", 
                20, 
                new ArrayList(){
                    {
                        add(SearchFilters.TransactionTypesEnum.ISSUE);
                        add(SearchFilters.TransactionTypesEnum.REDEEM);
                        add(SearchFilters.TransactionTypesEnum.TRANSFER);
                    }
                }, 
                1300, 
                "the reference string sent in the header object when transaction request was made", 
                Boolean.TRUE, 
                Boolean.FALSE, 
                1234567891, 
                1234567860, 
                new ArrayList(){
                    {
                        add(SearchFilters.StatusesEnum.PENDING);
                        add(SearchFilters.StatusesEnum.SUCCESSFUL);
                        add(SearchFilters.StatusesEnum.FAILED);
                        add(SearchFilters.StatusesEnum.COMPLETE);
                    }
                }, 
                1, 
                1000);
        api.GetTransactions(
                "badrequest.silamoney.eth",
                filters,
                DefaultConfigurations.userPrivateKey
        );
    }

    @Test(expected = ForbiddenException.class)
    public void Response403() throws 
            BadRequestException, 
            InvalidSignatureException, 
            ServerSideException, 
            IOException, 
            InterruptedException,
            ForbiddenException  {
        SearchFilters filters = new SearchFilters("some UUID string assigned by Sila", 
                20, 
                new ArrayList(){
                    {
                        add(SearchFilters.TransactionTypesEnum.ISSUE);
                        add(SearchFilters.TransactionTypesEnum.REDEEM);
                        add(SearchFilters.TransactionTypesEnum.TRANSFER);
                    }
                }, 
                1300, 
                "the reference string sent in the header object when transaction request was made", 
                Boolean.TRUE, 
                Boolean.FALSE, 
                1234567891, 
                1234567860, 
                new ArrayList(){
                    {
                        add(SearchFilters.StatusesEnum.PENDING);
                        add(SearchFilters.StatusesEnum.SUCCESSFUL);
                        add(SearchFilters.StatusesEnum.FAILED);
                        add(SearchFilters.StatusesEnum.COMPLETE);
                    }
                }, 
                1, 
                1000);
        api.GetTransactions(
                "forbidden.silamoney.eth",
                filters,
                DefaultConfigurations.userPrivateKey
        );
    }
    
    @Test(expected = ServerSideException.class)
    public void Response401() throws 
            BadRequestException, 
            InvalidSignatureException, 
            ServerSideException, 
            IOException, 
            InterruptedException,
            ForbiddenException  {
        SearchFilters filters = new SearchFilters("some UUID string assigned by Sila", 
                20, 
                new ArrayList(){
                    {
                        add(SearchFilters.TransactionTypesEnum.ISSUE);
                        add(SearchFilters.TransactionTypesEnum.REDEEM);
                        add(SearchFilters.TransactionTypesEnum.TRANSFER);
                    }
                }, 
                1300, 
                "the reference string sent in the header object when transaction request was made", 
                Boolean.TRUE, 
                Boolean.FALSE, 
                1234567891, 
                1234567860, 
                new ArrayList(){
                    {
                        add(SearchFilters.StatusesEnum.PENDING);
                        add(SearchFilters.StatusesEnum.SUCCESSFUL);
                        add(SearchFilters.StatusesEnum.FAILED);
                        add(SearchFilters.StatusesEnum.COMPLETE);
                    }
                }, 
                1, 
                1000);
        api.GetTransactions(
                "serverside.silamoney.eth",
                filters,
                DefaultConfigurations.userPrivateKey
        );
    }
}
