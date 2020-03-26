package com.silamoney.client.domain;

import com.google.gson.annotations.SerializedName;

/**
 * Class for Registering Wallet Endpoint
 * 
 * @author wzelada
 */
public class Wallet {

    @SerializedName("blockchain_address")
    public final String blockChainAddress;
    
    @SerializedName("blockchain_network")
    public final String blockChainNetwork;

    @SerializedName("nickname")
    public final String nickname;

    public Wallet(String blockChainAddress, String blockChainNetwork, String nickname) {
        this.blockChainAddress = blockChainAddress;
        this.blockChainNetwork = blockChainNetwork;
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
}