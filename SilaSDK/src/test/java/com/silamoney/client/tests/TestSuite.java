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
    RequestKYCTests.class,
    CheckKYCTests.class,
    CheckPartnerKycTests.class,
    GetEntityTests.class,
    DocumentsTests.class,
    ListDocumentsTests.class,
    GetDocumentTests.class,
    certifyBeneficialOwnerTests.class,
    CertifyBusinessTests.class,
    LinkAccountTests.class,
    UnlinkBusinessMemberTests.class,
    UpdateAccountTests.class,
    GetAccountsTests.class,
    CreateCkoTestingTokenTests.class,
    LinkCardTests.class,
    GetCardsTests.class,
    OpenVirtualAccountTests.class,
    GetVirtualAccountsTests.class,
    GetVirtualAccountTests.class,
    UpdateVirtualAccountTests.class,
    GetPaymentMethodsTests.class,
    GetAccountBalanceTests.class,
    CancelTransactionTests.class,
    CreateTestVirtualAccountAchTransactionTests.class,
    IssueSilaTests.class,
    RefundDebitCardTests.class,
    TransferSilaTests.class,
    RedeemSilaTests.class,
    GetTransactionsTests.class,
    GetWebhooksTests.class,
    RetryWebhooksTests.class,
    RegisterWalletTests.class,
    GetWalletTests.class,
    GetWalletsTests.class,
    GetWalletStatementDataTests.class,
    GetStatementsDataTests.class,
    GetStatementTransactionsTests.class,
    StatementsTests.class,
    ResendStatementsTests.class,
    UpdateWalletTests.class,
    DeleteWalletTests.class,
    DeleteCardTests.class,
    CloseVirtualAccountTests.class,
    SilaBalanceTests.class,
    TearDownTests.class,
    DeleteAccountTests.class,
})
public class TestSuite {}
