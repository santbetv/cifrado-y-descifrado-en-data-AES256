/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.encryption.infrastructure.encryption;

import java.nio.charset.StandardCharsets;
import java.security.InvalidParameterException;
import java.util.Arrays;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

import org.apache.commons.codec.binary.Base64;

/**
 *
 * @author rizzoli
 */
public class EncryptionExample {

    public static String aesEncrypt(String key, String data, String iv, String mode)
            throws InvalidParameterException, Exception {
        try {
            byte[] bytesOutput = null;
            Cipher cipher = Cipher.getInstance("AES/" + mode + "/PKCS5Padding");
            SecretKeySpec secretKey = new SecretKeySpec(key.getBytes(StandardCharsets.UTF_8), "AES");
            IvParameterSpec ivspec = new IvParameterSpec(iv.getBytes(StandardCharsets.UTF_8));

            cipher.init(Cipher.ENCRYPT_MODE, secretKey, ivspec);
            bytesOutput = cipher.doFinal(data.getBytes(StandardCharsets.UTF_8));

            return Base64.encodeBase64String(bytesOutput);
        } catch (Exception e) {
            throw new InvalidParameterException("Error al encriptar: " + e.getMessage());
        }
    }
    
    public static String aesDecrypt(String key, String encryptedData, String iv, String mode)
            throws InvalidParameterException, Exception {
        try {
            byte[] bytesOutput = null;
            Cipher cipher = Cipher.getInstance("AES/" + mode + "/PKCS5Padding");
            SecretKeySpec secretKey = new SecretKeySpec(key.getBytes(StandardCharsets.UTF_8), "AES");
            IvParameterSpec ivspec = new IvParameterSpec(iv.getBytes(StandardCharsets.UTF_8));
            byte[] encryptedBytes = Base64.decodeBase64(encryptedData);

            cipher.init(Cipher.DECRYPT_MODE, secretKey, ivspec);
            bytesOutput = cipher.doFinal(encryptedBytes);

            return new String(bytesOutput, StandardCharsets.UTF_8);
        } catch (Exception e) {
            throw new InvalidParameterException("Error al descifrar: " + e.getMessage());
        }
    }
    
}
