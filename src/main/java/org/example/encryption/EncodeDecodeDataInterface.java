package org.example.encryption;

/**
 * Interface for Encoders and Decoders
 */
public interface EncodeDecodeDataInterface {
    public byte[] encrypt(String inputStr) throws Exception;
    public String decrypt(byte[] item) throws Exception;
}
