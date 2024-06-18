package com.silamoney.client.domain;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;
/**
 * Object sent in the Statements method
 *
 * @author anuj kalal
 */
@Setter
@Getter
public class StatementsSearchFilters {

    @SerializedName("start_date")
    private String startDate;
    @SerializedName("end_date")
    private String endDate;
    @SerializedName("page")
    private int page;
    @SerializedName("per_page")
    private int perPage;
    @SerializedName("user_name")
    private String userName;
    @SerializedName("user_handle")
    private String userHandle;
    @SerializedName("account_type")
    private String accountType;
    @SerializedName("email")
    private String email;
    @SerializedName("status")
    private String status;
    @SerializedName("identifier")
    private String identifier;
}
