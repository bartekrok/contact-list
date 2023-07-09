package com.example.kontakty.controler;


import com.example.kontakty.models.LoginRequest;
import com.example.kontakty.service.TokenService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    private static final Logger LOG = LoggerFactory.getLogger(AuthController.class);

    private final TokenService tokenService;
    private final AuthenticationManager authenticationManager;

    public AuthController(TokenService tokenService, AuthenticationManager authenticationManager) {
        this.tokenService = tokenService;
        this.authenticationManager = authenticationManager;
    }
    // Endpoint do generowania tokenu uwierzytelniającego na podstawie danych logowania
    @PostMapping("/token")
    public String token(@RequestBody LoginRequest userLogin) throws AuthenticationException {
        // Uwierzytelnianie użytkownika przy użyciu menedżera uwierzytelniania
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userLogin.username(), userLogin.password()));
        // Generowanie tokenu na podstawie uwierzytelnienia
        return tokenService.generateToken(authentication);
    }

}
