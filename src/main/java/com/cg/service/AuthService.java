package com.cg.service;

import com.cg.dto.AuthRequest;
import com.cg.entity.User;
import com.cg.exception.*;
import com.cg.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    @Autowired
    private UserRepository repo;

    @Autowired
    private PasswordEncoder encoder;

    public String register(User user) {

        if (repo.existsByUsername(user.getUsername()))
            throw new ConflictException("Username exists");

        if (repo.existsByEmail(user.getEmail()))
            throw new ConflictException("Email exists");

        user.setPassword(encoder.encode(user.getPassword()));
        user.setRole("ADMIN");

        repo.save(user);
        return "Registered";
    }

    public User login(AuthRequest req) {

        User user = repo.findByUsername(req.getUsername())
                .orElseThrow(() -> new UnauthorizedException("Invalid username"));

        if (!encoder.matches(req.getPassword(), user.getPassword()))
            throw new UnauthorizedException("Invalid password");

        return user;
    }
}