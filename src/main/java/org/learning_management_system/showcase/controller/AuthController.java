package org.learning_management_system.showcase.controller;

import dto.LoginRequest;
import org.learning_management_system.showcase.model.User;
import org.learning_management_system.showcase.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/auth")
@Tag(name = "Authentication", description = "Endpoints for user authentication and token management")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    @Operation(summary = "User login", description = "Authenticate a user with username and password and return an authentication token (placeholder)")
    public ResponseEntity<String> login(@RequestBody LoginRequest credentials) {
        Optional<User> user = authService.authenticate(credentials.getUsername(), credentials.getPassword());
        if (user.isPresent()) {
            return ResponseEntity.ok("Token generated!");
        } else {
            return ResponseEntity.status(401).body("Invalid credentials");
        }
    }


}
