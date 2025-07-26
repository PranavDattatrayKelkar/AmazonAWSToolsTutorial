package org.example.dynamodb;

import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.model.AttributeValue;
import software.amazon.awssdk.services.dynamodb.model.QueryRequest;
import software.amazon.awssdk.services.dynamodb.model.QueryResponse;

import java.util.Map;

public class QueryDynamoDBClient {
    public DynamoDbClient dbClient;
    public QueryDynamoDBClient(DynamoDbClient dbClient) {
        this.dbClient = dbClient;
    }

    /**
     * API to query DynamoDB table using a queryIndex.
     *
     *
     * @param tableName Name of the table to query
     * @param index Name of the index to query
     * @param keyCondition Name of the key to query
     * @param attributeName Name of the attribute to query
     * @return response for the query
     */
    public QueryResponse queryDynamoDBTable(String tableName, String index, String keyCondition,
                                            String attributeName)
    {
        QueryRequest queryRequest = QueryRequest.builder()
                .tableName(tableName)
                .indexName(index)
                .keyConditionExpression("Id = :" + keyCondition)
                .expressionAttributeValues(Map.of(
                    ":" + keyCondition, AttributeValue.builder().s(attributeName).build()
                ))
                .build();
        return this.dbClient.query(queryRequest);
    }
}
