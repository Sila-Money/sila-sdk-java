package com.silamoney.client.testsutils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * GsonUtils
 * 
 * @author wzelada
 */
public class GsonUtils {

    /**
     * Converts object to linear String.
     * 
     * @param object
     * @return
     */
    public static String objectToJsonStringLineal(Object object) {
        String result = "";
        try {
            Gson json = new Gson();
            result = json.toJson(object);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * Converts object to Formated String.
     * 
     * @param object
     * @return
     */
    public static String objectToJsonStringFormat(Object object) {
        String result = "";
        try {
            Gson json = new GsonBuilder().setPrettyPrinting().create();
            result = json.toJson(object);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}