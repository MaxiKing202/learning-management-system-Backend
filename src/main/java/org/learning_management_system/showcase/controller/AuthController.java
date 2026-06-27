package org.learning_management_system.showcase.controller;

import dto.AuthRequestDTO;
import org.learning_management_system.showcase.service.JwtService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/auth")
@Tag(name = "Authentication", description = "Endpoints for user authentication and token management")
public class AuthController {

    private final AuthenticationManager authenticationManager;

    private final JwtService jwtService;


    public AuthController(AuthenticationManager authenticationManager, JwtService jwtService) {
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }

    @PostMapping("/login")
    @Operation(summary="User authentication and token generation", description="Authenticate a user with the provided credentials and return a JWT token if authentication is successful.<br>ATTENTION: Even though the Schema for a Login Request shows 'Username' as a field, the user's EMAIL ADDRESS should be given as value here as this is internally used for finding users and logging them in.")
    public ResponseEntity<String> login(@RequestBody AuthRequestDTO authRequest) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword())
        );
        return ResponseEntity.ok(jwtService.generateToken(authRequest.getUsername()));
    }

    @GetMapping("/user/profile")
    @Operation(summary = "Get current user profile", description = "Returns the username and roles of the authenticated user. Use this to verify your JWT token works.")
    public ResponseEntity<String> getUserProfile(Authentication authentication) {
        return ResponseEntity.ok("User: " + authentication.getName() + ", Roles: " + authentication.getAuthorities());
    }

}
