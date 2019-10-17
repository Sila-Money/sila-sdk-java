package com.silamoney.client.domain;

import com.google.gson.annotations.SerializedName;

/**
 * Object sent in the register endpoint.
 *
 * @author loren
 */
public class EntityMsg {

    /**
     * Address object used in the entity msg.
     */
    @SerializedName("address")
    public Address address;

    /**
     * Identity object used in the entity msg.
     */
    @SerializedName("identity")
    public Identity identity;

    /**
     * Contact object used in the entity msg.
     */
    @SerializedName("contact")
    public Contact contact;

    /**
     * Header object used in the entity msg.
     */
    @SerializedName("header")
    public Header header;

    /**
     * CryptoEntry object used in the entity msg.
     */
    @SerializedName("crypto_entry")
    public CryptoEntry cryptoEntry;

    /**
     * String field used for the message.
     */
    @SerializedName("message")
    public String message;

    /**
     * Entity object used in the entity msg.
     */
    @SerializedName("entity")
    public Entity entity;

    /**
     * Constructor for the EntityMsg object.
     *
     * @param user
     * @param appHandle
     */
    public EntityMsg(User user, String appHandle) {
        this.header = new Header(user.handle, appHandle);
        this.message = Message.ValueEnum.ENTITY_MSG.getValue();
        this.address = new Address(user);
        this.identity = new Identity(user);
        this.contact = new Contact(user);
        this.cryptoEntry = new CryptoEntry(user);
        this.entity = new Entity(user);
    }
}
