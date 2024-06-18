package com.silamoney.client.domain;

import java.util.List;

import com.google.gson.annotations.SerializedName;

public class CheckKYCResponse extends BaseResponse {

    @SerializedName(value = "entity_type")
    private String entityType;
    @SerializedName(value = "verification_status")
    private String verificationStatus;
    @SerializedName(value = "verification_history")
    private List<Verification> verificationHistory;
    @SerializedName(value = "valid_kyc_levels")
    private List<String> validKycLevels;
    @SerializedName(value = "certification_status")
    private String certificationStatus;
    @SerializedName(value = "certification_history")
    private List<String> certificationHistory;
    private List<Member> members;

    /**
     * @return the entityType
     */
    public String getEntityType() {
        return entityType;
    }

    /**
     * @param entityType the entityType to set
     */
    public void setEntityType(String entityType) {
        this.entityType = entityType;
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
     * @return the verificationHistory
     */
    public List<Verification> getVerificationHistory() {
        return verificationHistory;
    }

    /**
     * @param verificationHistory the verificationHistory to set
     */
    public void setVerificationHistory(List<Verification> verificationHistory) {
        this.verificationHistory = verificationHistory;
    }

    /**
     * @return the validKycLevels
     */
    public List<String> getValidKycLevels() {
        return validKycLevels;
    }

    /**
     * @param validKycLevels the validKycLevels to set
     */
    public void setValidKycLevels(List<String> validKycLevels) {
        this.validKycLevels = validKycLevels;
    }

    /**
     * @return the certificationStatus
     */
    public String getCertificationStatus() {
        return certificationStatus;
    }

    /**
     * @param certificationStatus the certificationStatus to set
     */
    public void setCertificationStatus(String certificationStatus) {
        this.certificationStatus = certificationStatus;
    }

    /**
     * @return the certificationHistory
     */
    public List<String> getCertificationHistory() {
        return certificationHistory;
    }

    /**
     * @param certificationHistory the certificationHistory to set
     */
    public void setCertificationHistory(List<String> certificationHistory) {
        this.certificationHistory = certificationHistory;
    }

    /**
     * @return the members
     */
    public List<Member> getMembers() {
        return members;
    }

    /**
     * @param members the members to set
     */
    public void setMembers(List<Member> members) {
        this.members = members;
    }

}