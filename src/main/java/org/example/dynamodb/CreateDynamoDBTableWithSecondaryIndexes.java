package org.example.dynamodb;

import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.model.*;

public class CreateDynamoDBTableWithSecondaryIndexes {
    private static String tableName;
    private static DynamoDbClient dynamoDbClient;
    public CreateDynamoDBTableWithSecondaryIndexes(String tableName, DynamoDbClient dbClient) {
        this.tableName = tableName;
        this.dynamoDbClient = dbClient;
    }

    /**
     * Creates DynamoDB tables with both primary and secondary indexes.
     *
     *
     * @param primaryIndexName This is the primary index for our DynamoDB table
     * @param secondaryIndexName This is an added secondary index for our DynamoDB table to faster query data
     */
    public void createDynamoDBWithSecondaryIndexes(String primaryIndexName, String secondaryIndexName) {
        try {

            CreateTableRequest clientRequest = CreateTableRequest.builder()
                    .tableName(tableName)
                    .attributeDefinitions(
                            AttributeDefinition.builder()
                                    .attributeName(primaryIndexName)
                                    .attributeType(ScalarAttributeType.S)
                                    .build()
                    )
                    .keySchema(
                            KeySchemaElement.builder()
                                    .attributeName(primaryIndexName)
                                    .keyType(KeyType.HASH)
                                    .build()
                    )
                    .globalSecondaryIndexes(
                            GlobalSecondaryIndex.builder()
                                    .indexName(secondaryIndexName)
                                    .keySchema(
                                            KeySchemaElement.builder()
                                                    .attributeName(secondaryIndexName)
                                                    .keyType(KeyType.HASH)
                                                    .build()
                                    )
                                    .projection(Projection.builder()
                                            .projectionType(ProjectionType.ALL) // Include all attributes
                                            .build())
                                    .provisionedThroughput(ProvisionedThroughput.builder()
                                            .readCapacityUnits(5L)
                                            .writeCapacityUnits(5L)
                                            .build())
                                    .build()
                    )
                    .provisionedThroughput(
                            ProvisionedThroughput.builder()
                                    .readCapacityUnits(5L)
                                    .writeCapacityUnits(5L)
                                    .build()
                    )
                    .build();
            dynamoDbClient.createTable(clientRequest);
        } catch (Exception e) {
            System.out.println("Table already exist" + e.getMessage());
        }
        System.out.println("Successfully createdTable " + tableName);

    }
}
