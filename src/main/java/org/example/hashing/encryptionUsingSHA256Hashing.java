package org.example.hashing;

import org.example.encryption.EncryptionUtility;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

public class encryptionUsingSHA256Hashing {

    /**
     * This API hashes the input string using SHA256 algorithm
     *
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
}
