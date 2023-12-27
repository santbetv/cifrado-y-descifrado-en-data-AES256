/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.encryption.infrastructure.controller;

import com.example.encryption.domain.Car;
import com.example.encryption.infrastructure.encryption.EncryptionExample;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 *
 * @author rizzoli
 */
@RestController
@RequestMapping("/encryption")
public class RestControllerEncryption {

    private String key = "iGTdaPAiaEMDGzWlGt5qrsNCe6H2XI3N";
    private String iv = "dsGRskhYO4sAfuRA";
    private String mode = "CBC";
    private String data2 = "{\"data\":{\"sofToken\":\"token\",\"hasConsent\":true,\"url\":\"https://\",\"embossName\":\"JohnDoe\",\"relatedAccounts\":[{\"cashAccountType\":{\"code\":\"CACC\"},\"identification\":{\"number\":\"2204032788\"},\"baseCurrency\":{\"code\":\"USD\"},\"customer\":{\"customerIdentifications\":[{\"typeOfIdentification\":\"CIFL\",\"otherIdentification\":{\"identificationForAccount\":\"4116486489712634\"}}]}}]}}";

    private ObjectMapper objectMapper = new ObjectMapper();
    
    @GetMapping("/encrypt")
    public ResponseEntity<?> get(@RequestBody Car input) throws Exception {

        String encryptedData = EncryptionExample.aesEncrypt(key, data2, iv, mode);
        return ResponseEntity.ok(encryptedData);
    }

    @GetMapping("/decrypt")
    public ResponseEntity<?> get() throws Exception{

        String decry = "NcUgcas8IH0XREZyxABOm/1JMBOiCtpjDmtKQ+CC7dBaaMwje1WrdrL51nVAl+borAA5cuiO0zTQ+GPB2qksnUTpfEv2t35q745wJVOLPVE58OM1gCdsmLqM7CzLHiGbNBjfGUPaOd+XVoECSa3rniH+BYkbLd+faRDxhW0Ra5tU/QXh7BHtKt0NkKFzXXQbLUwOlHdpjWhIXweyj3IFSWGNvkovk030jQZ9TwhfOPrWcjXyXq/XRhcChuMSBY2P7P/+b2nxdlA7R3mB75L4wjlvHaaddRIpsxQd4kQ5i8pnEo2sT9IhEshSey5spJMfAkW5T4RbJ+IVm3xILQDddiP0nf1/AEeXX4DS3eKd8Du5x1Xief6CXHa4yqds1xVjhh5wq/PLRtnK/v2VnhJQFptI7RS6BQs7tkaugLMXIzyYsgU82Gl9fl3vX5WMWrhBf+tRPb1U5Gtts0yOeQbImz7YASaGoVXyf8gV03Cfgbg=";

        String decryptedData = EncryptionExample.aesDecrypt(key, decry, iv, mode);
        JsonNode jsonNode = objectMapper.readTree(decryptedData);
        
        return ResponseEntity.ok(jsonNode);
    }

}
