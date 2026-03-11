package com.vtinstitute.vtinstitute_restapi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/auth/login", "/", "/images/**", "/css/**", "/api/**", "/js/**").permitAll()
                .requestMatchers("/students/**").hasRole("ADMIN")
                .requestMatchers("/scores/**").hasAnyRole("STUDENT", "ADMIN")
                .requestMatchers("/profile/**").hasRole("STUDENT")
                .anyRequest().authenticated()
            )
            .logout(logout -> logout
                .logoutUrl("/auth/logout")
                .logoutSuccessUrl("/auth/login")
                .permitAll()
            );
        return http.build();
    }
}
