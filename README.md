# Sila Java SDK

NOTE: This SDK is beta.

For this SDK you will need to use JDK 11 or later.

### Usage
#### Installation
Add the SDK from the Maven repository.
```java
<dependency>
    <groupId>com.silamoney</groupId>
    <artifactId>SilamoneySDK</artifactId>
    <version>0.1.0-rc</version>
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
User user = new User(String userHandle, String firstName, String
lastName, String streetAddress1, @Nullable String streetAddress2,
      String city, String state (2 characters), String postalCode (5 or
9 digit format), String phone, String email, String cryptoAddress,
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
```java
ApiResponse<object> response = api.RequestKYC(userHandle,
userPrivateKey);
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
ApiResponse<object> response = api.CheckKYC(userHandle, userPrivateKey);
```

##### Success Response Object
```java
Console.WriteLine(response.getStatusCode()); // 200
Console.WriteLine(((BaseResponse)response.getData()).getReference()); // Random reference number
Console.WriteLine(((BaseResponse)response.getData()).getStatus()); // SUCCESS
Console.WriteLine(((BaseResponse)response.getData()).getMessage()); // user has passed ID verification!
```
#### LinkAccount
Uses a provided Plaid public token to link a bank account to a verified entity.
```java
ApiResponse response = api.LinkAccount(userHandle, accountName,
publicToken, userPrivateKey);
```
Public token received in the /link/item/create plaid endpoint.

##### Success Response Object
```java
System.out.println(response.getStatusCode()); // 200
System.out.println(((LinkAccountResponse)
response.getData()).getStatus()); // SUCCESS
```
#### GetAccounts
Gets basic bank account names linked to user handle.
```java
ApiResponse response = api.GetAccounts(userHandle, userPrivateKey);
```
##### Success Object Response
```java
System.out.println(response.getStatusCode()); // 200
System.out.println(((List<Account>)
response.getData()).get(0).accountName); // Account name
System.out.println(((List<Account>)
response.getData()).get(0).accountNumber); // Account Number
System.out.println(((List<Account>)
response.getData()).get(0).accountStatus); // Account Status
System.out.println(((List<Account>)
response.getData()).get(0).accountType); // Account Type
```
#### IssueSila
Debits a specified account and issues tokens to the address belonging to the requested handle.

##### Success Object Response
```java
ApiResponse response = api.IssueSila(userHandle, amount, accountName,
userPrivateKey);
    System.out.println(response.getStatusCode()); // 200
System.out.println(((BaseResponse) response.getData()).getReference()); // Random reference number
System.out.println(((BaseResponse) response.getData()).getStatus()); // SUCCESS
System.out.println(((BaseResponse) response.getData()).getMessage()); // Transaction submitted to processing queue.
```
 
#### TransferSila
Starts a transfer of the requested amount of SILA to the requested destination handle.
```java
ApiResponse response = api.TransferSila(userHandle, 1000, destination,
userPrivateKey);
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
ApiResponse response = api.RedeemSila(userHandle, 1000, accountName,
userPrivateKey);
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
ApiResponse response = api.GetTransactions(userHandle, filters,
userPrivateKey);
```
 
##### Success Object Response
```java
System.out.println(response.getStatusCode()); // 200
((GetTransactionsResponse) response.getData()) // Access response data. 
