package org.example.encryption;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EncodeDecodeDataWithAESTest {

    private EncodeDecodeDataWithAES aesEncoder;

    @BeforeEach
    void setUp() {
        aesEncoder = new EncodeDecodeDataWithAES();
    }

    @Test
    void testEncryptReturnsNonNullByteArray() throws Exception {
        String input = "test data";
        
        byte[] encrypted = aesEncoder.encrypt(input);
        
        assertNotNull(encrypted);
        assertTrue(encrypted.length > 0);
    }

    @Test
    void testEncryptDoesNotThrow() {
        String input = "test data";
        
        assertDoesNotThrow(() -> {
            aesEncoder.encrypt(input);
        });
    }

    @Test
    void testEncryptWithEmptyStringDoesNotThrow() {
        String input = "";
        
        assertDoesNotThrow(() -> {
            aesEncoder.encrypt(input);
        });
    }

    @Test
    void testDecryptDoesNotThrow() {
        byte[] testData = "test".getBytes();
        
        assertDoesNotThrow(() -> {
            aesEncoder.decrypt(testData);
        });
    }
}