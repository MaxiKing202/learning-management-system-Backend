package org.learning_management_system.showcase.service;

import org.learning_management_system.showcase.model.User;
import org.learning_management_system.showcase.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Optional<User> authenticate(String usernameOrEmail, String rawPassword) {
        return userRepository.findByUsernameOrEmail(usernameOrEmail)
                .filter(user -> user.getPassword() != null && passwordEncoder.matches(rawPassword, user.getPassword()));
    }
}

