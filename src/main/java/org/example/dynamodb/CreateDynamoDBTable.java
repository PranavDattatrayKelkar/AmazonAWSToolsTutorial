package org.example.dynamodb;

import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.model.*;

public class CreateDynamoDBTable {
    private static String tableName;
    private static DynamoDbClient dynamoDbClient;
    public CreateDynamoDBTable(String tableName, DynamoDbClient dbClient) {
        this.tableName = tableName;
        this.dynamoDbClient = dbClient;
    }

    /**
     * creates dynamoDB table with a single attribute ID which also is the primary
     * index for the DDB table. This implementation does not consider secondary
     * indexes.
     *
     */
    public void createDDBTable() {
        try {
            CreateTableRequest clientRequest = CreateTableRequest.builder()
                    .tableName(tableName)
                    .attributeDefinitions(
                            AttributeDefinition.builder()
                                    .attributeName("Id")
                                    .attributeType(ScalarAttributeType.S)
                                    .build()
                    )
                    .keySchema(
                            KeySchemaElement.builder()
                                    .attributeName("Id")
                                    .keyType(KeyType.HASH)
                                    .build()
                    )
                    .provisionedThroughput(
                            ProvisionedThroughput.builder()
                                    .readCapacityUnits(5L) // Setting up RCU
                                    .writeCapacityUnits(5L) // Setting up WCU
                                    .build()
                    )
                    .build();
            dynamoDbClient.createTable(clientRequest);
        } catch (Exception e) {
            System.out.println("Table already exist" + e.getMessage());
        }
        System.out.println("Successfully createdTable " + tableName);
    }

    /**
     * This API creates DynamoDB tables with change logs enabled by using
     * the flag streamEnabled in the builder.
     *
     */
    public void createDDBTableWithChangeLogs() {
        CreateTableRequest request = CreateTableRequest.builder()
                .tableName("LeaderTable")
                .attributeDefinitions(
                        AttributeDefinition.builder().attributeName("id").attributeType(ScalarAttributeType.S).build()
                )
                .keySchema(KeySchemaElement.builder().attributeName("id").keyType(KeyType.HASH).build())
                .streamSpecification(
                        StreamSpecification.builder()
                                .streamEnabled(true)
                                .streamViewType(StreamViewType.NEW_AND_OLD_IMAGES) // Or NEW_IMAGE
                                .build()
                )
                .billingMode(BillingMode.PAY_PER_REQUEST)
                .build();

        dynamoDbClient.createTable(request);
    }
}
