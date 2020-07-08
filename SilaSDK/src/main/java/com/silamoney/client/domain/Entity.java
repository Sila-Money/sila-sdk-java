package com.silamoney.client.domain;

import java.text.SimpleDateFormat;

import com.google.gson.annotations.SerializedName;

/**
 * Object used in the entity msg.
 *
 * @author Karlo Lorenzana
 */
public class Entity {

    private enum RelationshipEnum {
        ORGANIZATION("organization"), DEVELOPER("developer"), USER("user"), VENDOR("vendor");

        private String value;

        RelationshipEnum(String value) {
            this.value = value;
        }

        private String getValue() {
            return value;
        }
    }

    @SerializedName("birthdate")
    private String birthdate;

    @SerializedName("entity_name")
    private String entityName;

    @SerializedName("last_name")
    private String lastName;

    @SerializedName("relationship")
    private String relationship;

    @SerializedName("first_name")
    private String firstName;

    private String type;

    @SerializedName(value = "business_type")
    private String businessType;

    @SerializedName(value = "business_type_uuid")
    private String businessTypeUuid;

    @SerializedName(value = "business_website")
    private String businessWebsite;

    @SerializedName(value = "doing_business_as")
    private String doingBusinessAs;

    @SerializedName(value = "naics_code")
    private int naicsCode;

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

    public Entity(BusinessUser user) {
        this.entityName = user.getEntityName();
        this.type = "business";
        this.businessType = user.getBusinessType().getName();
        //this.businessTypeUuid = user.getBusinessType().getUuid();
        this.businessWebsite = user.getBusinessWebsite();
        this.doingBusinessAs = user.getDoingBusinessAs();
        this.naicsCode = user.getNaicsCategory().getCode();
    }
}
