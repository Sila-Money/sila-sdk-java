package com.silamoney.client.domain;

import com.google.gson.annotations.SerializedName;

public class GetInstitutionsMsg {
    @SerializedName("header")
    private final Header header;
    @SerializedName("search_filters")
    private final InstitutionSearchFilters searchFilters;
    @SerializedName("message")
    private final String message;
    public GetInstitutionsMsg(String authHandle, InstitutionSearchFilters searchFilters,String reference) {
        this.header=new Header(null,authHandle);
        this.header.setReference(reference);
        this.searchFilters=searchFilters;
        this.message=Message.ValueEnum.HEADER_MSG.getValue();
    }
}
