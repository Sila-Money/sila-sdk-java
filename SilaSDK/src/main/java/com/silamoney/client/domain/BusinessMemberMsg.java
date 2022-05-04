package com.silamoney.client.domain;

import com.google.gson.annotations.SerializedName;

public class BusinessMemberMsg {

    @SerializedName("header")
    private final Header header;

    @SerializedName("role")
    private final String role;

    @SerializedName("role_uuid")
    private final String roleUuid;

    @SerializedName("member_handle")
    private String memberHandle;

    @SerializedName("ownership_stake")
    private Float ownershipStake;

    @SerializedName("details")
    private String details;

    public BusinessMemberMsg(String userHandle, String authHandle, String businessHandle, String name, String uuid, String memberHandle, Float ownershipStake, String details) {
        this.header = new Header(userHandle, authHandle, businessHandle);
        this.role = name;
        this.roleUuid = uuid;
        this.memberHandle = memberHandle;
        this.ownershipStake = ownershipStake;
        this.details = details;
    }

    public BusinessMemberMsg(String userHandle, String authHandle, String businessHandle, String name, String uuid) {
        this.header = new Header(userHandle, authHandle, businessHandle);
        this.role = name;
        this.roleUuid = uuid;
    }
}
