package org.example.s3;

import software.amazon.awssdk.auth.credentials.DefaultCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.model.ListObjectsV2Request;
import software.amazon.awssdk.services.s3.model.ListObjectsV2Response;
import software.amazon.awssdk.services.s3.model.S3Object;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;
import java.util.List;

public class DownloadFileFromS3 {
    /**
     * Helper class to fetch files from an S3 bucket.
     * Currently this implementation is dedicated to fetching text
     * files but it can be extended to any type of files.
     *
     *
     * @param bucketName S3 bucket name
     * @param prefix S3 prefix
     * @param localDir local directory to save the files
     * @throws IOException
     */
    public void downloadFileFromS3(String bucketName, String prefix, String localDir) throws IOException {
        S3Client s3 = S3Client.builder()
                .region(Region.of("us-west-2"))
                .credentialsProvider(DefaultCredentialsProvider.create())
                .build();

        ListObjectsV2Request request = ListObjectsV2Request.builder()
                .bucket(bucketName)
                .prefix(prefix)
                .build();

        ListObjectsV2Response response = s3.listObjectsV2(request);
        List<S3Object> objects = response.contents();
        for(S3Object obj: objects) {
            String key = obj.key();
            if(key.endsWith(".txt")) {
                System.out.println("Downloading the key : " + key);

                GetObjectRequest getReq = GetObjectRequest.builder()
                        .bucket(bucketName)
                        .key(key)
                        .build();
                File localFile = Paths.get(localDir, Paths.get(key).getFileName().toString()).toFile();
                localFile.getParentFile().mkdirs();

                try (InputStream is = s3.getObject(getReq);
                     FileOutputStream fos = new FileOutputStream(localFile)) {

                    byte[] buffer = new byte[8192];
                    int bytesRead;
                    while ((bytesRead = is.read(buffer)) != -1) {
                        fos.write(buffer, 0, bytesRead);
                    }
                }

                System.out.println("Saved to: " + localFile.getAbsolutePath());
            }
        }
        s3.close();
    }
}
