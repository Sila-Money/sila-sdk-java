package com.silamoney.client.domain;

import com.google.gson.annotations.SerializedName;

/**
 * Class for Registering Wallet Endpoint
 * 
 * @author wzelada
 */
public class Wallet {

    @SerializedName("blockchain_address")
    public String blockChainAddress;
    
    @SerializedName("blockchain_network")
    public String blockChainNetwork;

    @SerializedName("private_key")
    public String privateKey;

    @SerializedName("nickname")
    public String nickname;

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

    public String getBlockChainAddress() {
        return blockChainAddress;
    }

    public String getBlockChainNetwork() {
        return blockChainNetwork;
    }

    public String getNickname() {
        return nickname;
    }

    public String getPrivateKey() {
        return privateKey;
    }
}