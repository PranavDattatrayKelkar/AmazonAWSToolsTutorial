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

class CreateDynamoDBTableWithSecondaryIndexesTest {


    private DynamoDbClient dynamoDbClient;
    private CreateDynamoDBTableWithSecondaryIndexes createTableWithIndexes;
    private final String tableName = "test-table";

    @BeforeEach
    void setUp() {
        dynamoDbClient = DynamoDbClient.builder()
                .region(Region.US_WEST_2)
                .credentialsProvider(StaticCredentialsProvider.create(
                        AwsBasicCredentials.create("test", "test")))
                .endpointOverride(URI.create("http://localhost:8000"))
                .build();
        createTableWithIndexes = new CreateDynamoDBTableWithSecondaryIndexes(tableName, dynamoDbClient);
    }

    @Test
    void testConstructor() {
        assertNotNull(createTableWithIndexes);
    }

    @Test
    void testCreateDynamoDBWithSecondaryIndexesDoesNotThrow() {
        String primaryIndex = "primaryId";
        String secondaryIndex = "secondaryId";

        assertDoesNotThrow(() -> {
            createTableWithIndexes.createDynamoDBWithSecondaryIndexes(primaryIndex, secondaryIndex);
        });
    }
}