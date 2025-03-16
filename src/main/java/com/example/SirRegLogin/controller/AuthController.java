package com.example.SirRegLogin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import com.example.SirRegLogin.model.User;
import com.example.SirRegLogin.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Authentication Controller
 * Handles all authentication-related requests including login and registration.
 */
@Controller
public class AuthController {
    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);

    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public String showLoginPage() {
        return "login";
    }

    @GetMapping("/register")
    public String showRegisterPage(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    /**
     * Processes user registration
     * Handles the registration form submission, validates user input,
     * and creates a new user account if validation passes.
     */
    @PostMapping("/register")
    public String registerUser(@Valid @ModelAttribute("user") User user,
                               BindingResult result,
                               Model model) {
        logger.info("Received registration request for user: {}", user.getUsername());

        if (result.hasErrors()) {
            logger.error("Validation errors: {}", result.getAllErrors());
            return "register";
        }

        try {
            User registeredUser = userService.registerUser(user);
            logger.info("User registered successfully: {}", registeredUser.getUsername());
            return "redirect:/login?success=Registration successful! Please login.";
        } catch (RuntimeException e) {
            logger.error("Registration failed: {}", e.getMessage());
            model.addAttribute("error", e.getMessage());
            return "register";
        }
    }
}
