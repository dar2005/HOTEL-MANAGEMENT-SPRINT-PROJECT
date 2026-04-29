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
<<<<<<< HEAD
<<<<<<< Updated upstream
=======
=======
>>>>>>> fd381f44ae36d55c9970e6746dfe08896801ac3c
                .requestMatchers(
                        "/swagger-ui/**",
                        "/swagger-ui.html",
                        "/v3/api-docs",
                        "/v3/api-docs/**"
                ).permitAll()
                .requestMatchers(
                        "/rooms",
                        "/rooms/available",
                        "/rooms/type/**",
                        "/hotels/**"
                ).permitAll()

                // USER + ADMIN
                .requestMatchers("/reviews/**")
                .hasAnyRole("USER", "ADMIN")

                // USER only
                .requestMatchers(
                        "/api/reservations/**",
                        "/api/payments/**"
<<<<<<< HEAD
                ).hasAnyRole("USER","ADMIN")
=======
                ).hasRole("USER")
>>>>>>> fd381f44ae36d55c9970e6746dfe08896801ac3c

                // ADMIN only
                .requestMatchers(
                        "/api/amenities/**",
                        "/rooms/**"
                ).hasRole("ADMIN")

<<<<<<< HEAD
>>>>>>> Stashed changes
=======
>>>>>>> fd381f44ae36d55c9970e6746dfe08896801ac3c
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
