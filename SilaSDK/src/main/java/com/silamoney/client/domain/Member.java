package com.silamoney.client.domain;

import com.google.gson.annotations.SerializedName;

public class Member {

    @SerializedName(value = "user_handle")
    private String userHandle;
    @SerializedName(value = "first_name")
    private String firstName;
    @SerializedName(value = "last_name")
    private String lastName;
    private String role;
    private String details;
    @SerializedName(value = "ownership_stake")
    private Float ownershipStake;
    @SerializedName(value = "verification_status")
    private String verificationStatus;
    @SerializedName(value = "verification_required")
    private String verificationRequired;
    @SerializedName(value = "verification_id")
    private String verificationId;
    @SerializedName(value = "beneficial_owner_certification_status")
    private String beneficialOwnerCertificationStatus;
    @SerializedName(value = "business_certification_status")
    private String businessCertificationStatus;

    /**
     * @return the userHandle
     */
    public String getUserHandle() {
        return userHandle;
    }

    /**
     * @param userHandle the userHandle to set
     */
    public void setUserHandle(String userHandle) {
        this.userHandle = userHandle;
    }

    /**
     * @return the firstName
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * @param firstName the firstName to set
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * @return the lastName
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * @param lastName the lastName to set
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * @return the role
     */
    public String getRole() {
        return role;
    }

    /**
     * @param role the role to set
     */
    public void setRole(String role) {
        this.role = role;
    }

    /**
     * @return the details
     */
    public String getDetails() {
        return details;
    }

    /**
     * @param details the details to set
     */
    public void setDetails(String details) {
        this.details = details;
    }

    /**
     * @return the ownershipStake
     */
    public Float getOwnershipStake() {
        return ownershipStake;
    }

    /**
     * @param ownershipStake the ownershipStake to set
     */
    public void setOwnershipStake(Float ownershipStake) {
        this.ownershipStake = ownershipStake;
    }

    /**
     * @return the verificationStatus
     */
    public String getVerificationStatus() {
        return verificationStatus;
    }

    /**
     * @param verificationStatus the verificationStatus to set
     */
    public void setVerificationStatus(String verificationStatus) {
        this.verificationStatus = verificationStatus;
    }

    /**
     * @return the verificationRequired
     */
    public String getVerificationRequired() {
        return verificationRequired;
    }

    /**
     * @param verificationRequired the verificationRequired to set
     */
    public void setVerificationRequired(String verificationRequired) {
        this.verificationRequired = verificationRequired;
    }

    /**
     * @return the verificationId
     */
    public String getVerificationId() {
        return verificationId;
    }

    /**
     * @param verificationId the verificationId to set
     */
    public void setVerificationId(String verificationId) {
        this.verificationId = verificationId;
    }

    /**
     * @return the beneficialOwnerCertificationStatus
     */
    public String getBeneficialOwnerCertificationStatus() {
        return beneficialOwnerCertificationStatus;
    }

    /**
     * @param beneficialOwnerCertificationStatus the
     *                                           beneficialOwnerCertificationStatus
     *                                           to set
     */
    public void setBeneficialOwnerCertificationStatus(String beneficialOwnerCertificationStatus) {
        this.beneficialOwnerCertificationStatus = beneficialOwnerCertificationStatus;
    }

    /**
     * @return the businessCertificationStatus
     */
    public String getBusinessCertificationStatus() {
        return businessCertificationStatus;
    }

    /**
     * @param businessCertificationStatus the businessCertificationStatus to set
     */
    public void setBusinessCertificationStatus(String businessCertificationStatus) {
        this.businessCertificationStatus = businessCertificationStatus;
    }

}