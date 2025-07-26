package org.example.encryption;

public interface EncodeDecodeDataInterface {
    public byte[] encrypt(String inputStr) throws Exception;
    public String decrypt(byte[] item) throws Exception;
}
