package com.silamoney.client.security;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.bouncycastle.util.encoders.Hex;
import org.web3j.crypto.ECKeyPair;
import org.web3j.crypto.Sign;
import org.web3j.utils.Numeric;

/**
 * Class used to sign the messages.
 *
 * @author loren
 */
public class EcdsaUtil {

    private EcdsaUtil() {

    }

    /**
     * Hash and signs the received message with the received private key.
     *
     * @param message
     * @param privateKey
     * @return signature.
     */
    public static String sign(String message, String privateKey) {
        var msg = Sign.signMessage(message.getBytes(), ECKeyPair.create(new BigInteger(privateKey, 16)));

        return Numeric.toHexString(msg.getR()).substring(2).concat(Numeric.toHexString(msg.getS()).substring(2))
                .concat(Numeric.toHexString(msg.getV()).substring(2));
    }

    public static String hashFile(String filePath) throws FileNotFoundException, IOException, NoSuchAlgorithmException {
        byte[] buffer = new byte[8192];
        int count;
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        try (BufferedInputStream bis = new BufferedInputStream(new FileInputStream(filePath))) {
            while ((count = bis.read(buffer)) > 0) {
                digest.update(buffer, 0, count);
            }
        }

        byte[] hash = digest.digest();
        return new String(Hex.encode(hash));
    }
}
