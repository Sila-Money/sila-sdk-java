package com.silamoney.client.testsutils;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse.BodyHandlers;

public class CardTokenHelper {

	public static String getToken() throws IOException, InterruptedException {

            HttpRequest finalRequest = PrepareRequest();
        HttpClient httpClient = HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_1_1)
                .build();

        String data;

			data =httpClient.send(finalRequest,
			        BodyHandlers.ofString()).body();

			return data.replace("S2\td","");
    }

    private static HttpRequest PrepareRequest()  {
        var request = HttpRequest.newBuilder().uri(URI.create("https://sso.sandbox.tabapay.com:8443/v2/SSOEncrypt"));

        request.header("Content-Type", "application/tabapay-compact");
        return request.POST(HttpRequest.BodyPublishers.ofString( "cBm0RU8eASGfSxLYJjsG73Q	n9010111999999992	e202201	s4561"))
                .build();
    }

}
