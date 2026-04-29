package com.cg.config;

import com.cg.security.JwtFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.http.HttpMethod;

@Configuration
public class SecurityConfig {

    @Autowired
    private JwtFilter jwtFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
            .csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(auth -> auth

                // PUBLIC APIs
                .requestMatchers("/auth/**").permitAll()
                .requestMatchers(
                        "/swagger-ui/**",
                        "/swagger-ui.html",
                        "/v3/api-docs",
                        "/v3/api-docs/**"
                ).permitAll()
  
                .requestMatchers(HttpMethod.GET, "/rooms/**").permitAll()
                .requestMatchers("/hotels/**").permitAll()

                // USER + ADMIN
                .requestMatchers("/reviews/**")
                .hasAnyRole("USER", "ADMIN")

                // USER only
                .requestMatchers(
                        "/api/reservations/**",
                        "/api/payments/**"
                ).hasRole("USER")

                // ADMIN only
                .requestMatchers(HttpMethod.POST, "/rooms/**").hasRole("ADMIN")
                .requestMatchers(HttpMethod.PUT, "/rooms/**").hasRole("ADMIN")
                .requestMatchers(HttpMethod.DELETE, "/rooms/**").hasRole("ADMIN")
                .requestMatchers("/api/amenities/**").hasRole("ADMIN")

                .anyRequest().authenticated()
            )
            .httpBasic(httpBasic -> httpBasic.disable())
            .formLogin(form -> form.disable());

        http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
