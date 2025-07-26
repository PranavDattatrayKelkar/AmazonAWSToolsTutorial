package org.example.dynamodb;

import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.model.AttributeValue;
import software.amazon.awssdk.services.dynamodb.model.GetItemRequest;
import software.amazon.awssdk.services.dynamodb.model.GetItemResponse;

import java.util.Map;

public class GetDataFromDynamoDBTable {

    /**
     * This API helps to fetch data from a DynamoDB table
     *
     *
     * @param key - the key for which the value needs to be retrieved
     * @param dbClient - the DynamoDB client instance
     * @param tableName DynamoDB table name to fetch data from
     * @return returned value for the key
     */
    public static String getValueFromTable(String key, DynamoDbClient dbClient, String tableName) {
        GetItemRequest itemRequest = GetItemRequest.builder()
                .tableName(tableName)
                .key(Map.of(key, AttributeValue.fromS("Id")))
                .build();
        GetItemResponse value = dbClient.getItem(itemRequest);
        return value.toString();
    }
}
