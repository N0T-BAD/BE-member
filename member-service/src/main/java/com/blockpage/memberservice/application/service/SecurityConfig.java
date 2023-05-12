package com.blockpage.memberservice.application.service;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .httpBasic().disable()
            .cors()
            .and()
            .csrf().disable()
            .sessionManagement()
            .and().authorizeRequests()
            .antMatchers(("/v1/api/payments/best")).permitAll()
            .antMatchers("/").permitAll()
            .and()
            .oauth2Login().loginPage("/v1/oauth/login");
        return http.build();
    }

}
