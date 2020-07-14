package com.silamoney.client.domain;

import java.text.SimpleDateFormat;
import java.util.List;

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
    @SerializedName(value = "business_uuid")
    private String businessUuid;
    @SerializedName(value = "business_type_uuid")
    private String businessTypeUuid;
    @SerializedName(value = "business_website")
    private String businessWebsite;
    @SerializedName(value = "doing_business_as")
    private String doingBusinessAs;
    @SerializedName(value = "naics_code")
    private int naicsCode;
    @SerializedName(value = "naics_category")
    private String naicsCategory;
    @SerializedName(value = "naics_subcategory")
    private String naicsSubcategory;
    @SerializedName(value = "created_epoch")
    private int createdEpoch;
    private String handle;
    @SerializedName(value = "full_name")
    private String fullName;
    private int created;
    private String status;
    @SerializedName(value = "blockchain_addresses")
    private List<String> blockchainAddresses;

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
        // this.businessTypeUuid = user.getBusinessType().getUuid();
        this.businessWebsite = user.getBusinessWebsite();
        this.doingBusinessAs = user.getDoingBusinessAs();
        this.naicsCode = user.getNaicsCategory().getCode();
    }

    /**
     * @return the birthdate
     */
    public String getBirthdate() {
        return birthdate;
    }

    /**
     * @return the entityName
     */
    public String getEntityName() {
        return entityName;
    }

    /**
     * @return the lastName
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * @return the relationship
     */
    public String getRelationship() {
        return relationship;
    }

    /**
     * @return the firstName
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * @return the businessType
     */
    public String getBusinessType() {
        return businessType;
    }

    /**
     * @return the businessTypeUuid
     */
    public String getBusinessTypeUuid() {
        return businessTypeUuid;
    }

    /**
     * @return the businessWebsite
     */
    public String getBusinessWebsite() {
        return businessWebsite;
    }

    /**
     * @return the doingBusinessAs
     */
    public String getDoingBusinessAs() {
        return doingBusinessAs;
    }

    /**
     * @return the naicsCode
     */
    public int getNaicsCode() {
        return naicsCode;
    }

    /**
     * @return the createdEpoch
     */
    public int getCreatedEpoch() {
        return createdEpoch;
    }

    /**
     * @return the businessUuid
     */
    public String getBusinessUuid() {
        return businessUuid;
    }

    /**
     * @return the naicsCategory
     */
    public String getNaicsCategory() {
        return naicsCategory;
    }

    /**
     * @return the naicsSubcategory
     */
    public String getNaicsSubcategory() {
        return naicsSubcategory;
    }

    /**
     * @return the handle
     */
    public String getHandle() {
        return handle;
    }

    /**
     * @return the fullName
     */
    public String getFullName() {
        return fullName;
    }

    /**
     * @return the created
     */
    public int getCreated() {
        return created;
    }

    /**
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @return the blockchainAddresses
     */
    public List<String> getBlockchainAddresses() {
        return blockchainAddresses;
    }
}
