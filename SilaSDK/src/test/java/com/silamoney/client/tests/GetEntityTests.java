package com.silamoney.client.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import com.silamoney.client.api.ApiResponse;
import com.silamoney.client.api.SilaApi;
import com.silamoney.client.domain.GetEntityResponse;
import com.silamoney.client.domain.Membership;
import com.silamoney.client.testsutils.DefaultConfigurations;

import org.junit.Test;

/**
 *
 * @author Karlo Lorenzana
 */
public class GetEntityTests {

	SilaApi api = new SilaApi(DefaultConfigurations.host, DefaultConfigurations.appHandle,
			DefaultConfigurations.privateKey);

	@Test
	public void Response200Individual() throws Exception {
		ApiResponse response = api.getEntity(DefaultConfigurations.getUser2Handle(),
				DefaultConfigurations.getUser2PrivateKey());

		assertTrue(response.getSuccess());
		assertTrue(((GetEntityResponse) response.getData()).getAddresses().size() > 0);
		assertTrue(((GetEntityResponse) response.getData()).getEmails().size() > 0);
		assertTrue(((GetEntityResponse) response.getData()).getIdentities().size() > 0);
		assertTrue(((GetEntityResponse) response.getData()).getPhones().size() > 0);
		assertTrue(((GetEntityResponse) response.getData()).getMemberships().size() > 0);
		assertNotNull(((GetEntityResponse) response.getData()).getDevices());
		assertNotNull(((GetEntityResponse) response.getData()).getEntity().getEntityName());
		assertEquals("individual", ((GetEntityResponse) response.getData()).getEntityType());
		assertNotNull(((GetEntityResponse) response.getData()).getUserHandle());
		assertNotNull(((GetEntityResponse) response.getData()).getPhones().get(0).getSmsConfirmationRequested());
		assertNotNull(((GetEntityResponse) response.getData()).getPhones().get(0).getSmsConfirmed());

		for (Membership membership : ((GetEntityResponse) response.getData()).getMemberships()) {
			if (membership.getRole().equals("beneficial_owner")) {
				DefaultConfigurations.setBusinessOwnerToken(membership.getCertificationToken());
				break;
			}
		}
	}

	@Test
	public void Response200Business() throws Exception {
		ApiResponse response = api.getEntity(DefaultConfigurations.getBusinessHandle(),
				DefaultConfigurations.getBusinessPrivateKey());

		assertTrue(response.getSuccess());
		assertTrue(((GetEntityResponse) response.getData()).getAddresses().size() > 0);
		assertTrue(((GetEntityResponse) response.getData()).getEmails().size() > 0);
		assertTrue(((GetEntityResponse) response.getData()).getIdentities().size() > 0);
		assertTrue(((GetEntityResponse) response.getData()).getPhones().size() > 0);
		assertTrue(((GetEntityResponse) response.getData()).getMembers().size() > 0);
		assertNotNull(((GetEntityResponse) response.getData()).getDevices());
		assertNotNull(((GetEntityResponse) response.getData()).getEntity().getEntityName());
		assertEquals("business", ((GetEntityResponse) response.getData()).getEntityType());
		assertNotNull(((GetEntityResponse) response.getData()).getUserHandle());
	}
}
