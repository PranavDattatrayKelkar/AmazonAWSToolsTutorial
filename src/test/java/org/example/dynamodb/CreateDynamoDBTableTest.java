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

class CreateDynamoDBTableTest {


    private DynamoDbClient dynamoDbClient;
    private CreateDynamoDBTable createDynamoDBTable;
    private final String tableName = "test-table";

    @BeforeEach
    void setUp() {
        dynamoDbClient = DynamoDbClient.builder()
                .region(Region.US_WEST_2)
                .credentialsProvider(StaticCredentialsProvider.create(
                        AwsBasicCredentials.create("test", "test")))
                .endpointOverride(URI.create("http://localhost:8000"))
                .build();
        createDynamoDBTable = new CreateDynamoDBTable(tableName, dynamoDbClient);
    }

    @Test
    void testConstructor() {
        assertNotNull(createDynamoDBTable);
    }

    @Test
    void testCreateDDBTableDoesNotThrow() {
        assertDoesNotThrow(() -> {
            createDynamoDBTable.createDDBTable();
        });
    }

    @Test
    void testCreateDDBTableWithChangeLogsDoesNotThrow() {
        assertDoesNotThrow(() -> {
            createDynamoDBTable.createDDBTableWithChangeLogs();
        });
    }
}