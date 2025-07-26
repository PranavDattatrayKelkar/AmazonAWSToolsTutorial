package org.example.dynamodb;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;

import java.net.URI;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class QueryDynamoDBClientTest {

    private DynamoDbClient dynamoDbClient;
    private QueryDynamoDBClient queryDynamoDBClient;

    @BeforeEach
    void setUp() {
        dynamoDbClient = DynamoDbClient.builder()
                .region(Region.US_WEST_2)
                .credentialsProvider(StaticCredentialsProvider.create(
                        AwsBasicCredentials.create("test", "test")))
                .endpointOverride(URI.create("http://localhost:8000"))
                .build();
        queryDynamoDBClient = new QueryDynamoDBClient(dynamoDbClient);
    }

    @Test
    void testConstructor() {
        assertNotNull(queryDynamoDBClient);
    }

    @Test
    void testQueryDynamoDBTableDoesNotThrow() {
        String tableName = "test-table";
        String index = "test-index";
        String keyCondition = "testCondition";
        String attributeName = "testAttribute";

        assertDoesNotThrow(() -> {
            queryDynamoDBClient.queryDynamoDBTable(tableName, index, keyCondition, attributeName);
        });
    }
}