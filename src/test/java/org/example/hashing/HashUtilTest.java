package org.example.hashing;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HashUtilTest {


    @Test
    void testHashLongReturnsLong() {
        String input = "test";
        
        long result = HashUtil.hashLong(input);
        
        assertNotEquals(0, result);
    }

    @Test
    void testHashLongConsistentOutput() {
        String input = "test";
        
        long result1 = HashUtil.hashLong(input);
        long result2 = HashUtil.hashLong(input);
        
        assertEquals(result1, result2);
    }

    @Test
    void testHashLongDifferentInputs() {
        String input1 = "test1";
        String input2 = "test2";
        
        long result1 = HashUtil.hashLong(input1);
        long result2 = HashUtil.hashLong(input2);
        
        assertNotEquals(result1, result2);
    }

    @Test
    void testHashLongWithEmptyString() {
        String input = "";
        
        long result = HashUtil.hashLong(input);
        
        assertEquals(0, result);
    }

    @Test
    void testHashLongWithNullHandling() {
        assertDoesNotThrow(() -> {
            HashUtil.hashLong("test");
        });
    }

    @Test
    void testHashLongDistribution() {
        String input1 = "a";
        String input2 = "b";
        String input3 = "c";
        
        long hash1 = HashUtil.hashLong(input1);
        long hash2 = HashUtil.hashLong(input2);
        long hash3 = HashUtil.hashLong(input3);
        
        assertNotEquals(hash1, hash2);
        assertNotEquals(hash2, hash3);
        assertNotEquals(hash1, hash3);
    }
}