package org.learning_management_system.showcase.repository;

import org.learning_management_system.showcase.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);

    Optional<User> findByEmail(String email);

    default Optional<User> findByUsernameOrEmail(String usernameOrEmail) {
        return findByUsername(usernameOrEmail).or(() -> findByEmail(usernameOrEmail));
    }
}
