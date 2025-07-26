package org.example.storageorchestrator;

import com.github.benmanes.caffeine.cache.Cache;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;

/**
 * Helper class for testing the StorageOrchestrator package
 */
public class TestHelper {
    
    /**
     * Creates a Request object for testing
     */
    public static OrchestratorUtility.Request createRequest(
            String key, 
            String value, 
            Cache<String, String> cacheClient, 
            DynamoDbClient dbClient) {
        
        OrchestratorUtility utility = new OrchestratorUtility();
        return utility.new Request(key, value, cacheClient, dbClient);
    }
}