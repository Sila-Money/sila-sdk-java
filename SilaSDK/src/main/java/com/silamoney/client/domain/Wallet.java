package com.silamoney.client.domain;

import com.google.gson.annotations.SerializedName;

import lombok.Getter;
import lombok.Setter;

/**
 * Class for Registering Wallet Endpoint
 * 
 * @author wzelada
 */
public class Wallet {

    @SerializedName("blockchain_address")
    @Getter
    public String blockChainAddress;
    
    @SerializedName("blockchain_network")
    @Getter
    public String blockChainNetwork;

    @SerializedName("private_key")
    @Getter
    public String privateKey;

    @SerializedName("nickname")
    @Getter
    public String nickname;

    @SerializedName("default")
    @Getter
    public boolean defaultWallet;

    @Getter
    public boolean frozen;

    @SerializedName("wallet_id")
    @Getter
    public String walletId;

    @SerializedName("payment_instrument_id")
    @Getter
    public String paymentInstrumentId;

    @SerializedName("wallet_address")
    @Getter
    public String walletAddress;

    /**
     * boolean field used for the statements enabled.
     */
    @Getter
    @Setter
    @SerializedName("statements_enabled")
    public boolean statementsEnabled;

    @Getter
    @SerializedName("remote_account_details")
    private RemoteAccountDetails remoteAccountDetails;

    public Wallet(String blockChainAddress, String blockChainNetwork, String nickname) {
        this.blockChainAddress = blockChainAddress;
        this.blockChainNetwork = blockChainNetwork;
        this.nickname = nickname;
    }

    public Wallet(String blockChainAddress, String privateKey, String blockChainNetwork, String nickname) {
        this.blockChainAddress = blockChainAddress;
        this.blockChainNetwork = blockChainNetwork;
        this.privateKey = privateKey;
        this.nickname = nickname;
    }

    public Wallet(String blockChainAddress, String privateKey, String blockChainNetwork, String nickname,boolean defaultWallet) {
        this.blockChainAddress = blockChainAddress;
        this.blockChainNetwork = blockChainNetwork;
        this.privateKey = privateKey;
        this.nickname = nickname;
        this.defaultWallet=defaultWallet;
    }

    public Wallet(String blockChainAddress, String privateKey, String blockChainNetwork, String nickname, boolean defaultWallet, boolean frozen) {
        this.blockChainAddress = blockChainAddress;
        this.blockChainNetwork = blockChainNetwork;
        this.privateKey = privateKey;
        this.nickname = nickname;
        this.defaultWallet = defaultWallet;
        this.frozen = frozen;
    }
    public Wallet(String blockChainAddress, String privateKey, String blockChainNetwork, String nickname, boolean defaultWallet, boolean frozen, String walletId) {
        this.blockChainAddress = blockChainAddress;
        this.blockChainNetwork = blockChainNetwork;
        this.privateKey = privateKey;
        this.nickname = nickname;
        this.defaultWallet = defaultWallet;
        this.frozen = frozen;
        this.walletId = walletId;
    }
    public Wallet(String blockChainAddress, String privateKey, String blockChainNetwork, String nickname, boolean defaultWallet, boolean frozen, String walletId, String paymentInstrumentId) {
        this.blockChainAddress = blockChainAddress;
        this.blockChainNetwork = blockChainNetwork;
        this.privateKey = privateKey;
        this.nickname = nickname;
        this.defaultWallet = defaultWallet;
        this.frozen = frozen;
        this.walletId = walletId;
        this.paymentInstrumentId = paymentInstrumentId;
    }
    public Wallet(String blockChainAddress, String privateKey, String blockChainNetwork, String nickname, boolean defaultWallet, boolean frozen, String walletId, String paymentInstrumentId, boolean statementsEnabled) {
        this.blockChainAddress = blockChainAddress;
        this.blockChainNetwork = blockChainNetwork;
        this.privateKey = privateKey;
        this.nickname = nickname;
        this.defaultWallet = defaultWallet;
        this.frozen = frozen;
        this.walletId = walletId;
        this.paymentInstrumentId = paymentInstrumentId;
        this.statementsEnabled = statementsEnabled;
    }
    public Wallet(String blockChainAddress, String privateKey, String blockChainNetwork, String nickname, boolean defaultWallet, boolean frozen, String walletId, String paymentInstrumentId, boolean statementsEnabled, RemoteAccountDetails remoteAccountDetails) {
        this.blockChainAddress = blockChainAddress;
        this.blockChainNetwork = blockChainNetwork;
        this.privateKey = privateKey;
        this.nickname = nickname;
        this.defaultWallet = defaultWallet;
        this.frozen = frozen;
        this.walletId = walletId;
        this.paymentInstrumentId = paymentInstrumentId;
        this.statementsEnabled = statementsEnabled;
        this.remoteAccountDetails = remoteAccountDetails;
    }
}
