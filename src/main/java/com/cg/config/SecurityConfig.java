package com.cg.config;

import com.cg.security.JwtFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityConfig {

    @Autowired
    private JwtFilter jwtFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
            .csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(auth -> auth

                // 🔓 PUBLIC
                .requestMatchers("/auth/**").permitAll()
                .requestMatchers(
                        "/swagger-ui/**",
                        "/swagger-ui.html",
                        "/v3/api-docs/**"
                ).permitAll()

                // 🌍 PUBLIC READ (Rooms, Hotels)
                .requestMatchers(HttpMethod.GET, "/rooms/**").permitAll()
                .requestMatchers(HttpMethod.GET, "/hotels/**").permitAll()

                // 🌍 PUBLIC READ (Reviews)
                .requestMatchers(HttpMethod.GET, "/reviews/**").permitAll()

                // 👤 USER + ADMIN (write reviews)
                .requestMatchers(HttpMethod.POST, "/reviews/**").hasAnyRole("USER", "ADMIN")
                .requestMatchers(HttpMethod.PUT, "/reviews/**").hasAnyRole("USER", "ADMIN")
                .requestMatchers(HttpMethod.DELETE, "/reviews/**").hasAnyRole("USER", "ADMIN")

                // 👤 USER ONLY
                .requestMatchers("/api/reservations/**").hasAnyRole("USER","ADMIN")
                .requestMatchers("/api/payments/**").hasAnyRole("USER","ADMIN")

                // 👑 ADMIN ONLY (write rooms)
                .requestMatchers(HttpMethod.POST, "/rooms/**").hasRole("ADMIN")
                .requestMatchers(HttpMethod.PUT, "/rooms/**").hasRole("ADMIN")
                .requestMatchers(HttpMethod.DELETE, "/rooms/**").hasRole("ADMIN")

                // 👑 ADMIN ONLY (amenities)
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