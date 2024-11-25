package com.example.userservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable) // CSRF korumasını devre dışı bırakıyoruz (sadece geliştirme/test amaçlı)
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/users/**").permitAll() // Tüm kullanıcı endpoint'leri serbest
                        .requestMatchers("/api/roles/**").permitAll() // Tüm roller endpoint'leri serbest
                        .anyRequest().authenticated() // Diğer tüm endpoint'ler için doğrulama gerekli
                )
                .httpBasic(AbstractHttpConfigurer::disable); // HTTP Basic doğrulamasını devre dışı bırak (opsiyonel)

        return http.build();
    }
}
