package org.learning_management_system.showcase.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "users")
@Setter
@Getter
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    private String email;

    private Integer matrikelNumber;

    private String role;

    // store BCrypt-hashed password
    private String password;

    public User(String username, String email, Integer matrikelNumber, String role) {
        this.matrikelNumber = matrikelNumber;
        this.username = username;
        this.email = email;
        this.role = role;
    }

}
