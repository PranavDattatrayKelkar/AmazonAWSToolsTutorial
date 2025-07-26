package org.example.dynamodb;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.model.AttributeValue;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class AddDataToDynamoDBTableTest {

    private DynamoDbClient dynamoDbClient;
    private AddDataToDynamoDBTable addDataToDynamoDBTable;
    private final String tableName = "test-table";

    @BeforeEach
    void setUp() {
        dynamoDbClient = DynamoDbClient.builder()
                .region(Region.US_WEST_2)
                .credentialsProvider(StaticCredentialsProvider.create(
                        AwsBasicCredentials.create("test", "test")))
                .endpointOverride(URI.create("http://localhost:8000"))
                .build();
        addDataToDynamoDBTable = new AddDataToDynamoDBTable(tableName, dynamoDbClient);
    }

    @Test
    void testConstructor() {
        assertNotNull(addDataToDynamoDBTable);
    }

    @Test
    void testAddDataAsStringToDynamoDBTableDoesNotThrow() {
        String keyName = "testKey";
        String valueName = "testValue";

        assertDoesNotThrow(() -> {
            addDataToDynamoDBTable.addDataAsStringToDynamoDBTable(keyName, valueName);
        });
    }

    @Test
    void testAddDataAsMapToDynamoDBTableDoesNotThrow() {
        Map<String, AttributeValue> requestMap = new HashMap<>();
        requestMap.put("id", AttributeValue.builder().s("123").build());
        requestMap.put("name", AttributeValue.builder().s("test").build());

        assertDoesNotThrow(() -> {
            addDataToDynamoDBTable.addDataAsMapToDynamoDBTable(requestMap);
        });
    }
}