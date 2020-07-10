package com.silamoney.client.domain;

import java.util.List;

import com.google.gson.annotations.SerializedName;

public class Verification {

    @SerializedName(value = "verification_id")
    private String verificationId;
    @SerializedName(value = "verification_status")
    private String verificationStatus;
    @SerializedName(value = "kyc_level")
    private String kycLevel;
    @SerializedName(value = "requested_at")
    private int requestedAt;
    @SerializedName(value = "updated_at")
    private int updatedAt;
    private List<String> reasons;
    private List<String> tags;
    private float score;
    @SerializedName(value = "parent_verification")
    private Verification parentVerification;

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
     * @return the kycLevel
     */
    public String getKycLevel() {
        return kycLevel;
    }

    /**
     * @param kycLevel the kycLevel to set
     */
    public void setKycLevel(String kycLevel) {
        this.kycLevel = kycLevel;
    }

    /**
     * @return the requestedAt
     */
    public int getRequestedAt() {
        return requestedAt;
    }

    /**
     * @param requestedAt the requestedAt to set
     */
    public void setRequestedAt(int requestedAt) {
        this.requestedAt = requestedAt;
    }

    /**
     * @return the updatedAt
     */
    public int getUpdatedAt() {
        return updatedAt;
    }

    /**
     * @param updatedAt the updatedAt to set
     */
    public void setUpdatedAt(int updatedAt) {
        this.updatedAt = updatedAt;
    }

    /**
     * @return the reasons
     */
    public List<String> getReasons() {
        return reasons;
    }

    /**
     * @param reasons the reasons to set
     */
    public void setReasons(List<String> reasons) {
        this.reasons = reasons;
    }

    /**
     * @return the tags
     */
    public List<String> getTags() {
        return tags;
    }

    /**
     * @param tags the tags to set
     */
    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    /**
     * @return the score
     */
    public float getScore() {
        return score;
    }

    /**
     * @param score the score to set
     */
    public void setScore(float score) {
        this.score = score;
    }

    /**
     * @return the parentVerification
     */
    public Verification getParentVerification() {
        return parentVerification;
    }

    /**
     * @param parentVerification the parentVerification to set
     */
    public void setParentVerification(Verification parentVerification) {
        this.parentVerification = parentVerification;
    }
}