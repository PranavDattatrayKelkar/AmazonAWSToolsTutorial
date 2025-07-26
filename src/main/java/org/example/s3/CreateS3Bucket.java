package org.example.s3;

import software.amazon.awssdk.auth.credentials.AwsCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.CreateBucketConfiguration;
import software.amazon.awssdk.services.s3.model.CreateBucketRequest;

public class CreateS3Bucket {

    /**
     * Helper function to create S3 bucket with provided credentials
     * and the specific AWS region name.
     *
     *
     * @param bucketName : name of the bucket to be created
     * @param credProvider AWS credentials used to create the bucket
     * @param region AWS region where the bucket is to be created.
     */
    public void createBucket(String bucketName, AwsCredentialsProvider credProvider, String region) {
        try {
            S3Client s3Client = S3Client.builder()
                    .region(Region.of(region))
                    .credentialsProvider(credProvider)
                    .build();

            CreateBucketRequest bucketRequest = CreateBucketRequest.builder()
                    .bucket(bucketName)
                    .createBucketConfiguration(CreateBucketConfiguration.builder()
                            .locationConstraint(region)
                            .build())
                    .build();

            s3Client.createBucket(bucketRequest);
        } catch(Exception e) {
            System.out.println("Unable to create bucket : " + e.getMessage());
        }
        System.out.println("Successfully created bucket : " +  bucketName);
    }
}
