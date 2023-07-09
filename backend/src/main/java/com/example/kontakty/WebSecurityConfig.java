package com.example.kontakty;

import com.example.kontakty.security.Jwks;
import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
import java.util.List;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class WebSecurityConfig{

    private RSAKey rsaKey;

    @Bean
    public AuthenticationManager authManager(UserDetailsService userDetailsService) {
        var authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        return new ProviderManager(authProvider);
    }



    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .cors(Customizer.withDefaults()) // Włączenie obsługi CORS
                .csrf(AbstractHttpConfigurer::disable) // Wyłączenie obsługi CSRF
                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers("/contacts").permitAll() // Zezwolenie na dostęp do "/contacts" bez uwierzytelniania
                        .requestMatchers("/token").permitAll() // Zezwolenie na dostęp do "/token" bez uwierzytelniania
                        .requestMatchers("/contacts/**").permitAll() // Zezwolenie na dostęp do "/contacts/**" bez uwierzytelniania
                        .anyRequest().authenticated() // Wymaganie uwierzytelnienia dla wszystkich innych żądań
                )
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // Konfiguracja zarządzania sesjami jako bezzasadniczej
                .oauth2ResourceServer(OAuth2ResourceServerConfigurer::jwt) // Konfiguracja zasobów serwera OAuth2
                .build();
    }

    @Bean
    public JWKSource<SecurityContext> jwkSource() {
        rsaKey = Jwks.generateRsa(); // Wygenerowanie klucza RSA przy użyciu klasy Jwks
        JWKSet jwkSet = new JWKSet(rsaKey);
        return (jwkSelector, securityContext) -> jwkSelector.select(jwkSet);
    }

    @Bean
    JwtEncoder jwtEncoder(JWKSource<SecurityContext> jwks) {
        return new NimbusJwtEncoder(jwks);
    }

    @Bean
    JwtDecoder jwtDecoder() throws JOSEException {
        return NimbusJwtDecoder.withPublicKey(rsaKey.toRSAPublicKey()).build();
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource(){
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("http://localhost:5173")); // Ustawienie dozwolonych źródeł żądań CORS
        configuration.setAllowedMethods(Arrays.asList("GET","PUT","POST","DELETE")); // Ustawienie dozwolonych metod żądań CORS
        configuration.setAllowedHeaders(List.of("*")); // Ustawienie dozwolonych nagłówków żądań CORS
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**",configuration);
        return source;
    }


    @Bean
    public UserDetailsService userDetailsService() { // Dane dostepowe administratora
        return new InMemoryUserDetailsManager(
                User.withUsername("root")
                        .password("{noop}root")
                        .authorities("admin")
                        .build()
        );
    }
}