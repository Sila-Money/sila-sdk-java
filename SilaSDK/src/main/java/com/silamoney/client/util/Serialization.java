package com.silamoney.client.util;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.time.Instant;

/**
 * Util used to serialize or deserialize objects.
 *
 * @author Karlo Lorenzana
 */
public class Serialization {
    
    private Serialization(){
        
    }

    private static final Gson Serializer = new GsonBuilder()
            .registerTypeAdapter(Instant.class, new InstantDeserializer())
            .create();
    /**
     * Converts the object into a json string.
     *
     * @param obj
     * @return json from object.
     */
    public static String serialize(Object obj) {
        return Serializer.toJson(obj);
    }

    /**
     * Converts a json into an object.
     *
     * @param str
     * @param type
     * @return an instance of the specified object.
     */
    public static Object deserialize(String str, Type type) {
        return Serializer.fromJson(str, type);
    }
    
    /**
     * Converts a json into an object.
     *
     * @param str
     * @param token
     * @return an instance of the specified object.
     */
    public static Object deserialize(String str, TypeToken<?> token) {
        return Serializer.fromJson(str, token.getType());
    }

    public static class InstantDeserializer implements JsonDeserializer<Instant> {
        @Override
        public Instant deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
            return Instant.parse(json.getAsString());
        }
    }

}
