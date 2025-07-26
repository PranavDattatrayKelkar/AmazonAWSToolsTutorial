package org.example.dynamodb;

import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.AwsCredentialsProvider;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;

public class DDBTableUtility {
    public static String tableName = "pranav-table";
    public static AwsCredentialsProvider credentialsProvider = StaticCredentialsProvider.create(
            AwsBasicCredentials.create("AKIA4S7LOIWHQMGBHHXF",
                    "b8mBy49AOqa6IGxPwRay2PCazezZ68T4PnSz2Sb6")
    );
    public static DynamoDbClient dbClient = DynamoDBClientFactory.createClient(credentialsProvider,
            Region.of("us-west-2"));
}
