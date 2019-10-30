package com.silamoney.client.security;

import java.math.BigInteger;
import org.web3j.crypto.ECKeyPair;
import org.web3j.crypto.Sign;
import org.web3j.utils.Numeric;

/**
 * Class used to sign the messages.
 *
 * @author loren
 */
public class EcdsaUtil {
    
    private EcdsaUtil(){
        
    }

    /**
     * Hash and signs the received message with the received private key.
     *
     * @param message
     * @param privateKey
     * @return signature.
     */
    public static String sign(String message, String privateKey) {
        var msg = Sign.signMessage(message.getBytes(),
                ECKeyPair.create(new BigInteger(privateKey, 16)));

        return Numeric.toHexString(msg.getR()).substring(2)
                .concat(Numeric.toHexString(msg.getS()).substring(2))
                .concat(Numeric.toHexString(msg.getV()).substring(2));
    }
}
