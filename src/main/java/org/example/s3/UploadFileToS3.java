package org.example.s3;

import software.amazon.awssdk.auth.credentials.DefaultCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.nio.file.Paths;

public class UploadFileToS3 {
    /**
     * Helper function to upload data to an S3 bucket.
     *
     * @param bucketName
     * @param keyName
     * @param filePath
     */
    public void uploadFile(String bucketName, String keyName, String filePath) {
        S3Client s3 = S3Client.builder()
                .region(Region.of("us-west-2"))
                .credentialsProvider(DefaultCredentialsProvider.create())
                .build();

        PutObjectRequest request = PutObjectRequest.builder()
                .bucket(bucketName)
                .key(keyName)
                .build();

        s3.putObject(request, Paths.get(filePath));

        System.out.println("File uploaded to S3: " + bucketName + "/" + keyName);
        s3.close();
    }
}
