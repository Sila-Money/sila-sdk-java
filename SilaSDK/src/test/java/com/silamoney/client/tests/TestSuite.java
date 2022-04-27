package com.silamoney.client.tests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

/**
 *
 * @author Karlo Lorenzana
 */
@RunWith(Suite.class)
@SuiteClasses({
    GetInstitutionsTest.class,
    GetBusinessTypesTests.class,
    GetBusinessRolesTests.class,
    GetNaicsCategoriesTest.class,
    GetDocumentTypesTests.class,
    GetEntitiesTests.class,
    CheckHandleTests.class,
    RegisterTests.class,
    RegisterBusinessTests.class,
    DeleteRegistrationTests.class,
    AddRegistrationDataTests.class,
    UpdateRegistrationDataTests.class,
    LinkBusinessMemberTests.class,
    UnlinkBusinessMemberTests.class,
    RequestKYCTests.class,
    CheckKYCTests.class,
    CheckPartnerKycTests.class,
    GetEntityTests.class,
    DocumentsTests.class,
    ListDocumentsTests.class,
    GetDocumentTests.class,
    certifyBeneficialOwnerTests.class,
    CertifyBusinessTests.class,
    PlaidLinkTokenTests.class,
    LinkAccountTests.class,
    CheckInstantAchTests.class,
    PlaidUpdateLinkTokenTests.class,
    UpdateAccountTests.class,
    DeleteAccountTests.class,
    GetAccountsTests.class,
    LinkCardTests.class,
    GetCardsTests.class,
    OpenVirtualAccountTests.class,
    GetVirtualAccountsTests.class,
    GetVirtualAccountTests.class,
    UpdateVirtualAccountTests.class,
    GetPaymentMethodsTests.class,
    GetAccountBalanceTests.class,
    CancelTransactionTests.class,
    CloseVirtualAccountTests.class,
    CreateTestVirtualAccountAchTransactionTests.class,
    IssueSilaTests.class,
    TransferSilaTests.class, 
    RedeemSilaTests.class,
    GetTransactionsTests.class,
    GetWebhooksTests.class,
    RetryWebhooksTests.class,
    ReverseTransactionTests.class,
    PlaidSameDayAuthTests.class,
    RegisterWalletTests.class,
    GetWalletTests.class,
    GetWalletsTests.class,
    UpdateWalletTests.class,
    DeleteWalletTests.class,
    DeleteCardTests.class,
    SilaBalanceTests.class
})
public class TestSuite {}
