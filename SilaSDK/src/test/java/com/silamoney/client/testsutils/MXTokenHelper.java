package com.silamoney.client.testsutils;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse.BodyHandlers;

public class MXTokenHelper {

    public static String getProviderToken() throws IOException, InterruptedException {
        HttpRequest finalRequest = PrepareRequest();

        HttpClient httpClient = HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_1_1)
                .build();

        JSONObject data;

        try {
            data = (JSONObject) new JSONTokener(httpClient.send(finalRequest,
                    BodyHandlers.ofString()).body()).nextValue();
            JSONObject jsonObject = data.getJSONObject("payment_processor_authorization_code");
            return jsonObject.getString("authorization_code");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return "";
    }

    private static HttpRequest PrepareRequest() throws IOException, InterruptedException {
        var request = HttpRequest.newBuilder()
                .uri(URI.create("https://int-api.mx.com/payment_processor_authorization_code"));

        request.header("Content-Type", "application/json")
                .header("Accept", "application/vnd.mx.api.v1+json")
                .header("Authorization", "Basic OWNlOTFhZWQtMDA4Zi00YjFmLThlMzktNGU3YTU5NjZlOTVhOmYyZThkYzE4MmY2MzQ5OTk5NjMzMDJlYTE3OGU3NTBkZWU2NDQ3ODM=");

        return request.POST(HttpRequest.BodyPublishers.ofString("{\r\n" +
                " \"payment_processor_authorization_code\":{"
                + "        \"user_guid\": \"USR-78912abf-a65b-4661-806b-bdcf4e062e16\",\r\n" +
                "        \"member_guid\": \"MBR-1e0d03f3-d42e-46e7-86fb-ae07b79c557a\",\r\n" +
                "		 \"account_guid\": \"ACT-cc129199-606c-41a3-aeec-ee32980362d4\"\r\n" +
                "}}")).build();
    }

}
