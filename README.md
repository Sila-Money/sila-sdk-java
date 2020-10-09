# Sila Java SDK 0.2.13-rc

NOTE: This SDK is a release candidate.

For this SDK you will need to use JDK 11 or later.

### Usage

#### Installation

Add the SDK from the Maven repository.

```xml
<dependency>
    <groupId>com.silamoney.client</groupId>
    <artifactId>SilamoneySDK</artifactId>
    <version>0.2.13-rc</version>
</dependency>
```

#### Initialize the application

```java
import com.silamoney.client.api.SilaApi;

SilaApi api = new SilaApi(Environments.SilaEnvironment.SANDBOX,
        appHandle,
        privateKey);
```

This sets up the app private key and handle for the SDK to use for signing subsequent requests. The other SDK functionality will not be available until this configuration is completed. The SDK does not store this information outside of the instance that is configured. Private keys are never transmitted over the network or stored outside the scope of this instance.

### User Methods

#### Check Handle

Checks if a specific handle is already taken.

```java
String userHandle = "user.silamoney.eth";
ApiResponse response = api.checkHandle(userHandle);
```

##### Success Response Object 200

```java
System.out.println(response.getStatusCode()); // 200
System.out.println(((BaseResponse)response.getData()).getReference()); // Random reference number
System.out.println(((BaseResponse)response.getData()).getStatus()); // SUCCESS
System.out.println(((BaseResponse)response.getData()).getMessage()); // user is available!
```

##### Failure Response Object 200

```java
System.out.println(response.getStatusCode()); // 200
System.out.println(((BaseResponse)response.getData()).getReference()); // Random reference number
System.out.println(((BaseResponse)response.getData()).getStatus()); // FAILURE
System.out.println(((BaseResponse)response.getData()).getMessage()); // user is already taken.
```

#### Register

Attaches KYC data and specified blockchain address to an assigned handle.

```java
User user = new User(String userHandle, String firstName, String lastName, String streetAddress1, @Nullable String streetAddress2,
      String city, String state (2 characters), String postalCode (5 or 9 digit format), String phone, String email, String cryptoAddress,
      String identityNumber (SSN format "AAA-GG-SSSS"), Date birthdate, String country (2 characters));
ApiResponse response = api.register(user);
```

##### Success Response Object

```java
System.out.println(response.getStatusCode()); // 200
System.out.println(((BaseResponse)response.getData()).getReference());// Random reference number
System.out.println(((BaseResponse)response.getData()).getStatus()); // SUCCESS
System.out.println(((BaseResponse)response.getData()).getMessage()); // user was successfully registered.
```

#### Register Business

```java
BusinessType businessType; //Get business type with api.getBusinessTypes() documentation below.
NaicsCategoryDescription naicsCategory; //Get naics category with api.getNaicsCategories() documentation below.

BusinessUser user = new BusinessUser("userhandle", "Office", "123 Main Street",
                "street address 2", "New City", "OR", "97204-1234", "503-123-4567", "example@silamoney.com", "123452222", "crypto address", "entity name", businessType, "https://www.website.com", "doing business as", naicsCategory, String country (2 characters));

ApiResponse response = api.registerBusiness(user);
```

##### Success Response Object

```java
System.out.println(response.getStatusCode()); // 200
System.out.println(((BaseResponse)response.getData()).getReference());// Random reference number
System.out.println(((BaseResponse)response.getData()).getStatus()); // SUCCESS
System.out.println(((BaseResponse)response.getData()).getMessage()); // user was successfully registered.
```

#### Delete Registration Data

Delete an existing email, phone number, street address, or identity.

```java
DeleteRegistrationMessage message = DeleteRegistrationMessage.builder()
        .userHandle("user_handle")
        .userPrivateKey("user_private_key")
        .uuid("some-uuid-code")
        .build();
response = api.deleteRegistrationData(RegistrationDataEnum.IDENTITY, message);
```

##### Success Response Object

```java
System.out.println(response.getStatusCode()); // 200
BaseResponse parsedResponse = (BaseResponse) response.getData();
System.out.println(parsedResponse.getSuccess());// Random reference number
System.out.println(parsedResponse.getStatus()); // SUCCESS
System.out.println(parsedResponse.getMessage()); // user was successfully registered.
```

#### RequestKYC

Starts KYC verification process on a registered user handle.

Normal Flow:

```java
ApiResponse response = api.requestKYC(userHandle, userPrivateKey);
```

Custom Flow:

```java
String kycLevel = "CUSTOM_KYC_FLOW_NAME";
ApiResponse response = api.requestKYC(userHandle, userPrivateKey, kycLevel);
```

##### Success Response Object

```java
System.out.println(response.getStatusCode()); // 200
System.out.println(((BaseResponse)response.getData()).getReference()); // Random reference number
System.out.println(((BaseResponse)response.getData()).getStatus()); // SUCCESS
System.out.println(((BaseResponse)response.getData()).getMessage()); // user submitted for KYC review.
```

#### CheckKYC

Returns whether the entity attached to the user handle is verified, not valid, or still pending.

```java
ApiResponse response = api.checkKYC(userHandle, userPrivateKey);
```

##### Success Response Object

```java
System.out.println(response.getStatusCode()); // 200
System.out.println(((BaseResponse)response.getData()).getReference()); // Random reference number
System.out.println(((BaseResponse)response.getData()).getStatus()); // SUCCESS
System.out.println(((BaseResponse)response.getData()).getMessage()); // user has passed ID verification!
```

#### LinkAccount

Uses a provided Plaid public token to link a bank account to a verified entity.

```java
String userHandle = 'user.silamoney.eth';
String accountName = 'plaid'; // Your desired account name
String publicToken = 'public-sandbox-xxx' // Your Plaid token
String userPrivateKey = 'some private key';
String accountId = 'some account id';

// Gets the first plaid account
ApiResponse response = api.linkAccount(userHandle, userPrivateKey, accountName, publicToken);
// If you want to specify the account id
ApiResponse response = api.linkAccount(userHandle, userPrivateKey, accountName, publicToken, accountId);
```

Public token received in the /link/item/create plaid endpoint.

For Direct account linking

```java
String userHandle = 'user.silamoney.eth';
String accountName = 'direct'; // Your desired account name
String accountNumber = '123456789012';
String routingNumber = '123456789';
String accountType = 'CHECKING'; // Currently the only allowed value
String userPrivateKey = 'some private key';

ApiResponse response = api.linkAccount(userHandle, userPrivateKey, accountName, accountNumber, routingNumber, accountType);
```

##### Success Response Object

```java
System.out.println(response.getStatusCode()); // 200
LinkAccountResponse parsedResponse = (LinkAccountResponse) response.getData();
System.out.println(parsedResponse.getStatus()); // SUCCESS
System.out.println(parsedResponse.getReference()); // Reference number
System.out.println(parsedResponse.getMessage()); // Successfully linked
System.out.println(parsedResponse.getAccountName()); // Your desired account name
System.out.println(parsedResponse.getMatchScore()); // Match score
```

#### Plaid Sameday Auth

Generates a Plaid token to complete Plaid's Same Day Microdeposit Authentication

```java
ApiResponse response = api.plaidSameDayAuth(userHandle, accountName, userPrivateKey);
```

##### Success Response Object

```java
System.out.println(response.getStatusCode()); // 200
System.out.println(((PlaidSameDayAuthResponse)response.getData()).getPublicToken());
```

#### Generate Wallet

Gets a random generated wallet

```java
Wallet newWallet = api.generateWallet();
```

#### Register Wallet

Adds another "wallet"/blockchain address to a user handle

```java
ApiResponse response = api.registerWallet(userHandle, wallet, walletVerificationSignature, userPrivateKey);
```

##### Success Response Object

```java
System.out.println(response.getStatusCode()); // 200
```

#### Get Wallets

Gets a paginated list of "wallets"/blockchain addresses attached to a user handle.

```java
SearchFilters filters = new SearchFilters()
filters.setPage(1);
filters.setPerPage(20);
filters.setSortAscending(true);
filters.setBlockchainNetwork("ETH");
filters.setBlockchainAddress("");
filters.setNickname("Some nickname");

ApiResponse response = api.getWallets(userHandle, filters, userPrivateKey);
```

##### Success Response Object

```java
System.out.println(response.getStatusCode()); // 200
System.out.println(((GetWalletsResponse)response.getData()).getWallets()); // Wallet list
System.out.println(((GetWalletsResponse)response.getData()).getPage()); // Actual page requested
System.out.println(((GetWalletsResponse)response.getData()).getReturnedCount()); // Total wallets returned
System.out.println(((GetWalletsResponse)response.getData()).getTotalCount()); // Total wallets exists
```

#### Get Wallet

Gets details about the user wallet used to generate the usersignature header.

```java
ApiResponse response = api.getWallet(userHandle, userPrivateKey);
```

##### Success Response Object

```java
System.out.println(response.getStatusCode()); // 200
System.out.println(((GetWalletResponse)response.getData()).getWallet()); // Wallet object
System.out.println(((GetWalletResponse)response.getData()).getIsWhitelisted()); // Boolean
System.out.println(((GetWalletResponse)response.getData()).getSilaBalance()); // Sila balance
```

#### Update Wallet

Updates nickname and/or default status of a wallet.

```java
ApiResponse response = api.updateWallet(userHandle, nickname, status, userPrivateKey);
```

##### Success Response Object

```java
System.out.println(response.getStatusCode()); // 200
```

#### Delete Wallet

Deletes a user wallet.

```java
ApiResponse response = api.deleteWallet(userHandle, userPrivateKey);
```

##### Success Response Object

```java
System.out.println(response.getStatusCode()); // 200
```

#### GetAccounts

Gets basic bank account names linked to user handle.

```java
ApiResponse response = api.GetAccounts(userHandle, userPrivateKey);
```

##### Success Response Object

```java
System.out.println(response.getStatusCode()); // 200
System.out.println(((List<Account>) response.getData()).get(0).accountName); // Account name
System.out.println(((List<Account>) response.getData()).get(0).accountNumber); // Account Number
System.out.println(((List<Account>) response.getData()).get(0).accountStatus); // Account Status
System.out.println(((List<Account>) response.getData()).get(0).accountType); // Account Type
```

#### IssueSila

Debits a specified account and issues tokens to the address belonging to the requested handle.

```java
AccountTransactionMessage issueMsg = AccountTransactionMessage.builder()
    .userHandle("user_handle")
    .userPrivateKey("user_private_key")
    .amount(100)
    .accountName("your_account_name")
    .descriptor("your custom descriptor") // Optional
    .businessUuid("some-business-uuid") // Optional
    .processingType(ProcessingTypeEnum.SAME_DAY) // Optional
    .build();
ApiResponse response = api.IssueSila(issueMsg);
```

##### Success Object Response

```java
System.out.println(response.getStatusCode()); // 200
TransactionResponse parsedResponse = (TransactionResponse) response.getData();
System.out.println(parsedResponse.getReference()); // Random reference number
System.out.println(parsedResponse.getStatus()); // SUCCESS
System.out.println(parsedResponse.getMessage()); // Transaction submitted to processing queue.
System.out.println(parsedResponse.getTransactionId()); // Transaction id
System.out.println(parsedResponse.getDescriptor()); // The transaction descriptor (if was set)
System.out.println(parsedResponse.getRerence()); // The transaction reference
```

#### TransferSila

Starts a transfer of the requested amount of SILA to the requested destination handle.

```java
ApiResponse response = api.TransferSila(userHandle, 1000, destination, destinationAddress, descriptor, businessUuid, userPrivateKey);
```

##### Success Object Response

```java
System.out.println(response.getStatusCode()); // 200
System.out.println(((TransactionResponse) response.getData()).getReference()); // Random reference number
System.out.println(((TransactionResponse) response.getData()).getStatus()); // SUCCESS
System.out.println(((TransactionResponse) response.getData()).getMessage()); // Transaction submitted to processing queue.
```

#### RedeemSila

Burns given the amount of SILA at the handle's blockchain address and credits their named bank account in the equivalent monetary amount.

```java
AccountTransactionMessage redeemMsg = AccountTransactionMessage.builder()
    .userHandle("user_handle")
    .userPrivateKey("user_private_key")
    .amount(100)
    .accountName("your_account_name")
    .descriptor("your custom descriptor") // Optional
    .businessUuid("some-business-uuid") // Optional
    .processingType(ProcessingTypeEnum.SAME_DAY) // Optional
    .build();
ApiResponse response = api.RedeemSila(redeemMsg);
```

##### Success Object Response

```java
System.out.println(response.getStatusCode()); // 200
TransactionResponse parsedResponse = (TransactionResponse) response.getData();
System.out.println(parsedResponse.getReference()); // Random reference number
System.out.println(parsedResponse.getStatus()); // SUCCESS
System.out.println(parsedResponse.getMessage()); // Transaction submitted to processing queue.
System.out.println(parsedResponse.getTransactionId()); // Transaction id
System.out.println(parsedResponse.getDescriptor()); // The transaction descriptor (if was set)
System.out.println(parsedResponse.getRerence()); // The transaction reference
```

#### Cancel Transaction

Cancel a pending transaction under certain circumstances

```java
CancelTransactionMessage cancelMsg = CancelTransactionMessage.builder()
        .userHandle("user_handle")
        .userPrivateKey("user_private_key")
        .transactionId("some-uuid-code")
        .build();
ApiResponse response = api.cancelTransaction(cancelMsg);
```

##### Success Object Response

```java
System.out.println(response.getStatusCode()); // 200
BaseResponse parsedResponse = (BaseResponse) response.getData();
System.out.println(parsedResponse.getSuccess()); // true
System.out.println(parsedResponse.getStatus()); // SUCCESS
System.out.println(parsedResponse.getMessage()); // Transaction some-uuid-code has been canceled
```

#### GetTransactions

Gets the array of user handle's transactions with detailed status information.

```java
SearchFilters filters = new SearchFilters()
filters.setTransactionId("some UUID string assigned by Sila");
filters.setReferenceId("The reference string sent in the header object when transaction request was made");
filters.setShowTimelines(true);
filters.setSortAscending(false);
filters.setMaxSilaAmount(1300);
filters.setMinSilaAmount(1000);
filters.setStatues(new List<string>() {"queued", "pending", "failed", "success", "rollback", "review"});
filters.setStartEpoch(1234567860);
filters.setEndEpoch(1234567891);
filters.setPage(1);
filters.setPerPage(20);
filters.setTransactionTypes(new List<string>() {"issue", "redeem", "transfer"});

ApiResponse response = api.GetTransactions(userHandle, filters, userPrivateKey);
```

##### Success Object Response

```java
System.out.println(response.getStatusCode()); // 200
GetTransactionsResponse parsedResponse = (GetTransactionsResponse) response.getData();
System.out.println(parsedResponse.success); // true
System.out.println(parsedResponse.status); // SUCCESS
System.out.println(parsedResponse.transactions.get(0).userHandle);
System.out.println(parsedResponse.transactions.get(0).referenceId);
System.out.println(parsedResponse.transactions.get(0).transactionId);
System.out.println(parsedResponse.transactions.get(0).transactionHash);
System.out.println(parsedResponse.transactions.get(0).transactionType);
System.out.println(parsedResponse.transactions.get(0).silaAmount);
System.out.println(parsedResponse.transactions.get(0).status);
System.out.println(parsedResponse.transactions.get(0).usdStatus);
System.out.println(parsedResponse.transactions.get(0).tokenStatus);
System.out.println(parsedResponse.transactions.get(0).created);
System.out.println(parsedResponse.transactions.get(0).lastUpdate);
System.out.println(parsedResponse.transactions.get(0).createdEpoch);
System.out.println(parsedResponse.transactions.get(0).lastUpdateEpoch)
System.out.println(parsedResponse.transactions.get(0).descriptor);
System.out.println(parsedResponse.transactions.get(0).descriptorAch);
System.out.println(parsedResponse.transactions.get(0).achName);
if (parsedResponse.transactions.get(0).transactionType.equals("transfer")) {
    System.out.println(parsedResponse.transactions.get(0).destinationAddress);
    System.out.println(parsedResponse.transactions.get(0).destinationHandle);
    System.out.println(parsedResponse.transactions.get(0).handleAddress);
} else {
    System.out.println(parsedResponse.transactions.get(0).processingType);
}
```

#### Get Sila Balance

Gets Sila balance for a given blockchain address.

```java
ApiResponse response = api.SilaBalance(address);
```

##### Success Object Response

```java
System.out.println(response.getStatusCode()); // 200
System.out.println(((GetSilaBalanceResponse)response.getData()).getAddress()); // address.
System.out.println(((GetSilaBalanceResponse)response.getData()).getSilaBalance()); // Sila balance.
```

#### GetBusinessTypes

Gets a list of valid business types that can be registered.

```java
ApiResponse response = api.getBusinessTypes();
```

##### Success Object Response

```java
System.out.println(response.getStatusCode()); // 200
((GetBusinessTypesResponse) response.getData()).getBusinessTypes(); // List of business types.
```

#### GetBusinessRoles

Retrieves the list of pre-defined business roles.

```java
ApiResponse response = api.getBusinessRoles();
```

##### Success Object Response

```java
System.out.println(response.getStatusCode()); // 200
((GetBusinessRolesResponse) response.getData()).getBusinessRoles(); // List of business roles.
```

#### GetNaicsCategories

```java
ApiResponse response = api.getNaicsCategories();
```

##### Success Object Response

```java
System.out.println(response.getStatusCode()); // 200
Map<String, ArrayList<NaicsCategoryDescription>> naicsCategories = ((GetNaicsCategoriesResponse) response.getData()).getNaicsCategories();
for (String key : naicsCategories.keySet()) {
    for (NaicsCategoryDescription categoryDescription : naicsCategories.get(key)) {
        System.out.println(categoryDescription.getSubcategory()); // Naics Category subcategory
        System.out.println(categoryDescription.getCode()); // Naics Category code
    }
}
```

#### LinkBusinessMemeber

```java
BusinessRole businessRole = ((GetBusinessRolesResponse)api.getBusinessRoles().getData()).get(0);
float ownershipStake = 0.30;
ApiResponse response = api.linkBusinessMember("user handle", "user private key", "business handle", "business private key", businessRole, (optional) "member handle", (optional) "test details", (optional) ownershipStake);
```

##### Success Object Response

```java
System.out.println(response.getStatusCode()); // 200
System.out.println(((LinkBusinessMemberResponse) response.getData()).getDetails())// test details
System.out.println(((LinkBusinessMemberResponse) response.getData()).getRole());// business role name
```

#### UnlinkBusinessMemeber

```java
BusinessRole businessRole = ((GetBusinessRolesResponse)api.getBusinessRoles().getData()).get(0);
float ownershipStake = 0.30;
ApiResponse response = api.unlinkBusinessMember("user handle", "user private key",   "business handle", "business private key", businessRole);
```

##### Success Object Response

```java
System.out.println(response.getStatusCode()); // 200
System.out.println(((LinkBusinessOperationResponse) response.getData()).getDetails())// test details
System.out.println(((LinkBusinessOperationResponse) response.getData()).getRole());// business role name
```

#### Get Entity (individual)

```java
ApiResponse response = api.getEntity("user handle", "user private key");
```

##### Success Object Response

```java
System.out.println(response.getStatusCode()); // 200
((GetEntityResponse)response.getData()).getAddresses(); // Get addresses list
((GetEntityResponse)response.getData()).getEmails(); // Get emails list
((GetEntityResponse)response.getData()).getIdentities(); // Get identities list
((GetEntityResponse)response.getData()).getPhones(); // Get phones list
((GetEntityResponse)response.getData()).getMemberships(); // Get memberships list
((GetEntityResponse)response.getData()).getEntity(); // Get entity object
((GetEntityResponse)response.getData()).getEntityType(); // "individual"
```

#### Get Entity (business)

```java
ApiResponse response = api.getEntity("business handle", "business private key");
```

##### Success Object Response

```java
System.out.println(response.getStatusCode()); // 200
((GetEntityResponse)response.getData()).getAddresses(); // Get addresses list
((GetEntityResponse)response.getData()).getEmails(); // Get emails list
((GetEntityResponse)response.getData()).getIdentities(); // Get identities list
((GetEntityResponse)response.getData()).getPhones(); // Get phones list
((GetEntityResponse)response.getData()).getMembers(); // Get members list
((GetEntityResponse)response.getData()).getEntity(); // Get entity object
((GetEntityResponse)response.getData()).getEntityType(); // "business"
```

#### Certify Beneficial Owner

```java
ApiResponse response = api.certifyBeneficialOwner("user handle", "user private key", "business handle", "business private key", "member handle", "certification token");
```

##### Success Object Response

```java
System.out.println(response.getStatusCode()); // 200
System.out.println(((BaseResponse) response.getData()).getMessage()); // Beneficial owner successfully certified.
```

#### Certify Business

```java
ApiResponse response = api.certifyBusiness("user handle", "user private key", "business handle", "business private key");
```

##### Success Object Response

```java
System.out.println(response.getStatusCode()); // 200
System.out.println(((BaseResponse) response.getData()).getMessage()); // Business successfully certified.
```

#### Get Entities

```java
int page = 1;
int perPage = 50;
ApiResponse response = api.getEntities("entity type", page, perPage);
```

##### Success Object Response

```java
System.out.println(response.getStatusCode()); // 200
((GetEntitiesResponse)response.getData()).getEntities() // Entities object
((GetEntitiesResponse)response.getData()).getPagination()// Pagination object
((GetEntitiesResponse)response.getData()).getEntities().getIndividuals() // List of individual entities
((GetEntitiesResponse)response.getData()).getEntities().getBusinesses()// List of business entities
```

#### Get Account Balance

```java
ApiResponse response = api.getAccountBalance("user_handle",
                "user_private_key", "defaultpt");
```

##### Success Object Response

```java
AccountBalanceResponse parsedResponse = (AccountBalanceResponse)response.getData();
System.out.println(response.getStatusCode()); // 200
System.out.println(parsedResponse.getAccountName());
System.out.println(parsedResponse.getMaskedAccountNumber());
```

#### Document Types

```java
// With no pagination
ApiResponse response = api.getDocumentTypes();
// With pagination
PaginationMessage pagination = PaginationMessage.builder()
    .page(1) // Optional. The page of the request
    .perPage(40) // Optional. The amount of results per page
    .build();
ApiResponse response = api.getDocumentTypes(pagination);
```

##### Success Object Response

```java
System.out.println(response.getStatusCode()); // 200
DocumentTypesResponse parsedResponse = (DocumentTypesResponse)response.getData();
System.out.println(parsedResponse.getSuccess()); // true
System.out.println(parsedResponse.getStatus()); // SUCCESS
System.out.println(parsedResponse.getMessage()); // Document type details returned.
System.out.println(parsedResponse.getDocumentTypes()); // List of DocumentType
System.out.println(parsedResponse.getDocumentTypes().get(0).getName()); // Document type name
System.out.println(parsedResponse.getDocumentTypes().get(0).getLabel()); // Document type label
System.out.println(parsedResponse.getDocumentTypes().get(0).getIdentityType()); // Document type identity type
System.out.println(parsedResponse.getPagination().getCurrentPage());
System.out.println(parsedResponse.getPagination().getReturnedCount());
System.out.println(parsedResponse.getPagination().getTotalPages());
System.out.println(parsedResponse.getPagination().getTotalCount());
```

#### Documents

```java
UploadDocumentMessage message = UploadDocumentMessage.builder()
        .userHandle("user_handle") // The user handle
        .userPrivateKey("user_private_key") // The user's private key
        .filePath("/path/to/file") // Full path to the file
        .filename("logo-geko") // File name (without extension)
        .mimeType("image/png") // File mime-type
        .documentType("doc_green_card") // Document type
        .identityType("other") // Identity type
        .name("some file name") // Optional. Descriptive name of the document
        .description("some file description") // Optional. General description of the document
        .build();
ApiResponse response = api.uploadDocument(message);
```

##### Success Object Response

```java
System.out.println(response.getStatusCode()); // 200
DocumentsResponse parsedResponse = (DocumentsResponse) response.getData();
System.out.println(parsedResponse.getSuccess()); // true
System.out.println(parsedResponse.getStatus()); // SUCCESS
System.out.println(parsedResponse.getMessage()); // File uploaded successfully
System.out.println(parsedResponse.getDocumentId());
System.out.println(parsedResponse.getReferenceId());
```

#### List Documents

```java
List<String> docTypes = new ArrayList<String>();
docTypes.add("doc_green_card");
// With no pagination
ListDocumentsMessage message = ListDocumentsMessage.builder()
        .userHandle("user_handle")
        .userPrivateKey("user_private_key")
        .order("asc") // Optional. Sort returned items (usually by creation date). Allowed values: asc (default), desc
        .search("logo") // Optional. Only return documents whose name or filename contains the search value. Partial matches allowed, no wildcards.
        .sortBy("name") // Optional. One of: name or date
        .docTypes(docTypes) // Optional. A list of strings of supported document types
        .startDate(LocalDate.now()) // Optional. Only return documents created on or after this date.
        .endDate(LocalDate.now()) // Optional. Only return documents created before or on this date.
        .build();
ApiResponse response = api.listDocuments(message);
// With pagination
PaginationMessage pagination = PaginationMessage.builder()
    .page(1) // Optional. Page number to retrieve. default: 1
    .perPage(40) // Optional. Number of items per page. default: 20, max: 100
    .build();
ApiResponse response = api.listDocuments(message, pagination);
```

##### Success Object Response

```java
System.out.println(response.getStatusCode()); // 200
ListDocumentsResponse parsedResponse = (ListDocumentsResponse) response.getData();
System.out.println(parsedResponse.getSuccess()); // true
System.out.println(parsedResponse.getStatus()); // SUCCESS
System.out.println(parsedResponse.getPagination().getCurrentPage()); // 1
System.out.println(parsedResponse.getPagination().getReturnedCount()); // 1
System.out.println(parsedResponse.getPagination().getTotalCount()); // 1
System.out.println(parsedResponse.getPagination().getTotalPages()); // 1
System.out.println(parsedResponse.getDocuments().get(0).getUserHandle()); // user_handle
System.out.println(parsedResponse.getDocuments().get(0).getDocumentId()); // some-uuid-code
System.out.println(parsedResponse.getDocuments().get(0).getName()); // logo
System.out.println(parsedResponse.getDocuments().get(0).getFilename()); // logo
System.out.println(parsedResponse.getDocuments().get(0).getHash()); // yourfilehash
System.out.println(parsedResponse.getDocuments().get(0).getType()); // doc_green_card
System.out.println(parsedResponse.getDocuments().get(0).getSize()); // 211341
System.out.println(parsedResponse.getDocuments().get(0).getCreated()); // 2020-08-03T17:09:24.917939
```

#### Get Document

```java
GetDocumentMessage message = GetDocumentMessage.builder()
        .userHandle("user_handle")
        .userPrivateKey("user_private_key")
        .documentId("some-uuid-code")
        .build();
ApiResponse response = api.getDocument(message);
```

##### Success Object Response

```java
System.out.println(response.getStatusCode()); // 200
System.out.println(response.getHeaders().get("Content-Type")); // Document MIME type
System.out.println(response.getHeaders().get("Content-Length")); // Document size in bytes
System.out.println(response.getHeaders().get("Content-Disposition")); // filename=<Document file name>
System.out.println((String) response.getData()); // The file binary data
```

#### Add Email

```java
UserHandleMessage user = UserHandleMessage.builder()
        .userHandle("user_handle")
        .userPrivateKey("user_private_key")
        .build();
EmailMessage message = EmailMessage.builder()
        .email("some_new_email@domain.sila")
        .build();
ApiResponse response = api.addEmail(user, message);
```

##### Success Object Response

```java
System.out.println(response.getStatusCode()); // 200
EmailResponse parsedResponse = (EmailResponse) response.getData();
System.out.println(parsedResponse.getSuccess()); // true
System.out.println(parsedResponse.getStatus()); // SUCCESS
System.out.println(parsedResponse.getMessage()); // Successfully added email
System.out.println(parsedResponse.getEmail().getAddedEpoch());
System.out.println(parsedResponse.getEmail().getModifiedEpoch());
System.out.println(parsedResponse.getEmail().getUuid());
System.out.println(parsedResponse.getEmail().getEmail());
```

#### Add Phone

```java
UserHandleMessage user = UserHandleMessage.builder()
        .userHandle("user_handle")
        .userPrivateKey("user_private_key")
        .build();
PhoneMessage message = PhoneMessage.builder()
        .phone("1234567890")
        .build();
ApiResponse response = api.addPhone(user, message);
```

##### Success Object Response

```java
System.out.println(response.getStatusCode()); // 200
PhoneResponse parsedResponse = (PhoneResponse) response.getData();
System.out.println(parsedResponse.getSuccess()); // true
System.out.println(parsedResponse.getStatus()); // SUCCESS
System.out.println(parsedResponse.getMessage()); // Successfully added phone
System.out.println(parsedResponse.getPhone().getAddedEpoch());
System.out.println(parsedResponse.getPhone().getModifiedEpoch());
System.out.println(parsedResponse.getPhone().getUuid());
System.out.println(parsedResponse.getPhone().getPhone());
```

#### Add Identity

```java
UserHandleMessage user = UserHandleMessage.builder()
        .userHandle("user_handle")
        .userPrivateKey("user_private_key")
        .build();
IdentityMessage message = IdentityMessage.builder()
        .identityAlias("SSN")
        .identityValue("123452222")
        .build();
ApiResponse response = api.addIdentity(user, message);
```

##### Success Object Response

```java
System.out.println(response.getStatusCode()); // 200
IdentityResponse parsedResponse = (IdentityResponse) response.getData();
System.out.println(parsedResponse.getSuccess()); // true
System.out.println(parsedResponse.getStatus()); // SUCCESS
System.out.println(parsedResponse.getMessage()); // Successfully added identity
System.out.println(parsedResponse.getIdentity().getAddedEpoch());
System.out.println(parsedResponse.getIdentity().getModifiedEpoch());
System.out.println(parsedResponse.getIdentity().getUuid());
System.out.println(parsedResponse.getIdentity().getIdentityType());
System.out.println(parsedResponse.getIdentity().getIdentity());
```

#### Add Address

```java
UserHandleMessage user = UserHandleMessage.builder()
        .userHandle("user_handle")
        .userPrivateKey("user_private_key")
        .build();
AddressMessage message = AddressMessage.builder()
        .addressAlias("new address")
        .streetAddress1("324 Songbird Avenue")
        .streetAddress2("Apt. 132") // Optional.
        .city("Portland")
        .state("VA")
        .country("US")
        .postalCode("12345")
        .build();
ApiResponse response = api.addAddress(user, message);
```

##### Success Object Response

```java
System.out.println(response.getStatusCode()); // 200
AddressResponse parsedResponse = (AddressResponse) response.getData();
System.out.println(parsedResponse.getSuccess()); // true
System.out.println(parsedResponse.getStatus()); // SUCCESS
System.out.println(parsedResponse.getMessage()); // Successfully added address
System.out.println(parsedResponse.getAddress().getAddedEpoch());
System.out.println(parsedResponse.getAddress().getModifiedEpoch());
System.out.println(parsedResponse.getAddress().getUuid());
System.out.println(parsedResponse.getAddress().getNickname());
System.out.println(parsedResponse.getAddress().getStreetAddress1());
System.out.println(parsedResponse.getAddress().getStreetAddress2());
System.out.println(parsedResponse.getAddress().getCity());
System.out.println(parsedResponse.getAddress().getState());
System.out.println(parsedResponse.getAddress().getCountry());
System.out.println(parsedResponse.getAddress().getPostalCode());
```

#### Update Email

```java
UserHandleMessage user = UserHandleMessage.builder()
        .userHandle("user_handle")
        .userPrivateKey("user_private_key")
        .build();
EmailMessage message = EmailMessage.builder()
        .uuid("some-uuid-code")
        .email("some_new_email@domain.sila")
        .build();
ApiResponse response = api.updateEmail(user, message);
```

##### Success Object Response

```java
System.out.println(response.getStatusCode()); // 200
EmailResponse parsedResponse = (EmailResponse) response.getData();
System.out.println(parsedResponse.getSuccess()); // true
System.out.println(parsedResponse.getStatus()); // SUCCESS
System.out.println(parsedResponse.getMessage()); // Successfully updated email
System.out.println(parsedResponse.getEmail().getAddedEpoch());
System.out.println(parsedResponse.getEmail().getModifiedEpoch());
System.out.println(parsedResponse.getEmail().getUuid());
System.out.println(parsedResponse.getEmail().getEmail());
```

#### Update Phone

```java
UserHandleMessage user = UserHandleMessage.builder()
        .userHandle("user_handle")
        .userPrivateKey("user_private_key")
        .build();
PhoneMessage message = PhoneMessage.builder()
        .uuid("some-uuid-code")
        .phone("1234567890")
        .build();
ApiResponse response = api.updatePhone(user, message);
```

##### Success Object Response

```java
System.out.println(response.getStatusCode()); // 200
PhoneResponse parsedResponse = (PhoneResponse) response.getData();
System.out.println(parsedResponse.getSuccess()); // true
System.out.println(parsedResponse.getStatus()); // SUCCESS
System.out.println(parsedResponse.getMessage()); // Successfully updated phone
System.out.println(parsedResponse.getPhone().getAddedEpoch());
System.out.println(parsedResponse.getPhone().getModifiedEpoch());
System.out.println(parsedResponse.getPhone().getUuid());
System.out.println(parsedResponse.getPhone().getPhone());
```

#### Update Identity

```java
UserHandleMessage user = UserHandleMessage.builder()
        .userHandle("user_handle")
        .userPrivateKey("user_private_key")
        .build();
IdentityMessage message = IdentityMessage.builder()
        .uuid("some-uuid-code")
        .identityAlias("SSN")
        .identityValue("123452222")
        .build();
ApiResponse response = api.updateIdentity(user, message);
```

##### Success Object Response

```java
System.out.println(response.getStatusCode()); // 200
IdentityResponse parsedResponse = (IdentityResponse) response.getData();
System.out.println(parsedResponse.getSuccess()); // true
System.out.println(parsedResponse.getStatus()); // SUCCESS
System.out.println(parsedResponse.getMessage()); // Successfully updated identity
System.out.println(parsedResponse.getIdentity().getAddedEpoch());
System.out.println(parsedResponse.getIdentity().getModifiedEpoch());
System.out.println(parsedResponse.getIdentity().getUuid());
System.out.println(parsedResponse.getIdentity().getIdentityType());
System.out.println(parsedResponse.getIdentity().getIdentity());
```

#### Update Address

```java
UserHandleMessage user = UserHandleMessage.builder()
        .userHandle("user_handle")
        .userPrivateKey("user_private_key")
        .build();
AddressMessage message = AddressMessage.builder()
        .uuid("some-uuid-code")
        .addressAlias("new address")
        .streetAddress1("324 Songbird Avenue")
        .streetAddress2("Apt. 132") // Optional.
        .city("Portland")
        .state("VA")
        .country("US")
        .postalCode("12345")
        .build();
ApiResponse response = api.updateAddress(user, message);
```

##### Success Object Response

```java
System.out.println(response.getStatusCode()); // 200
AddressResponse parsedResponse = (AddressResponse) response.getData();
System.out.println(parsedResponse.getSuccess()); // true
System.out.println(parsedResponse.getStatus()); // SUCCESS
System.out.println(parsedResponse.getMessage()); // Successfully updated address
System.out.println(parsedResponse.getAddress().getAddedEpoch());
System.out.println(parsedResponse.getAddress().getModifiedEpoch());
System.out.println(parsedResponse.getAddress().getUuid());
System.out.println(parsedResponse.getAddress().getNickname());
System.out.println(parsedResponse.getAddress().getStreetAddress1());
System.out.println(parsedResponse.getAddress().getStreetAddress2());
System.out.println(parsedResponse.getAddress().getCity());
System.out.println(parsedResponse.getAddress().getState());
System.out.println(parsedResponse.getAddress().getCountry());
System.out.println(parsedResponse.getAddress().getPostalCode());
```

#### Update Individual Entity

```java
UserHandleMessage user = UserHandleMessage.builder()
        .userHandle("user_handle")
        .userPrivateKey("user_private_key")
        .build();
IndividualEntityMessage message = IndividualEntityMessage.builder()
        .firstName("NewFirst")
        .lastName("NewLast")
        .entityName("NewFirst NewLast")
        .birthdate(LocalDate.of(1994, 1, 8))
        .build();
ApiResponse response = api.updateEntity(user, message);
```

##### Success Object Response

```java
System.out.println(response.getStatusCode()); // 200
IndividualEntityResponse parsedResponse = (IndividualEntityResponse) response.getData();
System.out.println(parsedResponse.getSuccess()); // true
System.out.println(parsedResponse.getStatus()); // SUCCESS
System.out.println(parsedResponse.getMessage()); // Successfully updated entity
System.out.println(parsedResponse.getUserHandle()); // user_handle
System.out.println(parsedResponse.getEntityType()); // individual
System.out.println(parsedResponse.getEntity().getCreatedEpoch());
System.out.println(parsedResponse.getEntity().getEntityName());
System.out.println(parsedResponse.getEntity().getBirthdate());
System.out.println(parsedResponse.getEntity().getFirstName());
System.out.println(parsedResponse.getEntity().getLastName());
```

#### Update Business Entity

```java
UserHandleMessage user = UserHandleMessage.builder()
        .userHandle("user_handle")
        .userPrivateKey("user_private_key")
        .build();
BusinessEntityMessage message = BusinessEntityMessage.builder()
        .entityName("New Company")
        .birthdate(LocalDate.now())
        .businessType("corporation")
        .naicsCode(721)
        .doingBusinessAs("NC")
        .businessWebsite("https://somedomain.go")
        .build();
ApiResponse response = api.updateEntity(user, message);
```

##### Success Object Response

```java
System.out.println(response.getStatusCode()); // 200
BusinessEntityMessage parsedResponse = (BusinessEntityMessage) response.getData();
System.out.println(parsedResponse.getSuccess()); // true
System.out.println(parsedResponse.getStatus()); // SUCCESS
System.out.println(parsedResponse.getMessage()); // Successfully updated entity
System.out.println(parsedResponse.getUserHandle()); // user_handle
System.out.println(parsedResponse.getEntityType()); // business
System.out.println(parsedResponse.getEntity().getCreatedEpoch());
System.out.println(parsedResponse.getEntity().getEntityName());
System.out.println(parsedResponse.getEntity().getBirthdate());
System.out.println(parsedResponse.getEntity().getBusinessType());
System.out.println(parsedResponse.getEntity().getNaicsCode());
System.out.println(parsedResponse.getEntity().getNaicsCategory());
System.out.println(parsedResponse.getEntity().getNaicsSubcategory());
System.out.println(parsedResponse.getEntity().getBusinessUuid());
System.out.println(parsedResponse.getEntity().getDoingBusinessAs());
System.out.println(parsedResponse.getEntity().getBusinessWebsite());
```
