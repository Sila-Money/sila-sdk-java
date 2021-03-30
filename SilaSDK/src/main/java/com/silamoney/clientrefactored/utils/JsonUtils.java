package com.silamoney.clientrefactored.utils;

import java.lang.reflect.Type;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class JsonUtils {

    private JsonUtils() {
    }

    private static Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public static String serialize(Object obj) {
        return gson.toJson(obj);
    }

    public static Object deserialize(String str, Type type) {
        return gson.fromJson(str, type);
    }

}
