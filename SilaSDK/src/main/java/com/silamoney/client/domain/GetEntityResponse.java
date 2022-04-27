package com.silamoney.client.domain;

import java.util.List;

import com.google.gson.annotations.SerializedName;

import lombok.Getter;

@Getter
public class GetEntityResponse {

    private boolean success;
    @SerializedName(value = "user_handle")
    private String userHandle;
    @SerializedName(value = "entity_type")
    private String entityType;
    private Entity entity;
    private List<Address> addresses;
    private List<Identity> identities;
    private List<Email> emails;
    private List<Phone> phones;
    private List<Membership> memberships;
    private List<Member> members;
    private List<Devices> devices;
    private String status;
    private String reference;
    @SerializedName("response_time_ms")
    private String responseTimeMs;

}