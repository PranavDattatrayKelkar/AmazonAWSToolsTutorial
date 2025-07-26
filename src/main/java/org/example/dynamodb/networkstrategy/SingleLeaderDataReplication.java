package org.example.dynamodb.networkstrategy;

import com.amazonaws.services.lambda.runtime.events.DynamodbEvent;
import com.amazonaws.services.lambda.runtime.events.models.dynamodb.AttributeValue;
import org.example.dynamodb.CreateDynamoDBTable;
import org.example.dynamodb.DynamoDBClientFactory;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.model.DeleteItemRequest;
import software.amazon.awssdk.services.dynamodb.model.PutItemRequest;

import javax.naming.Context;
import java.util.Map;

import static org.example.dynamodb.DDBTableUtility.credentialsProvider;

public class SingleLeaderDataReplication {
    public void setupSingleLeaderDataReplicationStrategy() {
        //AwsCredentialsProvider credProvider, Region region
        DynamoDBClientFactory dbClientFactory = new DynamoDBClientFactory();
        DynamoDbClient dbClient = dbClientFactory.createClient(credentialsProvider, Region.of("us-west-2"));
        CreateDynamoDBTable dynamoLeaderDBTable = new CreateDynamoDBTable("Leader-table", dbClient);
        CreateDynamoDBTable dynamoReplica1DBTable = new CreateDynamoDBTable("Replica1-table", dbClient);
        CreateDynamoDBTable dynamoReplica2DBTable = new CreateDynamoDBTable("Replica2-table", dbClient);

    }

    public Void handleRequest(DynamodbEvent event, Context context, DynamoDbClient replicaClient,
                              String replicaTableName) {
        for (DynamodbEvent.DynamodbStreamRecord record : event.getRecords()) {
            Map<String, AttributeValue> newImage = record.getDynamodb().getNewImage();

            /*
            if ("INSERT".equals(record.getEventName()) || "MODIFY".equals(record.getEventName())) {
                PutItemRequest putRequest = PutItemRequest.builder()
                        .tableName(replicaTableName)
                        .item(newImage)
                        .build();
                replicaClient.putItem(putRequest);

                // Repeat for ReplicaTable2
            } else if ("REMOVE".equals(record.getEventName())) {
                DeleteItemRequest deleteReq = DeleteItemRequest.builder()
                        .tableName("ReplicaTable1")
                        .key(record.getDynamodb().getKeys())
                        .build();
                replicaClient.deleteItem(deleteReq);
            }

             */
        }
        return null;
    }
}
