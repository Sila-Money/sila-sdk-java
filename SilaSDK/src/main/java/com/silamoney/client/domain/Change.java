package com.silamoney.client.domain;

import com.google.gson.annotations.SerializedName;

import lombok.Getter;

@Getter
public class Change {
    
    private String attribute;
    @SerializedName("old_value")
    private String oldValue;
    @SerializedName("new_value")
    private String newValue;

}
