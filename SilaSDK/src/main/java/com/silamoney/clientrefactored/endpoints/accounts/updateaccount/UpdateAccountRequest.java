package com.silamoney.clientrefactored.endpoints.accounts.updateaccount;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
    private Optional<Boolean> active;

}
