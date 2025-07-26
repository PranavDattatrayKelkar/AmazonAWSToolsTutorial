package org.example.storageorchestrator;

import com.github.benmanes.caffeine.cache.Cache;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;

public class OrchestratorUtility {


    /**
     * Reques wrapper used as an Entity for validations
     * on various storage systems.
     */
    class Request {
        String key;
        String value;
        Cache<String , String> cacheClient;
        DynamoDbClient dbClient;

        public Request(String key, String value) {
            this.key = key;
            this.value = value;
        }

        public Request(String key, String value,
                       Cache<String, String> cacheClient,
                       DynamoDbClient dbClient) {
            this.key = key;
            this.value = value;
            this.cacheClient = cacheClient;
            this.dbClient = dbClient;
        }
    }
}
