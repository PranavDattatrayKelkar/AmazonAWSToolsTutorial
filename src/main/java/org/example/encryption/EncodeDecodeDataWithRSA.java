package org.example.encryption;

import javax.crypto.Cipher;

public class EncodeDecodeDataWithRSA implements EncodeDecodeDataInterface{
    /**
     * API uses RSA encryption algorithm for encrypting all the read queries
     * with a public key
     *
     *
     * @param inputStr input String for encryption
     * @return encrypted UTF-8 byte array.
     */
    @Override
    public byte[] encrypt(String inputStr) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, EncryptionUtility.generateKeyRSA().getPublic());
        return cipher.doFinal(inputStr.getBytes("UTF-8"));
    }

    /**
     * API uses RSA decryption algorithm for decrypting the input byte array
     * by using the private key of the crypto encryption algorithm
     *
     *
     * @param item byte array that was encrypted using RSA encryption algorithm
     * @return decrypted key that was previously encoded using RSA encryption algorithm
     */
    @Override
    public String decrypt(byte[] item) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, EncryptionUtility.generateKeyRSA().getPrivate());
        byte[] decrypted = cipher.doFinal(item);
        return new String(decrypted, "UTF-8");
    }
}
