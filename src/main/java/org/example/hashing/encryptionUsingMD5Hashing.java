package org.example.hashing;

import org.example.encryption.EncryptionUtility.*;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

public class encryptionUsingMD5Hashing {
    /**
     * NOTES : Flaws in mapping in this way
     *  1. highly non‑uniform (similar length keys map together)
     *  2. all keys remap when
     *  3. can’t weight by server capacity
     *
     *
     * @param input String to make the hash for
     * @return Page object with the encrypted data and server selection
     * @throws NoSuchAlgorithmException
     */
    public static Page hashMD5(String input) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(input.getBytes());
        byte[] digest = md.digest();
        // md5 alwasy has a 16 byte encoding so the length of the hashed bytes will always be 16
        Page page = new Page(0, digest, input.length() % 4);
        return page;
    }

    /**
     * Better distribution than length but still remaps almost everything when
     * server count changes.
     *
     *
     * @param input String to make the hash for
     * @param numOfServers Number of servers to distribute the data to
     * @return Page object with the encrypted data and server selection
     * @throws NoSuchAlgorithmException
     */
    public static Page hashMD5to64BitAndModByServerCount(String input, int numOfServers) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] digest =  md.digest(input.getBytes(StandardCharsets.UTF_8));
        // fold first 4 bytes into an int (unsigned)
        int h = ((digest[0] & 0xff) << 24) |
                ((digest[1] & 0xff) << 16) |
                ((digest[2] & 0xff) << 8) |
                (digest[3] & 0xff);
        h = h & 0x7fffffff;
        return new Page(0, digest, numOfServers);
    }
}
