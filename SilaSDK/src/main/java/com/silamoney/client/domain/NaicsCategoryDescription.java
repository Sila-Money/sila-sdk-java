package com.silamoney.client.domain;

public class NaicsCategoryDescription {

    private int code;
    private String subcategory;

    /**
     * @return the code
     */
    public int getCode() {
        return code;
    }

    /**
     * @param code the code to set
     */
    public void setCode(int code) {
        this.code = code;
    }

    /**
     * @return the subcategory
     */
    public String getSubcategory() {
        return subcategory;
    }

    /**
     * @param subcategory the subcategory to set
     */
    public void setSubcategory(String subcategory) {
        this.subcategory = subcategory;
    }

}