package org.learning_management_system.showcase.service;

import org.jspecify.annotations.NullMarked;
import org.learning_management_system.showcase.error_handling.BusinessRuleException;
import org.learning_management_system.showcase.error_handling.ResourceNotFoundException;
import org.learning_management_system.showcase.model.User;
import org.learning_management_system.showcase.repository.UserRepository;
import org.learning_management_system.showcase.security.UserInfoDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    private final PasswordEncoder encoder;

    @Autowired
    public UserService(UserRepository repository, PasswordEncoder encoder) {
        this.userRepository = repository;
        this.encoder = encoder;
    }

    @Override
    @NullMarked
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + username));
        return new UserInfoDetails(user);
    }

    public User addUser(User user){
        user.setPassword(encoder.encode(user.getPassword()));
        if (user.getRole() == null || user.getRole().isBlank()) {
            user.setRole("ROLE_USER");
        }
        return userRepository.save(user);
    }

    public User resetPassword(User user){
        User existingUser = userRepository.findByEmail(user.getEmail())
                .orElseThrow(() -> new ResourceNotFoundException("User not found with email: " + user.getEmail()));
        if (encoder.matches(user.getPassword(), existingUser.getPassword())) {
            throw new BusinessRuleException("New password cannot be the same as the old password.");
        }
        existingUser.setPassword(encoder.encode(user.getPassword()));
        return userRepository.save(existingUser);
    }
}
