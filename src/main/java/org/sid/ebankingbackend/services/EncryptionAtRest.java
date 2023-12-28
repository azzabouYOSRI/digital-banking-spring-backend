package org.sid.ebankingbackend.services;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.util.Base64;

@Service
public class EncryptionAtRest {

    // Replace this key with your own secret key
    private static final String SECRET_KEY = "56ghqWutyPqTGIfPVVIiXg==";

    public String encrypt(String data) throws Exception {
        byte[] encryptedData = encryptData(data.getBytes(StandardCharsets.UTF_8), SECRET_KEY);
        return Base64.getEncoder().encodeToString(encryptedData);
    }

    public String decrypt(String encryptedData) throws Exception {
        byte[] decryptedData = decryptData(Base64.getDecoder().decode(encryptedData), SECRET_KEY);
        return new String(decryptedData, StandardCharsets.UTF_8);
    }

    private static byte[] encryptData(byte[] data, String key) throws Exception {
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, generateSecretKey(key));
        return cipher.doFinal(data);
    }

    private static byte[] decryptData(byte[] encryptedData, String key) throws Exception {
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE, generateSecretKey(key));
        return cipher.doFinal(encryptedData);
    }

    private static SecretKeySpec generateSecretKey(String key) {
        byte[] keyBytes = key.getBytes(StandardCharsets.UTF_8);
        return new SecretKeySpec(keyBytes, "AES");
    }

    private static String generateRandomKey() {
        SecureRandom secureRandom = new SecureRandom();
        byte[] key = new byte[16]; // 128 bits
        secureRandom.nextBytes(key);
        return Base64.getEncoder().encodeToString(key);
    }
}
