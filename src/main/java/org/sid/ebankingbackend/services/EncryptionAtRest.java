package org.sid.ebankingbackend.services;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;
import org.springframework.stereotype.Service;

@Service
public class EncryptionAtRest {

     private static final String AES_ALGORITHM = "AES";
    private static final String FIXED_KEY_STRING = "yourFixedKeyString"; // Replace with a secure, random string

    private final SecretKey secretKey;

    public EncryptionAtRest() {
        this.secretKey = generateSecretKey();
    }

    private SecretKey generateSecretKey() {
        byte[] keyBytes = Base64.getDecoder().decode(FIXED_KEY_STRING);
        return new SecretKeySpec(keyBytes, AES_ALGORITHM);
    }

    public String encrypt(String plaintext) throws Exception {
        Cipher cipher = Cipher.getInstance(AES_ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        byte[] encryptedBytes = cipher.doFinal(plaintext.getBytes());
        return Base64.getEncoder().encodeToString(encryptedBytes);
    }

    public String decrypt(String encryptedText) throws Exception {
        Cipher cipher = Cipher.getInstance(AES_ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        byte[] encryptedBytes = Base64.getDecoder().decode(encryptedText);
        byte[] decryptedBytes = cipher.doFinal(encryptedBytes);
        return new String(decryptedBytes);
    }

}
