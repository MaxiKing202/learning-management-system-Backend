package org.learning_management_system.showcase.security;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.jspecify.annotations.Nullable;
import org.learning_management_system.showcase.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class UserInfoDetails implements UserDetails {

    private String userName;
    private String password;
    private String matrikelNumber;
    private List<GrantedAuthority> authorities;

    public UserInfoDetails(User user) {
        this.userName = user.getEmail(); // Use email as username
        this.password = user.getPassword();
        this.matrikelNumber = user.getMatrikelNumber();
        if(user.getRole() != null) {
            this.authorities = List.of(new SimpleGrantedAuthority(user.getRole()));
        } else {
            this.authorities = List.of();
        }
        this.authorities = List.of(user.getRole().split(","))
                .stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public @Nullable String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return userName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}