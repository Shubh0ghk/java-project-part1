package com.example.SirRegLogin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.example.SirRegLogin.model.User;
import com.example.SirRegLogin.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * User Service
 * Handles business logic for user-related operations.
 */
@Service
public class UserService {
    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User registerUser(User user) {
        logger.info("Attempting to register user: {}", user.getUsername());

        if (userRepository.existsByEmail(user.getEmail())) {
            logger.error("Registration failed: Email {} already exists", user.getEmail());
            throw new RuntimeException("Email already registered");
        }

        if (userRepository.existsByUsername(user.getUsername())) {
            logger.error("Registration failed: Username {} already exists", user.getUsername());
            throw new RuntimeException("Username already taken");
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        User savedUser = userRepository.save(user);
        logger.info("User registered successfully: {}", savedUser.getUsername());
        return savedUser;
    }
}
