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

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest credentials) {
        Optional<User> user = authService.authenticate(credentials.getUsername(), credentials.getPassword());
        if (user.isPresent()) {
            return ResponseEntity.ok("Token generated!");
        } else {
            System.out.println("name " + credentials.getUsername() + " " + credentials.getPassword());
            return ResponseEntity.status(401).body("Invalid credentials");
        }
    }


}
