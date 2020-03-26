package com.silamoney.client.util;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;

/**
 * Util used to serialize or deserialize objects.
 *
 * @author Karlo Lorenzana
 */
public class Serialization {
    
    private Serialization(){
        
    }

    private static final Gson Serializer = new Gson();

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
}
