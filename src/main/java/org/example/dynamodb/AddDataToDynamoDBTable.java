package org.example.dynamodb;

import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.model.AttributeValue;
import software.amazon.awssdk.services.dynamodb.model.PutItemRequest;

import java.util.HashMap;
import java.util.Map;

public class AddDataToDynamoDBTable {
    private static String tableName;
    private static DynamoDbClient dynamoDbClient;
    public AddDataToDynamoDBTable(String tableName, DynamoDbClient dbClient) {
        this.tableName = tableName;
        this.dynamoDbClient = dbClient;
    }

    /**
     * Adds a String, key-value pair to the DDB table.
     *
     * @param keyName Key to add to the bucket
     * @param valueName Value to add to the key
     */
    public void addDataAsStringToDynamoDBTable(String keyName, String valueName) {
        Map<String, AttributeValue> insertMap = new HashMap<>() {{
            put(keyName, AttributeValue.builder().s(valueName).build());
        }};
        PutItemRequest itemRequest = PutItemRequest.builder()
                .tableName(tableName)
                .item(insertMap)
                .build();
        dynamoDbClient.putItem(itemRequest);
    }

    /**
     * Adds a map request as an entry in a DDB table.
     *
     *
     * @param requestMap requests data to add to DynamoDB table
     */
    public void addDataAsMapToDynamoDBTable(Map<String, AttributeValue> requestMap) {
        PutItemRequest itemRequest = PutItemRequest.builder()
                .tableName(tableName)
                .item(requestMap)
                .build();
        dynamoDbClient.putItem(itemRequest);
    }
}
