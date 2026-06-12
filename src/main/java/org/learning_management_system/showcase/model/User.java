package org.learning_management_system.showcase.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.UniqueElements;

@Entity
@Table(name = "users")
@Setter
@Getter
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @UniqueElements
    private String username;

    @UniqueElements
    private String email;

    @Pattern(regexp = "\\d{6}")
    @Column(length = 6)
    private String matrikelNumber;

    private String role;

    // store BCrypt-hashed password
    private String password;

    public User(String username, String email, String matrikelNumber, String role) {
        this.matrikelNumber = matrikelNumber;
        this.username = username;
        this.email = email;
        this.role = role;
    }

}
