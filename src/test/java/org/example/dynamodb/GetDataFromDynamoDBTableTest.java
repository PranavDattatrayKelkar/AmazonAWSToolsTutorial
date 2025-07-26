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

class GetDataFromDynamoDBTableTest {

    private DynamoDbClient dynamoDbClient;

    @BeforeEach
    void setUp() {
        dynamoDbClient = DynamoDbClient.builder()
                .region(Region.US_WEST_2)
                .credentialsProvider(StaticCredentialsProvider.create(
                        AwsBasicCredentials.create("test", "test")))
                .endpointOverride(URI.create("http://localhost:8000"))
                .build();
    }

    @Test
    void testGetValueFromTableDoesNotThrow() {
        String key = "testKey";
        String tableName = "test-table";

        assertDoesNotThrow(() -> {
            String result = GetDataFromDynamoDBTable.getValueFromTable(key, dynamoDbClient, tableName);
            assertNotNull(result);
        });
    }
}