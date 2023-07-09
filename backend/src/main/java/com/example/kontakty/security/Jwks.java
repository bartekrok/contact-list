package com.example.kontakty.security;

import com.nimbusds.jose.jwk.RSAKey;

import java.security.KeyPair;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.UUID;

public class Jwks {

    private Jwks() {}

    public static RSAKey generateRsa() { // Metoda do generowania klucza RSA i tworzenia obiektu RSAKey
        KeyPair keyPair = KeyGeneratorUtils.generateRsaKey(); // Generowanie pary kluczy RSA

        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();// Pobieranie klucza publicznego i prywatnego z pary kluczy
        RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();

        return new RSAKey.Builder(publicKey) // Tworzenie obiektu RSAKey z kluczem publicznym, prywatnym i unikalnym identyfikatorem (key ID)
                .privateKey(privateKey)
                .keyID(UUID.randomUUID().toString())
                .build();
    }
}
