package com.silamoney.client.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

import com.silamoney.client.api.ApiResponse;
import com.silamoney.client.api.SilaApi;
import com.silamoney.client.domain.GetNaicsCategoriesResponse;
import com.silamoney.client.domain.NaicsCategoryDescription;
import com.silamoney.client.exceptions.BadRequestException;
import com.silamoney.client.exceptions.ForbiddenException;
import com.silamoney.client.exceptions.InvalidSignatureException;
import com.silamoney.client.exceptions.ServerSideException;
import com.silamoney.client.testsutils.DefaultConfigurations;

import org.junit.Test;

/**
 *
 * @author Karlo Lorenzana
 */
public class GetNaicsCategoriesTest {

	SilaApi api = new SilaApi(DefaultConfigurations.host, DefaultConfigurations.appHandle,
			DefaultConfigurations.privateKey);

	static String userHandle = "";

	@Test
	public void Response200Success() throws Exception {
		ApiResponse response = api.getNaicsCategories();

		assertEquals(200, response.getStatusCode());

		Map<String, ArrayList<NaicsCategoryDescription>> naicsCategories = ((GetNaicsCategoriesResponse) response.getData()).getNaicsCategories();

		assertTrue(naicsCategories.size() > 0);
		for (String key : naicsCategories.keySet()) {
			for (NaicsCategoryDescription categoryDescription : naicsCategories.get(key)) {
				assertTrue(categoryDescription.getSubcategory() != null);
				assertTrue(categoryDescription.getCode() > 0);
			}
		}

		DefaultConfigurations.setNaicsCategories(naicsCategories);
	}

	@Test
	public void Response400() throws BadRequestException, InvalidSignatureException, ServerSideException, IOException,
			InterruptedException, ForbiddenException {

		api = new SilaApi(DefaultConfigurations.host, "",
				DefaultConfigurations.privateKey);

		ApiResponse response = api.getNaicsCategories();
		assertEquals(400, response.getStatusCode());
	}

	@Test
	public void Response401() throws BadRequestException, InvalidSignatureException, ServerSideException, IOException,
			InterruptedException, ForbiddenException {
		api = new SilaApi(DefaultConfigurations.host, DefaultConfigurations.appHandle,
				"3a1076bf45ab87712ad64ccb3b10217737f7faacbf2872e88fdd9a537d8fe266");
		ApiResponse response = api.getNaicsCategories();
		assertEquals(403, response.getStatusCode());
	}
}
