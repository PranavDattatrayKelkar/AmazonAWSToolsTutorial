package org.example.storageorchestrator;

import static org.example.cache.CaffineCache.getFromCache;

public class CacheChecker extends StorageOrchestrator {
    /**
     * Checks if the key is present in the cache. If not it sends the request to the next
     * handler in the chain which is the DynamoDB database.
     *
     * @param request
     */
    @Override
    public void handle(OrchestratorUtility.Request request) {
        if(!getFromCache(request.key, request.cacheClient).isEmpty()) {
            request.value = getFromCache(request.key, request.cacheClient);
        } else {
            System.out.println("Not a Chached request: checking in the datastore");
            forward(request);
        }
    }
}
