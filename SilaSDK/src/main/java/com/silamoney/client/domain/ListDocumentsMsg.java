package com.silamoney.client.domain;

import java.time.format.DateTimeFormatter;
import java.util.List;

import com.google.gson.annotations.SerializedName;

public class ListDocumentsMsg {
    @SerializedName("header")
    private HeaderBase header;
    @SerializedName("message")
    private String message;
    @SerializedName("start_date")
    private String startDate;
    @SerializedName("end_date")
    private String endDate;
    @SerializedName("doc_types")
    private List<String> docTypes;
    @SerializedName("search")
    private String search;
    @SerializedName("sort_by")
    private String sortBy;

    public ListDocumentsMsg(String authHandle, ListDocumentsMessage message) {
        this.header = new HeaderBuilder(authHandle).withUserHandle(message.getUserHandle()).useVersion(VersionEnum.V0_2)
                .withCrypto(CryptoEnum.ETH).withReference().build();
        this.message = Message.ValueEnum.HEADER_MSG.getValue();
        this.startDate = message.getStartDate() != null
                ? message.getStartDate().format(DateTimeFormatter.ISO_LOCAL_DATE)
                : null;
        this.endDate = message.getEndDate() != null ? message.getEndDate().format(DateTimeFormatter.ISO_LOCAL_DATE)
                : null;
        this.docTypes = message.getDocTypes();
        this.search = message.getSearch();
        this.sortBy = message.getSortBy();
    }
}
