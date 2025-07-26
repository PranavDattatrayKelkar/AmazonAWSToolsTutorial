package org.example.hashing;

import org.example.encryption.EncryptionUtility;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

public class encryptionUsingSHA256Hashing {

    /**
     * This API hashes the input string using SHA256 algorithm
     *
     * @param input String to make the hash for
     * @return Page object with the hashed data and the server selection
     * @throws NoSuchAlgorithmException
     */
    public static EncryptionUtility.Page hashSHA256(String input) throws NoSuchAlgorithmException {
        MessageDigest sha = MessageDigest.getInstance("SHA-256");
        sha.update(input.getBytes());
        byte[] digest = sha.digest();
        // sha alwasy has a 32 byte encoding so the length of the hashed bytes will always be 16
        EncryptionUtility.Page page = new EncryptionUtility.Page(1, digest, input.length() % 4);
        return page;
    }

    public static void main(String[] args) throws NoSuchAlgorithmException {
        String input = "pr";
        int[] data = new int[5];
        EncryptionUtility.Page encryptedPage = hashSHA256(input);
        Arrays.fill(data, -1);
        int index = input.length() % 4;

        System.out.println(encryptedPage.encryptedData.length);
        System.out.println(encryptedPage.serverSelection);
        if(input.length() % 4 == encryptedPage.serverSelection) {
            System.out.println("AssertTrue");
        } else
        {
            System.out.println("AssertFalse");
        }

    }
}
