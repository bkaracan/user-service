package com.example.userservice.config;

import com.example.userservice.converter.CustomJwtGrantedAuthoritiesConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableMethodSecurity // @PreAuthorize kullanımı için
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable) // CSRF korumasını devre dışı bırakıyoruz (test amaçlı)
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/users/**").hasAnyRole("MANAGER","ADMIN") // MANAGER rolü için erişim
                        .requestMatchers("/api/roles/**").hasRole("ADMIN") // ADMIN rolü için erişim
                        .anyRequest().authenticated() // Diğer tüm endpoint'ler için kimlik doğrulama
                )
                .oauth2Login(oauth2 -> oauth2 // OAuth2 giriş yapılandırması
                        .defaultSuccessUrl("/") // Başarılı giriş sonrası yönlendirme
                )
                .logout(logout -> logout // Çıkış yapılandırması
                        .logoutSuccessUrl("/") // Çıkış sonrası yönlendirme
                        .invalidateHttpSession(true) // Oturum sıfırlama
                )
                .oauth2ResourceServer(oauth2 -> oauth2 // JWT tabanlı kimlik doğrulama yapılandırması
                        .jwt(jwt -> jwt.jwtAuthenticationConverter(jwtAuthenticationConverter()))
                );

        return http.build();
    }

    @Bean
    public JwtAuthenticationConverter jwtAuthenticationConverter() {
        JwtAuthenticationConverter converter = new JwtAuthenticationConverter();
        converter.setJwtGrantedAuthoritiesConverter(new CustomJwtGrantedAuthoritiesConverter()); // Özel authority dönüştürücü
        return converter;
    }
}

