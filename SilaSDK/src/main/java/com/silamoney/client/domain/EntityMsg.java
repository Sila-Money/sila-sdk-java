package com.silamoney.client.domain;

import com.google.gson.annotations.SerializedName;

/**
 * Object sent in the register endpoint.
 *
 * @author Karlo Lorenzana
 */
public class EntityMsg {

    @SerializedName("address")
    private final Address address;

    @SerializedName("identity")
    private final Identity identity;

    @SerializedName("contact")
    private final Contact contact;

    @SerializedName("header")
    private final Header header;

    @SerializedName("crypto_entry")
    private final CryptoEntry cryptoEntry;

    @SerializedName("message")
    private final String message;

    @SerializedName("entity")
    private final Entity entity;

    /**
     * Constructor for the EntityMsg object.
     *
     * @param user
     * @param appHandle
     */
    public EntityMsg(User user, String appHandle) {
        this.header = new Header(user.getHandle(), appHandle);
        this.message = Message.ValueEnum.ENTITY_MSG.getValue();
        this.address = new Address(user);
        this.identity = new Identity(user);
        this.contact = new Contact(user);
        this.cryptoEntry = new CryptoEntry(user);
        this.entity = new Entity(user);
    }

    /**
     * Constructor for the EntityMsg object.
     *
     * @param user
     * @param appHandle
     */
    public EntityMsg(BusinessUser user, String appHandle) {
        this.header = new Header(user.getHandle(), appHandle);
        this.message = Message.ValueEnum.ENTITY_MSG.getValue();
        this.address = new Address(user);
        this.identity = new Identity(user);
        this.contact = new Contact(user);
        this.cryptoEntry = new CryptoEntry(user);
        this.entity = new Entity(user);
    }
}
