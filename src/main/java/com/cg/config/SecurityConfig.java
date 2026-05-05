package com.cg.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.cg.security.JwtFilter;

import jakarta.servlet.http.HttpServletResponse;

@Configuration
public class SecurityConfig {

    @Autowired
    private JwtFilter jwtFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
            .cors(cors -> cors.configure(http))
            .csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(auth -> auth

                .requestMatchers(
                        "/swagger-ui/**",
                        "/swagger-ui.html",
                        "/v3/api-docs/**"
                ).permitAll()

                .requestMatchers("/api/auth/**").permitAll()
                .requestMatchers("/auth/**").permitAll()
                .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()

                .requestMatchers(HttpMethod.GET, "/rooms/**").permitAll()
                .requestMatchers(HttpMethod.GET, "/hotels/**").permitAll()
                .requestMatchers(HttpMethod.GET, "/reviews/**").permitAll()
                .requestMatchers(HttpMethod.GET, "/roomtypes/**").permitAll()

                .requestMatchers(HttpMethod.POST, "/reviews/**").hasAnyRole("USER", "ADMIN")
                .requestMatchers(HttpMethod.PUT, "/reviews/**").hasAnyRole("USER", "ADMIN")
                .requestMatchers(HttpMethod.DELETE, "/reviews/**").hasAnyRole("USER", "ADMIN")

                .requestMatchers("/api/reservations/**").authenticated()
                .requestMatchers("/api/payments/**").authenticated()

                .requestMatchers(HttpMethod.POST, "/rooms/**").hasRole("ADMIN")
                .requestMatchers(HttpMethod.PUT, "/rooms/**").hasRole("ADMIN")
                .requestMatchers(HttpMethod.DELETE, "/rooms/**").hasRole("ADMIN")

                .requestMatchers(HttpMethod.POST, "/roomtypes/**").hasRole("ADMIN")
                .requestMatchers(HttpMethod.PUT, "/roomtypes/**").hasRole("ADMIN")
                .requestMatchers(HttpMethod.DELETE, "/roomtypes/**").hasRole("ADMIN")

                .requestMatchers(HttpMethod.POST, "/hotels/**").hasRole("ADMIN")
                .requestMatchers(HttpMethod.PUT, "/hotels/**").hasRole("ADMIN")

                .requestMatchers("/api/amenities/**").hasRole("ADMIN")
                .anyRequest().authenticated()
            )
            .exceptionHandling(ex -> ex.authenticationEntryPoint(
                    (request, response, authException) ->
                            response.sendError(HttpServletResponse.SC_UNAUTHORIZED)
            ))
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
