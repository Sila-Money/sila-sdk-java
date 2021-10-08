package com.silamoney.clientrefactored.endpoints.accounts.updateaccount;

import lombok.Builder;
import lombok.Data;

import java.util.Optional;

@Builder
@Data
public class UpdateAccountRequest {

    private String userHandle;
    private String userPrivateKey;
    private String accountName;
    private String newAccountName;
    private Boolean active;

}
