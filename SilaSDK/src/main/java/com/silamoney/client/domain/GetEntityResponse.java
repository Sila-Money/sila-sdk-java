package com.silamoney.client.domain;

import java.util.List;

import com.google.gson.annotations.SerializedName;

import lombok.Getter;

public class GetEntityResponse {

    @Getter
    private boolean success;
    @SerializedName(value = "user_handle")
    @Getter
    private String userHandle;
    @SerializedName(value = "entity_type")
    @Getter
    private String entityType;
    @Getter
    private Entity entity;
    @Getter
    private List<Address> addresses;
    @Getter
    private List<Identity> identities;
    @Getter
    private List<Email> emails;
    @Getter
    private List<Phone> phones;
    @Getter
    private List<Membership> memberships;
    @Getter
    private List<Member> members;

}