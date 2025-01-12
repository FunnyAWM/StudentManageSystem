package com.funnyawm.studentmanager.utils;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.util.Base64;

public class AESUtils {
    private static final String ALGORITHM = "AES";
    private static final String MODE = "AES/ECB/PKCS5Padding";
    private static final byte[] keyValue = "TD2g45EfXqGnBLaXpzPZ6A==".getBytes();

    public static String encrypt(String plaintext) throws Exception {
        SecretKeySpec secretKey = generateKey();
        Cipher cipher = Cipher.getInstance(MODE);
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        byte[] encryptedBytes = cipher.doFinal(plaintext.getBytes());
        return Base64.getEncoder().encodeToString(encryptedBytes);
    }

    public static String decrypt(String ciphertext) throws Exception {
        SecretKeySpec secretKey = generateKey();
        Cipher cipher = Cipher.getInstance(MODE);
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        byte[] decryptedBytes = cipher.doFinal(Base64.getDecoder().decode(ciphertext));
        return new String(decryptedBytes);
    }

    private static @NotNull SecretKeySpec generateKey() {
        return new SecretKeySpec(keyValue, ALGORITHM);
    }

    ///Use this to update key if necessary
    @Contract("_ -> new")
    private static @NotNull Key generateKey(String key) {
        return new SecretKeySpec(key.getBytes(), ALGORITHM);
    }
}
