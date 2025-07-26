package org.example.hashing;

import org.example.encryption.EncryptionUtility;
import org.junit.jupiter.api.Test;

import java.security.NoSuchAlgorithmException;

import static org.junit.jupiter.api.Assertions.*;

class encryptionUsingSHA256HashingTest {

    @Test
    void testHashSHA256ReturnsPage() throws NoSuchAlgorithmException {
        String input = "test";
        
        EncryptionUtility.Page result = encryptionUsingSHA256Hashing.hashSHA256(input);
        
        assertNotNull(result);
        assertNotNull(EncryptionUtility.Page.encryptedData);
        assertEquals(32, EncryptionUtility.Page.encryptedData.length);
        assertEquals(1, EncryptionUtility.Page.encryptionType);
    }

    @Test
    void testHashSHA256ConsistentOutput() throws NoSuchAlgorithmException {
        String input = "test";
        
        EncryptionUtility.Page result1 = encryptionUsingSHA256Hashing.hashSHA256(input);
        EncryptionUtility.Page result2 = encryptionUsingSHA256Hashing.hashSHA256(input);
        
        assertArrayEquals(result1.encryptedData, result2.encryptedData);
    }

    @Test
    void testHashSHA256WithEmptyString() throws NoSuchAlgorithmException {
        String input = "";
        
        EncryptionUtility.Page result = encryptionUsingSHA256Hashing.hashSHA256(input);
        
        assertNotNull(result);
        assertEquals(32, EncryptionUtility.Page.encryptedData.length);
    }

    @Test
    void testHashSHA256ServerSelection() throws NoSuchAlgorithmException {
        String input = "test";
        
        EncryptionUtility.Page result = encryptionUsingSHA256Hashing.hashSHA256(input);
        
        assertEquals(input.length() % 4, EncryptionUtility.Page.serverSelection);
    }

    @Test
    void testHashSHA256DoesNotThrow() {
        assertDoesNotThrow(() -> {
            encryptionUsingSHA256Hashing.hashSHA256("test");
        });
    }
}