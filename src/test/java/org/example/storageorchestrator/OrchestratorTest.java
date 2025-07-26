package org.example.storageorchestrator;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;

import java.net.URI;

import static org.junit.jupiter.api.Assertions.*;

public class OrchestratorTest {


    private Cache<String, String> cache;
    private DynamoDbClient dynamoDbClient;
    private CacheChecker cacheChecker;
    private DynamoDBChecker dynamoDBChecker;
    private OrchestratorUtility.Request request;

    @BeforeEach
    void setUp() {
        cache = Caffeine.newBuilder().maximumSize(100).build();
        dynamoDbClient = DynamoDbClient.builder()
                .region(Region.US_WEST_2)
                .credentialsProvider(StaticCredentialsProvider.create(
                        AwsBasicCredentials.create("test", "test")))
                .endpointOverride(URI.create("http://localhost:8000"))
                .build();
        
        cacheChecker = new CacheChecker();
        dynamoDBChecker = new DynamoDBChecker();
        cacheChecker.setNext(dynamoDBChecker);
        
        request = TestHelper.createRequest("testKey", "", cache, dynamoDbClient);
    }

    @Test
    void testOrchestratorChainCreation() {
        assertNotNull(cacheChecker);
        assertNotNull(dynamoDBChecker);
        assertEquals(dynamoDBChecker, cacheChecker.next);
    }

    @Test
    void testCacheCheckerHandleDoesNotThrow() {
        assertDoesNotThrow(() -> {
            cacheChecker.handle(request);
        });
    }

    @Test
    void testDynamoDBCheckerHandleDoesNotThrow() {
        assertDoesNotThrow(() -> {
            dynamoDBChecker.handle(request);
        });
    }

    @Test
    void testRequestValueInitialization() {
        assertEquals("", request.value);
        assertEquals("testKey", request.key);
    }

    @Test
    void testForwardFunctionality() {
        CacheChecker testChecker = new CacheChecker();
        DynamoDBChecker testDynamoChecker = new DynamoDBChecker();
        testChecker.setNext(testDynamoChecker);
        
        assertDoesNotThrow(() -> {
            testChecker.forward(request);
        });
    }
}