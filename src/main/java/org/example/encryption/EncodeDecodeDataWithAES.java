package org.example.encryption;

import javax.crypto.Cipher;

public class EncodeDecodeDataWithAES implements EncodeDecodeDataInterface{
    /**
     * API uses AES encryption algorithm for encrypting all the input read queries
     *
     * @param inputStr input String for encryption
     * @return encrypted byte array
     */
    @Override
    public byte[] encrypt(String inputStr) throws Exception {
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, EncryptionUtility.generateKeyAES());
        return cipher.doFinal(inputStr.getBytes("UTF-8"));
    }

    /**
     * API uses AES decryption algorithm for decrypting a UTF-8 encoded
     * byte array.
     *
     * @param item byte array that was encrypted using AES encryption algorithm
     * @return decrypted string that was previously encoded using AES encryption algorithm
     */
    @Override
    public String decrypt(byte[] item) throws Exception {
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE, EncryptionUtility.generateKeyAES());
        byte[] decrypted = cipher.doFinal(item);
        return new String(decrypted, "UTF-8");
    }
}
