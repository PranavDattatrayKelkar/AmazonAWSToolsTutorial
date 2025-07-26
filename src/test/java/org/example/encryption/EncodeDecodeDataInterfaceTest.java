package org.example.encryption;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EncodeDecodeDataInterfaceTest {


    @Test
    void testAESImplementsInterface() {
        EncodeDecodeDataWithAES aesImpl = new EncodeDecodeDataWithAES();
        
        assertTrue(aesImpl instanceof EncodeDecodeDataInterface);
    }

    @Test
    void testRSAImplementsInterface() {
        EncodeDecodeDataWithRSA rsaImpl = new EncodeDecodeDataWithRSA();
        
        assertTrue(rsaImpl instanceof EncodeDecodeDataInterface);
    }

    @Test
    void testInterfaceMethodsExist() throws Exception {
        EncodeDecodeDataInterface aesImpl = new EncodeDecodeDataWithAES();
        String testData = "test";
        
        assertDoesNotThrow(() -> {
            byte[] encrypted = aesImpl.encrypt(testData);
            assertNotNull(encrypted);
        });
        
        assertDoesNotThrow(() -> {
            aesImpl.decrypt("test".getBytes());
        });
    }
}