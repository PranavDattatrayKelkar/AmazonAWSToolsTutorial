package org.example.dynamodb.networkstrategy;

import com.amazonaws.services.lambda.runtime.events.DynamodbEvent;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;

import javax.naming.Context;
import java.net.URI;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertNull;

class SingleLeaderDataReplicationTest {


    private DynamoDbClient replicaClient;
    private SingleLeaderDataReplication singleLeaderDataReplication;

    @BeforeEach
    void setUp() {
        replicaClient = DynamoDbClient.builder()
                .region(Region.US_WEST_2)
                .credentialsProvider(StaticCredentialsProvider.create(
                        AwsBasicCredentials.create("test", "test")))
                .endpointOverride(URI.create("http://localhost:8000"))
                .build();
        singleLeaderDataReplication = new SingleLeaderDataReplication();
    }

    @Test
    void testSetupSingleLeaderDataReplicationStrategyDoesNotThrow() {
        assertDoesNotThrow(() -> {
            singleLeaderDataReplication.setupSingleLeaderDataReplicationStrategy();
        });
    }

    @Test
    void testHandleRequestWithEmptyRecords() {
        DynamodbEvent event = new DynamodbEvent();
        event.setRecords(new ArrayList<>());
        Context context = null;

        Void result = singleLeaderDataReplication.handleRequest(event, context, replicaClient, "replica-table");

        assertNull(result);
    }
}