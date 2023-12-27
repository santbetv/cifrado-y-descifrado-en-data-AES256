package com.example.encryption;

import com.example.encryption.infrastructure.encryption.EncryptionExample;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class EncryptionApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(EncryptionApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        try {
            String key = "iGTdaPAiaEMDGzWlGt5qrsNCe6H2XI3N";
            String iv = "dsGRskhYO4sAfuRA";
            String mode = "CBC";
            String data = "Hola mundo";
            String data2 = "{\"data\":{\"sofToken\":\"token\",\"hasConsent\":true,\"url\":\"https://\",\"embossName\":\"JohnDoe\",\"relatedAccounts\":[{\"cashAccountType\":{\"code\":\"CACC\"},\"identification\":{\"number\":\"2204032788\"},\"baseCurrency\":{\"code\":\"USD\"},\"customer\":{\"customerIdentifications\":[{\"typeOfIdentification\":\"CIFL\",\"otherIdentification\":{\"identificationForAccount\":\"4116486489712634\"}}]}}]}}"; 

            String encryptedData = EncryptionExample.aesEncrypt(key, data2, iv, mode);
            System.out.println("Valor encriptado: " + encryptedData);

            String decryptedData = EncryptionExample.aesDecrypt(key, encryptedData, iv, mode);
            System.out.println("Datos descifrados: " + decryptedData);
        } catch (Exception e) {
            e.printStackTrace();
        }
    
    }

}
