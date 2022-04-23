package com.appfactory.privacycontacts.db;

import android.util.Base64;
import android.util.Log;

import com.appfactory.privacycontacts.utills.Logger;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class DataEncryption {
    private static final DataEncryption INSTANCE = new DataEncryption();
    private SecretKey secretKey;

    private DataEncryption() {
    }

    public void setPassword(String password) {
        secretKey = generateKey(getMD5EncryptedString(password + password));
    }

    public static DataEncryption getInstance() {
        return INSTANCE;
    }


    public String getMD5EncryptedString(String encTarget) {
        MessageDigest mdEnc = null;
        try {
            mdEnc = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            Logger.log("Exception while encrypting to md5");
            e.printStackTrace();
        } // Encryption algorithm
        mdEnc.update(encTarget.getBytes(), 0, encTarget.length());
        String md5 = new BigInteger(1, mdEnc.digest()).toString(16);
        while (md5.length() < 32) {
            md5 = "0" + md5;
        }
        return md5;
    }

    private SecretKey generateKey(String password) {
        return new SecretKeySpec(password.getBytes(), "AES");
    }

    public String encryptText(String plainText)
            throws NoSuchAlgorithmException, InvalidKeyException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, UnsupportedEncodingException {
        if (plainText.isEmpty()) {
            return plainText;
        }
        /* Encrypt the message. */
        Cipher cipher = null;
        cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        byte[] cipherText = cipher.doFinal(plainText.getBytes("UTF-8"));
        return Base64.encodeToString(cipherText, Base64.NO_WRAP);
    }

    public String decryptText(String cipherText)
            throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException, UnsupportedEncodingException {
        if (cipherText.isEmpty()) {
            return cipherText;
        }

        byte[] decode = Base64.decode(cipherText, Base64.NO_WRAP);
        /* Decrypt the message, given derived encContentValues and initialization vector. */
        Cipher cipher = null;
        cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        String decryptString = new String(cipher.doFinal(decode));
        return decryptString;
    }
}