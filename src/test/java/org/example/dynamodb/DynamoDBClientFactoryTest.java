package org.example.dynamodb;

import org.junit.jupiter.api.Test;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;

import java.net.URI;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class DynamoDBClientFactoryTest {

    @Test
    void testCreateClientWithCredentialsAndRegion() {
        StaticCredentialsProvider credProvider = StaticCredentialsProvider.create(
                AwsBasicCredentials.create("test-key", "test-secret"));
        Region region = Region.US_WEST_2;

        DynamoDbClient client = DynamoDBClientFactory.createClient(credProvider, region);

        assertNotNull(client);
        client.close();
    }

    @Test
    void testCreateClientWithCredentialsRegionAndEndpoint() {
        StaticCredentialsProvider credProvider = StaticCredentialsProvider.create(
                AwsBasicCredentials.create("test-key", "test-secret"));
        Region region = Region.US_WEST_2;
        URI endpoint = URI.create("http://localhost:8000");

        DynamoDbClient client = DynamoDBClientFactory.createClient(credProvider, region, endpoint);

        assertNotNull(client);
        client.close();
    }
}