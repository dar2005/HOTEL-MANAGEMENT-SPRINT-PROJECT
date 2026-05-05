package com.cg.controller;

import com.cg.dto.*;
import com.cg.entity.User;
import com.cg.security.JwtUtil;
import com.cg.service.AuthService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserDetailsService userDetailsService;

    @PostMapping("/register")
    public String register(@RequestBody User user) {
        return authService.register(user);
    }

    @PostMapping("/login")
    public AuthResponse login(@RequestBody AuthRequest request) {

        User user = authService.login(request);

        UserDetails userDetails =
                userDetailsService.loadUserByUsername(user.getUsername());

        String token = jwtUtil.generateToken(userDetails);

        String role = cleanRoleForFrontend(user.getRole());

        return new AuthResponse(token, user.getUsername(), user.getEmail(), role);
    }

    private String cleanRoleForFrontend(String role) {
        if (role == null || role.trim().isEmpty()) {
            return "USER";
        }

        role = role.trim().toUpperCase();

        if (role.startsWith("ROLE_")) {
            role = role.substring(5);
        }

        return role;
    }
}
