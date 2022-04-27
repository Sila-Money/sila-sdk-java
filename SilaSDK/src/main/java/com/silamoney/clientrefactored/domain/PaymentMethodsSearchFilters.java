package com.silamoney.clientrefactored.domain;

import com.google.gson.annotations.SerializedName;
import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * Object used in the PaymentMethods object.
 *
 * @author Anuj
 */
@Builder
@Data
public class PaymentMethodsSearchFilters {
    @SerializedName("page")
    private int page;
    @SerializedName("per_page")
    private int perPage;
    @SerializedName("payment_method_types")
    private List<String> paymentMethodsTypes ;
}
