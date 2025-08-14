package com.silamoney.client.testsutils;

import java.io.IOException;
import java.math.BigInteger;
import java.security.InvalidAlgorithmParameterException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

import com.silamoney.client.domain.*;

import org.web3j.crypto.exception.CipherException;
import org.web3j.crypto.ECKeyPair;
import org.web3j.crypto.Keys;
import org.web3j.crypto.Wallet;
import org.web3j.crypto.WalletFile;

import lombok.Getter;
import lombok.Setter;

public class DefaultConfigurations {

    public static String host = Environments.SilaEnvironment.SANDBOX.getUrl();
     public static String appHandle = "arc_sandbox_test_app01";
     public static String privateKey = "9c17e7b767b8f4a63863caf1619ef3e9967a34b287ce58542f3eb19b5a72f076";
    public static String walletWithStatements = "1220126f-aae1-417c-baee-e664fedb71da";
    public static String ckoPublicKey="pk_sbox_i2uzy5w5nsllogfsc4xdscorcii";
    private static String userHandle;
    @Getter
    @Setter
    private static List<DocumentType> documentTypes;
    @Getter
    @Setter
    private static String documentId;
    @Getter
    @Setter
    private static String emailUuid;
    @Getter
    @Setter
    private static String phoneUuid;
    @Getter
    @Setter
    private static String identityUuid;
    @Getter
    @Setter
    private static String addressUuid;
    @Getter
    @Setter
    private static String verificationUuid;
    @Getter
    @Setter
    private static com.silamoney.client.domain.Wallet newWallet;
    @Getter
    @Setter
    private static com.silamoney.clientrefactored.domain.Wallet newWalletRefactored;
    @Getter
    @Setter
    private static com.silamoney.client.domain.Wallet wallet1;
    @Getter
    @Setter
    private static com.silamoney.client.domain.Wallet wallet2;

    /**
     * @return String
     */
    public static String getUserHandle() {
        userHandle = userHandle == null || userHandle.isBlank() ? "javaSDK-User1-" + new Random().nextInt() : userHandle;
        return userHandle;
    }

    private static String user2Handle;

    /**
     * @return String
     */
    public static String getUser2Handle() {
        user2Handle = user2Handle == null || user2Handle.isBlank() ? "javaSDK-User2-" + new Random().nextInt() : user2Handle;
        return user2Handle;
    }

    private static String user3Handle;

    /**
     * @return String
     */
    public static String getUser3Handle() {
        user3Handle = user3Handle == null || user3Handle.isBlank() ? "javaSDK-User3-" + new Random().nextInt() : user3Handle;
        return user3Handle;
    }

    private static String businessHandle;

    /**
     * @return String
     */
    public static String getBusinessHandle() {
        businessHandle = businessHandle == null || businessHandle.isBlank() ? "javaSDK-User4-" + new Random().nextInt()
                : businessHandle;
        return businessHandle;
    }

    private static String wallet_verification_signature;

    /**
     * @return String
     */
    public static String getWallet_verification_signature() {
        return wallet_verification_signature;
    }

    private static List<BusinessType> businessTypes;

    /**
     * @return List<BusinessType>
     */
    public static List<BusinessType> getBusinessTypes() {
        return businessTypes;
    }

    /**
     * @param pBusinessTypes
     */
    public static void setBusinessTypes(List<BusinessType> pBusinessTypes) {
        businessTypes = pBusinessTypes;
    }

    private static Map<String, ArrayList<NaicsCategoryDescription>> naicsCategories;
    /**
     * Default correct business uuid
     */
    //Sandbox
    public static String correctUuid = "9f280665-629f-45bf-a694-133c86bffd5e";
    /**
     * Default wrong business uuid
     */
    public static String wrongUuid = "6d933c10-fa89-41ab-b443-2e78a7cc8cac";

    /**
     * @return Map<String, ArrayList<NaicsCategoryDescription>>
     */
    public static Map<String, ArrayList<NaicsCategoryDescription>> getNaicsCategories() {
        return naicsCategories;
    }

    /**
     * @param pNaicsCategories
     */
    public static void setNaicsCategories(Map<String, ArrayList<NaicsCategoryDescription>> pNaicsCategories) {
        naicsCategories = pNaicsCategories;
    }

    /**
     * @return NaicsCategoryDescription
     */
    public static NaicsCategoryDescription getDefaultNaicCategoryDescription() {
        for (String key : naicsCategories.keySet()) {
            for (NaicsCategoryDescription categoryDescription : naicsCategories.get(key)) {
                return categoryDescription;
            }
        }

        return null;
    }

    private static List<BusinessRole> businessRoles;

    /**
     * @return List<BusinessRole>
     */
    public static List<BusinessRole> getBusinessRoles() {
        return businessRoles;
    }

    /**
     * @param businessRoles
     */
    public static void setBusinessRoles(List<BusinessRole> businessRoles) {
        DefaultConfigurations.businessRoles = businessRoles;
    }

    /**
     * @param role
     * @return BusinessRole
     */
    public static BusinessRole getBusinessRole(String role) {
        for (BusinessRole businessRole : getBusinessRoles()) {
            if (businessRole.getName().equals(role)) {
                return businessRole;
            }
        }
        return null;
    }

    public static SearchFilters filters = new SearchFilters().showTimelines().setMaxSilaAmount(1300)
            .setMinSilaAmount(1000).setStatuses(new ArrayList<>() {
                private static final long serialVersionUID = -5522339729487411576L;
                {
                    add(SearchFilters.StatusesEnum.PENDING);
                    add(SearchFilters.StatusesEnum.FAILED);
                }
            }).setPage(1).setPerPage(20).setTransactionTypes(new ArrayList<>() {
                private static final long serialVersionUID = -5630390615963025868L;
                {
                    add(SearchFilters.TransactionTypesEnum.ISSUE);
                    add(SearchFilters.TransactionTypesEnum.REDEEM);
                    add(SearchFilters.TransactionTypesEnum.TRANSFER);
                }
            });

    private static String userCryptoAddress;

    /**
     * @return String
     */
    public static String getUserCryptoAddress() {
        if (userCryptoAddress == null || userCryptoAddress.isBlank()) {
            try {

                ECKeyPair ecKeyPair = Keys.createEcKeyPair();
                BigInteger privateKeyInDec = ecKeyPair.getPrivateKey();

                userPrivateKey = privateKeyInDec.toString(16);

                WalletFile aWallet = Wallet.createLight(UUID.randomUUID().toString(), ecKeyPair);
                userCryptoAddress = "0x" + aWallet.getAddress();
            } catch (InvalidAlgorithmParameterException | NoSuchAlgorithmException | NoSuchProviderException
                    | CipherException e) {
                e.printStackTrace();
            }
        }

        return userCryptoAddress;
    }

    private static String user2CryptoAddress;

    /**
     * @return String
     */
    public static String getUser2CryptoAddress() {
        if (user2CryptoAddress == null || user2CryptoAddress.isBlank()) {
            try {

                ECKeyPair ecKeyPair = Keys.createEcKeyPair();
                BigInteger privateKeyInDec = ecKeyPair.getPrivateKey();

                user2PrivateKey = privateKeyInDec.toString(16);

                WalletFile aWallet = Wallet.createLight(UUID.randomUUID().toString(), ecKeyPair);
                user2CryptoAddress = "0x" + aWallet.getAddress();
            } catch (InvalidAlgorithmParameterException | NoSuchAlgorithmException | NoSuchProviderException
                    | CipherException e) {
                e.printStackTrace();
            }
        }

        return user2CryptoAddress;
    }

    private static String user3CryptoAddress;

    /**
     * @return String
     */
    public static String getUser3CryptoAddress() {
        if (user3CryptoAddress == null || user3CryptoAddress.isBlank()) {
            try {

                ECKeyPair ecKeyPair = Keys.createEcKeyPair();
                BigInteger privateKeyInDec = ecKeyPair.getPrivateKey();

                user3PrivateKey = privateKeyInDec.toString(16);

                WalletFile aWallet = Wallet.createLight(UUID.randomUUID().toString(), ecKeyPair);
                user3CryptoAddress = "0x" + aWallet.getAddress();
            } catch (InvalidAlgorithmParameterException | NoSuchAlgorithmException | NoSuchProviderException
                    | CipherException e) {
                e.printStackTrace();
            }
        }

        return user3CryptoAddress;
    }

    private static String user5CryptoAddress;

    /**
     * @return String
     */
    public static String getUser5CryptoAddress() {
        try {
            ECKeyPair ecKeyPair = Keys.createEcKeyPair();
            BigInteger privateKeyInDec = ecKeyPair.getPrivateKey();

            user5PrivateKey = privateKeyInDec.toString(16);

            WalletFile aWallet = Wallet.createLight(UUID.randomUUID().toString(), ecKeyPair);
            user5CryptoAddress = "0x" + aWallet.getAddress();
        } catch (InvalidAlgorithmParameterException | NoSuchAlgorithmException | NoSuchProviderException
                 | CipherException e) {
            e.printStackTrace();
        }

        return user5CryptoAddress;
    }
    public static String getUser5PrivateKey() {
        return user5PrivateKey;
    }

    private static String user5PrivateKey;

    private static String userPrivateKey;

    /**
     * @return String
     */
    public static String getUserPrivateKey() {
        getUserCryptoAddress();
        return userPrivateKey;
    }

    private static String user2PrivateKey;

    /**
     * @return String
     */
    public static String getUser2PrivateKey() {
        getUser2CryptoAddress();
        return user2PrivateKey;
    }

    private static String user3PrivateKey;

    /**
     * @return String
     */
    public static String getUser3PrivateKey() {
        getUser3CryptoAddress();
        return user3PrivateKey;
    }

    private static String businessCryptoAddress;

    /**
     * @return String
     */
    public static String getBusinessCryptoAddress() {
        if (businessCryptoAddress == null || businessCryptoAddress.isBlank()) {
            try {

                ECKeyPair ecKeyPair = Keys.createEcKeyPair();
                BigInteger privateKeyInDec = ecKeyPair.getPrivateKey();

                businessPrivateKey = privateKeyInDec.toString(16);

                WalletFile aWallet = Wallet.createLight(UUID.randomUUID().toString(), ecKeyPair);
                businessCryptoAddress = "0x" + aWallet.getAddress();
            } catch (InvalidAlgorithmParameterException | NoSuchAlgorithmException | NoSuchProviderException
                    | CipherException e) {
                e.printStackTrace();
            }
        }

        return businessCryptoAddress;
    }

    private static String businessPrivateKey;

    /**
     * @return String
     */
    public static String getBusinessPrivateKey() {
        getBusinessCryptoAddress();
        return businessPrivateKey;
    }

    private static String blockchain_address;

    /**
     * @return String
     */
    public static String registerWallet() {
        if (blockchain_address == null || userCryptoAddress.isBlank()) {
            try {

                ECKeyPair ecKeyPair = Keys.createEcKeyPair();
                BigInteger privateKeyInDec = ecKeyPair.getPrivateKey();

                wallet_verification_signature = privateKeyInDec.toString(16);

                WalletFile aWallet = Wallet.createLight(UUID.randomUUID().toString(), ecKeyPair);
                blockchain_address = "0x" + aWallet.getAddress();
            } catch (InvalidAlgorithmParameterException | NoSuchAlgorithmException | NoSuchProviderException
                    | CipherException e) {
                e.printStackTrace();
            }
        }

        return blockchain_address;
    }

    private static String businessOwnerToken;

    /**
     * @return the businessOwnerToken
     */
    public static String getBusinessOwnerToken() {
        return businessOwnerToken;
    }

    /**
     * @param businessOwnerToken the businessOwnerToken to set
     */
    public static void setBusinessOwnerToken(String businessOwnerToken) {
        DefaultConfigurations.businessOwnerToken = businessOwnerToken;
    }

    public static List<VirtualAccount> getVirtualAccounts() {
        return virtualAccounts;
    }

    public static void setVirtualAccounts(List<VirtualAccount> virtualAccounts) {
        DefaultConfigurations.virtualAccounts = virtualAccounts;
    }

    private static List<VirtualAccount> virtualAccounts;

    public static VirtualAccount getVirtualAccounts2() {
        return virtualAccounts2;
    }

    public static void setVirtualAccounts2(VirtualAccount virtualAccounts2) {
        DefaultConfigurations.virtualAccounts2 = virtualAccounts2;
    }

    private static VirtualAccount virtualAccounts2;

    public static List<PaymentMethods> getPaymentMethods() {
        return paymentMethods;
    }

    public static void setPaymentMethods(List<PaymentMethods> paymentMethods) {
        DefaultConfigurations.paymentMethods = paymentMethods;
    }

    private static List<PaymentMethods> paymentMethods;

    public static String transactionIdempotencyUuid = "e1adcdf4-c6bd-463b-9ac7-932474ade222";

    private static String providerToken;

    /**
     * @return String
     */
    public static String getProviderToken() {
        try {
            providerToken = providerToken == null || providerToken.isBlank() ? MXTokenHelper.getProviderToken() : providerToken;
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return providerToken;
    }


    public static String getWalletId() {
        return walletId;
    }

    public static void setWalletId(String walletId) {
        DefaultConfigurations.walletId = walletId;
    }

    private static String walletId;
    @Getter
    @Setter
    private static String statementId;

    @Getter
    @Setter
    private static String ckoToken;

    private static String cardName;

    /**
     * @return String
     */
    public static String getCardName() {
        cardName = cardName == null || cardName.isBlank() ? "cko" + new Random().nextInt(80000) : cardName;
        return cardName;
    }

    private static String user5Handle;

    /**
     * @return String
     */
    public static String getUser5Handle() {
        user5Handle = user5Handle == null || user5Handle.isBlank() ? "javaSDK-User5-" + new Random().nextInt() : user5Handle;
        return user5Handle;
    }
    private static String user6Handle;

    /**
     * @return String
     */
    public static String getUser6Handle() {
        user6Handle = user6Handle == null || user6Handle.isBlank() ? "javaSDK-User6-" + new Random().nextInt() : user6Handle;
        return user6Handle;
    }
    private static String user6PrivateKey;
    private static String user6CryptoAddress;
    public static String getUser6CryptoAddress() {
        try {
            ECKeyPair ecKeyPair = Keys.createEcKeyPair();
            BigInteger privateKeyInDec = ecKeyPair.getPrivateKey();

            user6PrivateKey = privateKeyInDec.toString(16);

            WalletFile aWallet = Wallet.createLight(UUID.randomUUID().toString(), ecKeyPair);
            user6CryptoAddress = "0x" + aWallet.getAddress();
        } catch (InvalidAlgorithmParameterException | NoSuchAlgorithmException | NoSuchProviderException
                 | CipherException e) {
            e.printStackTrace();
        }

        return user6CryptoAddress;
    }
    public static String getUser6PrivateKey() {
        return user6PrivateKey;
    }
}
