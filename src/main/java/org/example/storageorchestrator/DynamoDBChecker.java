package org.example.storageorchestrator;

import static org.example.dynamodb.GetDataFromDynamoDBTable.getValueFromTable;

public class DynamoDBChecker extends StorageOrchestrator {
    /**
     * Checks if the key is present in the DynamoDB database
     *
     * @param request
     */
    @Override
    public void handle(OrchestratorUtility.Request request) {
        if(!getValueFromTable(request.key, request.dbClient, "pranav-table").isEmpty()) {
            request.value = getValueFromTable(request.key, request.dbClient, "pranav-table");
            System.out.println("Storage Request: Request served from datastore");
        } else {
            System.out.println("Storage Request: Did not find the request in the storage");
        }
    }
}
