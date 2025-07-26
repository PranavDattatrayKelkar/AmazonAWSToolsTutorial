package org.example.dynamodb;

import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.AwsCredentialsProvider;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;

public class DDBTableUtility {
    public static String tableName = "pranav-table";
    public static AwsCredentialsProvider credentialsProvider = StaticCredentialsProvider.create(
            AwsBasicCredentials.create("Add your access keyId here",
                    "Add your secret access key here")
    );
    public static DynamoDbClient dbClient = DynamoDBClientFactory.createClient(credentialsProvider,
            Region.of("us-west-2"));
}
