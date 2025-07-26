package org.example.dynamodb;

import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.model.AttributeValue;
import software.amazon.awssdk.services.dynamodb.model.GetItemRequest;
import software.amazon.awssdk.services.dynamodb.model.GetItemResponse;

import java.util.Map;

public class GetDataFromDynamoDBTable {

    public static String getValueFromTable(String key, DynamoDbClient dbClient, String tableName) {
        GetItemRequest itemRequest = GetItemRequest.builder()
                .tableName(tableName)
                .key(Map.of(key, AttributeValue.fromS("Id")))
                .build();
        GetItemResponse value = dbClient.getItem(itemRequest);
        return value.toString();
    }
}
