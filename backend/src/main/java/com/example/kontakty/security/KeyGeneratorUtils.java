package com.example.kontakty.security;

import org.springframework.stereotype.Component;

import java.security.KeyPair;
import java.security.KeyPairGenerator;

@Component
final class KeyGeneratorUtils {

    private KeyGeneratorUtils() {}

    static KeyPair generateRsaKey() { // Metoda do generowania pary kluczy RSA
        KeyPair keyPair;
        try { // Inicjalizacja generatora pary kluczy RSA
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
            keyPairGenerator.initialize(2048); // Ustawienie rozmiaru klucza na 2048 bitów
            keyPair = keyPairGenerator.generateKeyPair(); // Generowanie pary kluczy
        } catch (Exception ex) {
            throw new IllegalStateException(ex);
        }
        return keyPair;
    }

}