package org.example.hashing;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ConsistentHashTest {


    private ConsistentHash<String> consistentHash;
    private List<String> nodes;

    @BeforeEach
    void setUp() {
        nodes = Arrays.asList("server1", "server2", "server3");
        consistentHash = new ConsistentHash<>(100, nodes);
    }

    @Test
    void testConstructor() {
        assertNotNull(consistentHash);
    }

    @Test
    void testGetReturnsNode() {
        String result = consistentHash.get("testKey");
        
        assertNotNull(result);
        assertTrue(nodes.contains(result));
    }

    @Test
    void testGetConsistentMapping() {
        String key = "testKey";
        String result1 = consistentHash.get(key);
        String result2 = consistentHash.get(key);
        
        assertEquals(result1, result2);
    }

    @Test
    void testAddNode() {
        assertDoesNotThrow(() -> {
            consistentHash.add("server4");
        });
    }

    @Test
    void testRemoveNode() {
        assertDoesNotThrow(() -> {
            consistentHash.remove("server1");
        });
    }

    @Test
    void testGetAfterRemove() {
        consistentHash.remove("server1");
        String result = consistentHash.get("testKey");
        
        assertNotNull(result);
        assertNotEquals("server1", result);
    }
}