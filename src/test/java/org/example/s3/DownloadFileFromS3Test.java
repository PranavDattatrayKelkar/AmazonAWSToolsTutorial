package org.example.s3;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class DownloadFileFromS3Test {

    private DownloadFileFromS3 downloadFileFromS3;
    private Path tempDir;

    @BeforeEach
    void setUp() throws IOException {
        downloadFileFromS3 = new DownloadFileFromS3();
        tempDir = Files.createTempDirectory("s3-download-test");
    }

    @Test
    void testConstructor() {
        assertNotNull(downloadFileFromS3);
    }

    @Test
    void testDownloadFileFromS3DoesNotThrow() {
        String bucketName = "test-bucket";
        String prefix = "test-prefix";
        String localDir = tempDir.toString();

        assertDoesNotThrow(() -> {
            downloadFileFromS3.downloadFileFromS3(bucketName, prefix, localDir);
        });
    }

    @Test
    void testDownloadFileWithEmptyPrefix() {
        String bucketName = "test-bucket";
        String prefix = "";
        String localDir = tempDir.toString();

        assertDoesNotThrow(() -> {
            downloadFileFromS3.downloadFileFromS3(bucketName, prefix, localDir);
        });
    }

    @Test
    void testDownloadFileWithNonExistentBucket() {
        String bucketName = "non-existent-bucket";
        String prefix = "test-prefix";
        String localDir = tempDir.toString();

        assertDoesNotThrow(() -> {
            downloadFileFromS3.downloadFileFromS3(bucketName, prefix, localDir);
        });
    }

    @Test
    void testDownloadFileWithDifferentLocalDir() throws IOException {
        Path anotherTempDir = Files.createTempDirectory("another-s3-test");
        String bucketName = "test-bucket";
        String prefix = "documents/";
        String localDir = anotherTempDir.toString();

        assertDoesNotThrow(() -> {
            downloadFileFromS3.downloadFileFromS3(bucketName, prefix, localDir);
        });
    }
}