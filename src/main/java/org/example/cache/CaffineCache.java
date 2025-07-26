package org.example.cache;

import com.github.benmanes.caffeine.cache.Cache;

public class CaffineCache {
    /**
     * This API fetches a value for a key from cache.
     *
     * @param key : Key to put in the cache for faster reads
     * @param cache : Cache instance to add key to the cache
     * @return value that maps to the key in the cache
     */
    public static String getFromCache(String key, Cache<String, String> cache) {
        try {
            String value = cache.getIfPresent(key);
            if(value != null) {
                return value;
            }
        } catch (Exception e) {
            System.out.println("Invalid key");
        }
        return "";
    }
}
