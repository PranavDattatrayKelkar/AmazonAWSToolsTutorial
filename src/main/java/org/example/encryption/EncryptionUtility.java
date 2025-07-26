package org.example.encryption;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.KeyPair;
import java.security.KeyPairGenerator;

/**
 * Utility package for Encryption algorithms
 */
public class EncryptionUtility {
    public static class Page {
        public static int encryptionType;
        public static byte[] encryptedData;
        public static int serverSelection;
        public static int version;
        public Page(int encryptionType, byte[] encryptedData, int serverSelection) {
            this.encryptionType = encryptionType;
            this.encryptedData = encryptedData;
            this.serverSelection = serverSelection;
        }
    }

    /**
     * API that is used to generate crypto key using AES crypto
     * encryption algorithm.
     *
     *
     * @return Encrypted secret key.
     * @throws Exception
     */
    public static SecretKey generateKeyAES() throws Exception {
        KeyGenerator keyGen = KeyGenerator.getInstance("AES");
        keyGen.init(128);
        return keyGen.generateKey();
    }

    /**
     * API that is used to generate crypto key using RSA crypto
     * encryption algorithm.
     *
     *
     * @return Encrypted public and private key pair to be used further.
     * @throws Exception
     */
    public static KeyPair generateKeyRSA() throws Exception {
        KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
        keyGen.initialize(2048);
        return keyGen.generateKeyPair();
    }
}
