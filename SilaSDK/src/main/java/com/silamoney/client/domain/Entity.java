package com.silamoney.client.domain;

import com.google.gson.annotations.SerializedName;
import java.text.SimpleDateFormat;

/**
 * Object used in the entity msg.
 *
 * @author Karlo Lorenzana
 */
public class Entity {
    
    private enum RelationshipEnum {
        ORGANIZATION("organization"),
        DEVELOPER("developer"),
        USER("user"),
        VENDOR("vendor");

        private final String value;

        RelationshipEnum(String value) {
            this.value = value;
        }

        private String getValue() {
            return value;
        }
    }

    @SerializedName("birthdate")
    private final String birthdate;

    @SerializedName("entity_name")
    private final String entityName;

    @SerializedName("last_name")
    private final String lastName;

    @SerializedName("relationship")
    private final String relationship;

    @SerializedName("first_name")
    private final String firstName;

    /**
     * Constructor for Entity object.
     *
     * @param user
     */
    public Entity(User user) {
        String pattern = "yyyy-MM-dd";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        String date = simpleDateFormat.format(user.getBirthdate());
        this.birthdate = date;
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.entityName = this.firstName + " " + this.lastName;
        this.relationship = RelationshipEnum.USER.getValue();
    }
}
