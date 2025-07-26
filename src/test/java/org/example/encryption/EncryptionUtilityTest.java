package org.example.encryption;

import org.junit.jupiter.api.Test;

import javax.crypto.SecretKey;
import java.security.KeyPair;

import static org.junit.jupiter.api.Assertions.*;

class EncryptionUtilityTest {

    @Test
    void testGenerateKeyAESReturnsValidKey() throws Exception {
        SecretKey key = EncryptionUtility.generateKeyAES();
        
        assertNotNull(key);
        assertEquals("AES", key.getAlgorithm());
    }

    @Test
    void testGenerateKeyRSAReturnsValidKeyPair() throws Exception {
        KeyPair keyPair = EncryptionUtility.generateKeyRSA();
        
        assertNotNull(keyPair);
        assertNotNull(keyPair.getPublic());
        assertNotNull(keyPair.getPrivate());
        assertEquals("RSA", keyPair.getPublic().getAlgorithm());
        assertEquals("RSA", keyPair.getPrivate().getAlgorithm());
    }

    @Test
    void testPageConstructor() {
        byte[] testData = "test".getBytes();
        EncryptionUtility.Page page = new EncryptionUtility.Page(1, testData, 2);
        
        assertEquals(1, EncryptionUtility.Page.encryptionType);
        assertArrayEquals(testData, EncryptionUtility.Page.encryptedData);
        assertEquals(2, EncryptionUtility.Page.serverSelection);
    }

    @Test
    void testPageStaticFields() {
        byte[] testData = "test".getBytes();
        new EncryptionUtility.Page(5, testData, 10);
        
        assertEquals(5, EncryptionUtility.Page.encryptionType);
        assertArrayEquals(testData, EncryptionUtility.Page.encryptedData);
        assertEquals(10, EncryptionUtility.Page.serverSelection);
    }
}