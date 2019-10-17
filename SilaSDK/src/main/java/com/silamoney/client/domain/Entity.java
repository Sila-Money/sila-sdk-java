package com.silamoney.client.domain;

import com.google.gson.annotations.SerializedName;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Object used in the entity msg.
 *
 * @author Karlo Lorenzana
 */
public class Entity {

    /**
     * Date field used for the birthdate.
     */
    @SerializedName("birthdate")
    public String birthdate;

    /**
     * String field used for the entity name.
     */
    @SerializedName("entity_name")
    public String entityName;

    /**
     * String field used for the last name.
     */
    @SerializedName("last_name")
    public String lastName;

    private enum RelationshipEnum {
        ORGANIZATION("organization"),
        DEVELOPER("developer"),
        USER("user"),
        VENDOR("vendor");

        private final String value;

        RelationshipEnum(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }

    /**
     * String field used for the entity relationship.
     */
    @SerializedName("relationship")
    public String relationship;

    /**
     * String field used for the first name.
     */
    @SerializedName("first_name")
    public String firstName;

    /**
     * Constructor for Entity object.
     *
     * @param user
     */
    public Entity(User user) {
        String pattern = "yyyy-MM-dd";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        String date = simpleDateFormat.format(user.birthdate);
        this.birthdate = date;
        this.firstName = user.firstName;
        this.lastName = user.lastName;
        this.entityName = this.firstName + " " + this.lastName;
        this.relationship = RelationshipEnum.USER.getValue();
    }
}
