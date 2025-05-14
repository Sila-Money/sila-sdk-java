package com.silamoney.client.tests;

import com.silamoney.client.api.ApiResponse;
import com.silamoney.client.domain.GetWalletResponse;
import com.silamoney.client.domain.Message;
import com.silamoney.client.util.ResponseUtil;
import org.junit.Assert;
import org.junit.Test;

import javax.net.ssl.SSLSession;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpHeaders;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.Map;
import java.util.Optional;

public class ResponseUtilTest {

    @Test
    public void testWalletResponse(){
        HttpResponse<?> response = new HttpResponse<Object>() {
            @Override
            public int statusCode() {
                return 200;
            }

            @Override
            public HttpRequest request() {
                return new HttpRequest() {
                    @Override
                    public Optional<BodyPublisher> bodyPublisher() {
                        return Optional.empty();
                    }

                    @Override
                    public String method() {
                        return "GET";
                    }

                    @Override
                    public Optional<Duration> timeout() {
                        return Optional.empty();
                    }

                    @Override
                    public boolean expectContinue() {
                        return false;
                    }

                    @Override
                    public URI uri() {
                        return URI.create("https://api.silamoney.com/v1/wallets");
                    }

                    @Override
                    public Optional<HttpClient.Version> version() {
                        return Optional.empty();
                    }

                    @Override
                    public HttpHeaders headers() {
                        return java.net.http.HttpHeaders.of(Map.of(), (s, s2) -> true);
                    }
                };
            }

            @Override
            public Optional<HttpResponse<Object>> previousResponse() {
                return Optional.empty();
            }

            @Override
            public HttpHeaders headers() {
                return java.net.http.HttpHeaders.of(Map.of(), (s, s2) -> true);
            }

            @Override
            public Object body() {
                return "{\n" +
                        "  \"success\": true,\n" +
                        "  \"wallet\": {\n" +
                        "    \"wallet_id\": \"wallet_uuid\",\n" +
                        "    \"payment_instrument_id\": \"wallet_uuid\",\n" +
                        "    \"nickname\": \"nickname\",\n" +
                        "    \"default\": true,\n" +
                        "    \"wallet_address\": null,\n" +
                        "    \"network\": null,\n" +
                        "    \"statements_enabled\": true\n" +
                        "  },\n" +
                        "  \"is_whitelisted\": true,\n" +
                        "  \"sila_balance\": 5000,\n" +
                        "  \"sila_available_balance\": 5000,\n" +
                        "  \"sila_pending_balance\": 0,\n" +
                        "  \"status\": \"SUCCESS\",\n" +
                        "  \"response_time_ms\": \"49\",\n" +
                        "  \"sila_reference_id\": \"req_jei3jf7\",\n" +
                        "  \"remote_account_details\": {\n" +
                        "    \"account_number\": \"12321311\",\n" +
                        "    \"routing_number\": \"12321312\",\n" +
                        "    \"wire_account_number\": \"12321313\",\n" +
                        "    \"wire_routing_number\": \"12321314\"\n" +
                        "\n" +
                        "  }\n" +
                        "}";
            }

            @Override
            public Optional<SSLSession> sslSession() {
                return Optional.empty();
            }

            @Override
            public URI uri() {
                return null;
            }

            @Override
            public HttpClient.Version version() {
                return null;
            }
        };
        ApiResponse apiResponse = ResponseUtil.prepareResponse(response, Message.ValueEnum.GET_WALLET_MSG.getValue());
        GetWalletResponse walletResponse = (GetWalletResponse) apiResponse.getData();
        Assert.assertEquals("wallet_uuid", walletResponse.getWallet().getWalletId());
        Assert.assertEquals("12321311", walletResponse.getRemoteAccountDetails().getAccountNumber());
        Assert.assertEquals("12321312", walletResponse.getRemoteAccountDetails().getRoutingNumber());
        Assert.assertEquals("12321313", walletResponse.getRemoteAccountDetails().getWireAccountNumber());
        Assert.assertEquals("12321314", walletResponse.getRemoteAccountDetails().getWireRoutingNumber());
    }
}
