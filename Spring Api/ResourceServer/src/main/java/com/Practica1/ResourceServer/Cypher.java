package com.Practica1.ResourceServer;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

public class Cypher {
    private final static String alg = "AES";
    private final static String cI = "AES/CBC/PKCS5Padding";


    public static String encrypt(String key, String iv, String cleartext) throws Exception {
        Cipher cipher = Cipher.getInstance(cI);
        SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes(), alg);
        IvParameterSpec ivParameterSpec = new IvParameterSpec(iv.getBytes());
        cipher.init(Cipher.ENCRYPT_MODE, skeySpec, ivParameterSpec);
        byte[] encrypted = Base64.getEncoder().encode(cleartext.getBytes());
        return new String(encrypted);
    }

    public static String decrypt(String key, String iv, String encrypted) throws Exception {
        Cipher cipher = Cipher.getInstance(cI);
        SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes(), alg);
        IvParameterSpec ivParameterSpec = new IvParameterSpec(iv.getBytes());
        byte[] enc = Base64.getDecoder().decode(encrypted);
        cipher.init(Cipher.DECRYPT_MODE, skeySpec, ivParameterSpec);
        byte[] decrypted = cipher.doFinal(enc);
        return new String(enc);
    }

    public static byte[] encrypt2(byte[] plainTextData, byte[] secretKey) throws Exception {
        try {
            String iv = new String(secretKey).substring(0, 16);

            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");

            byte[] dataBytes = plainTextData;
            int plaintextLength = dataBytes.length;
            byte[] plaintext = new byte[plaintextLength];
            System.arraycopy(dataBytes, 0, plaintext, 0, dataBytes.length);

            SecretKeySpec keyspec = new SecretKeySpec(secretKey, "AES");
            IvParameterSpec ivspec = new IvParameterSpec(iv.getBytes());

            cipher.init(Cipher.ENCRYPT_MODE, keyspec, ivspec);
            byte[] encrypted = cipher.doFinal(plaintext);

            return new String(Base64.getEncoder().encode(encrypted)).getBytes();

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static byte[] decrypt2(byte[] cipherTextData, byte[] secretKey) throws Exception {
        try {
            String iv = new String(secretKey).substring(0, 16);

            byte[] encrypted = Base64.getDecoder().decode(cipherTextData);

            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
            SecretKeySpec keyspec = new SecretKeySpec(secretKey, "AES");
            IvParameterSpec ivspec = new IvParameterSpec(iv.getBytes());

            cipher.init(Cipher.DECRYPT_MODE, keyspec, ivspec);

            byte[] original = cipher.doFinal(encrypted);
            String originalString = new String(original);
            return originalString.getBytes();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}