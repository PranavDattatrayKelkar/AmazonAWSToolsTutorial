package org.example.hashing;

import org.example.encryption.EncryptionUtility;
import org.junit.jupiter.api.Test;

import java.security.NoSuchAlgorithmException;

import static org.junit.jupiter.api.Assertions.*;

class encryptionUsingMD5HashingTest {


    @Test
    void testHashMD5ReturnsPage() throws NoSuchAlgorithmException {
        String input = "test";
        
        EncryptionUtility.Page result = encryptionUsingMD5Hashing.hashMD5(input);
        
        assertNotNull(result);
        assertNotNull(EncryptionUtility.Page.encryptedData);
        assertEquals(16, EncryptionUtility.Page.encryptedData.length);
        assertEquals(0, EncryptionUtility.Page.encryptionType);
    }

    @Test
    void testHashMD5ConsistentOutput() throws NoSuchAlgorithmException {
        String input = "test";
        
        EncryptionUtility.Page result1 = encryptionUsingMD5Hashing.hashMD5(input);
        EncryptionUtility.Page result2 = encryptionUsingMD5Hashing.hashMD5(input);
        
        assertArrayEquals(result1.encryptedData, result2.encryptedData);
    }

    @Test
    void testHashMD5to64BitAndModByServerCount() throws NoSuchAlgorithmException {
        String input = "test";
        int numServers = 4;
        
        EncryptionUtility.Page result = encryptionUsingMD5Hashing.hashMD5to64BitAndModByServerCount(input, numServers);
        
        assertNotNull(result);
        assertNotNull(EncryptionUtility.Page.encryptedData);
        assertEquals(16, EncryptionUtility.Page.encryptedData.length);
        assertEquals(numServers, EncryptionUtility.Page.serverSelection);
    }

    @Test
    void testHashMD5WithEmptyString() throws NoSuchAlgorithmException {
        String input = "";
        
        EncryptionUtility.Page result = encryptionUsingMD5Hashing.hashMD5(input);
        
        assertNotNull(result);
        assertEquals(16, EncryptionUtility.Page.encryptedData.length);
    }

    @Test
    void testHashMD5DoesNotThrow() {
        assertDoesNotThrow(() -> {
            encryptionUsingMD5Hashing.hashMD5("test");
        });
    }
}