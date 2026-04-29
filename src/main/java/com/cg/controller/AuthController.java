package com.cg.controller;

import com.cg.dto.*;
import com.cg.entity.User;
import com.cg.security.JwtUtil;
import com.cg.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/register")
    public String register(@RequestBody User user) {
        return authService.register(user);
    }

    @PostMapping("/login")
    public AuthResponse login(@RequestBody AuthRequest request) {
        User user = authService.login(request);
        String token = jwtUtil.generateToken(user.getUsername());
        return new AuthResponse(token);
    }
}