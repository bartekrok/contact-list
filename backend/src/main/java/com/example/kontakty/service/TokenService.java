package com.example.kontakty.service;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.stream.Collectors;

@Service
public class TokenService {

    private final JwtEncoder encoder;

    public TokenService(JwtEncoder encoder) {
        this.encoder = encoder;
    }

    public String generateToken(Authentication authentication) { // Metoda do generowania tokena uwierzytelniającego na podstawie obiektu Authentication
        Instant now = Instant.now();
        String scope = authentication.getAuthorities().stream() // Pobieranie zakresu (scopes) uprawnień z obiektu Authentication
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(" "));
        JwtClaimsSet claims = JwtClaimsSet.builder() // Tworzenie obiektu JwtClaimsSet zawierającego informacje o tokenie
                .issuer("self") // Wydawca tokena
                .issuedAt(now) // Data wydania tokena
                .expiresAt(now.plusMillis(1000 * 60 * 60)) // Data wygaśnięcia tokena (1 godzina od teraz)
                .subject(authentication.getName()) // Podmiot (nazwa użytkownika)
                .claim("scope", scope) // Zakres uprawnień
                .build();
        return this.encoder.encode(JwtEncoderParameters.from(claims)).getTokenValue(); // Kodowanie tokena przy użyciu JwtEncoder
    }

}
