package com.silamoney.client.testsutils;

import java.nio.charset.StandardCharsets;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import com.github.tomakehurst.wiremock.extension.Parameters;
import com.github.tomakehurst.wiremock.extension.ServeEventListener;
import com.github.tomakehurst.wiremock.stubbing.ServeEvent;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonParser;

/**
 * Remembers the crypto addresses each user handle registered (register and register_wallet),
 * so InvalidUserSignatureMatcher can check usersignature like the real API.
 */
public class UserAddressRegistry implements ServeEventListener {

    public static final String NAME = "user-address-registry";

    private final Map<String, Set<String>> addresses = new ConcurrentHashMap<>();

    @Override
    public String getName() {
        return NAME;
    }

    @Override
    public void afterComplete(ServeEvent serveEvent, Parameters parameters) {
        int status = serveEvent.getResponse().getStatus();
        if (status < 200 || status >= 300) {
            return;
        }
        String url = serveEvent.getRequest().getUrl();
        JsonObject body = parse(serveEvent.getRequest().getBody());
        if (body == null) {
            return;
        }
        if ("/register".equals(url)) {
            add(body, "crypto_entry", "crypto_address");
        } else if ("/register_wallet".equals(url)) {
            add(body, "wallet", "blockchain_address");
        }
    }

    boolean isRegistered(String handle) {
        return addresses.containsKey(handle.toLowerCase(Locale.ROOT));
    }

    boolean hasAddress(String handle, String address) {
        Set<String> registered = addresses.get(handle.toLowerCase(Locale.ROOT));
        return registered != null && registered.contains(SignatureRecovery.normalize(address));
    }

    /**
     * @return header.user_handle of a JSON request body, or null.
     */
    static String userHandle(byte[] body) {
        JsonObject json = parse(body);
        return json == null ? null : string(json, "header", "user_handle");
    }

    private void add(JsonObject body, String object, String field) {
        String handle = string(body, "header", "user_handle");
        String address = string(body, object, field);
        if (handle != null && address != null) {
            addresses.computeIfAbsent(handle.toLowerCase(Locale.ROOT), h -> ConcurrentHashMap.newKeySet())
                    .add(SignatureRecovery.normalize(address));
        }
    }

    private static JsonObject parse(byte[] body) {
        if (body == null || body.length == 0) {
            return null;
        }
        try {
            JsonElement json = JsonParser.parseString(new String(body, StandardCharsets.UTF_8));
            return json.isJsonObject() ? json.getAsJsonObject() : null;
        } catch (JsonParseException e) {
            return null;
        }
    }

    private static String string(JsonObject json, String object, String field) {
        JsonElement parent = json.get(object);
        if (parent == null || !parent.isJsonObject()) {
            return null;
        }
        JsonElement value = parent.getAsJsonObject().get(field);
        return value == null || !value.isJsonPrimitive() ? null : value.getAsString();
    }
}
