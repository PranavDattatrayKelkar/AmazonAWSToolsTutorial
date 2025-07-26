package org.example.dynamodb;

import software.amazon.awssdk.auth.credentials.AwsCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;

import java.net.URI;

public class DynamoDBClientFactory {

    /**
     * Factory to create the DynamoDB client's static instance
     *
     *
     * @param credProvider AWS credentials used to create the DynamoDB client
     * @param region AWS region to create the instance of DynamoDB client in
     * @return Static DynamoDb client instance
     */
    public static DynamoDbClient createClient(AwsCredentialsProvider credProvider, Region region) {
        return DynamoDbClient.builder()
                .region(region)
                .credentialsProvider(credProvider)
                .build();
    }

    /**
     * Factory to create a DynamoDB client's static instance
     *
     * @param credProvider AWS credentials used to create the DynamoDB client
     * @param region AWS region to create the instance of DynamoDB client in
     * @param endpoint DynamoDB endpoint to connect to
     * @return Static DynamoDB client instance
     */
    public static DynamoDbClient createClient(AwsCredentialsProvider credProvider, Region region, URI endpoint) {
        return DynamoDbClient.builder()
                .region(region)
                .credentialsProvider(credProvider)
                .endpointOverride(endpoint)
                .build();
    }
}
