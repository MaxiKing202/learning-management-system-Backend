package org.learning_management_system.showcase.service;

import org.jspecify.annotations.NullMarked;
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

    public String addUser(User user){
        user.setPassword(encoder.encode(user.getPassword()));
        userRepository.save(user);
        return "User added successfully";
    }

    public String resetPassword(User user){
        User notUpdatedUser = userRepository.findByEmail(user.getEmail())
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + user.getEmail()));
        String oldPassword = notUpdatedUser.getPassword();
        String newPassword = encoder.encode(user.getPassword());
        if (oldPassword.equals(newPassword)) {
            return "New password cannot be the same as the old password.";
        }
        notUpdatedUser.setPassword(newPassword);
        userRepository.save(notUpdatedUser);
        return "Password changed successfully";
    }
}
