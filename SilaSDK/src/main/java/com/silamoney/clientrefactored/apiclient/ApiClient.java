package com.silamoney.clientrefactored.apiclient;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.Map;

import com.silamoney.clientrefactored.configuration.Environment;

public class ApiClient {

    private static final String PRODUCT = "SilaSDK-java";
    private static final String VERSION = "0.2.41";

    private HttpClient httpClient;
    private String basePath;

    public ApiClient(Environment environment) {
        httpClient = HttpClient.newBuilder().version(HttpClient.Version.HTTP_1_1).build();
        basePath = environment.getUrl();
    }

    public HttpResponse<String> send(String endpoint, String body, Map<String, String> headers)
            throws IOException, InterruptedException {
        HttpRequest finalRequest = prepareRequest(endpoint, headers, body);

        return httpClient.send(finalRequest, BodyHandlers.ofString());
    }

    private HttpRequest prepareRequest(String path, Map<String, String> headers, String body) {
        var request = HttpRequest.newBuilder().uri(URI.create(basePath + path));
        headers.put("User-Agent", PRODUCT + '/' + VERSION);

        headers.entrySet().forEach(entry -> request.header(entry.getKey(), entry.getValue()));

        return request.POST(HttpRequest.BodyPublishers.ofString(body)).build();
    }

}
