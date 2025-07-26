package org.example.cache;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.concurrent.TimeUnit;

import static org.example.cache.CaffineCache.getFromCache;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class CaffineCacheTest {

    static Cache<String, String> cache;

    @BeforeAll
    public static void setup() {
        cache = Caffeine.newBuilder()
                .expireAfterWrite(30, TimeUnit.MINUTES)
                .maximumSize(1000)
                .build();
    }

    @Test
    public void getValidKeyFromCacheTest() {
        cache.put("key1", "val1");
        assertEquals("val1",getFromCache("key1", cache));
    }

    @Test
    public void getInvalidKeyFromCacheTest() {
        cache.put("key1", "val1");
        assertNotEquals("val2",getFromCache("key1", cache));
    }
}
