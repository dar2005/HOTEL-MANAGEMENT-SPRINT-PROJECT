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

    // REGISTER
    @PostMapping("/register")
    public String register(@RequestBody User user) {
        return authService.register(user);
    }

    // LOGIN (FIXED)
    @PostMapping("/login")
    public AuthResponse login(@RequestBody AuthRequest request) {

        // ✅ Validate user (your existing logic)
        User user = authService.login(request);

        // ✅ Load full UserDetails (IMPORTANT)
        UserDetails userDetails =
                userDetailsService.loadUserByUsername(user.getUsername());

        // ✅ Generate token with roles
        String token = jwtUtil.generateToken(userDetails);

        return new AuthResponse(token);
    }
}