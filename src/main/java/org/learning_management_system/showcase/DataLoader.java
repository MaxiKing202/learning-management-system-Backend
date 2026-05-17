package org.learning_management_system.showcase;

import org.learning_management_system.showcase.model.User;
import org.learning_management_system.showcase.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class DataLoader {

    @Bean
    @Profile("h2")
    public CommandLineRunner loadTestData(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        return args -> {
            if (userRepository.count() == 0) {
                User u1 = new User("user", "user@example.com", 123, "ROLE_USER");
                u1.setPassword(passwordEncoder.encode("password"));
                userRepository.save(u1);

                User u2 = new User("alice", "alice@example.com", 1234, "ROLE_USER");
                u2.setPassword(passwordEncoder.encode("password"));
                userRepository.save(u2);

                User u3 = new User("bob", "bob@example.com", 12345, "ROLE_USER");
                u3.setPassword(passwordEncoder.encode("password"));
                userRepository.save(u3);
            }
        };
    }
}
