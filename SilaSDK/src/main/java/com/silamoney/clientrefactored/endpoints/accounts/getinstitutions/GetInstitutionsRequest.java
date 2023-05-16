package com.silamoney.clientrefactored.endpoints.accounts.getinstitutions;

import com.silamoney.clientrefactored.domain.InstitutionSearchFilters;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class GetInstitutionsRequest {
    
    private InstitutionSearchFilters searchFilters;
    private String reference;
    
}
