package com.silamoney.client.api;

import com.silamoney.client.util.DefaultLogger;
import com.silamoney.client.util.Logger;
import com.silamoney.client.util.ResponseUtil;
import java.io.*;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.Collections;
import java.util.Map;

import java.util.logging.Level;
import org.apache.http.HttpEntity;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;

/**
 *
 * @author Karlo Lorenzana
 */
public class ApiClient {

    private String basePath;

    private static final String PRODUCT = "SilaSDK-java";
    private static final String VERSION = "0.2.24";

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
     * Logger logger to trace API calls
     */
    private Logger logger;

    public Logger getLogger() {
        return logger;
    }

    public void setLogger(Logger logger) {
        this.logger = logger;
    }

    /**
     * API client constructor.
     */


    public ApiClient() {
        httpClient = HttpClient.newBuilder().version(HttpClient.Version.HTTP_1_1).build();
        logger = new DefaultLogger(ApiClient.class);
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
        try {
            logRequest(path, headers, body);
            HttpRequest finalRequest = prepareRequest(path, headers, body);
            return httpClient.send(finalRequest, BodyHandlers.ofString());
        } catch (Exception ex) {
            logger.log(Level.SEVERE, Map.of("message", "Error calling api", "error", ex, "path", path, "body", body));
            throw new RuntimeException(ex);
        }
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
        final File file = new File(filePath);
        return callApi(path, headers, body, new FileInputStream(file), contentType, file.getName());
    }

    /**
     *
     * @param path
     * @param headers
     * @param body
     * @return
     * @throws FileNotFoundException
     * @throws IOException
     * @throws InterruptedException
     */
    @SuppressWarnings("all")
    public HttpResponse callApi(String path, Map<String, String> headers, String body, InputStream binaryStream,
        String contentType, String name) throws FileNotFoundException, IOException, InterruptedException {
        String fullPath = basePath + path;
        logRequest(fullPath, headers, body);
        try {
            headers.put("User-Agent", PRODUCT + '/' + VERSION);
            var request = HttpRequest.newBuilder().uri(URI.create(basePath + path));
            headers.entrySet().forEach(entry -> request.header(entry.getKey(), entry.getValue()));
            MultipartEntityBuilder builder = MultipartEntityBuilder.create();
            builder.addTextBody("data", body, ContentType.TEXT_PLAIN);
            builder.addBinaryBody("file", binaryStream, ContentType.create(contentType), name);
            HttpEntity multipart = builder.build();
            ByteArrayOutputStream outStream = new ByteArrayOutputStream();
            multipart.writeTo(outStream);
            request.POST(HttpRequest.BodyPublishers.ofByteArray(outStream.toByteArray()));
            outStream.close();
            request.header("Content-Type", multipart.getContentType().getValue());
            return httpClient.send(request.build(), BodyHandlers.ofString());
        } catch (Exception ex) {
            logger.log(Level.SEVERE, Map.of("message", "Error calling api", "error", ex, "http_request_uri", fullPath, "body", body));
            throw new RuntimeException(ex);
        }
    }


    private HttpRequest prepareRequest(String path, Map<String, String> headers, String body) {
        String fullPath = basePath + path;
        logRequest(fullPath, headers, body);
        var request = HttpRequest.newBuilder().uri(URI.create(fullPath));
        headers.entrySet().forEach(entry -> request.header(entry.getKey(), entry.getValue()));

        return request.POST(HttpRequest.BodyPublishers.ofString(body)).build();
    }

    private void logRequest(String path, Map<String, String> headers, String body) {
        Map<String, Object> normHeaders = ResponseUtil.normHeaderMap(headers != null ? headers : Collections.emptyMap());
        logger.log(Level.INFO, Map.of("body", body != null ? body : "",
            "http_request_uri", path,
            "headers", normHeaders));
    }

}
