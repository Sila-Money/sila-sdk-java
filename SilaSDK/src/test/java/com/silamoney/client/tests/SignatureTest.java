/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.silamoney.client.tests;

import com.silamoney.client.security.EcdsaUtil;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author loren
 */
public class SignatureTest {

    @Test
    public void Signatures() {
        String privateKey = "badba7368134dcd61c60f9b56979c09196d03f5891a20c155"
                + "7b1afac0202a97c";

        //<editor-fold defaultstate="collapsed" desc="Sila">
        String message = "Sila";
        String expectedSignature = "ea3706a8d2b4c627f847c0c6bfcd59f001021d790f"
                + "06924ff395e9faecb510c53c09274b70cc1d29bde630d277096d570ee79"
                + "83455344915d19085cc13288b421b";

        assertEquals(expectedSignature, EcdsaUtil.sign(message, privateKey));
        //</editor-fold>

        //<editor-fold defaultstate="collapsed" desc="test">
        message = "test";
        expectedSignature = "f9978f3af681d3de06b3bcf5acf2181b5ebf54e0110f1d9d7"
                + "73d691ca2b42bdc39bf478d9ea8287bd15369fa3fd25c09b8c3c02bdbaf"
                + "d19f2aad043e350a037c1b";

        assertEquals(expectedSignature, EcdsaUtil.sign(message, privateKey));
        //</editor-fold>

        //<editor-fold defaultstate="collapsed" desc="{"test":"message"}">
        message = "{\"test\":\"message\"}";
        expectedSignature = "835e9235dcdc03ed8928df5ace375bc70ea6f41699cd861b8"
                + "801c9c617b4f2b658ff8e2cda47ea84401cab8019e5bb9daf3c0af2e7d2"
                + "ab96cba6966a75e017171b";

        assertEquals(expectedSignature, EcdsaUtil.sign(message, privateKey));
        //</editor-fold>

        //<editor-fold defaultstate="collapsed" desc="{"test": "message"}">
        message = "{\"test\": \"message\"}";
        expectedSignature = "2de2f5d3f778e485f234956679373b9730b717c33e628651c"
                + "3371e7eb31c4a27738af1a3bf85472a2a7dfc0628ddd21f8611ff0e170e"
                + "bd24003c2a34b2760d5c1c";

        assertEquals(expectedSignature, EcdsaUtil.sign(message, privateKey));
        //</editor-fold>
    }
}
