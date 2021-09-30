package com.silamoney.client.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import com.silamoney.client.api.ApiResponse;
import com.silamoney.client.api.SilaApi;
import com.silamoney.client.domain.BadRequestResponse;
import com.silamoney.client.domain.CheckPartnerKycResponse;
import com.silamoney.client.domain.GetInstitutionsResponse;
import com.silamoney.client.domain.InstitutionSearchFilters;
import com.silamoney.client.testsutils.DefaultConfigurations;

import org.junit.Test;

public class GetInstitutionsTest {

	SilaApi api = new SilaApi(DefaultConfigurations.host, DefaultConfigurations.appHandle,
			DefaultConfigurations.privateKey);

	@Test
	public void Register200() throws Exception {

		InstitutionSearchFilters searchFilters = new InstitutionSearchFilters();
		searchFilters.setInstitutionName("1st advantage bank");
		searchFilters.setPage(1);
		searchFilters.setPerPage(20);

		ApiResponse response = api.getInstitutions(searchFilters);

		GetInstitutionsResponse parsedResponse = (GetInstitutionsResponse) response.getData();

		assertNotNull(parsedResponse.getInstitutions().get(0).getName());
	}
	@Test
	public void Register200WithoutSearchFilter() throws Exception {
		ApiResponse response = api.getInstitutions();
		GetInstitutionsResponse parsedResponse = (GetInstitutionsResponse) response.getData();
		assertNotNull(parsedResponse.getInstitutions());
	}
}
