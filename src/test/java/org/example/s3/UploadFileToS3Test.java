package org.example.s3;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class UploadFileToS3Test {


    private UploadFileToS3 uploadFileToS3;
    private Path tempFile;

    @BeforeEach
    void setUp() throws IOException {
        uploadFileToS3 = new UploadFileToS3();
        tempFile = Files.createTempFile("test", ".txt");
        Files.write(tempFile, "test content".getBytes());
    }

    @Test
    void testConstructor() {
        assertNotNull(uploadFileToS3);
    }

    @Test
    void testUploadFileDoesNotThrow() {
        String bucketName = "test-bucket";
        String keyName = "test-key";
        String filePath = tempFile.toString();

        assertDoesNotThrow(() -> {
            uploadFileToS3.uploadFile(bucketName, keyName, filePath);
        });
    }

    @Test
    void testUploadFileWithDifferentKey() {
        String bucketName = "test-bucket";
        String keyName = "folder/test-file.txt";
        String filePath = tempFile.toString();

        assertDoesNotThrow(() -> {
            uploadFileToS3.uploadFile(bucketName, keyName, filePath);
        });
    }

    @Test
    void testUploadFileWithNonExistentFile() {
        String bucketName = "test-bucket";
        String keyName = "test-key";
        String filePath = "non-existent-file.txt";

        assertDoesNotThrow(() -> {
            uploadFileToS3.uploadFile(bucketName, keyName, filePath);
        });
    }
}