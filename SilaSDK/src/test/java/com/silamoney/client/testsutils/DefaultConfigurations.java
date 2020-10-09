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

import com.silamoney.client.domain.BusinessRole;
import com.silamoney.client.domain.BusinessType;
import com.silamoney.client.domain.DocumentType;
import com.silamoney.client.domain.Environments;
import com.silamoney.client.domain.NaicsCategoryDescription;
import com.silamoney.client.domain.SearchFilters;

import org.web3j.crypto.CipherException;
import org.web3j.crypto.ECKeyPair;
import org.web3j.crypto.Keys;
import org.web3j.crypto.Wallet;
import org.web3j.crypto.WalletFile;

import lombok.Getter;
import lombok.Setter;

public class DefaultConfigurations {

    public static String host = Environments.SilaEnvironment.SANDBOX.getUrl();
    public static String appHandle = "digital_geko_e2e.silamoney.eth";
    public static String privateKey = "e60a5c57130f4e82782cbdb498943f31fe8f92ab96daac2cc13cbbbf9c0b4d9e";

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
    private static com.silamoney.client.domain.Wallet newWallet;

    /**
     * @return String
     */
    public static String getUserHandle() {
        userHandle = userHandle == null || userHandle.isBlank() ? "javaSDK-" + new Random().nextInt() : userHandle;
        return userHandle;
    }

    private static String user2Handle;

    /**
     * @return String
     */
    public static String getUser2Handle() {
        user2Handle = user2Handle == null || user2Handle.isBlank() ? "javaSDK-" + new Random().nextInt() : user2Handle;
        return user2Handle;
    }

    private static String businessHandle;

    /**
     * @return String
     */
    public static String getBusinessHandle() {
        businessHandle = businessHandle == null || businessHandle.isBlank() ? "javaSDK-" + new Random().nextInt()
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
                    // add(SearchFilters.StatusesEnum.SUCCESSFUL);
                    add(SearchFilters.StatusesEnum.FAILED);
                    // add(SearchFilters.StatusesEnum.COMPLETE);
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
                // System.out.println("wallet_verification_signature >>> " +
                // wallet_verification_signature);
            } catch (InvalidAlgorithmParameterException | NoSuchAlgorithmException | NoSuchProviderException
                    | CipherException e) {
                e.printStackTrace();
            }
        }

        return blockchain_address;
    }

    private static String plaidToken;

    /**
     * @return String
     */
    public static String getPlaidToken() {
        try {
            plaidToken = plaidToken == null || plaidToken.isBlank() ? PlaidTokenHelper.getPlaidToken() : plaidToken;
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return plaidToken;
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

}
