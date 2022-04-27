package com.silamoney.client.domain;

import com.google.gson.annotations.SerializedName;

import lombok.Getter;

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
}