# Sila Java SDK 0.2.8-rc

NOTE: This SDK is a release candidate.

For this SDK you will need to use JDK 11 or later.

### Usage
#### Installation
Add the SDK from the Maven repository.
```xml
<dependency>
    <groupId>com.silamoney</groupId>
    <artifactId>SilamoneySDK</artifactId>
    <version>0.2.8-rc</version>
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
ApiResponse response = api.CheckHandle(userHandle);
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
      String identityNumber (SSN format "AAA-GG-SSSS"), Date birthdate);
ApiResponse response = api.Register(user);
```

##### Success Response Object
```java
System.out.println(response.getStatusCode()); // 200
System.out.println(((BaseResponse)response.getData()).getReference());// Random reference number
System.out.println(((BaseResponse)response.getData()).getStatus()); // SUCCESS
System.out.println(((BaseResponse)response.getData()).getMessage()); // user was successfully registered.
```
#### RequestKYC

Starts KYC verification process on a registered user handle.

Normal Flow:
```java
ApiResponse response = api.RequestKYC(userHandle, userPrivateKey);
```
Custom Flow:
```java
String kycLevel = "CUSTOM_KYC_FLOW_NAME";
ApiResponse response = api.RequestKYC(userHandle, userPrivateKey, kycLevel);
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
ApiResponse response = api.CheckKYC(userHandle, userPrivateKey);
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
ApiResponse response = api.LinkAccount(userHandle, accountName, publicToken, userPrivateKey); 
```
Public token received in the /link/item/create plaid endpoint.

##### Success Response Object
```java
System.out.println(response.getStatusCode()); // 200
System.out.println(((LinkAccountResponse) response.getData()).getStatus()); // SUCCESS
```
#### Plaid Sameday Auth
Generates a Plaid token to complete Plaid's Same Day Microdeposit Authentication
```java
ApiResponse response = api.plaidSameDayAuth(userHandle, accountName, userPrivateKey);
```
##### Success Response Object
```java
System.out.println(response.getStatusCode()); // 200
System.out.println(response.getData());
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
System.out.println(response.getData().getWallets()); // Wallet list
System.out.println(response.getData().getPage()); // Actual page requested
System.out.println(response.getData().getReturnedCount()); // Total wallets returned
System.out.println(response.getData().getTotalCount()); // Total wallets exists
```
#### Get Wallet
Gets details about the user wallet used to generate the usersignature header.
```java
ApiResponse response = api.getWallet(userHandle, userPrivateKey);
```
##### Success Response Object
```java
System.out.println(response.getStatusCode()); // 200
System.out.println(response.getData().getWallet()); // Wallet object
System.out.println(response.getData().getIsWhitelisted()); // Boolean
System.out.println(response.getData().getSilaBalance); // Sila balance
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
ApiResponse response = api.IssueSila(userHandle, amount, accountName, userPrivateKey);
```

##### Success Object Response
```java
System.out.println(response.getStatusCode()); // 200
System.out.println(((BaseResponse) response.getData()).getReference()); // Random reference number
System.out.println(((BaseResponse) response.getData()).getStatus()); // SUCCESS
System.out.println(((BaseResponse) response.getData()).getMessage()); // Transaction submitted to processing queue.
```
 
#### TransferSila
Starts a transfer of the requested amount of SILA to the requested destination handle.
```java
ApiResponse response = api.TransferSila(userHandle, 1000, destination, userPrivateKey);
```
##### Success Object Response
```java
System.out.println(response.getStatusCode()); // 200
System.out.println(((BaseResponse) response.getData()).getReference()); // Random reference number
System.out.println(((BaseResponse) response.getData()).getStatus()); // SUCCESS
System.out.println(((BaseResponse) response.getData()).getMessage()); // Transaction submitted to processing queue.
```
#### RedeemSila
Burns given the amount of SILA at the handle's blockchain address and credits their named bank account in the equivalent monetary amount.
```java
ApiResponse response = api.RedeemSila(userHandle, 1000, accountName, userPrivateKey); 
```
##### Success Object Response
```java
System.out.println(response.getStatusCode()); // 200
System.out.println(((BaseResponse) response.getData()).getReference()); // Random reference number
System.out.println(((BaseResponse) response.getData()).getStatus()); // SUCCESS
System.out.println(((BaseResponse) response.getData()).getMessage()); // Transaction submitted to processing queue.
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
System.out.println((GetTransactionsResponse) response.getData()); // Access response data.
```
#### Get Sila Balance
Gets Sila balance for a given blockchain address.
```java
ApiResponse response = api.SilaBalance(host, address);
```
##### Success Object Response
```java
System.out.println(response.getStatusCode()); // 200
System.out.println(response.getData()); // Sila Tokens.
```
