package org.example.s3;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class CreateS3BucketTest {

    private CreateS3Bucket createS3Bucket;
    private StaticCredentialsProvider credentialsProvider;

    @BeforeEach
    void setUp() {
        createS3Bucket = new CreateS3Bucket();
        credentialsProvider = StaticCredentialsProvider.create(
                AwsBasicCredentials.create("test-key", "test-secret"));
    }

    @Test
    void testConstructor() {
        assertNotNull(createS3Bucket);
    }

    @Test
    void testCreateBucketDoesNotThrow() {
        String bucketName = "test-bucket";
        String region = "us-west-2";

        assertDoesNotThrow(() -> {
            createS3Bucket.createBucket(bucketName, credentialsProvider, region);
        });
    }

    @Test
    void testCreateBucketWithDifferentRegion() {
        String bucketName = "test-bucket-east";
        String region = "us-east-1";

        assertDoesNotThrow(() -> {
            createS3Bucket.createBucket(bucketName, credentialsProvider, region);
        });
    }

    @Test
    void testCreateBucketWithEmptyName() {
        String bucketName = "";
        String region = "us-west-2";

        assertDoesNotThrow(() -> {
            createS3Bucket.createBucket(bucketName, credentialsProvider, region);
        });
    }
}