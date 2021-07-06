package com.silamoney.client.domain;

import java.util.List;

import com.google.gson.annotations.SerializedName;

import lombok.Getter;

@Getter
public class Institution {

    private String name;
    @SerializedName("office_code")
    private String officeCode;
    @SerializedName("routing_number")
    private String routingNumber;
    @SerializedName("record_type_code")
    private String recordTypeCode;
    @SerializedName("change_date")
    private String changeDate;
    @SerializedName("new_routing_number")
    private String newRoutingNumber;
    private Address address;
    private String phone;
    @SerializedName("institution_status_code")
    private String institutionStatusCode;
    @SerializedName("data_view_code")
    private String dataViewCode;
    private List<String> products;

}
