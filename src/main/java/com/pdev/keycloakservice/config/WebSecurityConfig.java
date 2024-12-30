package com.pdev.keycloakservice.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

/**
 * @author @maleeshasa
 * @Date 2024/11/15
 */
@Slf4j
@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        log.info("WebSecurityConfig.securityFilterChain() => started.");
        httpSecurity
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(
                        request -> request
                                .requestMatchers("/health/healthChecker").permitAll()
                                .requestMatchers("/api/kcs/v1/auth/user/token").permitAll()
                                .requestMatchers("/api/kcs/v1/auth/user/token/validate").permitAll()
                                .anyRequest().authenticated()
                ).sessionManagement(ses -> ses.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        log.info("WebSecurityConfig.securityFilterChain() => ended.");
        return httpSecurity
                .build();
    }
}
