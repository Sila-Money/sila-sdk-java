package com.silamoney.client.domain;

import java.util.List;

import com.google.gson.annotations.SerializedName;

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

    /**
     * @return the success
     */
    public boolean isSuccess() {
        return success;
    }

    /**
     * @return the userHandle
     */
    public String getUserHandle() {
        return userHandle;
    }

    /**
     * @return the entityType
     */
    public String getEntityType() {
        return entityType;
    }

    /**
     * @return the entity
     */
    public Entity getEntity() {
        return entity;
    }

    /**
     * @return the address
     */
    public List<Address> getAddresses() {
        return addresses;
    }

    /**
     * @return the identities
     */
    public List<Identity> getIdentities() {
        return identities;
    }

    /**
     * @return the emails
     */
    public List<Email> getEmails() {
        return emails;
    }

    /**
     * @return the phones
     */
    public List<Phone> getPhones() {
        return phones;
    }

    /**
     * @return the memberships
     */
    public List<Membership> getMemberships() {
        return memberships;
    }

    /**
     * @return the members
     */
    public List<Member> getMembers() {
        return members;
    }

}