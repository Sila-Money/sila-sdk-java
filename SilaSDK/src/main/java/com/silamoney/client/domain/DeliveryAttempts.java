package com.silamoney.client.domain;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;

/**
 * Entity used for the List of delivery_attempts in the '/statements' endpoint.
 *
 * @author Anuj Kalal
 */
@Getter
public class DeliveryAttempts {

    /**
     * String field used for the created.
     */
    private String created;

    /**
     * String field used for the user name.
     */
    @SerializedName("user_name")
    private String userName;

    /**
     * String field used for the user handle.
     */
    @SerializedName("user_handle")
    private String userHandle;

    /**
     * String field used for the account type.
     */
    @SerializedName("account_type")
    private String accountType;

    /**
     * String field used for the identifier.
     */
    private String identifier;

    /**
     * String field used for the message id.
     */
    @SerializedName("message_id")
    private String messageId;

    /**
     * String field used for the statement id.
     */
    @SerializedName("statement_id")
    private String statementId;

    /**
     * String field used for the notification type.
     */
    @SerializedName("notification_type")
    private String notificationType;

    /**
     * String field used for the email.
     */
    private String email;

    /**
     * String field used for the delivery status.
     */
    @SerializedName("delivery_status")
    private String deliveryStatus;

    /**
     * String field used for the sent date.
     */
    @SerializedName("sent_date")
    private String sentDate;
}
