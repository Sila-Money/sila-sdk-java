package com.silamoney.client.domain;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * Object used in the PaymentMethods object.
 *
 * @author Anuj
 */
public class PaymentMethodsSearchFilters {

    /**
     * Available values for the Payment Methods Types Types.
     */
    public enum PaymentMethodsTypesEnum {

        /**
         * String value for blockchain_address type.
         */
        BLOCKCHAIN_ADDRESS("blockchain_address"),
        /**
         * String value for bank_account type.
         */
        BANK_ACCOUNT("bank_account"),
        /**
         * String value for card type.
         */
        CARD("card");

        private final String value;

        PaymentMethodsTypesEnum(String value) {
            this.value = value;
        }

        /**
         * Gets the enum value.
         *
         * @return SearchFilters  string value.
         */
        public String getValue() {
            return value;
        }
    }

    @Setter
    @Getter
    @SerializedName("page")
    private int page;
    @Setter
    @Getter
    @SerializedName("per_page")
    private int perPage;
    @SerializedName("payment_method_types")
    private List<String> paymentMethodsTypes ;

    /**
     * Sets the payment methods types to the filters.
     *
     * @param paymentMethodsTypes
     * @return SearchFilters
     */
    public PaymentMethodsSearchFilters setPaymentMethodsTypes(List<PaymentMethodsTypesEnum> paymentMethodsTypes) {
        if (paymentMethodsTypes != null) {
            this.paymentMethodsTypes = new ArrayList<>();
            paymentMethodsTypes.forEach(transactionType ->
                    this.paymentMethodsTypes.add(transactionType.getValue())
            );
        }
        return this;
    }
}
