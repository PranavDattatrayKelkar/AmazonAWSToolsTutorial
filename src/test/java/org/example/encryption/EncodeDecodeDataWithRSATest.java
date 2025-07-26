package org.example.encryption;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EncodeDecodeDataWithRSATest {


    private EncodeDecodeDataWithRSA rsaEncoder;

    @BeforeEach
    void setUp() {
        rsaEncoder = new EncodeDecodeDataWithRSA();
    }

    @Test
    void testEncryptReturnsNonNullByteArray() throws Exception {
        String input = "test data";
        
        byte[] encrypted = rsaEncoder.encrypt(input);
        
        assertNotNull(encrypted);
        assertTrue(encrypted.length > 0);
    }

    @Test
    void testEncryptDoesNotThrow() {
        String input = "test data";
        
        assertDoesNotThrow(() -> {
            rsaEncoder.encrypt(input);
        });
    }

    @Test
    void testEncryptWithEmptyStringDoesNotThrow() {
        String input = "";
        
        assertDoesNotThrow(() -> {
            rsaEncoder.encrypt(input);
        });
    }

    @Test
    void testDecryptDoesNotThrow() {
        byte[] testData = "test".getBytes();
        
        assertDoesNotThrow(() -> {
            rsaEncoder.decrypt(testData);
        });
    }
}