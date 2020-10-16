package com.silamoney.client.api;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;

/**
 *
 * @author Karlo Lorenzana
 */
public class ApiClient {

    private String basePath;

    /**
     * Gets the api base path.
     * 
     * @return basePath
     */
    public String getBasePath() {
        return basePath;
    }

    /**
     * Sets the api base path.
     * 
     * @param basePath
     */
    public void setBasePath(String basePath) {
        this.basePath = basePath;
    }

    /**
     * HTTP client used to make the API calls.
     */
    private HttpClient httpClient;

    /**
     * API client constructor.
     */
    public ApiClient() {
        httpClient = HttpClient.newBuilder().version(HttpClient.Version.HTTP_1_1).build();
    }

    /**
     * Makes the call to the sila API.
     *
     * @param path
     * @param headers
     * @param body
     * @return
     * @throws IOException
     * @throws InterruptedException
     */
    @SuppressWarnings("all")
    public HttpResponse callApi(String path, Map<String, String> headers, String body)
            throws IOException, InterruptedException {
        HttpRequest finalRequest = prepareRequest(path, headers, body);

        return httpClient.send(finalRequest, BodyHandlers.ofString());
    }

    /**
     * 
     * @param path
     * @param headers
     * @param body
     * @param filePath
     * @return
     * @throws FileNotFoundException
     * @throws IOException
     * @throws InterruptedException
     */
    @SuppressWarnings("all")
    public HttpResponse callApi(String path, Map<String, String> headers, String body, String filePath,
            String contentType) throws FileNotFoundException, IOException, InterruptedException {
        var request = HttpRequest.newBuilder().uri(URI.create(basePath + path));
        headers.entrySet().forEach(entry -> request.header(entry.getKey(), entry.getValue()));
        MultipartEntityBuilder builder = MultipartEntityBuilder.create();
        builder.addTextBody("data", body, ContentType.TEXT_PLAIN);
        File f = new File(filePath);
        builder.addBinaryBody("file", new FileInputStream(f), ContentType.create(contentType), f.getName());
        HttpEntity multipart = builder.build();
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        multipart.writeTo(outStream);
        request.POST(HttpRequest.BodyPublishers.ofByteArray(outStream.toByteArray()));
        outStream.close();
        request.header("Content-Type", multipart.getContentType().getValue());
        return httpClient.send(request.build(), BodyHandlers.ofString());
    }

    private HttpRequest prepareRequest(String path, Map<String, String> headers, String body) {
        var request = HttpRequest.newBuilder().uri(URI.create(basePath + path));

        headers.entrySet().forEach(entry -> request.header(entry.getKey(), entry.getValue()));

        return request.POST(HttpRequest.BodyPublishers.ofString(body)).build();
    }
}
